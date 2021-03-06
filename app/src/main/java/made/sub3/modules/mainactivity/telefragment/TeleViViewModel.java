package made.sub3.modules.mainactivity.telefragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.ContentValues;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import made.sub3.BuildConfig;
import made.sub3.ItemDetail;

public class TeleViViewModel extends ViewModel {
    private  MutableLiveData<List<ItemDetail>> data = new MutableLiveData<>();

    LiveData<List<ItemDetail>> getData() {
        return data;
    }

    void setData() {
        data = new MutableLiveData<>();
        final String url = "https://api.themoviedb.org/3/discover/tv?api_key="+ BuildConfig.TMDB_API_KEY+"&language=en-US";
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<ItemDetail> itemDetails = new ArrayList<>();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject item = list.getJSONObject(i);
                        ItemDetail itemDetail = new ItemDetail(item, "tv");
                        itemDetails.add(itemDetail);

                        ContentValues mContentValues = new ContentValues();


                    }
                    data.postValue(itemDetails);
                } catch (Exception e) {
                    Log.e("Exception", e.toString());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    void setSearchData(String query) {
        data = new MutableLiveData<>();
        final String url = "https://api.themoviedb.org/3/search/tv?api_key="
                + BuildConfig.TMDB_API_KEY
                + "&language=en-US&query="
                + query;
        System.out.println(url);
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<ItemDetail> itemDetails = new ArrayList<>();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject item = list.getJSONObject(i);
                        ItemDetail itemDetail = new ItemDetail(item, "tv");
                        itemDetails.add(itemDetail);

                    }
                    data.postValue(itemDetails);
                } catch (Exception e) {
                    Log.e("Exception", e.toString());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
