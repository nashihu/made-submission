package made.sub3.favactivity.favmoviefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import made.sub3.FavAdapter;
import made.sub3.ItemDetail;
import made.sub3.R;
import made.sub3.database.AppDatabase;
import made.sub3.database.AppExecutors;

public class FavMovieFragment extends Fragment {
    FavAdapter favAdapter;
    RecyclerView recyclerView;
    private AppDatabase mDb;

    public FavMovieFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArrayList<String> id_images = new ArrayList<>();
        id_images.add(String.valueOf(R.drawable.movie_alita));
        id_images.add(String.valueOf(R.drawable.movie_bohemian));
        ArrayList<String> titles = new ArrayList<>();
        titles.add("alita");
        titles.add("bohem");
        favAdapter = new FavAdapter(id_images, titles);
        mDb = AppDatabase.getInstance(getContext());
        return inflater.inflate(R.layout.fragment_movie_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_movie_fav);

    }

    @Override
    public void onResume() {
        super.onResume();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<ItemDetail> items = mDb.itemDao().loadAllTontonanItem("movie");
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            favAdapter.setData(items);
                        }
                    });
                }

            }
        });
        LinearLayoutManager verticalLayout
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(verticalLayout);
        recyclerView.setAdapter(favAdapter);
    }


}
