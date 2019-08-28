package made.sub3.providerstuff;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import static made.sub3.providerstuff.DatabaseContract.AUTHORITY;
import static made.sub3.providerstuff.DatabaseContract.TontonanColumns.CONTENT_URI;
import static made.sub3.providerstuff.DatabaseContract.TontonanColumns.TABLE_NAME;

public class TontonanProvider extends ContentProvider {

    public static final int TONTONAN = 1;
    public static final int TONTONAN_ID = 2;
    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME,TONTONAN);
        sUriMatcher.addURI(AUTHORITY,TABLE_NAME+"/#",TONTONAN_ID);
    }

    private TontonanHelper tontonanHelper;

    @Override
    public boolean onCreate() {
        tontonanHelper = TontonanHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        tontonanHelper.open();
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case TONTONAN:
                cursor = tontonanHelper.queryProvider();
                break;
            case TONTONAN_ID:
                cursor = tontonanHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {return null;}


    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        tontonanHelper.open();
        long added;
        switch (sUriMatcher.match(uri)) {
            case TONTONAN:
                added = tontonanHelper.insertProvider(contentValues);
                break;
            default:
                added = 0;
                break;
        }

//        getContext().getContentResolver().notifyChange(CONTENT_URI, new MainActivity.DataObserver(new Handler(), getContext()));

        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        tontonanHelper.open();
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case TONTONAN_ID:
                deleted = tontonanHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

//        getContext().getContentResolver().notifyChange(CONTENT_URI, new MainActivity.DataObserver(new Handler(), getContext()));

        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        tontonanHelper.open();
        int updated;
        switch (sUriMatcher.match(uri)) {
            case TONTONAN_ID:
                updated = tontonanHelper.updateProvider(uri.getLastPathSegment(), contentValues);
                break;
            default:
                updated = 0;
                break;
        }

//        getContext().getContentResolver().notifyChange(CONTENT_URI, new MainActivity.DataObserver(new Handler(), getContext()));

        return updated;
    }
}
