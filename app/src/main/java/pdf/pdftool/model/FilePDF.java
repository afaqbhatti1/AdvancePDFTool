package pdf.pdftool.model;

import java.io.File;

public class FilePDF {
    private File mPdfFile;
    private boolean mIsEncrypted;

    public FilePDF(File mPdfFile, boolean mIsEncrypted) {
        this.mPdfFile = mPdfFile;
        this.mIsEncrypted = mIsEncrypted;
    }

    public File getPdfFile() {
        return mPdfFile;
    }

    public void setPdfFile(File mPdfFile) {
        this.mPdfFile = mPdfFile;
    }

    public boolean isEncrypted() {
        return mIsEncrypted;
    }

    public void setEncrypted(boolean mIsEncrypted) {
        this.mIsEncrypted = mIsEncrypted;
    }
}
