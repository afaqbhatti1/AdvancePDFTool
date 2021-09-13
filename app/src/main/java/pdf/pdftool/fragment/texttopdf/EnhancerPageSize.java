package pdf.pdftool.fragment.texttopdf;

import android.content.Context;
import androidx.annotation.NonNull;

import pdf.pdftool.R;
import pdf.pdftool.interfaces.Enhancer;
import pdf.pdftool.model.OptionsEntityEnhancement;
import pdf.pdftool.preferences.PreferencesTextToPdf;
import pdf.pdftool.util.PageSizeUtils;

/**
 * An {@link Enhancer} that lets you select page size.
 */
public class EnhancerPageSize implements Enhancer {

    private final PageSizeUtils mPageSizeUtils;
    private final OptionsEntityEnhancement mEnhancementOptionsEntity;

    EnhancerPageSize(@NonNull final Context context) {
        mPageSizeUtils = new PageSizeUtils(context);
        mEnhancementOptionsEntity = new OptionsEntityEnhancement(
                context, R.drawable.ic_image_size, R.string.set_page_size_text);

        PageSizeUtils.mPageSize = new PreferencesTextToPdf(context).getPageSize();
    }

    @Override
    public void enhance() {
        mPageSizeUtils.showPageSizeDialog(false);
    }

    @Override
    public OptionsEntityEnhancement getEnhancementOptionsEntity() {
        return mEnhancementOptionsEntity;
    }

}
