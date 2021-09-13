package pdf.pdftool.fragment;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pdf.pdftool.R;
import pdf.pdftool.adapter.AdapterFAQ;
import pdf.pdftool.interfaces.OnItemClickListener;
import pdf.pdftool.model.ItemsFAQ;


public class FragmentFAQ extends Fragment implements OnItemClickListener {

    private AdapterFAQ mFaqAdapter;
    private List<ItemsFAQ> mFaqs;
    private Context mContext;

    @BindView(R.id.recycler_view_faq)
    RecyclerView mFAQRecyclerView;

    public FragmentFAQ() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.faq_fragment, container, false);

        ButterKnife.bind(this, view);
        mContext = view.getContext();

        initFAQs();

        initFAQRecyclerView();

        return view;
    }

    /**
     * Initializes the FAQs questions and answers by populating data from the
     * strings.xml file and adding it to the custom FAQItem Model.
     */
    private void initFAQs() {
        mFaqs = new ArrayList<>();
        String[] questionAnswers = mContext.getResources().getStringArray(R.array.faq_question_answers);
        ItemsFAQ faqItem;
        for (String questionAnswer : questionAnswers) {
            String[] questionAnswerSplit = questionAnswer.split("#####");
            faqItem = new ItemsFAQ(questionAnswerSplit[0], questionAnswerSplit[1]);
            mFaqs.add(faqItem);
        }
    }


    /**
     * Initializes the RecyclerView using the FAQAdapter to populate the views
     * for FAQs
     */
    private void initFAQRecyclerView() {
        mFaqAdapter = new AdapterFAQ(mFaqs, this);
        mFAQRecyclerView.setAdapter(mFaqAdapter);
    }

    /**
     * This method defines the behavior of the FAQItem when clicked.
     * It makes the FAQItem view expanded or contracted.
     * @param position - view position
     */
    @Override
    public void onItemClick(int position) {
        ItemsFAQ faqItem = mFaqs.get(position);
        faqItem.setExpanded(!faqItem.isExpanded());
        mFaqAdapter.notifyItemChanged(position);
    }
}
