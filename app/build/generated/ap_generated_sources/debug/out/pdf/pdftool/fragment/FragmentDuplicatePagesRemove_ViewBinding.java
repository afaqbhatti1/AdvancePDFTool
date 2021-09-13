// Generated code from Butter Knife. Do not modify!
package pdf.pdftool.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.lottie.LottieAnimationView;
import com.dd.morphingbutton.MorphingButton;
import java.lang.IllegalStateException;
import java.lang.Override;
import pdf.pdftool.R;

public class FragmentDuplicatePagesRemove_ViewBinding implements Unbinder {
  private FragmentDuplicatePagesRemove target;

  private View view7f0a0285;

  private View view7f0a0252;

  private View view7f0a0317;

  @UiThread
  public FragmentDuplicatePagesRemove_ViewBinding(final FragmentDuplicatePagesRemove target,
      View source) {
    this.target = target;

    View view;
    target.mLottieProgress = Utils.findRequiredViewAsType(source, R.id.lottie_progress, "field 'mLottieProgress'", LottieAnimationView.class);
    view = Utils.findRequiredView(source, R.id.selectFile, "field 'selectFileButton' and method 'showFileChooser'");
    target.selectFileButton = Utils.castView(view, R.id.selectFile, "field 'selectFileButton'", MorphingButton.class);
    view7f0a0285 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showFileChooser();
      }
    });
    view = Utils.findRequiredView(source, R.id.remove, "field 'removeDuplicateButton' and method 'parse'");
    target.removeDuplicateButton = Utils.castView(view, R.id.remove, "field 'removeDuplicateButton'", MorphingButton.class);
    view7f0a0252 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.parse();
      }
    });
    target.layoutBottomSheet = Utils.findRequiredViewAsType(source, R.id.bottom_sheet, "field 'layoutBottomSheet'", LinearLayout.class);
    target.mUpArrow = Utils.findRequiredViewAsType(source, R.id.upArrow, "field 'mUpArrow'", ImageView.class);
    target.mDownArrow = Utils.findRequiredViewAsType(source, R.id.downArrow, "field 'mDownArrow'", ImageView.class);
    target.mLayout = Utils.findRequiredViewAsType(source, R.id.layout, "field 'mLayout'", RelativeLayout.class);
    target.mRecyclerViewFiles = Utils.findRequiredViewAsType(source, R.id.recyclerViewFiles, "field 'mRecyclerViewFiles'", RecyclerView.class);
    target.mViewPdf = Utils.findRequiredViewAsType(source, R.id.view_pdf, "field 'mViewPdf'", Button.class);
    view = Utils.findRequiredView(source, R.id.viewFiles, "method 'onViewFilesClick'");
    view7f0a0317 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewFilesClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    FragmentDuplicatePagesRemove target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mLottieProgress = null;
    target.selectFileButton = null;
    target.removeDuplicateButton = null;
    target.layoutBottomSheet = null;
    target.mUpArrow = null;
    target.mDownArrow = null;
    target.mLayout = null;
    target.mRecyclerViewFiles = null;
    target.mViewPdf = null;

    view7f0a0285.setOnClickListener(null);
    view7f0a0285 = null;
    view7f0a0252.setOnClickListener(null);
    view7f0a0252 = null;
    view7f0a0317.setOnClickListener(null);
    view7f0a0317 = null;
  }
}
