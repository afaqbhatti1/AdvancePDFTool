package pdf.pdftool.util;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import pdf.pdftool.R;
import pdf.pdftool.fragment.FragmentAddImages;
import pdf.pdftool.fragment.FragmentDuplicatePagesRemove;
import pdf.pdftool.fragment.FragmentExceltoPdf;
import pdf.pdftool.fragment.FragmentExtractText;
import pdf.pdftool.fragment.FragmentFilesSplit;
import pdf.pdftool.fragment.FragmentHistory;
import pdf.pdftool.fragment.FragmentImageToPdf;
import pdf.pdftool.fragment.FragmentInvertPdf;
import pdf.pdftool.fragment.FragmentMergeFiles;
import pdf.pdftool.fragment.FragmentPdfToImage;
import pdf.pdftool.fragment.FragmentQrBarcodeScan;
import pdf.pdftool.fragment.FragmentRemovePages;
import pdf.pdftool.fragment.FragmentViewFiles;
import pdf.pdftool.fragment.FragmentZipToPdf;
import pdf.pdftool.fragment.texttopdf.FragmentTextToPdf;

import static pdf.pdftool.util.Constants.ADD_WATERMARK;
import static pdf.pdftool.util.Constants.ADD_WATERMARK_KEY;
import static pdf.pdftool.util.Constants.BUNDLE_DATA;
import static pdf.pdftool.util.Constants.ROTATE_PAGES;
import static pdf.pdftool.util.Constants.ROTATE_PAGES_KEY;

public class FragmentUtils {

    private final Context mContext;

    public FragmentUtils(Context mContext) {
        this.mContext = mContext;
    }

    public String getFragmentName(Fragment fragment) {
        String name = mContext.getString(R.string.app_name);
        if (fragment instanceof FragmentImageToPdf) {
            name = mContext.getString(R.string.images_to_pdf);
        } else if (fragment instanceof FragmentTextToPdf) {
            name = mContext.getString(R.string.text_to_pdf);
        } else if (fragment instanceof FragmentQrBarcodeScan) {
            name = mContext.getString(R.string.qr_barcode_pdf);
        } else if (fragment instanceof FragmentExceltoPdf) {
            name = mContext.getString(R.string.excel_to_pdf);
        } else if (fragment instanceof FragmentViewFiles) {
            name = checkViewFilesFragmentCode(fragment.getArguments());
        } else if (fragment instanceof FragmentHistory) {
            name = mContext.getString(R.string.history);
        } else if (fragment instanceof FragmentExtractText) {
            name = mContext.getString(R.string.extract_text);
        } else if (fragment instanceof FragmentAddImages) {
            name = mContext.getString(R.string.add_images);
        } else if (fragment instanceof FragmentMergeFiles) {
            name = mContext.getString(R.string.merge_pdf);
        } else if (fragment instanceof FragmentFilesSplit) {
            name = mContext.getString(R.string.split_pdf);
        } else if (fragment instanceof FragmentInvertPdf) {
            name = mContext.getString(R.string.invert_pdf);
        } else if (fragment instanceof FragmentDuplicatePagesRemove) {
            name = mContext.getString(R.string.remove_duplicate);
        } else if (fragment instanceof FragmentRemovePages) {
            name = fragment.getArguments() != null ?
                    fragment.getArguments().getString(BUNDLE_DATA) : null;
        } else if (fragment instanceof FragmentPdfToImage) {
            name = mContext.getString(R.string.pdf_to_images);
        } else if (fragment instanceof FragmentZipToPdf) {
            name = mContext.getString(R.string.zip_to_pdf);
        }
        return name;
    }

    public boolean handleFragmentBottomSheetBehavior(Fragment fragment) {
        boolean bottomSheetBehaviour = false;
        if (fragment instanceof FragmentInvertPdf) {
            bottomSheetBehaviour = ((FragmentInvertPdf) fragment).checkSheetBehaviour();
            if (bottomSheetBehaviour) ((FragmentInvertPdf) fragment).closeBottomSheet();
        } else if (fragment instanceof FragmentMergeFiles) {
            bottomSheetBehaviour = ((FragmentMergeFiles) fragment).checkSheetBehaviour();
            if (bottomSheetBehaviour) ((FragmentMergeFiles) fragment).closeBottomSheet();
        } else if (fragment instanceof FragmentDuplicatePagesRemove) {
            bottomSheetBehaviour = ((FragmentDuplicatePagesRemove) fragment).checkSheetBehaviour();
            if (bottomSheetBehaviour) ((FragmentDuplicatePagesRemove) fragment).closeBottomSheet();
        } else if (fragment instanceof FragmentRemovePages) {
            bottomSheetBehaviour = ((FragmentRemovePages) fragment).checkSheetBehaviour();
            if (bottomSheetBehaviour) ((FragmentRemovePages) fragment).closeBottomSheet();
        } else if (fragment instanceof FragmentAddImages) {
            bottomSheetBehaviour = ((FragmentAddImages) fragment).checkSheetBehaviour();
            if (bottomSheetBehaviour) ((FragmentAddImages) fragment).closeBottomSheet();
        } else if (fragment instanceof FragmentPdfToImage) {
            bottomSheetBehaviour = ((FragmentPdfToImage) fragment).checkSheetBehaviour();
            if (bottomSheetBehaviour) ((FragmentPdfToImage) fragment).closeBottomSheet();
        } else if (fragment instanceof FragmentFilesSplit) {
            bottomSheetBehaviour = ((FragmentFilesSplit) fragment).checkSheetBehaviour();
            if (bottomSheetBehaviour) ((FragmentFilesSplit) fragment).closeBottomSheet();
        }
        return bottomSheetBehaviour;
    }

    /**
     * Checks the arguments of the ViewFilesFragment
     * to determine the name of the fragment.
     * @param arguments A Bundle containing the args of the fragment.
     * @return The name of the fragment.
     */
    private String checkViewFilesFragmentCode(Bundle arguments) {
        if (arguments != null) {
            int code = arguments.getInt(BUNDLE_DATA);
            if (code == ROTATE_PAGES) {
                return ROTATE_PAGES_KEY;
            } else if (code == ADD_WATERMARK) {
                return ADD_WATERMARK_KEY;
            }
        }
        return mContext.getString(R.string.viewFiles);
    }
}
