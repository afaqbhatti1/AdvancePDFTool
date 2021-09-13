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

public class ModifyexistingPDFsFragment_ViewBinding implements Unbinder {
  private ModifyexistingPDFsFragment target;

  @UiThread
  public ModifyexistingPDFsFragment_ViewBinding(ModifyexistingPDFsFragment target, View source) {
    this.target = target;

    target.splitPdf = Utils.findRequiredViewAsType(source, R.id.split_pdf, "field 'splitPdf'", OurCardView.class);
    target.mergePdf = Utils.findRequiredViewAsType(source, R.id.merge_pdf, "field 'mergePdf'", OurCardView.class);
    target.compressPdf = Utils.findRequiredViewAsType(source, R.id.compress_pdf, "field 'compressPdf'", OurCardView.class);
    target.removeDuplicatePages = Utils.findRequiredViewAsType(source, R.id.remove_duplicates_pages_pdf, "field 'removeDuplicatePages'", OurCardView.class);
    target.invertPdf = Utils.findRequiredViewAsType(source, R.id.invert_pdf, "field 'invertPdf'", OurCardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ModifyexistingPDFsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.splitPdf = null;
    target.mergePdf = null;
    target.compressPdf = null;
    target.removeDuplicatePages = null;
    target.invertPdf = null;
  }
}
