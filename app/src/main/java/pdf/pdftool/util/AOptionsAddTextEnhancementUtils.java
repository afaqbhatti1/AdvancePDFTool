package pdf.pdftool.util;

import android.content.Context;

import com.itextpdf.text.Font;

import java.util.ArrayList;

import pdf.pdftool.R;
import pdf.pdftool.model.OptionsEntityEnhancement;

public class AOptionsAddTextEnhancementUtils {

    private AOptionsAddTextEnhancementUtils() {

    }

    public ArrayList<OptionsEntityEnhancement> getEnhancementOptions(Context context,
                                                                     String fontTitle,
                                                                     Font.FontFamily fontFamily) {
        ArrayList<OptionsEntityEnhancement> options = new ArrayList<>();

        options.add(new OptionsEntityEnhancement(
                context.getResources().getDrawable(R.drawable.ic_text_size),
                fontTitle));
        options.add(new OptionsEntityEnhancement(
                context, R.drawable.ic_extract_text,
                String.format(context.getString(R.string.default_font_family_text), fontFamily.name())));
        return options;
    }

    private static class SingletonHolder {
        static final AOptionsAddTextEnhancementUtils INSTANCE = new AOptionsAddTextEnhancementUtils();
    }

    public static AOptionsAddTextEnhancementUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
