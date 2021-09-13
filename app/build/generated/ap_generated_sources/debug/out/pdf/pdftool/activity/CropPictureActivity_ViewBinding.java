// Generated code from Butter Knife. Do not modify!
package pdf.pdftool.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.lang.IllegalStateException;
import java.lang.Override;
import pdf.pdftool.R;

public class CropPictureActivity_ViewBinding implements Unbinder {
  private CropPictureActivity target;

  private View view7f0a00c2;

  private View view7f0a0265;

  private View view7f0a01fb;

  private View view7f0a023a;

  @UiThread
  public CropPictureActivity_ViewBinding(CropPictureActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CropPictureActivity_ViewBinding(final CropPictureActivity target, View source) {
    this.target = target;

    View view;
    target.mImageCount = Utils.findRequiredViewAsType(source, R.id.imagecount, "field 'mImageCount'", TextView.class);
    target.mCropImageView = Utils.findRequiredViewAsType(source, R.id.cropImageView, "field 'mCropImageView'", CropImageView.class);
    view = Utils.findRequiredView(source, R.id.cropButton, "field 'cropImageButton' and method 'cropButtonClicked'");
    target.cropImageButton = Utils.castView(view, R.id.cropButton, "field 'cropImageButton'", Button.class);
    view7f0a00c2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cropButtonClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.rotateButton, "method 'rotateButtonClicked'");
    view7f0a0265 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.rotateButtonClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.nextimageButton, "method 'nextImageClicked'");
    view7f0a01fb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.nextImageClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.previousImageButton, "method 'prevImgBtnClicked'");
    view7f0a023a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.prevImgBtnClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CropPictureActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mImageCount = null;
    target.mCropImageView = null;
    target.cropImageButton = null;

    view7f0a00c2.setOnClickListener(null);
    view7f0a00c2 = null;
    view7f0a0265.setOnClickListener(null);
    view7f0a0265 = null;
    view7f0a01fb.setOnClickListener(null);
    view7f0a01fb = null;
    view7f0a023a.setOnClickListener(null);
    view7f0a023a = null;
  }
}
