package pdf.pdftool.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("SELECT * FROM HistoryDatabase order by mId desc")
    List<HistoryDatabase> getAllHistory();

    @Insert
    void insertAll(HistoryDatabase... histories);

    @Query("Delete from HistoryDatabase")
    void deleteHistory();

    @Query("select * from HistoryDatabase where operation_type IN(:types) order by mId desc")
    List<HistoryDatabase> getHistoryByOperationType(String[] types);
}