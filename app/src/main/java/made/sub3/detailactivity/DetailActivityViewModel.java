package made.sub3.detailactivity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import made.sub3.BuildConfig;
import made.sub3.ItemDetail;
import made.sub3.R;
import made.sub3.mainactivity.MainActivity;

public class DetailActivityViewModel extends ViewModel {
    private final MutableLiveData<ItemDetail> dataDetail = new MutableLiveData<>();

    void setData(final Context context, String url, final String type, final String curr_lang, final String code) {

        final ItemDetail itemDetail = new ItemDetail();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    String desc_tmdb = response.optString("overview");
                    if (desc_tmdb.equals("") && curr_lang.equals("Indonesia")) {

                        String url = "https://api.themoviedb.org/3/" + type + "/" + code + "?api_key="+ BuildConfig.TMDB_API_KEY+"&language=en-US";
                        RequestQueue requestQueue2 = Volley.newRequestQueue(context);
                        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (response != null) {
                                    String desc_tmdb2 = response.optString("overview");
                                    desc_tmdb2 = context.getString(R.string.lang_not_available) + "\r\n\r\n" + desc_tmdb2;
                                    itemDetail.setDesc_or_title(desc_tmdb2);


                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        requestQueue2.add(jsonObjectRequest2);

                    }
                    itemDetail.setDesc_or_title(desc_tmdb);
                    String vote_tmdb = response.optString("vote_average");
                    itemDetail.setRating(vote_tmdb);
                    String voter_tmdb = response.optString("vote_count");
                    itemDetail.setVoters(voter_tmdb);
                    String release_date_tmdb, duration_tmdb;
                    if (type.equals("tv")) {
                        release_date_tmdb = response.optString("first_air_date");
                        duration_tmdb = response.optJSONArray("episode_run_time").optString(0);

                    } else {
                        release_date_tmdb = response.optString("release_date");

                        duration_tmdb = response.optString("runtime");
                    }
                    itemDetail.setRelease_date(release_date_tmdb);
                    itemDetail.setDuration_or_id_tmdb(duration_tmdb);
                    final String backdrop_tmdb = response.optString("backdrop_path");
                    final String backdrop_url = "https://image.tmdb.org/t/p/original" + backdrop_tmdb;
                    itemDetail.setBackdrop_url_or_poster_url(backdrop_url);
                    String poster_url = "https://image.tmdb.org/t/p/original" + response.optString("poster_path");
                    itemDetail.setPoster_url_for_detail_activity(poster_url);

                    try {
                        JSONArray genres = response.getJSONArray("genres");
                        StringBuilder genrezz = new StringBuilder();
                        for (int i = 0; i < genres.length(); i++) {
                            String genre0 = genres.getJSONObject(i).getString("name");
                            if (i != (genres.length() - 1)) {
                                genrezz.append(genre0).append(", ");
                            } else {
                                genrezz.append(genre0);
                            }
                        }
                        itemDetail.setGenres_or_type(genrezz.toString());
                    } catch (JSONException e) {
                        //asdfasdf
                    }

                    dataDetail.postValue(itemDetail);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO bikinin alert dialog di sini ato di observer activity
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                context.startActivity(new Intent(context, MainActivity.class));


            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    LiveData<ItemDetail> getData() {
        return dataDetail;
    }
}
