package com.example.content_receiver;

import android.database.Cursor;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.content_receiver.DatabaseContract.TontonanColumns.DESCRIPTION;
import static com.example.content_receiver.DatabaseContract.TontonanColumns.PHOTO_URL;
import static com.example.content_receiver.DatabaseContract.TontonanColumns.TITLE;

public class MappingHelper {
    public static ArrayList<TontonanItem> mapCursorToArrayList(Cursor tontonanCursor) {
        ArrayList<TontonanItem> tontonanItems = new ArrayList<>();

        while (tontonanCursor.moveToNext()) {
            int id = tontonanCursor.getInt(tontonanCursor.getColumnIndexOrThrow(_ID));
            String title = tontonanCursor.getString(tontonanCursor.getColumnIndexOrThrow(TITLE));
            String desc  = tontonanCursor.getString(tontonanCursor.getColumnIndexOrThrow(DESCRIPTION));
            String url   = tontonanCursor.getString(tontonanCursor.getColumnIndexOrThrow(PHOTO_URL));
            tontonanItems.add(new TontonanItem(id,title,desc,url));
        }

        return tontonanItems;
    }
}
