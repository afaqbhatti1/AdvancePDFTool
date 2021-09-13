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

public class ViewPDFsFragment_ViewBinding implements Unbinder {
  private ViewPDFsFragment target;

  @UiThread
  public ViewPDFsFragment_ViewBinding(ViewPDFsFragment target, View source) {
    this.target = target;

    target.viewFiles = Utils.findRequiredViewAsType(source, R.id.view_files, "field 'viewFiles'", OurCardView.class);
    target.viewHistory = Utils.findRequiredViewAsType(source, R.id.view_history, "field 'viewHistory'", OurCardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ViewPDFsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewFiles = null;
    target.viewHistory = null;
  }
}
