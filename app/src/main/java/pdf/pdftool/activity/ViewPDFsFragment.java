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
import pdf.pdftool.fragment.FragmentHistory;
import pdf.pdftool.fragment.FragmentViewFiles;
import pdf.pdftool.model.PageHomeItem;
import pdf.pdftool.util.AdRunner;
import pdf.pdftool.util.CommonCodeTestingUtils;
import pdf.pdftool.util.RecentUtil;


public class ViewPDFsFragment extends Fragment implements View.OnClickListener{

    private Activity mActivity;
    @BindView(R.id.view_files)
    OurCardView viewFiles;
    @BindView(R.id.view_history)
    OurCardView viewHistory;

    private Map<Integer, PageHomeItem> mFragmentPositionMap;


    public ViewPDFsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_view_p_d_fs, container, false);
        ButterKnife.bind(this, rootview);
        mFragmentPositionMap = CommonCodeTestingUtils.getInstance().fillNavigationItemsMap(true);

        viewFiles.setOnClickListener(this);
        viewHistory.setOnClickListener(this);

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
            case R.id.view_files:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentViewFiles();
                break;
            case R.id.view_history:
                AdRunner.getAds().showFBFirst(mActivity);
                fragment = new FragmentHistory();
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