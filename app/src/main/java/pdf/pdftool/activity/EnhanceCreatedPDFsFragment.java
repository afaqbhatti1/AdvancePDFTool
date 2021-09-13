package pdf.pdftool.activity;

import static pdf.pdftool.util.Constants.ADD_IMAGES;
import static pdf.pdftool.util.Constants.ADD_PWD;
import static pdf.pdftool.util.Constants.ADD_WATERMARK;
import static pdf.pdftool.util.Constants.BUNDLE_DATA;
import static pdf.pdftool.util.Constants.REMOVE_PWd;
import static pdf.pdftool.util.Constants.ROTATE_PAGES;

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
import pdf.pdftool.fragment.FragmentAddImages;
import pdf.pdftool.fragment.FragmentAddText;
import pdf.pdftool.fragment.FragmentRemovePages;
import pdf.pdftool.fragment.FragmentViewFiles;
import pdf.pdftool.model.PageHomeItem;
import pdf.pdftool.util.AdRunner;
import pdf.pdftool.util.CommonCodeTestingUtils;
import pdf.pdftool.util.RecentUtil;


public class EnhanceCreatedPDFsFragment extends Fragment implements View.OnClickListener {

    private Activity mActivity;
    @BindView(R.id.add_password)
    OurCardView addPassword;
    @BindView(R.id.remove_password)
    OurCardView removePassword;
    @BindView(R.id.add_text)
    OurCardView addText;
    @BindView(R.id.rotate_pages)
    OurCardView rotatePdf;
    @BindView(R.id.add_watermark)
    OurCardView addWatermark;
    @BindView(R.id.add_images)
    OurCardView addImages;

    private Map<Integer, PageHomeItem> mFragmentPositionMap;


    public EnhanceCreatedPDFsFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_enhance_created_p_d_fs, container, false);
        ButterKnife.bind(this, rootview);
        mFragmentPositionMap = CommonCodeTestingUtils.getInstance().fillNavigationItemsMap(true);

        addPassword.setOnClickListener(this);
        removePassword.setOnClickListener(this);
        rotatePdf.setOnClickListener(this);
        addWatermark.setOnClickListener(this);
        addImages.setOnClickListener(this);
        addText.setOnClickListener(this);

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
            case R.id.add_password:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentRemovePages();
                bundle.putString(BUNDLE_DATA, ADD_PWD);
                fragment.setArguments(bundle);
                break;
            case R.id.remove_password:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentRemovePages();
                bundle.putString(BUNDLE_DATA, REMOVE_PWd);
                fragment.setArguments(bundle);
                break;
            case R.id.rotate_pages:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentViewFiles();
                bundle.putInt(BUNDLE_DATA, ROTATE_PAGES);
                fragment.setArguments(bundle);
                break;
            case R.id.add_watermark:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentViewFiles();
                bundle.putInt(BUNDLE_DATA, ADD_WATERMARK);
                fragment.setArguments(bundle);
                break;
            case R.id.add_images:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentAddImages();
                bundle.putString(BUNDLE_DATA, ADD_IMAGES);
                fragment.setArguments(bundle);
                break;
            case R.id.add_text:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentAddText();
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