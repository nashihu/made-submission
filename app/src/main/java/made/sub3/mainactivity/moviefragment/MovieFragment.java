package made.sub3.mainactivity.moviefragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import made.sub3.ItemDetail;
import made.sub3.R;
import made.sub3.TontonanAdapterSub3;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private TontonanAdapterSub3 newTontonanAdapter;
    private ProgressBar progressBar;
    private final Observer<List<ItemDetail>> result = new Observer<List<ItemDetail>>() {
        @Override
        public void onChanged(@Nullable List<ItemDetail> itemDetails) {
            if (itemDetails != null) {
                newTontonanAdapter.setData(itemDetails);
                if (newTontonanAdapter.getItemCount() > 0) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        }
    };

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        newTontonanAdapter = new TontonanAdapterSub3();
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MovieViewModel model;
        progressBar = view.findViewById(R.id.movie_progress_bar);
        LinearLayoutManager verticalLayout
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_movie);
        recyclerView.setLayoutManager(verticalLayout);
        if (getActivity() != null) {
            model = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
            model.setData();
            model.getData().observe(getActivity(), result);

        }

        newTontonanAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(newTontonanAdapter);
    }
}
