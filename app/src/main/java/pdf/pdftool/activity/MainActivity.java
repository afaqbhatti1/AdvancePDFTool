package pdf.pdftool.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import pdf.pdftool.R;
import pdf.pdftool.fragment.FragmentImageToPdf;
import pdf.pdftool.providers.fragmentmanagement.ManagementFragment;
import pdf.pdftool.util.AdRunner;
import pdf.pdftool.util.FeedbackUtils;
import pdf.pdftool.util.FileUtils;
import pdf.pdftool.util.PermissionsUtils;
import pdf.pdftool.util.ThemeUtils;

import static pdf.pdftool.util.Constants.IS_WELCOME_ACTIVITY_SHOWN;
import static pdf.pdftool.util.Constants.LAUNCH_COUNT;
import static pdf.pdftool.util.Constants.READ_WRITE_CAMERA_PERMISSIONS;
import static pdf.pdftool.util.Constants.READ_WRITE_PERMISSIONS;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , BottomNavigationView.OnItemSelectedListener {

    private FeedbackUtils mFeedbackUtils;
    private NavigationView mNavigationView;
    private SharedPreferences mSharedPreferences;
    private SparseIntArray mFragmentSelectedMap;
    private ManagementFragment mFragmentManagement;

    private static final float END_SCALE = 0.7f;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TextView labelView;
    private View contentView;

    private static final int PERMISSION_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.getInstance().setThemeApp(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loadFragment(new FragmentHome());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnItemSelectedListener(this);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        labelView = (TextView) findViewById(R.id.label);
        contentView = findViewById(R.id.holder);

        toolbar.setNavigationIcon(new DrawerArrowDrawable(this));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (drawerLayout.isDrawerOpen(mNavigationView)) {
            drawerLayout.closeDrawer(mNavigationView);
            }
            else {
            drawerLayout.openDrawer(mNavigationView);
            }
            }
            }
        );
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
               @Override
               public void onDrawerSlide(View drawerView, float slideOffset) {
                   labelView.setVisibility(slideOffset > 0 ? View.VISIBLE : View.GONE);

                   final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                   final float offsetScale = 1 - diffScaledOffset;
                   contentView.setScaleX(offsetScale);
                   contentView.setScaleY(offsetScale);

                   final float xOffset = drawerView.getWidth() * slideOffset;
                   final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                   final float xTranslation = xOffset - xOffsetDiff;
                   contentView.setTranslationX(xTranslation);
               }

               @Override
               public void onDrawerClosed(View drawerView) {
                   labelView.setVisibility(View.GONE);
               }
           }
        );

        AdRunner.getAds().loadFullAdmob(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_hammenu);
        Bitmap newIcon = resizeBitmapImageFn(icon, 60);
        Drawable d = new BitmapDrawable(getResources(), newIcon);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(d);

        initializeValues();

        setXMLParsers();
        Fragment fragment = mFragmentManagement.checkForAppShortcutClicked();

        // Check if  images are received
        handleReceivedImagesIntent(fragment);

        displayFeedBackAndWhatsNew();
        getRuntimePermissions();

        //check for welcome activity
        openWelcomeActivity();
    }
    private Bitmap resizeBitmapImageFn(Bitmap bmpSource, int maxResolution) {
        int iWidth = bmpSource.getWidth();
        int iHeight = bmpSource.getHeight();
        int newWidth = iWidth;
        int newHeight = iHeight;
        float rate;

        if (iWidth > iHeight ) {
            if (maxResolution < iWidth ) {
                rate = maxResolution / (float) iWidth;
                newHeight = (int) (iHeight * rate);
                newWidth = maxResolution;
            }
        } else {
            if (maxResolution < iHeight ) {
                rate = maxResolution / (float) iHeight;
                newWidth = (int) (iWidth * rate);
                newHeight = maxResolution;
            }
        }
        return Bitmap.createScaledBitmap(
                bmpSource, newWidth, newHeight, true);
    }

    private  void setXMLParsers() {
        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory",
                "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory",
                "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory",
                "com.fasterxml.aalto.stax.EventFactoryImpl");
    }


    private void displayFeedBackAndWhatsNew() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int count = mSharedPreferences.getInt(LAUNCH_COUNT, 0);
        if (count > 0 && count % 15 == 0) {
            mFeedbackUtils.rateUs();
        }
        if (count != -1) {
            mSharedPreferences.edit().putInt(LAUNCH_COUNT, count + 1).apply();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new FragmentHome();
                break;

            case R.id.navigation_dashboard:
                fragment = new ViewPDFsFragment();
                break;

            case R.id.navigation_notifications:
                fragment = new EnhanceCreatedPDFsFragment();
                break;

            case R.id.navigation_profile:
                fragment = new ModifyexistingPDFsFragment();
                break;

            case R.id.navigation_moreoptions:
                fragment = new MoreOptionsFragment();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isStoragePermissionGranted()) {
            FileUtils.makeAndClearTemp();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favourites, menu);


        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_favorite); //Converting drawable into bitmap
        Bitmap newIcon = resizeBitmapImageFn(icon, 58); //resizing the bitmap
        Drawable d = new BitmapDrawable(getResources(), newIcon); //Converting bitmap into drawable
        menu.getItem(0).setIcon(d); //choose the item number you want to set
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_favourites_item) {
            setTitle(R.string.favourites);
            mFragmentManagement.favouritesFragmentOption();
        } else if (item.getItemId() == R.id.menu_home_item) {
            Fragment fragment;
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragment = new FragmentHome();
            setTitle(R.string.app_name);
            try {
                if (fragment != null)
                    fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private void openWelcomeActivity() {
        if (!mSharedPreferences.getBoolean(IS_WELCOME_ACTIVITY_SHOWN, false)) {
            Intent intent = new Intent(MainActivity.this, ActivityWelcome.class);
            mSharedPreferences.edit().putBoolean(IS_WELCOME_ACTIVITY_SHOWN, true).apply();
            startActivity(intent);
        }
    }

    private void initializeValues() {
        mFeedbackUtils = new FeedbackUtils(this);
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.nav_home);

        mFragmentManagement = new ManagementFragment(this, mNavigationView);
        setTitleMap();
    }

    private void handleReceivedImagesIntent(Fragment fragment) {
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (type == null || !type.startsWith("image/"))
            return;

        if (Intent.ACTION_SEND_MULTIPLE.equals(action)) {
            handleSendMultipleImages(intent, fragment); // Handle multiple images
        } else if (Intent.ACTION_SEND.equals(action)) {
            handleSendImage(intent, fragment); // Handle single image
        }
    }


    private void handleSendImage(Intent intent, Fragment fragment) {
        Uri uri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        ArrayList<Uri> imageUris = new ArrayList<>();
        imageUris.add(uri);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(getString(R.string.bundleKey), imageUris);
        fragment.setArguments(bundle);
    }


    private void handleSendMultipleImages(Intent intent, Fragment fragment) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(getString(R.string.bundleKey), imageUris);
            fragment.setArguments(bundle);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            boolean shouldExit = mFragmentManagement.handleBackPressed();
            if (shouldExit)
                super.onBackPressed();
        }
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//
//        setTitleFragment(mFragmentSelectedMap.get(item.getItemId()));
//
//        return mFragmentManagement.handleNavigationItemSelected(item.getItemId());
//    }

    public void setNavigationViewSelection(int id) {
        mNavigationView.setCheckedItem(id);
    }

    private void getRuntimePermissions() {
        PermissionsUtils.getInstance().requestRuntimePermissions(this,
                READ_WRITE_CAMERA_PERMISSIONS,
                PERMISSION_REQUEST_CODE);
    }

    private boolean isStoragePermissionGranted() {
        return PermissionsUtils.getInstance().checkRuntimePermissions(this, READ_WRITE_PERMISSIONS);
    }

    public void convertImagesToPdf(ArrayList<Uri> imageUris) {
        Fragment fragment = new FragmentImageToPdf();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(getString(R.string.bundleKey), imageUris);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    }

    private void setTitleMap() {
        mFragmentSelectedMap = new SparseIntArray();

        mFragmentSelectedMap.append(R.id.nav_home, R.string.app_name);
        mFragmentSelectedMap.append(R.id.nav_camera, R.string.images_to_pdf);
        mFragmentSelectedMap.append(R.id.nav_qrcode, R.string.qr_barcode_pdf);
        mFragmentSelectedMap.append(R.id.nav_add_text, R.string.add_text);
        mFragmentSelectedMap.append(R.id.nav_gallery, R.string.viewFiles);
        mFragmentSelectedMap.append(R.id.nav_merge, R.string.merge_pdf);
        mFragmentSelectedMap.append(R.id.nav_split, R.string.split_pdf);
        mFragmentSelectedMap.append(R.id.nav_text_to_pdf, R.string.text_to_pdf);
        mFragmentSelectedMap.append(R.id.nav_history, R.string.history);
        mFragmentSelectedMap.append(R.id.nav_add_password, R.string.add_password);
        mFragmentSelectedMap.append(R.id.nav_remove_password, R.string.remove_password);
        mFragmentSelectedMap.append(R.id.nav_about, R.string.about_us);
        mFragmentSelectedMap.append(R.id.nav_settings, R.string.settings);
        mFragmentSelectedMap.append(R.id.nav_extract_images, R.string.extract_images);
        mFragmentSelectedMap.append(R.id.nav_pdf_to_images, R.string.pdf_to_images);
        mFragmentSelectedMap.append(R.id.nav_remove_pages, R.string.remove_pages);
        mFragmentSelectedMap.append(R.id.nav_rearrange_pages, R.string.reorder_pages);
        mFragmentSelectedMap.append(R.id.nav_compress_pdf, R.string.compress_pdf);
        mFragmentSelectedMap.append(R.id.nav_add_images, R.string.add_images);
        mFragmentSelectedMap.append(R.id.nav_remove_duplicate_pages, R.string.remove_duplicate_pages);
        mFragmentSelectedMap.append(R.id.nav_invert_pdf, R.string.invert_pdf);
        mFragmentSelectedMap.append(R.id.nav_add_watermark, R.string.add_watermark);
        mFragmentSelectedMap.append(R.id.nav_zip_to_pdf, R.string.zip_to_pdf);
        mFragmentSelectedMap.append(R.id.nav_rotate_pages, R.string.rotate_pages);
        mFragmentSelectedMap.append(R.id.nav_excel_to_pdf, R.string.excel_to_pdf);
        mFragmentSelectedMap.append(R.id.nav_faq, R.string.faqs);
    }

    private void setTitleFragment(int title) {
        if (title != 0)
            setTitle(title);
    }
}
