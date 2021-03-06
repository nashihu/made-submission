package com.example.content_receiver;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.example.content_receiver.DatabaseContract.TontonanColumns.CONTENT_URI;

public class MainActivity extends AppCompatActivity implements LoadTontonanCallback {
    private DataObserver myObserver;
    RecyclerView recyclerView;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        myObserver = new DataObserver(handler, this);
        try {
            getContentResolver().registerContentObserver(CONTENT_URI, true, myObserver);
        } catch (RuntimeException e) {
            Log.e("TAG", "kena RE woiii");
        }
        new getData(this, this).execute();
        recyclerView = findViewById(R.id.rv_main);
        adapter = new MainAdapter();
        LinearLayoutManager verticalLayout
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(verticalLayout);


    }

    @Override
    public void postExecute(Cursor tontonans) {
        ArrayList<TontonanItem> tontonanItems = new ArrayList<>();
        String url = "https://raw.githubusercontent.com/nashihu/todo_app/master/tidak%20ada%20data%20bro.png";
        if (tontonans != null) {
            if (!tontonans.moveToNext()) {
                tontonanItems.add(new TontonanItem(2, "ga ada film favorit..", "", url));
            } else {
                tontonanItems = MappingHelper.mapCursorToArrayList(tontonans);
            }
        } else {
            tontonanItems.add(new TontonanItem(1, "aplikasi utamanya belum di-install..", "..", url));
        }
        adapter.setItems(this, tontonanItems);
        recyclerView.setAdapter(adapter);


    }

    private static class getData extends AsyncTask<Void, Void, Cursor> {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadTontonanCallback> weakCallback;


        private getData(Context context, LoadTontonanCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return weakContext.get().getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor data) {
            super.onPostExecute(data);
            weakCallback.get().postExecute(data);
        }

    }

    static class DataObserver extends ContentObserver {

        final Context context;

        DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new getData(context, (MainActivity) context).execute();
        }
    }
}
