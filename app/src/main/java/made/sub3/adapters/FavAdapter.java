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
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import made.sub3.ItemDetail;
import made.sub3.R;
import made.sub3.modules.detailactivity.DetailActivity;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {


    private List<String> id_images;
    private List<String> title_tontonans;
    private List<ItemDetail> itemDetails;
    private Context context;

    public FavAdapter(ArrayList<String> id_images, ArrayList<String> title_tontonans) {
        this.id_images = id_images;
        this.title_tontonans = title_tontonans;

    }

    public void setData(List<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
        id_images = new ArrayList<>();
        title_tontonans = new ArrayList<>();
        for (int i = 0; i < itemDetails.size(); i++) {
            id_images.add(itemDetails.get(i).getPoster_url_for_detail_activity());
            title_tontonans.add(itemDetails.get(i).getThis_title());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tontonan_item, viewGroup, false);
        this.context = viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String title = id_images.get(i);
        int image = R.drawable.movie_alita;
        viewHolder.title_tontonan.setText(title_tontonans.get(i));
        viewHolder.id_image_holder.setImageResource(image);
        Picasso.get().load(title).into(viewHolder.id_image_holder);
        final int z = viewHolder.getAdapterPosition();
        viewHolder.tontonan_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailActivity.class)
                .putExtra("title",itemDetails.get(z).getThis_title())
                .putExtra("id_tmdb",itemDetails.get(z).getId_tmdb())
                .putExtra("type",itemDetails.get(z).getActivity_type()));
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

        ViewHolder(View view) {
            super(view);
            id_image_holder = view.findViewById(R.id.rv_iv_tontonan);
            title_tontonan = view.findViewById(R.id.rv_tv_tontonan);
            tontonan_item = view.findViewById(R.id.LL_item);
            view.findViewById(R.id.tontonan_progress_bar).setVisibility(View.GONE);

        }

    }
}
