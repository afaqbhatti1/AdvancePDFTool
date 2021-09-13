// Generated code from Butter Knife. Do not modify!
package pdf.pdftool.activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import pdf.pdftool.R;

public class RearrangePictures_ViewBinding implements Unbinder {
  private RearrangePictures target;

  private View view7f0a02a3;

  @UiThread
  public RearrangePictures_ViewBinding(RearrangePictures target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RearrangePictures_ViewBinding(final RearrangePictures target, View source) {
    this.target = target;

    View view;
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'mRecyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.sort, "method 'sortImg'");
    view7f0a02a3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sortImg();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RearrangePictures target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;

    view7f0a02a3.setOnClickListener(null);
    view7f0a02a3 = null;
  }
}
