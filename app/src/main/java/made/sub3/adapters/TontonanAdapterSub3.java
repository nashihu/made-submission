package made.sub3.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import made.sub3.ItemDetail;
import made.sub3.R;
import made.sub3.modules.detailactivity.DetailActivity;

public class TontonanAdapterSub3 extends RecyclerView.Adapter<TontonanAdapterSub3.ViewHolder> {


    private final List<String> id_images = new ArrayList<>();
    private final List<String> title_tontonans = new ArrayList<>();
    private final List<String> id_tmdbs = new ArrayList<>();
    private String type;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tontonan_item, viewGroup, false);
        this.context = viewGroup.getContext();
        return new ViewHolder(view);
    }

    public void setData(List<ItemDetail> itemDetails) {
        title_tontonans.clear();
        id_images.clear();
        type = "";
        notifyItemRangeChanged(0, 0);
        if (itemDetails.size() > 0) {
            for (int i = 0; i < itemDetails.size(); i++) {
                title_tontonans.add(itemDetails.get(i).getDesc_or_title());
                id_images.add(itemDetails.get(i).getBackdrop_url_or_poster_url());
                id_tmdbs.add(itemDetails.get(i).getDuration_or_id_tmdb());
            }
            type = itemDetails.get(0).getGenres_or_type();
        }
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String id_image = id_images.get(position);
        StringBuilder url = new StringBuilder();
        url = url.append("https://image.tmdb.org/t/p/original").append(id_image);
        final String title_tontonan = title_tontonans.get(position);

        holder.title_tontonan.setText(title_tontonan);
        final ProgressBar progprog = holder.tontonan_progress;
        Picasso.get().load(url.toString()).into(holder.id_image_holder, new Callback() {
            @Override
            public void onSuccess() {
                progprog.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        final int z = holder.getAdapterPosition();
        holder.tontonan_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailActivity.class)
                        .putExtra("id_tmdb", id_tmdbs.get(z))
                        .putExtra("type", type)
                        .putExtra("title",title_tontonan));
            }
        });

    }

    @Override
    public int getItemCount() {
        return id_images.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView id_image_holder;
        final TextView title_tontonan;
        final LinearLayout tontonan_item;
        final ProgressBar tontonan_progress;

        ViewHolder(View view) {
            super(view);
            id_image_holder = view.findViewById(R.id.rv_iv_tontonan);
            title_tontonan = view.findViewById(R.id.rv_tv_tontonan);
            tontonan_item = view.findViewById(R.id.LL_item);
            tontonan_progress = view.findViewById(R.id.tontonan_progress_bar);
        }

    }
}
