package pdf.pdftool.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class OptionsEntityEnhancement {
    private Drawable mImage;
    private String mName;

    public OptionsEntityEnhancement(Drawable image, String name) {
        this.mImage = image;
        this.mName = name;
    }

    public OptionsEntityEnhancement(Context context, int imageId, String name) {
        this.mImage = context.getResources().getDrawable(imageId);
        this.mName = name;
    }

    public OptionsEntityEnhancement(Context context, int resourceId, int stringId) {
        this.mImage = context.getResources().getDrawable(resourceId);
        this.mName = context.getString(stringId);
    }

    public Drawable getImage() {
        return mImage;
    }

    public void setImage(Drawable image) {
        this.mImage = image;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
