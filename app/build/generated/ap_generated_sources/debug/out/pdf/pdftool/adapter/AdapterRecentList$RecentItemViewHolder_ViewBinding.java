// Generated code from Butter Knife. Do not modify!
package pdf.pdftool.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import pdf.pdftool.R;

public class AdapterRecentList$RecentItemViewHolder_ViewBinding implements Unbinder {
  private AdapterRecentList.RecentItemViewHolder target;

  @UiThread
  public AdapterRecentList$RecentItemViewHolder_ViewBinding(
      AdapterRecentList.RecentItemViewHolder target, View source) {
    this.target = target;

    target.icon_ = Utils.findRequiredViewAsType(source, R.id.option_image_, "field 'icon_'", ImageView.class);
    target.name_ = Utils.findRequiredViewAsType(source, R.id.option_name_, "field 'name_'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AdapterRecentList.RecentItemViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_ = null;
    target.name_ = null;
  }
}
