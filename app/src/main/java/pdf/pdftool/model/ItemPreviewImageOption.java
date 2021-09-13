package pdf.pdftool.model;

public class ItemPreviewImageOption {
    private int mOptionImageId;
    private String mOptionName;

    public ItemPreviewImageOption(int mOptionImageId, String mOptionName) {
        this.mOptionImageId = mOptionImageId;
        this.mOptionName = mOptionName;
    }

    public int getOptionImageId() {
        return mOptionImageId;
    }

    public void setOptionImageId(int mOptionImageId) {
        this.mOptionImageId = mOptionImageId;
    }

    public String getOptionName() {
        return mOptionName;
    }

    public void setOptionName(String mOptionName) {
        this.mOptionName = mOptionName;
    }
}
