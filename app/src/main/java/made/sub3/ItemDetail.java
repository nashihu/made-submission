package made.sub3;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "detail")
public class ItemDetail {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String desc_or_title;
    private String this_title = "";
    private String rating;
    private String voters;
    private String release_date;
    private String duration_or_id_tmdb;
    private String backdrop_url_or_poster_url;
    private String genres_or_type;
    private String poster_url_for_detail_activity;
    private String activity_type = "";
    private String id_tmdb = "";

    @Ignore
    public ItemDetail(JSONObject jsonObject, String type) {
        desc_or_title = rating = voters = release_date = duration_or_id_tmdb = backdrop_url_or_poster_url = genres_or_type = "";
        try {
            if (type.equals("tv")) {
                this.desc_or_title = jsonObject.getString("name");
                this.duration_or_id_tmdb = String.valueOf(jsonObject.getInt("id"));
                this.backdrop_url_or_poster_url = jsonObject.getString("poster_path");
                this.genres_or_type = type;
            } else {
                this.desc_or_title = jsonObject.getString("title");
                this.duration_or_id_tmdb = String.valueOf(jsonObject.getInt("id"));
                this.backdrop_url_or_poster_url = jsonObject.getString("poster_path");
                this.genres_or_type = type;
            }
        } catch (JSONException e) {
            Log.e("JSONExc", e.toString());
        }
    }

    @Ignore
    public ItemDetail() {
        desc_or_title = rating = voters = release_date = duration_or_id_tmdb = backdrop_url_or_poster_url = genres_or_type = "";
    }

    @Ignore
    public ItemDetail(String desc_or_title, String rating, String voters, String release_date, String duration_or_id_tmdb, String backdrop_url_or_poster_url, String genres_or_type, String poster_url_for_detail_activity, String this_title, String activity_type, String id_tmdb) {
        this.desc_or_title = desc_or_title;
        this.rating = rating;
        this.voters = voters;
        this.release_date = release_date;
        this.duration_or_id_tmdb = duration_or_id_tmdb;
        this.backdrop_url_or_poster_url = backdrop_url_or_poster_url;
        this.genres_or_type = genres_or_type;
        this.poster_url_for_detail_activity = poster_url_for_detail_activity;
        this.this_title = this_title;
        this.activity_type = activity_type;
        this.id_tmdb = id_tmdb;
    }

    public ItemDetail(int id, String desc_or_title, String rating, String voters, String release_date, String duration_or_id_tmdb, String backdrop_url_or_poster_url, String genres_or_type, String poster_url_for_detail_activity, String this_title, String activity_type, String id_tmdb) {
        this.id = id;
        this.desc_or_title = desc_or_title;
        this.rating = rating;
        this.voters = voters;
        this.release_date = release_date;
        this.duration_or_id_tmdb = duration_or_id_tmdb;
        this.backdrop_url_or_poster_url = backdrop_url_or_poster_url;
        this.genres_or_type = genres_or_type;
        this.poster_url_for_detail_activity = poster_url_for_detail_activity;
        this.this_title = this_title;
        this.activity_type = activity_type;
        this.id_tmdb = id_tmdb;
    }

    @Ignore
    public ItemDetail(int id, String title, String photo_url, String desc) {
        this.id = id;
        this.desc_or_title = desc;
        this.poster_url_for_detail_activity = photo_url;
        this.rating = "";
        this.voters = "";
        this.release_date = "";
        this.duration_or_id_tmdb = "";
        this.backdrop_url_or_poster_url = "";
        this.genres_or_type = "";
        this.this_title = title;
        this.activity_type = "";
        this.id_tmdb = "";
    }

    public String getId_tmdb() {
        return id_tmdb;
    }

    public void setId_tmdb(String id_tmdb) {
        this.id_tmdb = id_tmdb;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public String getThis_title() {
        return this_title;
    }

    public void setThis_title(String this_title) {
        this.this_title = this_title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_url_for_detail_activity() {
        return poster_url_for_detail_activity;
    }

    public void setPoster_url_for_detail_activity(String poster_url_for_detail_activity) {
        this.poster_url_for_detail_activity = poster_url_for_detail_activity;
    }

    public String getGenres_or_type() {
        return genres_or_type;
    }

    public void setGenres_or_type(String genres_or_type) {
        this.genres_or_type = genres_or_type;
    }

    public String getDesc_or_title() {
        return desc_or_title;
    }

    public void setDesc_or_title(String desc_or_title) {
        this.desc_or_title = desc_or_title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVoters() {
        return voters;
    }

    public void setVoters(String voters) {
        this.voters = voters;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getDuration_or_id_tmdb() {
        return duration_or_id_tmdb;
    }

    public void setDuration_or_id_tmdb(String duration_or_id_tmdb) {
        this.duration_or_id_tmdb = duration_or_id_tmdb;
    }

    public String getBackdrop_url_or_poster_url() {
        return backdrop_url_or_poster_url;
    }

    public void setBackdrop_url_or_poster_url(String backdrop_url_or_poster_url) {
        this.backdrop_url_or_poster_url = backdrop_url_or_poster_url;
    }

    public String toStringg() {
        return "id: " + id + "\r\ndesc: " + desc_or_title + "\r\ntitle: " + this_title + "\r\nrating: " + rating
                + "\r\nvoters " + voters + "\r\nrelease date: " + release_date
                + "\r\nduration: " + duration_or_id_tmdb + "\r\nbackdrop url: " + backdrop_url_or_poster_url
                + "\r\ngenre: " + genres_or_type + "\r\nposter url: " + poster_url_for_detail_activity
                + "type: " + activity_type +"\r\bid tmdb: "+id_tmdb;
    }
}
