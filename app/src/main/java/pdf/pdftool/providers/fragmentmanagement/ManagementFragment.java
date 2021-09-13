package pdf.pdftool.providers.fragmentmanagement;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.widget.Toast;

import java.util.Objects;

import pdf.pdftool.R;
import pdf.pdftool.activity.ActivityWelcome;
import pdf.pdftool.activity.FragmentHome;
import pdf.pdftool.fragment.FragmentAboutUs;
import pdf.pdftool.fragment.FragmentAddImages;
import pdf.pdftool.fragment.FragmentAddText;
import pdf.pdftool.fragment.FragmentDuplicatePagesRemove;
import pdf.pdftool.fragment.FragmentExceltoPdf;
import pdf.pdftool.fragment.FragmentExtractText;
import pdf.pdftool.fragment.FragmentFAQ;
import pdf.pdftool.fragment.FragmentFavourites;
import pdf.pdftool.fragment.FragmentFilesSplit;
import pdf.pdftool.fragment.FragmentHistory;
import pdf.pdftool.fragment.FragmentImageToPdf;
import pdf.pdftool.fragment.FragmentInvertPdf;
import pdf.pdftool.fragment.FragmentMergeFiles;
import pdf.pdftool.fragment.FragmentPdfToImage;
import pdf.pdftool.fragment.FragmentQrBarcodeScan;
import pdf.pdftool.fragment.FragmentRemovePages;
import pdf.pdftool.fragment.FragmentSettings;
import pdf.pdftool.fragment.FragmentViewFiles;
import pdf.pdftool.fragment.FragmentZipToPdf;
import pdf.pdftool.fragment.texttopdf.FragmentTextToPdf;
import pdf.pdftool.util.FeedbackUtils;
import pdf.pdftool.util.FragmentUtils;

import static pdf.pdftool.util.Constants.ACTION_MERGE_PDF;
import static pdf.pdftool.util.Constants.ACTION_SELECT_IMAGES;
import static pdf.pdftool.util.Constants.ACTION_TEXT_TO_PDF;
import static pdf.pdftool.util.Constants.ACTION_VIEW_FILES;
import static pdf.pdftool.util.Constants.ADD_IMAGES;
import static pdf.pdftool.util.Constants.ADD_PWD;
import static pdf.pdftool.util.Constants.ADD_WATERMARK;
import static pdf.pdftool.util.Constants.BUNDLE_DATA;
import static pdf.pdftool.util.Constants.COMPRESS_PDF;
import static pdf.pdftool.util.Constants.EXTRACT_IMAGES;
import static pdf.pdftool.util.Constants.OPEN_SELECT_IMAGES;
import static pdf.pdftool.util.Constants.PDF_TO_IMAGES;
import static pdf.pdftool.util.Constants.REMOVE_PAGES;
import static pdf.pdftool.util.Constants.REMOVE_PWd;
import static pdf.pdftool.util.Constants.REORDER_PAGES;
import static pdf.pdftool.util.Constants.ROTATE_PAGES;
import static pdf.pdftool.util.Constants.SHOW_WELCOME_ACT;

/**
 * This is a fragment service that manages the fragments
 * mainly for the MainActivity.
 */
public class ManagementFragment implements IFragmentManagement {
    private final FragmentActivity mContext;
    private final NavigationView mNavigationView;
    private boolean mDoubleBackToExitPressedOnce = false;
    private final FeedbackUtils mFeedbackUtils;
    private final FragmentUtils mFragmentUtils;

    public ManagementFragment(FragmentActivity context, NavigationView navigationView) {
        mContext = context;
        mNavigationView = navigationView;
        mFeedbackUtils = new FeedbackUtils(mContext);
        mFragmentUtils = new FragmentUtils(mContext);
    }

    public void favouritesFragmentOption() {
        Fragment currFragment = mContext.getSupportFragmentManager().findFragmentById(R.id.content);

        Fragment fragment = new FragmentFavourites();
        FragmentManager fragmentManager = mContext.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .replace(R.id.content, fragment);
        if (!(currFragment instanceof FragmentHome)) {
            transaction.addToBackStack(mFragmentUtils.getFragmentName(currFragment));
        }
        transaction.commit();
    }

    public Fragment checkForAppShortcutClicked() {
        Fragment fragment = new FragmentHome();
        if (mContext.getIntent().getAction() != null) {
            switch (Objects.requireNonNull(mContext.getIntent().getAction())) {
                case ACTION_SELECT_IMAGES:
                    fragment = new FragmentImageToPdf();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(OPEN_SELECT_IMAGES, true);
                    fragment.setArguments(bundle);
                    break;
                case ACTION_VIEW_FILES:
                    fragment = new FragmentViewFiles();
                    setNavigationViewSelection(R.id.nav_gallery);
                    break;
                case ACTION_TEXT_TO_PDF:
                    fragment = new FragmentTextToPdf();
                    setNavigationViewSelection(R.id.nav_text_to_pdf);
                    break;
                case ACTION_MERGE_PDF:
                    fragment = new FragmentMergeFiles();
                    setNavigationViewSelection(R.id.nav_merge);
                    break;
                default:
                    fragment = new FragmentHome(); // Set default fragment
                    break;
            }
        }
        if (areImagesReceived())
            fragment = new FragmentImageToPdf();

        mContext.getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();

        return fragment;
    }

    public boolean handleBackPressed() {
        Fragment currentFragment = mContext.getSupportFragmentManager()
                .findFragmentById(R.id.content);
        if (currentFragment instanceof FragmentHome) {
            return checkDoubleBackPress();
        } else {
            if (mFragmentUtils.handleFragmentBottomSheetBehavior(currentFragment))
                return false;
        }
        handleBackStackEntry();
        return false;
    }

    public boolean handleNavigationItemSelected(int itemId) {
        Fragment fragment = null;
        FragmentManager fragmentManager = mContext.getSupportFragmentManager();
        Bundle bundle = new Bundle();

        switch (itemId) {
            case R.id.nav_home:
                fragment = new FragmentHome();
                break;
            case R.id.nav_camera:
                fragment = new FragmentImageToPdf();
                break;
            case R.id.nav_qrcode:
                fragment = new FragmentQrBarcodeScan();
                break;
            case R.id.nav_gallery:
                fragment = new FragmentViewFiles();
                break;
            case R.id.nav_merge:
                fragment = new FragmentMergeFiles();
                break;
            case R.id.nav_split:
                fragment = new FragmentFilesSplit();
                break;
            case R.id.nav_text_to_pdf:
                fragment = new FragmentTextToPdf();
                break;
            case R.id.nav_history:
                fragment = new FragmentHistory();
                break;
            case R.id.nav_add_text:
                fragment = new FragmentAddText();
                break;
            case R.id.nav_add_password:
                fragment = new FragmentRemovePages();
                bundle.putString(BUNDLE_DATA, ADD_PWD);
                fragment.setArguments(bundle);
                break;
            case R.id.nav_remove_password:
                fragment = new FragmentRemovePages();
                bundle.putString(BUNDLE_DATA, REMOVE_PWd);
                fragment.setArguments(bundle);
                break;
            case R.id.nav_share:
                mFeedbackUtils.shareApplication();
                break;
            case R.id.nav_about:
                fragment = new FragmentAboutUs();
                break;
            case R.id.nav_settings:
                fragment = new FragmentSettings();
                break;
            case R.id.nav_extract_images:
                fragment = new FragmentPdfToImage();
                bundle.putString(BUNDLE_DATA, EXTRACT_IMAGES);
                fragment.setArguments(bundle);
                break;
            case R.id.nav_pdf_to_images:
                fragment = new FragmentPdfToImage();
                bundle.putString(BUNDLE_DATA, PDF_TO_IMAGES);
                fragment.setArguments(bundle);
                break;
            case R.id.nav_excel_to_pdf:
                fragment = new FragmentExceltoPdf();
                break;
            case R.id.nav_remove_pages:
                fragment = new FragmentRemovePages();
                bundle.putString(BUNDLE_DATA, REMOVE_PAGES);
                fragment.setArguments(bundle);
                break;
            case R.id.nav_rearrange_pages:
                fragment = new FragmentRemovePages();
                bundle.putString(BUNDLE_DATA, REORDER_PAGES);
                fragment.setArguments(bundle);
                break;
            case R.id.nav_compress_pdf:
                fragment = new FragmentRemovePages();
                bundle.putString(BUNDLE_DATA, COMPRESS_PDF);
                fragment.setArguments(bundle);
                break;
            case R.id.nav_add_images:
                fragment = new FragmentAddImages();
                bundle.putString(BUNDLE_DATA, ADD_IMAGES);
                fragment.setArguments(bundle);
                break;
            case R.id.nav_help:
                Intent intent = new Intent(mContext, ActivityWelcome.class);
                intent.putExtra(SHOW_WELCOME_ACT, true);
                mContext.startActivity(intent);
                break;
            case R.id.nav_remove_duplicate_pages:
                fragment = new FragmentDuplicatePagesRemove();
                break;
            case R.id.nav_invert_pdf:
                fragment = new FragmentInvertPdf();
                break;
            case R.id.nav_add_watermark:
                fragment = new FragmentViewFiles();
                bundle.putInt(BUNDLE_DATA, ADD_WATERMARK);
                fragment.setArguments(bundle);
                break;
            case R.id.nav_zip_to_pdf:
                fragment = new FragmentZipToPdf();
                break;
            case R.id.nav_rateus:
                mFeedbackUtils.openWebPage("https://play.google.com/store/apps/details?id=com.advanced.pdfconverter");
                break;
            case R.id.nav_rotate_pages:
                fragment = new FragmentViewFiles();
                bundle.putInt(BUNDLE_DATA, ROTATE_PAGES);
                fragment.setArguments(bundle);
                break;
            case R.id.nav_text_extract:
                fragment = new FragmentExtractText();
                break;
            case R.id.nav_faq:
                fragment = new FragmentFAQ();
                break;
        }

        try {
            if (fragment != null)
                fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // if help or share or what's new is clicked then return false, as we don't want
        // them to be selected
        return itemId != R.id.nav_share && itemId != R.id.nav_help;
                /*&& itemId != R.id.nav_whatsNew*/
    }

    /**
     * Closes the app only when double clicked
     */
    private boolean checkDoubleBackPress() {
        if (mDoubleBackToExitPressedOnce) {
            return true;
        }
        mDoubleBackToExitPressedOnce = true;
        Toast.makeText(mContext, R.string.confirm_exit_message, Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     *  Back stack count will be 1 when we open a item from favourite menu
     *  on clicking back, return back to fav menu and change title
     */
    private void handleBackStackEntry() {
        int count = mContext.getSupportFragmentManager().getBackStackEntryCount();
        if (count > 0) {
            String s = mContext.getSupportFragmentManager().getBackStackEntryAt(count - 1).getName();
            mContext.setTitle(s);
            mContext.getSupportFragmentManager().popBackStack();
        } else {
            Fragment fragment = new FragmentHome();
            mContext.getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
            mContext.setTitle(R.string.app_name);
            setNavigationViewSelection(R.id.nav_home);
        }
    }

    private boolean areImagesReceived() {
        Intent intent = mContext.getIntent();
        String type = intent.getType();
        return type != null && type.startsWith("image/");
    }

    private void setNavigationViewSelection(int id) {
        mNavigationView.setCheckedItem(id);
    }
}
