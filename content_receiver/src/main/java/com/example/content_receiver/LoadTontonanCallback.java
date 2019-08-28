package com.example.content_receiver;

import android.database.Cursor;

public interface LoadTontonanCallback {
    void postExecute(Cursor tontonans);
}
