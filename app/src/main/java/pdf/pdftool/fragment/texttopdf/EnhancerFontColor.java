package pdf.pdftool.fragment.texttopdf;

import android.app.Activity;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.danielnilsson9.colorpickerview.view.ColorPickerView;

import pdf.pdftool.R;
import pdf.pdftool.interfaces.Enhancer;
import pdf.pdftool.model.OptionsEntityEnhancement;
import pdf.pdftool.model.OptionsTextToPDF;
import pdf.pdftool.preferences.PreferencesTextToPdf;
import pdf.pdftool.util.ColorUtilsTool;
import pdf.pdftool.util.StringUtils;

/**
 * An {@link Enhancer} that lets you select font colors.
 */
public class EnhancerFontColor implements Enhancer {

    private final Activity mActivity;
    private final OptionsEntityEnhancement mEnhancementOptionsEntity;
    private final PreferencesTextToPdf mPreferences;
    private final OptionsTextToPDF.Builder mBuilder;

    EnhancerFontColor(@NonNull final Activity activity,
                      @NonNull final OptionsTextToPDF.Builder builder) {
        mActivity = activity;
        mPreferences = new PreferencesTextToPdf(activity);
        mBuilder = builder;
        mEnhancementOptionsEntity =  new OptionsEntityEnhancement(
                mActivity, R.drawable.ic_font_color, R.string.font_color);
    }

    @Override
    public void enhance() {
        MaterialDialog materialDialog = new MaterialDialog.Builder(mActivity)
                .title(R.string.font_color)
                .customView(R.layout.dialog_color_options, true)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive((dialog, which) -> {
                    View view = dialog.getCustomView();
                    ColorPickerView colorPickerView = view.findViewById(R.id.color_picker);
                    CheckBox defaultCheckbox = view.findViewById(R.id.set_default);
                    final int fontcolor = colorPickerView.getColor();
                    final int pageColor = mPreferences.getPageColor();
                    if (ColorUtilsTool.getInstance().colorSimilarCheck(fontcolor, pageColor)) {
                        StringUtils.getInstance().showSnackbar(mActivity, R.string.snackbar_color_too_close);
                    }
                    if (defaultCheckbox.isChecked()) {
                        mPreferences.setFontColor(fontcolor);
                    }
                    mBuilder.setFontColor(fontcolor);
                })
                .build();
        ColorPickerView colorPickerView = materialDialog.getCustomView().findViewById(R.id.color_picker);
        colorPickerView.setColor(mBuilder.getFontColor());
        materialDialog.show();
    }

    @Override
    public OptionsEntityEnhancement getEnhancementOptionsEntity() {
        return mEnhancementOptionsEntity;
    }
}
