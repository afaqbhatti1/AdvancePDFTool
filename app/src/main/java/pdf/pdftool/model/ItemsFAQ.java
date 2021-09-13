package pdf.pdftool.model;

public class ItemsFAQ {

    private String mQuestion;
    private String mAnswer;
    private boolean mIsExpanded;

    /**
     * FAQ Item constructor
     * @param question - question text
     * @param answer - answer text
     */
    public ItemsFAQ(String question, String answer) {
        this.mQuestion = question;
        this.mAnswer = answer;
        mIsExpanded = false;
    }

    public boolean isExpanded() {
        return mIsExpanded;
    }

    public void setExpanded(boolean expanded) {
        mIsExpanded = expanded;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getAnswer() {
        return mAnswer;
    }


}
