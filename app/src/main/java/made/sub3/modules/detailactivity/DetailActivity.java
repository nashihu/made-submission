package made.sub3.modules.detailactivity;

import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Locale;

import made.sub3.BuildConfig;
import made.sub3.ItemDetail;
import made.sub3.R;
import made.sub3.database.AppDatabase;
import made.sub3.database.AppExecutors;
import made.sub3.widgets.StackWidgetProvider;
import made.sub3.widgets.WidgetItem;

import static made.sub3.providerstuff.DatabaseContract.TontonanColumns.CONTENT_URI;
import static made.sub3.providerstuff.DatabaseContract.TontonanColumns.DESCRIPTION;
import static made.sub3.providerstuff.DatabaseContract.TontonanColumns.PHOTO_URL;
import static made.sub3.providerstuff.DatabaseContract.TontonanColumns.TITLE;
import static made.sub3.providerstuff.MappingHelper.mapCursorToArrayList;

public class DetailActivity extends AppCompatActivity {

    private ImageView backdrop, poster;
    private TextView desc;
    private TextView vote;
    private TextView voter;
    private TextView duration;
    private TextView release_date;
    private TextView genre;
    private ItemDetail itemDetaill;
    private FloatingActionButton fab;
    private AppDatabase mDb;
    private DetailActivityViewModel model;
    private Intent intent;
    private Integer successCounter = 0;
    String absolute_path;
    String child;
    private final Observer<ItemDetail> strings = new Observer<ItemDetail>() {

        @Override
        public void onChanged(@Nullable final ItemDetail itemDetail) {
            if (itemDetail != null) {
                desc.setText(itemDetail.getDesc_or_title());
                vote.setText(itemDetail.getRating());
                voter.setText(itemDetail.getVoters());
                duration.setText(itemDetail.getDuration_or_id_tmdb());
                release_date.setText(itemDetail.getRelease_date().substring(0, 4));
                genre.setText(itemDetail.getGenres_or_type());
                itemDetaill = new ItemDetail(itemDetail.getDesc_or_title(),
                        itemDetail.getRating(),
                        itemDetail.getVoters(),
                        itemDetail.getRelease_date(),
                        itemDetail.getDuration_or_id_tmdb(),
                        itemDetail.getBackdrop_url_or_poster_url(),
                        itemDetail.getGenres_or_type(),
                        itemDetail.getPoster_url_for_detail_activity(),
                        intent.getStringExtra("title"),
                        intent.getStringExtra("type"),
                        intent.getStringExtra("id_tmdb")
                );

                ((TextView) findViewById(R.id.tv_detail_info_releasedate)).setText(getString(R.string.detail_info_release_date));
                ((TextView) findViewById(R.id.tv_detail_info_vote)).setText(getString(R.string.detail_info_vote));
                ((TextView) findViewById(R.id.tv_detail_info_voted_by)).setText(getString(R.string.detail_info_voted_by));
                ((TextView) findViewById(R.id.tv_detail_info_voter)).setText(getString(R.string.detail_info_voter));
                ((TextView) findViewById(R.id.tv_detail_info_duration)).setText(getString(R.string.detail_info_duration));
                ((TextView) findViewById(R.id.tv_detail_info_duration_minute)).setText(getString(R.string.detail_minutes));
                final String poster_url = itemDetail.getPoster_url_for_detail_activity();
                Log.i("URLL", itemDetail.getPoster_url_for_detail_activity());
                Log.i("URLL", itemDetail.getBackdrop_url_or_poster_url());
                Picasso.get().load(itemDetail.getBackdrop_url_or_poster_url()).into(backdrop, new Callback() {
                    @Override
                    public void onSuccess() {
                        backdrop.setVisibility(View.VISIBLE);
                        showButtonFav();
                        findViewById(R.id.detail_backdrop_progress_bar).setVisibility(View.GONE);
                        backdrop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                                    show_full_image(getString(R.string.backdrop), itemDetail.getBackdrop_url_or_poster_url(), "String");
                                } else {
                                    Toast.makeText(DetailActivity.this, getString(R.string.use_backdrop), Toast.LENGTH_SHORT).show();
                                }


                            }
                        });


                    }

                    @Override
                    public void onError(Exception e) {

                    }

                });
                Picasso.get().load(itemDetail.getPoster_url_for_detail_activity()).into(poster, new Callback() {
                    @Override
                    public void onSuccess() {
                        poster.setVisibility(View.VISIBLE);
                        findViewById(R.id.detail_poster_progress_bar).setVisibility(View.GONE);
                        poster.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                show_full_image(getString(R.string.poster), poster_url, "int");
                            }
                        });
                        showButtonFav();
                        Bitmap bitmapImage = ((BitmapDrawable) poster.getDrawable()).getBitmap();
                        ContextWrapper cw = new ContextWrapper(getApplicationContext());
                        // path to /data/data/yourapp/app_data/imageDir
                        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                        // Create imageDir
                        child = intent.getStringExtra("type") + "_" + intent.getStringExtra("id_tmdb");
                        File mypath = new File(directory, child);

                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(mypath);
                            // Use the compress method on the BitMap object to write image to the OutputStream
                            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (fos != null) fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        absolute_path = directory.getAbsolutePath();

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });


            } else {
                Toast.makeText(DetailActivity.this, "null bro..", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mDb = AppDatabase.getInstance(getApplicationContext());
        setContentView(R.layout.activity_detail);
        intent = getIntent();
        final String type = getIntent().getStringExtra("type");
        final String code = getIntent().getStringExtra("id_tmdb");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        }
        String lang = "en-US";
        final String curr_lang = Locale.getDefault().getDisplayLanguage();
        if (curr_lang.equals("Indonesia")) {
            lang = "id";
        }
        model = ViewModelProviders.of(this).get(DetailActivityViewModel.class);
        final String url = "https://api.themoviedb.org/3/" + type + "/" + code + "?api_key=" + BuildConfig.TMDB_API_KEY + "&language=" + lang;
        model.getData().observe(DetailActivity.this, strings);

        TextView release_date_field;
        findViewById(R.id.image_poster_core).setVisibility(View.INVISIBLE);
        poster = findViewById(R.id.image_poster_core);
        release_date = findViewById(R.id.tv_detail_releasedate);
        vote = findViewById(R.id.tv_detail_vote);
        voter = findViewById(R.id.tv_detail_number_vote);
        duration = findViewById(R.id.tv_detail_duration);
        desc = findViewById(R.id.tv_detail_description);
        genre = findViewById(R.id.tv_genre_1);
        backdrop = findViewById(R.id.iv_image_backdrop_core);
        backdrop.setVisibility(View.INVISIBLE);
        fab = findViewById(R.id.fab_detail_fav);
        fab.hide();
        release_date_field = findViewById(R.id.tv_detail_info_releasedate);
        new LoadTontonanAsyc(this).execute();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final ItemDetail itemDetail = mDb.itemDao().loadAMovie(intent.getStringExtra("title"));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (itemDetail != null) {
                            fab.setImageDrawable(getDrawable(R.drawable.ic_min_box_black_24dp));

                        }
                    }
                });
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        final ItemDetail itemDetail = mDb.itemDao().loadAMovie(intent.getStringExtra("title"));
                        final String title = intent.getStringExtra("title");
                        if (itemDetail != null) {
                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mDb.itemDao().deteleTontonan(title);
                                    mDb.itemDao().deleteWidgetItem(title);
                                    Uri data = Uri.parse(CONTENT_URI + "/" + itemDetail.getId());
                                    getContentResolver().delete(data, null, null);
                                    Intent toastIntent = new Intent(getApplicationContext(), StackWidgetProvider.class);
                                    toastIntent.setAction(StackWidgetProvider.TOAST_ACTION);
                                    sendBroadcast(toastIntent);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(DetailActivity.this, "success removed from fav's", Toast.LENGTH_SHORT).show();
                                            fab.setImageDrawable(getDrawable(R.drawable.ic_favorite_black_24dp));
                                        }
                                    });
                                }
                            });
                        } else {
                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mDb.itemDao().insertItem(itemDetaill);
                                    mDb.itemDao().insertWidgetItem(new WidgetItem(title, absolute_path, child));
                                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                                    ComponentName thisWidget = new ComponentName(getApplicationContext(), StackWidgetProvider.class);
                                    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

                                    for (int appWidgetId : appWidgetIds) {
                                        Intent toastIntent = new Intent(getApplicationContext(), StackWidgetProvider.class);
                                        toastIntent.setAction(StackWidgetProvider.TOAST_ACTION);
                                        toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                                        sendBroadcast(toastIntent);
                                    }
                                    ContentValues values = new ContentValues();
                                    values.put(TITLE, title);
                                    values.put(DESCRIPTION, "desc");
                                    values.put(PHOTO_URL, itemDetaill.getPoster_url_for_detail_activity());
                                    getContentResolver().insert(CONTENT_URI, values);


                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(DetailActivity.this, "success added to fav's", Toast.LENGTH_SHORT).show();
                                            fab.setImageDrawable(getDrawable(R.drawable.ic_min_box_black_24dp));
                                        }
                                    });
                                }
                            });

                        }

                    }
                });
            }
        });


        release_date_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setData(DetailActivity.this, url, type, curr_lang, code);
            }
        });
        release_date_field.callOnClick();

    }

    private void show_full_image(String title, String id_image, String type) {

        AlertDialog.Builder alert = new AlertDialog.Builder(DetailActivity.this).setTitle(title);
        LayoutInflater factory = LayoutInflater.from(DetailActivity.this);
        View view = factory.inflate(R.layout.zoom_in_alert, null);
        ImageView ss = view.findViewById(R.id.report_alert_image);
        if (type.equals("String")) {
            if (android.provider.Settings.System.getInt(getContentResolver(),
                    Settings.System.ACCELEROMETER_ROTATION, 0) == 1) {
                Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
                if (android.provider.Settings.System.getInt(getContentResolver(),
                        Settings.System.ACCELEROMETER_ROTATION, 0) == 1) {
                    Toast.makeText(this, getString(R.string.failed_auto_rotate), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getString(R.string.success_auto_rotate), Toast.LENGTH_SHORT).show();
                }
            }
            Picasso.get().load(id_image).rotate(90).into(ss);
        } else {
            Picasso.get().load(id_image).into(ss);
        }
        alert.setView(view);
        alert.setNeutralButton(getString(R.string.back), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }

    private static class LoadTontonanAsyc extends AsyncTask<Void, Void, Cursor> {

        private final WeakReference<Context> weakContext;
        private final Context context;

        private LoadTontonanAsyc(Context context) {
            weakContext = new WeakReference<>(context);
            this.context = context;
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            Context context = weakContext.get();
            return context.getContentResolver().query(CONTENT_URI, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            ArrayList<ItemDetail> itemDetails = mapCursorToArrayList(cursor);
            Toast.makeText(context, "size: " + itemDetails.size() + "", Toast.LENGTH_SHORT).show();
        }
    }

    void showButtonFav() {
        successCounter++;
        if (successCounter >= 2) {
            fab.show();
        }
    }
}
