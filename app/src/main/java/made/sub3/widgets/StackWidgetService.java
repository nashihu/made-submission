package made.sub3.widgets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import made.sub3.R;
import made.sub3.widgets.WidgetItem;
import made.sub3.database.AppDatabase;
import made.sub3.database.AppExecutors;

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }

}

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private List<Bitmap> bitmaps = new ArrayList<>();
    private Context mContext;
    private List<WidgetItem> DAOitems = new ArrayList<>();
    //TODO ini ntar di uncomm
    private int mAppWidgetId;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
//        TODO ini ntar di uncomm
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    public void onCreate() {

        // In onCreate() you setup any connections / cursors to your data source. Heavy lifting,
        // for example downloading or creating content etc, should be deferred to onDataSetChanged()
        // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.
        mDb = AppDatabase.getInstance(mContext);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {

            @Override
            public void run() {
                bitmaps.clear();
                DAOitems = mDb.itemDao().loadAllWidgetItem();
                onDataSetChanged();


            }
        });


//        TODO ini sebelumnya dipindahin ke onDatasetChanged

//        bitmaps.add(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.movie_alita));
//        bitmaps.add(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.backdrop_alita));


        // We sleep for 3 seconds here to show how the empty view appears in the interim.
        // The empty view is set in the StackWidgetProvider and should be a sibling of the
        // collection view.
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void onDestroy() {
        // In onDestroy() you should tear down anything that was setup for your data source,
        // eg. cursors, connections, etc.
        bitmaps.clear();
    }

    public int getCount() {
        return bitmaps.size();
    }

    public RemoteViews getViewAt(int position) {
        // position will always range from 0 to getCount() - 1.
        // We construct a remote views item based on our widget item xml file, and set the
        // text based on the position.
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        rv.setImageViewBitmap(R.id.widget_item_image, bitmaps.get(position));

//        rv.setTextViewText(R.id.widget_item, mWidgetItems.get(position).text);
        // Next, we set a fill-intent which will be used to fill-in the pending intent template
        // which is set on the collection view in StackWidgetProvider.
        Bundle extras = new Bundle();
        extras.putInt(StackWidgetProvider.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.widget_item_image, fillInIntent);

//        AppExecutors.getInstance().diskIO().execute(new Runnable() {
//
//            @Override
//            public void run() {
//
//                DAOitems = mDb.itemDao().loadAllWidgetItem();
//
//
//                Log.d("SWS", "udah update nih dao nya, size: " + DAOitems.size());
//                Log.d("SWS", "kalo getCountnya? ->" + getCount());
//                Log.d("SWS", mAppWidgetId + "");
//
//
//            }
//
//        });


//        new DownloadImageTask(bitmaps).execute("https://image.tmdb.org/t/p/original/or06FN3Dka5tukK1e9sl16pB3iy.jpg");
//        new DownloadImageTask(bitmaps).execute("https://image.tmdb.org/t/p/original/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg");


        // You can do heaving lifting in here, synchronously. For example, if you need to
        // process an image, fetch something from the network, etc., it is ok to do it here,
        // synchronously. A loading view will show up in lieu of the actual contents in the
        // interim.
//        try {
//            System.out.println("Loading view " + position);
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // Return the remote views object.
        return rv;
    }

    public RemoteViews getLoadingView() {
        // You can create a custom loading view (for instance when getViewAt() is slow.) If you
        // return null here, you will get the default loading view.
        return null;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public long getItemId(int position) {
        //TODO ini juga sblmnya position deh
//        return position;
        return 0;
    }

    public boolean hasStableIds() {
        //TODO ini sebelumnya true wkwkwk
        return false;
    }

    private AppDatabase mDb;

    public void onDataSetChanged() {
//
//        AppWidgetManager mgr = AppWidgetManager.getInstance(mContext);
//        mgr.notifyAppWidgetViewDataChanged(mAppWidgetId,R.id.stack_view);


        // This is triggered when you call AppWidgetManager notifyAppWidgetViewDataChanged
        // on the collection view corresponding to this factory. You can do heaving lifting in
        // here, synchronously. For example, if you need to process an image, fetch something
        // from the network, etc., it is ok to do it here, synchronously. The widget will remain
        // in its current state while work is being done here, so you don't need to worry about
        // locking up the widget.
        bitmaps.add(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.backdrop_alita));
        bitmaps.clear();
        for (int position = 0; position < DAOitems.size(); position++) {

            try {
                Log.d("Widget", "index ke-" + position);
                String absolute_path = DAOitems.get(position).getAbsolute_path();
                String child = DAOitems.get(position).getChild_path();
                File f = new File(absolute_path, child);
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                bitmaps.add(b);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d("Widget", "duh gagal coy.. katanya: " + e.toString());
            }
        }


    }

//    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        List<Bitmap> bmImage;
//
//        public DownloadImageTask(List<Bitmap> bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap bmp = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                bmp = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return bmp;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            this.bmImage.add(result);
//            getViewAt(0);
//
//        }
//    }
}
