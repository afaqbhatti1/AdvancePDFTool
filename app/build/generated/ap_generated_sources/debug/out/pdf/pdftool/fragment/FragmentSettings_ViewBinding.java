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
import java.lang.IllegalStateException;
import java.lang.Override;
import pdf.pdftool.R;

public class FragmentSettings_ViewBinding implements Unbinder {
  private FragmentSettings target;

  private View view7f0a02c3;

  @UiThread
  public FragmentSettings_ViewBinding(final FragmentSettings target, View source) {
    this.target = target;

    View view;
    target.mEnhancementOptionsRecycleView = Utils.findRequiredViewAsType(source, R.id.settings_list, "field 'mEnhancementOptionsRecycleView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.storagelocation, "field 'storageLocation' and method 'modifyStorageLocation'");
    target.storageLocation = Utils.castView(view, R.id.storagelocation, "field 'storageLocation'", TextView.class);
    view7f0a02c3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.modifyStorageLocation();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    FragmentSettings target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEnhancementOptionsRecycleView = null;
    target.storageLocation = null;

    view7f0a02c3.setOnClickListener(null);
    view7f0a02c3 = null;
  }
}
