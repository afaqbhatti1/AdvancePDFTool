// Generated code from Butter Knife. Do not modify!
package pdf.pdftool.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

public class FragmentExceltoPdf_ViewBinding implements Unbinder {
  private FragmentExceltoPdf target;

  private View view7f0a0208;

  private View view7f0a00bd;

  private View view7f0a0289;

  @UiThread
  public FragmentExceltoPdf_ViewBinding(final FragmentExceltoPdf target, View source) {
    this.target = target;

    View view;
    target.mLottieProgress = Utils.findRequiredViewAsType(source, R.id.lottie_progress, "field 'mLottieProgress'", LottieAnimationView.class);
    target.mTextView = Utils.findRequiredViewAsType(source, R.id.tv_excel_file_name_bottom, "field 'mTextView'", TextView.class);
    view = Utils.findRequiredView(source, R.id.open_pdf, "field 'mOpenPdf' and method 'openPdf'");
    target.mOpenPdf = Utils.castView(view, R.id.open_pdf, "field 'mOpenPdf'", MorphingButton.class);
    view7f0a0208 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.openPdf();
      }
    });
    view = Utils.findRequiredView(source, R.id.create_excel_to_pdf, "field 'mCreateExcelPdf' and method 'openExcelToPdf'");
    target.mCreateExcelPdf = Utils.castView(view, R.id.create_excel_to_pdf, "field 'mCreateExcelPdf'", MorphingButton.class);
    view7f0a00bd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.openExcelToPdf();
      }
    });
    target.mEnhancementOptionsRecycleView = Utils.findRequiredViewAsType(source, R.id.enhancement_options_recycle_view, "field 'mEnhancementOptionsRecycleView'", RecyclerView.class);
    target.layoutBottomSheet = Utils.findRequiredViewAsType(source, R.id.bottom_sheet, "field 'layoutBottomSheet'", LinearLayout.class);
    target.mUpArrow = Utils.findRequiredViewAsType(source, R.id.upArrow, "field 'mUpArrow'", ImageView.class);
    target.mLayout = Utils.findRequiredViewAsType(source, R.id.layout, "field 'mLayout'", RelativeLayout.class);
    target.mRecyclerViewFiles = Utils.findRequiredViewAsType(source, R.id.recyclerViewFiles, "field 'mRecyclerViewFiles'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.select_excel_file, "method 'selectExcelFile'");
    view7f0a0289 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.selectExcelFile();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    FragmentExceltoPdf target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mLottieProgress = null;
    target.mTextView = null;
    target.mOpenPdf = null;
    target.mCreateExcelPdf = null;
    target.mEnhancementOptionsRecycleView = null;
    target.layoutBottomSheet = null;
    target.mUpArrow = null;
    target.mLayout = null;
    target.mRecyclerViewFiles = null;

    view7f0a0208.setOnClickListener(null);
    view7f0a0208 = null;
    view7f0a00bd.setOnClickListener(null);
    view7f0a00bd = null;
    view7f0a0289.setOnClickListener(null);
    view7f0a0289 = null;
  }
}
