// Generated code from Butter Knife. Do not modify!
package pdf.pdftool.fragment;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.dd.morphingbutton.MorphingButton;
import java.lang.IllegalStateException;
import java.lang.Override;
import pdf.pdftool.R;

public class FragmentImageToPdf_ViewBinding implements Unbinder {
  private FragmentImageToPdf target;

  private View view7f0a022f;

  private View view7f0a0230;

  private View view7f0a0051;

  @UiThread
  public FragmentImageToPdf_ViewBinding(final FragmentImageToPdf target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.pdfCreate, "field 'mCreatePdf' and method 'pdfCreateClicked'");
    target.mCreatePdf = Utils.castView(view, R.id.pdfCreate, "field 'mCreatePdf'", MorphingButton.class);
    view7f0a022f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.pdfCreateClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.pdfOpen, "field 'mOpenPdf' and method 'openPdf'");
    target.mOpenPdf = Utils.castView(view, R.id.pdfOpen, "field 'mOpenPdf'", MorphingButton.class);
    view7f0a0230 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.openPdf();
      }
    });
    target.mEnhancementOptionsRecycleView = Utils.findRequiredViewAsType(source, R.id.enhancement_options_recycle_view, "field 'mEnhancementOptionsRecycleView'", RecyclerView.class);
    target.mNoOfImages = Utils.findRequiredViewAsType(source, R.id.tvNoOfImages, "field 'mNoOfImages'", TextView.class);
    view = Utils.findRequiredView(source, R.id.addImages, "method 'startAddingImages'");
    view7f0a0051 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.startAddingImages();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    FragmentImageToPdf target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mCreatePdf = null;
    target.mOpenPdf = null;
    target.mEnhancementOptionsRecycleView = null;
    target.mNoOfImages = null;

    view7f0a022f.setOnClickListener(null);
    view7f0a022f = null;
    view7f0a0230.setOnClickListener(null);
    view7f0a0230 = null;
    view7f0a0051.setOnClickListener(null);
    view7f0a0051 = null;
  }
}
