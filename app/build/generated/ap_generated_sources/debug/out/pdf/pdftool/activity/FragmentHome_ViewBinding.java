// Generated code from Butter Knife. Do not modify!
package pdf.pdftool.activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import pdf.pdftool.R;
import pdf.pdftool.customviews.OurCardView;

public class FragmentHome_ViewBinding implements Unbinder {
  private FragmentHome target;

  @UiThread
  public FragmentHome_ViewBinding(FragmentHome target, View source) {
    this.target = target;

    target.imagesToPdf = Utils.findRequiredViewAsType(source, R.id.images_to_pdf, "field 'imagesToPdf'", OurCardView.class);
    target.qrbarcodeToPdf = Utils.findRequiredViewAsType(source, R.id.qr_barcode_to_pdf, "field 'qrbarcodeToPdf'", OurCardView.class);
    target.textToPdf = Utils.findRequiredViewAsType(source, R.id.text_to_pdf, "field 'textToPdf'", OurCardView.class);
    target.excelToPdf = Utils.findRequiredViewAsType(source, R.id.excel_to_pdf, "field 'excelToPdf'", OurCardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FragmentHome target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imagesToPdf = null;
    target.qrbarcodeToPdf = null;
    target.textToPdf = null;
    target.excelToPdf = null;
  }
}
