package pdf.pdftool.util;

import android.content.Context;

import java.util.ArrayList;

import pdf.pdftool.R;
import pdf.pdftool.model.OptionsEntityEnhancement;

public class MergePdfEnhancementOptionsUtils {
    private static class SingletonHolder {
        static final MergePdfEnhancementOptionsUtils INSTANCE = new MergePdfEnhancementOptionsUtils();
    }

    public static MergePdfEnhancementOptionsUtils getInstance() {
        return MergePdfEnhancementOptionsUtils.SingletonHolder.INSTANCE;
    }

    public ArrayList<OptionsEntityEnhancement> getEnhancementOptions(Context context) {
        ArrayList<OptionsEntityEnhancement> options = new ArrayList<>();

        options.add(new OptionsEntityEnhancement(
                context, R.drawable.ic_add_password, R.string.set_password));
        return options;
    }
}