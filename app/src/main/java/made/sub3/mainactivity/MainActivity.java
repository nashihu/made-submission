package made.sub3.mainactivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import made.sub3.MyAdapter;
import made.sub3.NullFragment;
import made.sub3.R;
import made.sub3.mainactivity.moviefragment.MovieFragment;
import made.sub3.mainactivity.telefragment.TeleViFragment;

public class MainActivity extends AppCompatActivity {
    public static final String ACTION_VIEW = "android.intent.action.VIEW";
    public static final String EXTRA_ID = "wadigidi";
    int selectedFragment = 0;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tab_movie:
                    setCheckable(navView,true);
                    selectedFragment = R.id.tab_movie;
                    fragment = new MovieFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    clearTab();
                    return true;
                case R.id.tab_tv:
                    setCheckable(navView,true);
                    selectedFragment = R.id.tab_tv;
                    fragment = new TeleViFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    clearTab();
                    return true;
                case R.id.tab_home:
                    selectedFragment = 0;
                    setCheckable(navView,false);
                    fragment = new NullFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    clearTab();
                    showTab();
            }
            return false;
        }
    };

    public static void setCheckable(BottomNavigationView view, boolean checkable) {
        final Menu menu = view.getMenu();
        for(int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setCheckable(checkable);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menuu,menu);
        menuInflater.inflate(R.menu.main_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager)  MainActivity.this.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = null;
        if(searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if(searchView != null && searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    if(selectedFragment == 0) {
                        int selected = tabLayout.getSelectedTabPosition();
                        if(selected==0) {
                            searchMovie(s);
                        } else if (selected==1) {
                            searchTV(s);
                        } else {
                            Toast.makeText(MainActivity.this, "silakan pilih tab movie/tv terlebih dahulu", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        if(selectedFragment == R.id.tab_movie) {
                            searchMovie(s);
                        } else if (selectedFragment == R.id.tab_tv) {
                            searchTV(s);
                        }
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }

        return super.onCreateOptionsMenu(menu);
    }

    void searchMovie(String s) {
        setCheckable(navView,true);
        selectedFragment = R.id.tab_movie;
        Bundle bundlee = new Bundle();
        bundlee.putString("query",s);
        fragment = new MovieFragment();
        fragment.setArguments(bundlee);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                .commit();
        clearTab();
    }

    void searchTV(String s) {
        setCheckable(navView,true);
        Bundle bundlee = new Bundle();
        bundlee.putString("query",s);
        selectedFragment = R.id.tab_tv;
        fragment = new TeleViFragment();
        fragment.setArguments(bundlee);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                .commit();
        clearTab();
    }

    TabLayout tabLayout;
    ViewPager viewPager;
    BottomNavigationView navView;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        setCheckable(navView,false);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
    }

    void clearTab() {
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), 0));
        tabLayout.removeAllTabs();
    }

    void showTab() {
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.title_movie)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.title_tv)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_language_setting) {
            startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
        }
        return super.onOptionsItemSelected(item);
    }
}
