package pdf.pdftool.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.eftimoff.viewpagertransformers.DepthPageTransformer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pdf.pdftool.R;
import pdf.pdftool.adapter.AdapterPreview;
import pdf.pdftool.adapter.AdapterPreviewImageOptions;
import pdf.pdftool.model.ItemPreviewImageOption;
import pdf.pdftool.util.Constants;
import pdf.pdftool.util.ImageSortUtils;
import pdf.pdftool.util.ThemeUtils;

import static pdf.pdftool.util.Constants.PREVIEW_IMAGES;

public class ActivityPreview extends AppCompatActivity implements AdapterPreviewImageOptions.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private ArrayList<String> mImagesArrayList;
    private static final int INTENT_REQUEST_REARRANGE_IMAGE = 1;
    private AdapterPreview mPreviewAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ThemeUtils.getInstance().setThemeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_folder);

        ButterKnife.bind(this);
        // Extract mImagesArrayList uri array from the intent
        Intent intent = getIntent();
        mImagesArrayList = intent.getStringArrayListExtra(PREVIEW_IMAGES);

        mViewPager = findViewById(R.id.viewpager);
        mPreviewAdapter = new AdapterPreview(this, mImagesArrayList);
        mViewPager.setAdapter(mPreviewAdapter);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        showOptions();
    }

    private ArrayList<ItemPreviewImageOption> getOptions() {
        ArrayList<ItemPreviewImageOption> mOptions = new ArrayList<>();
        mOptions.add(new ItemPreviewImageOption(R.drawable.ic_rearrange, getString(R.string.rearrange_text)));
        mOptions.add(new ItemPreviewImageOption(R.drawable.ic_sort, getString(R.string.sort)));
        return mOptions;
    }

    private void showOptions() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        AdapterPreviewImageOptions adapter = new AdapterPreviewImageOptions(this, getOptions(),
                getApplicationContext());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK)
            return;

        if (requestCode == INTENT_REQUEST_REARRANGE_IMAGE) {
            try {
                mImagesArrayList = data.getStringArrayListExtra(Constants.RESULT);
                mPreviewAdapter.setData(mImagesArrayList);
                mViewPager.setAdapter(mPreviewAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Intent getStartIntent(Context context, ArrayList<String> uris) {
        Intent intent = new Intent(context, ActivityPreview.class);
        intent.putExtra(PREVIEW_IMAGES, uris);
        return intent;
    }

    private void sortImages() {
        new MaterialDialog.Builder(this)
                .title(R.string.sort_by_title)
                .items(R.array.sort_options_images)
                .itemsCallback((dialog, itemView, position, text) -> {
                    ImageSortUtils.getInstance().performSortOperation(position, mImagesArrayList);
                    mPreviewAdapter.setData(new ArrayList<>(mImagesArrayList));
                    mViewPager.setAdapter(mPreviewAdapter);
                })
                .negativeText(R.string.cancel)
                .show();
    }

    private void passUris() {
        Intent returnIntent = new Intent();
        returnIntent.putStringArrayListExtra(Constants.RESULT, mImagesArrayList);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }



    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                startActivityForResult(RearrangePictures.getStartIntent(this, mImagesArrayList),
                        INTENT_REQUEST_REARRANGE_IMAGE);
                break;
            case 1:
                sortImages();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        passUris();
    }


}