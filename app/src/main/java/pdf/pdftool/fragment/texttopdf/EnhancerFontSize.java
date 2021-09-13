package pdf.pdftool.fragment.texttopdf;

import android.app.Activity;
import androidx.annotation.NonNull;
import android.widget.CheckBox;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import pdf.pdftool.R;
import pdf.pdftool.interfaces.Enhancer;
import pdf.pdftool.model.OptionsEntityEnhancement;
import pdf.pdftool.model.OptionsTextToPDF;
import pdf.pdftool.preferences.PreferencesTextToPdf;
import pdf.pdftool.util.StringUtils;

/**
 * An {@link Enhancer} that lets you select font size.
 */
public class EnhancerFontSize implements Enhancer {

    private final Activity mActivity;
    private OptionsEntityEnhancement mEnhancementOptionsEntity;
    private final TextToPdfContract.View mView;
    private final PreferencesTextToPdf mPreferences;
    private final OptionsTextToPDF.Builder mBuilder;

    EnhancerFontSize(@NonNull final Activity activity,
                     @NonNull final TextToPdfContract.View view,
                     @NonNull final OptionsTextToPDF.Builder builder) {
        mActivity = activity;
        mPreferences = new PreferencesTextToPdf(activity);
        mBuilder = builder;
        mBuilder.setFontSizeTitle(
                String.format(mActivity.getString(R.string.edit_font_size),
                        mPreferences.getFontSize()));
        mEnhancementOptionsEntity = new OptionsEntityEnhancement(
                mActivity.getResources().getDrawable(R.drawable.ic_text_size),
                mBuilder.getFontSizeTitle());
        mView = view;
    }

    /**
     * Function to take the font size of pdf as user input
     */
    @Override
    public void enhance() {
        new MaterialDialog.Builder(mActivity)
                .title(mBuilder.getFontSizeTitle())
                .customView(R.layout.font_size_dialog_file, true)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive((dialog, which) -> {
                    final EditText fontInput = dialog.getCustomView().findViewById(R.id.fontInput);
                    final CheckBox cbSetDefault = dialog.getCustomView().findViewById(R.id.cbSetFontDefault);
                    try {
                        int check = Integer.parseInt(String.valueOf(fontInput.getText()));
                        if (check > 1000 || check < 0) {
                            StringUtils.getInstance().showSnackbar(mActivity, R.string.invalid_entry);
                        } else {
                            mBuilder.setFontSize(check);
                            showFontSize();
                            StringUtils.getInstance().showSnackbar(mActivity, R.string.font_size_changed);
                            if (cbSetDefault.isChecked()) {
                                mPreferences.setFontSize(mBuilder.getFontSize());
                                mBuilder.setFontSizeTitle(String.format(mActivity.getString(R.string.edit_font_size),
                                        mPreferences.getFontSize()));
                            }
                        }
                    } catch (NumberFormatException e) {
                        StringUtils.getInstance().showSnackbar(mActivity, R.string.invalid_entry);
                    }
                })
                .show();
    }

    @Override
    public OptionsEntityEnhancement getEnhancementOptionsEntity() {
        return mEnhancementOptionsEntity;
    }

    /**
     * Displays font size in UI
     */
    private void showFontSize() {
        mEnhancementOptionsEntity
                .setName(String.format(mActivity.getString(R.string.font_size),
                        mBuilder.getFontSize()));
        mView.updateView();
    }
}
