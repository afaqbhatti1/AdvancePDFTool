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

public class EnhanceCreatedPDFsFragment_ViewBinding implements Unbinder {
  private EnhanceCreatedPDFsFragment target;

  @UiThread
  public EnhanceCreatedPDFsFragment_ViewBinding(EnhanceCreatedPDFsFragment target, View source) {
    this.target = target;

    target.addPassword = Utils.findRequiredViewAsType(source, R.id.add_password, "field 'addPassword'", OurCardView.class);
    target.removePassword = Utils.findRequiredViewAsType(source, R.id.remove_password, "field 'removePassword'", OurCardView.class);
    target.addText = Utils.findRequiredViewAsType(source, R.id.add_text, "field 'addText'", OurCardView.class);
    target.rotatePdf = Utils.findRequiredViewAsType(source, R.id.rotate_pages, "field 'rotatePdf'", OurCardView.class);
    target.addWatermark = Utils.findRequiredViewAsType(source, R.id.add_watermark, "field 'addWatermark'", OurCardView.class);
    target.addImages = Utils.findRequiredViewAsType(source, R.id.add_images, "field 'addImages'", OurCardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EnhanceCreatedPDFsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.addPassword = null;
    target.removePassword = null;
    target.addText = null;
    target.rotatePdf = null;
    target.addWatermark = null;
    target.addImages = null;
  }
}
