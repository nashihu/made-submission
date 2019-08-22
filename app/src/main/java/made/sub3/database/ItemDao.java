package made.sub3.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import made.sub3.ItemDetail;
import made.sub3.widgets.WidgetItem;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM DETAIL WHERE activity_type = :activity_type")
    List<ItemDetail> loadAllTontonanItem(String activity_type);

    @Insert
    void insertItem(ItemDetail itemDetail);

    @Query("DELETE FROM DETAIL WHERE this_title = :title")
    void deteleTontonan(String title);

    @Query("SELECT * FROM DETAIL WHERE this_title = :title")
    ItemDetail loadAMovie(String title);

    @Insert
    void insertWidgetItem(WidgetItem item);

    @Query("DELETE FROM WIDGET WHERE title = :title")
    void deleteWidgetItem(String title);

    @Query("SELECT * FROM WIDGET")
    List<WidgetItem> loadAllWidgetItem();

}
