package pdf.pdftool.activity;

import static pdf.pdftool.util.Constants.BUNDLE_DATA;
import static pdf.pdftool.util.Constants.EXTRACT_IMAGES;
import static pdf.pdftool.util.Constants.PDF_TO_IMAGES;
import static pdf.pdftool.util.Constants.REMOVE_PAGES;
import static pdf.pdftool.util.Constants.REORDER_PAGES;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import pdf.pdftool.R;
import pdf.pdftool.customviews.OurCardView;
import pdf.pdftool.fragment.FragmentExtractText;
import pdf.pdftool.fragment.FragmentPdfToImage;
import pdf.pdftool.fragment.FragmentRemovePages;
import pdf.pdftool.fragment.FragmentZipToPdf;
import pdf.pdftool.model.PageHomeItem;
import pdf.pdftool.util.AdRunner;
import pdf.pdftool.util.CommonCodeTestingUtils;
import pdf.pdftool.util.RecentUtil;


public class MoreOptionsFragment extends Fragment implements View.OnClickListener {
    private Activity mActivity;
    @BindView(R.id.remove_pages)
    OurCardView removePages;
    @BindView(R.id.rearrange_pages)
    OurCardView rearrangePages;
    @BindView(R.id.extract_images)
    OurCardView extractImages;
    @BindView(R.id.pdf_to_images)
    OurCardView mPdfToImages;
    @BindView(R.id.extract_text)
    OurCardView extractText;
    @BindView(R.id.zip_to_pdf)
    OurCardView zipToPdf;

    private Map<Integer, PageHomeItem> mFragmentPositionMap;

    public MoreOptionsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_more_options, container, false);
        ButterKnife.bind(this, rootview);
        mFragmentPositionMap = CommonCodeTestingUtils.getInstance().fillNavigationItemsMap(true);

        removePages.setOnClickListener(this);
        rearrangePages.setOnClickListener(this);
        extractImages.setOnClickListener(this);
        mPdfToImages.setOnClickListener(this);
        zipToPdf.setOnClickListener(this);
        extractText.setOnClickListener(this);

        return rootview;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onClick(View v) {

        Fragment fragment = null;
        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle = new Bundle();

        highlightNavigationDrawerItem(mFragmentPositionMap.get(v.getId()).getNavigationItemId());
        setTitleFragment(mFragmentPositionMap.get(v.getId()).getTitleString());

        Map<String, String> feature = new HashMap<>();
        feature.put(
                String.valueOf(mFragmentPositionMap.get(v.getId()).getTitleString()),
                String.valueOf(mFragmentPositionMap.get(v.getId()).getmDrawableId()));

        try {
            RecentUtil.getInstance().addFeatureInRecentList(PreferenceManager
                    .getDefaultSharedPreferences(mActivity), v.getId(), feature);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        switch (v.getId()) {
            case R.id.extract_images:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentPdfToImage();
                bundle.putString(BUNDLE_DATA, EXTRACT_IMAGES);
                fragment.setArguments(bundle);
                break;
            case R.id.pdf_to_images:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentPdfToImage();
                bundle.putString(BUNDLE_DATA, PDF_TO_IMAGES);
                fragment.setArguments(bundle);
                break;
            case R.id.remove_pages:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentRemovePages();
                bundle.putString(BUNDLE_DATA, REMOVE_PAGES);
                fragment.setArguments(bundle);
                break;
            case R.id.rearrange_pages:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentRemovePages();
                bundle.putString(BUNDLE_DATA, REORDER_PAGES);
                fragment.setArguments(bundle);
                break;
            case R.id.zip_to_pdf:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentZipToPdf();
                break;
            case R.id.extract_text:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentExtractText();
                break;
        }

        try {
            if (fragment != null && fragmentManager != null)
                fragmentManager.beginTransaction().replace(R.id.content, fragment).addToBackStack(null).commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void highlightNavigationDrawerItem(int id) {
        if (mActivity instanceof MainActivity)
            ((MainActivity) mActivity).setNavigationViewSelection(id);
    }


    private void setTitleFragment(int title) {
        if (title != 0)
            mActivity.setTitle(title);
    }


}
