package pdf.pdftool.fragment.texttopdf;

import android.app.Activity;

import pdf.pdftool.interfaces.Enhancer;
import pdf.pdftool.model.OptionsTextToPDF;

/**
 * The {@link Enhancers} represent a list of enhancers for the Text-to-PDF feature.
 */
public enum Enhancers {
    FONT_COLOR {
        @Override
        Enhancer getEnhancer(Activity activity, TextToPdfContract.View view,
                             OptionsTextToPDF.Builder builder) {
            return new EnhancerFontColor(activity, builder);
        }
    },
    FONT_FAMILY {
        @Override
        Enhancer getEnhancer(Activity activity, TextToPdfContract.View view,
                             OptionsTextToPDF.Builder builder) {
            return new EnhancerFontFamily(activity, view, builder);
        }
    },
    FONT_SIZE {
        @Override
        Enhancer getEnhancer(Activity activity, TextToPdfContract.View view,
                             OptionsTextToPDF.Builder builder) {
            return new EnhancerFontSize(activity, view, builder);
        }
    },
    PAGE_COLOR {
        @Override
        Enhancer getEnhancer(Activity activity, TextToPdfContract.View view,
                             OptionsTextToPDF.Builder builder) {
            return new EnhancerPageColor(activity, builder);
        }
    },
    PAGE_SIZE {
        @Override
        Enhancer getEnhancer(Activity activity, TextToPdfContract.View view,
                             OptionsTextToPDF.Builder builder) {
            return new EnhancerPageSize(activity);
        }
    },
    PASSWORD {
        @Override
        Enhancer getEnhancer(Activity activity, TextToPdfContract.View view,
                             OptionsTextToPDF.Builder builder) {
            return new EnhancerPassword(activity, view, builder);
        }
    };

    /**
     * @param activity The {@link Activity} context.
     * @param view The {@link TextToPdfContract.View} that needs the enhancement.
     * @param builder The builder for {@link OptionsTextToPDF}.
     * @return An instance of the {@link Enhancer}.
     */
    abstract Enhancer getEnhancer(Activity activity, TextToPdfContract.View view,
                                  OptionsTextToPDF.Builder builder);
}
