package com.buckheit.joe.buckheitlab07;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.HashMap;


public class MainActivity extends ActionBarActivity
        implements NavigationFragment.OnNavigationItemSelectedListener,
        MovieListFragment.OnListItemSelectedListener
    {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private RelativeLayout mDrawer;

    private String[] mDrawerItemList;

    @Override
    public void onNavigationItemSelected(int position) {

        Intent intent;

        switch (position) {
            case 1:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(1))
                        .addToBackStack(null)
                        .commit();
                break;
            default:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new NavigationFragment())
                        .addToBackStack(null)
                        .commit();
                break;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDrawerItemList = new String[]{"Task Two - Demo","Movie List", "Movie Grid", "#", "About Joe", "Family", "Bike"};

        useRecyclerView();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, PlaceholderFragment.newInstance(50))
                    .commit();
        }

    }

    private void useRecyclerView() {
        RecyclerView mDrawerList;
        MyDrawerRecyclerViewAdapter mDrawerRecyclerViewAdapter;

        setContentView(R.layout.activity_main);
        mDrawer = (RelativeLayout) findViewById(R.id.drawer);
        mDrawerList = (RecyclerView) findViewById(R.id.drawer_list);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this));
        mDrawerRecyclerViewAdapter = new MyDrawerRecyclerViewAdapter(this, mDrawerItemList);
        mDrawerRecyclerViewAdapter.SetOnItemClickListener(new MyDrawerRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                selectItem(position);
            }
        });

        mDrawerList.setAdapter(mDrawerRecyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void selectItem(int position) {
        switch (position) {
            case 0:
                //Navigation
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new NavigationFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case 1:
                // Movie List
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, MovieListFragment.newInstance(MovieListFragment.LIST_LAYOUT))
                        .addToBackStack(null)
                        .commit();
                break;
            case 2:
                //MovieGrid
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, MovieListFragment.newInstance(MovieListFragment.GRID_LAYOUT))
                        .addToBackStack(null)
                        .commit();
                break;
            case 4:
                //About Me
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(1))
                        .addToBackStack(null)
                        .commit();
                break;
            case 5:
                //Family
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(5))
                        .addToBackStack(null)
                        .commit();
                break;
            case 6:
                //Bike
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(6))
                        .addToBackStack(null)
                        .commit();
                break;
            default:
                break;
        }

        mDrawerLayout.closeDrawer(mDrawer);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_OPTION = "argument_option";

        public static PlaceholderFragment newInstance(int option) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_OPTION, option);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Bundle arguments = getArguments();
            int option = 0;

            if (arguments != null)
                option = arguments.getInt(ARG_OPTION);

            View rootView;

            switch (option) {
                case 1:
                    //About Me
                    rootView =inflater.inflate(R.layout.fragment_aboutme, container, false);
                    break;
                case 2:
                    //ViewPager
                    rootView =inflater.inflate(R.layout.fragment_movie, container, false);
                    break;
                case 5:
                    //Family
                    rootView =inflater.inflate(R.layout.fragment_family, container, false);
                    break;
                case 6:
                    //Bike
                    rootView =inflater.inflate(R.layout.fragment_bike, container, false);
                    break;
                default:
                    rootView = inflater.inflate(R.layout.fragment_cover, container, false);
                    break;
            }

            return rootView;
        }
    }

        @Override
        public void onListItemSelected(int position, HashMap<String, ?> movie) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,MovieFragment.newInstance(movie))
                .addToBackStack(null)
                .commit();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
