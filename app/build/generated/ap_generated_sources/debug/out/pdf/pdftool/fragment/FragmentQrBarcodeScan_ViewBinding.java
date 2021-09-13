// Generated code from Butter Knife. Do not modify!
package pdf.pdftool.fragment;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import pdf.pdftool.R;
import pdf.pdftool.customviews.OurCardView;

public class FragmentQrBarcodeScan_ViewBinding implements Unbinder {
  private FragmentQrBarcodeScan target;

  @UiThread
  public FragmentQrBarcodeScan_ViewBinding(FragmentQrBarcodeScan target, View source) {
    this.target = target;

    target.scanQrcode = Utils.findRequiredViewAsType(source, R.id.scan_qrcode, "field 'scanQrcode'", OurCardView.class);
    target.scanBarcode = Utils.findRequiredViewAsType(source, R.id.scan_barcode, "field 'scanBarcode'", OurCardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FragmentQrBarcodeScan target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.scanQrcode = null;
    target.scanBarcode = null;
  }
}
