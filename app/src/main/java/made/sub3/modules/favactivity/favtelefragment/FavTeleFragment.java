package made.sub3.modules.favactivity.favtelefragment;

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

import made.sub3.adapters.FavAdapter;
import made.sub3.ItemDetail;
import made.sub3.R;
import made.sub3.database.AppDatabase;
import made.sub3.database.AppExecutors;

public class FavTeleFragment extends Fragment {
    FavAdapter favAdapter;
    RecyclerView recyclerView;
    private AppDatabase mDb;

    public FavTeleFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArrayList<String> id_images = new ArrayList<>();
        id_images.add(String.valueOf(R.drawable.movie_bohemian));
        id_images.add(String.valueOf(R.drawable.movie_alita));
        ArrayList<String> titles = new ArrayList<>();
        titles.add("bohemian rhapsody");
        titles.add("alita angel");
        favAdapter = new FavAdapter(id_images, titles);
        mDb = AppDatabase.getInstance(getContext());
        return inflater.inflate(R.layout.fragment_tele_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager verticalLayout
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.rv_tele_fav);
        recyclerView.setLayoutManager(verticalLayout);
        recyclerView.setAdapter(favAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<ItemDetail> persons = mDb.itemDao().loadAllTontonanItem("tv");
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            favAdapter.setData(persons);
                        }
                    });
                }

            }
        });
    }
}
