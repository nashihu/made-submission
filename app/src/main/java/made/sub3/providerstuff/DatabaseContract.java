package made.sub3.providerstuff;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String AUTHORITY = "made.sub3";
    public static final String SCHEME = "content";


    public static final class TontonanColumns implements BaseColumns {
        public static final String TABLE_NAME = "tontonan";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String PHOTO_URL = "photo_url";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }


    public DatabaseContract() { }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
}
