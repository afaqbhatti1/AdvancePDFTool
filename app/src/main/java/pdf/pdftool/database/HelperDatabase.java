package pdf.pdftool.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import java.util.Date;

public class HelperDatabase {
    private final Context mContext;

    public HelperDatabase(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * To insert record in the database
     * @param filePath path of the file
     * @param operationType operation performed on file
     */
    public void insertRecord(String filePath, String operationType) {
        new Insert().execute(new HistoryDatabase(filePath,  new Date().toString(), operationType));
    }

    @SuppressLint("StaticFieldLeak")
    private class Insert extends AsyncTask<HistoryDatabase, Void, Void> {

        @Override
        protected Void doInBackground(HistoryDatabase... histories) {
            AppDatabase db = AppDatabase.getDatabase(mContext.getApplicationContext());
            db.historyDao().insertAll(histories);
            return null;
        }
    }
}