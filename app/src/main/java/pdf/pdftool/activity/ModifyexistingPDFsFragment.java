package pdf.pdftool.activity;

import static pdf.pdftool.util.Constants.BUNDLE_DATA;
import static pdf.pdftool.util.Constants.COMPRESS_PDF;

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
import pdf.pdftool.fragment.FragmentDuplicatePagesRemove;
import pdf.pdftool.fragment.FragmentFilesSplit;
import pdf.pdftool.fragment.FragmentInvertPdf;
import pdf.pdftool.fragment.FragmentMergeFiles;
import pdf.pdftool.fragment.FragmentRemovePages;
import pdf.pdftool.model.PageHomeItem;
import pdf.pdftool.util.AdRunner;
import pdf.pdftool.util.CommonCodeTestingUtils;
import pdf.pdftool.util.RecentUtil;


public class ModifyexistingPDFsFragment extends Fragment implements View.OnClickListener {

    private Activity mActivity;

    @BindView(R.id.split_pdf)
    OurCardView splitPdf;
    @BindView(R.id.merge_pdf)
    OurCardView mergePdf;
    @BindView(R.id.compress_pdf)
    OurCardView compressPdf;
    @BindView(R.id.remove_duplicates_pages_pdf)
    OurCardView removeDuplicatePages;
    @BindView(R.id.invert_pdf)
    OurCardView invertPdf;

    private Map<Integer, PageHomeItem> mFragmentPositionMap;


    public ModifyexistingPDFsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_modifyexisting_p_d_fs, container, false);
        ButterKnife.bind(this, rootview);
        mFragmentPositionMap = CommonCodeTestingUtils.getInstance().fillNavigationItemsMap(true);

        splitPdf.setOnClickListener(this);
        mergePdf.setOnClickListener(this);
        compressPdf.setOnClickListener(this);
        removeDuplicatePages.setOnClickListener(this);
        invertPdf.setOnClickListener(this);

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
            case R.id.merge_pdf:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentMergeFiles();
                break;
            case R.id.split_pdf:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentFilesSplit();
                break;
            case R.id.compress_pdf:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentRemovePages();
                bundle.putString(BUNDLE_DATA, COMPRESS_PDF);
                fragment.setArguments(bundle);
                break;
            case R.id.remove_duplicates_pages_pdf:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentDuplicatePagesRemove();
                break;
            case R.id.invert_pdf:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentInvertPdf();
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