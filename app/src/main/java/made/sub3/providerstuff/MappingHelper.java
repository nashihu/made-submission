package made.sub3.providerstuff;

import android.database.Cursor;

import java.util.ArrayList;
import static android.provider.BaseColumns._ID;
import static made.sub3.providerstuff.DatabaseContract.TontonanColumns.DESCRIPTION;
import static made.sub3.providerstuff.DatabaseContract.TontonanColumns.PHOTO_URL;
import static made.sub3.providerstuff.DatabaseContract.TontonanColumns.TITLE;
import made.sub3.ItemDetail;

public class MappingHelper {
    public static ArrayList<ItemDetail> mapCursorToArrayList(Cursor tontonanCursor) {
        ArrayList<ItemDetail> itemDetails = new ArrayList<>();
        while(tontonanCursor.moveToNext()) {
            int id = tontonanCursor.getInt(tontonanCursor.getColumnIndexOrThrow(_ID));
            String title = tontonanCursor.getString(tontonanCursor.getColumnIndexOrThrow(TITLE));
            String desc  = tontonanCursor.getString(tontonanCursor.getColumnIndexOrThrow(DESCRIPTION));
            String url   = tontonanCursor.getString(tontonanCursor.getColumnIndexOrThrow(PHOTO_URL));
            itemDetails.add(new ItemDetail(id,title,desc,url));

        }
        return itemDetails;
    }
}
