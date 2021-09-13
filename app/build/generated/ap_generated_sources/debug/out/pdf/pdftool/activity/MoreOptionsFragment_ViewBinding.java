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

public class MoreOptionsFragment_ViewBinding implements Unbinder {
  private MoreOptionsFragment target;

  @UiThread
  public MoreOptionsFragment_ViewBinding(MoreOptionsFragment target, View source) {
    this.target = target;

    target.removePages = Utils.findRequiredViewAsType(source, R.id.remove_pages, "field 'removePages'", OurCardView.class);
    target.rearrangePages = Utils.findRequiredViewAsType(source, R.id.rearrange_pages, "field 'rearrangePages'", OurCardView.class);
    target.extractImages = Utils.findRequiredViewAsType(source, R.id.extract_images, "field 'extractImages'", OurCardView.class);
    target.mPdfToImages = Utils.findRequiredViewAsType(source, R.id.pdf_to_images, "field 'mPdfToImages'", OurCardView.class);
    target.extractText = Utils.findRequiredViewAsType(source, R.id.extract_text, "field 'extractText'", OurCardView.class);
    target.zipToPdf = Utils.findRequiredViewAsType(source, R.id.zip_to_pdf, "field 'zipToPdf'", OurCardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MoreOptionsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.removePages = null;
    target.rearrangePages = null;
    target.extractImages = null;
    target.mPdfToImages = null;
    target.extractText = null;
    target.zipToPdf = null;
  }
}
