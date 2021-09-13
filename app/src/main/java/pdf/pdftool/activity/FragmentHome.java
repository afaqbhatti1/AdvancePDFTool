package pdf.pdftool.activity;

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
import pdf.pdftool.fragment.FragmentExceltoPdf;
import pdf.pdftool.fragment.FragmentImageToPdf;
import pdf.pdftool.fragment.FragmentQrBarcodeScan;
import pdf.pdftool.fragment.texttopdf.FragmentTextToPdf;
import pdf.pdftool.model.PageHomeItem;
import pdf.pdftool.util.AdRunner;
import pdf.pdftool.util.CommonCodeTestingUtils;
import pdf.pdftool.util.RecentUtil;

public class FragmentHome extends Fragment  implements  View.OnClickListener{

    private Activity mActivity;
    @BindView(R.id.images_to_pdf)
    OurCardView imagesToPdf;
    @BindView(R.id.qr_barcode_to_pdf)
    OurCardView qrbarcodeToPdf;
    @BindView(R.id.text_to_pdf)
    OurCardView textToPdf;
    @BindView(R.id.excel_to_pdf)
    OurCardView excelToPdf;

    private Map<Integer, PageHomeItem> mFragmentPositionMap;


    public FragmentHome() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootview);
        mFragmentPositionMap = CommonCodeTestingUtils.getInstance().fillNavigationItemsMap(true);


        imagesToPdf.setOnClickListener(this);
        qrbarcodeToPdf.setOnClickListener(this);
        textToPdf.setOnClickListener(this);
        excelToPdf.setOnClickListener(this);


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
            case R.id.images_to_pdf:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentImageToPdf();
                break;
            case R.id.qr_barcode_to_pdf:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentQrBarcodeScan();
                break;
            case R.id.text_to_pdf:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentTextToPdf();
                break;
            case R.id.excel_to_pdf:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentExceltoPdf();
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
