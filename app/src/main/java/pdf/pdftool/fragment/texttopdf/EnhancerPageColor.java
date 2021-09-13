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
 * An {@link Enhancer} that lets you select the page color.
 */
public class EnhancerPageColor implements Enhancer {

    private final Activity mActivity;
    private final OptionsEntityEnhancement mEnhancementOptionsEntity;
    private final PreferencesTextToPdf mPreferences;
    private final OptionsTextToPDF.Builder mBuilder;

    EnhancerPageColor(@NonNull final Activity activity,
                      @NonNull final OptionsTextToPDF.Builder builder) {
        mActivity = activity;
        mPreferences = new PreferencesTextToPdf(activity);
        mBuilder = builder;
        mEnhancementOptionsEntity = new OptionsEntityEnhancement(
                mActivity, R.drawable.ic_color_fill, R.string.page_color);
    }

    @Override
    public void enhance() {
        MaterialDialog materialDialog = new MaterialDialog.Builder(mActivity)
                .title(R.string.page_color)
                .customView(R.layout.dialog_color_options, true)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive((dialog, which) -> {
                    View view = dialog.getCustomView();
                    ColorPickerView colorPickerView = view.findViewById(R.id.color_picker);
                    CheckBox defaultCheckbox = view.findViewById(R.id.set_default);
                    final int pageColor = colorPickerView.getColor();
                    final int fontColor = mPreferences.getFontColor();
                    if (ColorUtilsTool.getInstance().colorSimilarCheck(fontColor, pageColor)) {
                        StringUtils.getInstance().showSnackbar(mActivity, R.string.snackbar_color_too_close);
                    }
                    if (defaultCheckbox.isChecked()) {
                        mPreferences.setPageColor(pageColor);
                    }
                    mBuilder.setPageColor(pageColor);
                })
                .build();
        ColorPickerView colorPickerView = materialDialog.getCustomView().findViewById(R.id.color_picker);
        colorPickerView.setColor(mBuilder.getPageColor());
        materialDialog.show();
    }

    @Override
    public OptionsEntityEnhancement getEnhancementOptionsEntity() {
        return mEnhancementOptionsEntity;
    }
}
