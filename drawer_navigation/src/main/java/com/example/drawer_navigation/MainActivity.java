package com.example.drawer_navigation;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//www.101apps.co.za
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LOG_TAG";
    private DrawerLayout drawerLayout;
    private ListView listView;

    private ActionBarDrawerToggle drawerToggle;

    private CharSequence drawerTitle;
    private CharSequence title;
    private String[] listItemsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // gets the action bar title
        title = drawerTitle = getTitle();

        // initialize drawer's list of items
        // get the array for the list
        listItemsArray = getResources().getStringArray(R.array.quotes_array_list);

        // get the drawer's layout'
        drawerLayout = findViewById(R.id.drawer_layout);

        // get the list view
        listView = findViewById(R.id.left_drawer);

        // set up the adapter
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, listItemsArray));

        // set listener for the list item clicks
        listView.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon so we can use it to open and close the drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //links the activity and the drawer. The drawer icon will also animate (small on open & big on close)
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                // set title back to app title
                getSupportActionBar().setTitle(title);
                // make sure the menu is rebuilt to reflect any changes
                // creates call to onPrepareOptionsMenu()
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerTitle);
                // make sure the menu is rebuilt to reflect any changes
                // creates call to onPrepareOptionsMenu()
                supportInvalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);

//        we're creating the activity for the first time so set default selected list item
        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call supportInvalidateOptionsMenu()
    * to rebuild the menu to reflect any changes*/
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(listView);
        menu.findItem(R.id.dummy_action_item).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "selected item: " + String.valueOf(item.getItemId()));

        // The action bar home/up action opens and closes the drawer.
        // ActionBarDrawerToggle will take care of this selection
        if (drawerToggle.onOptionsItemSelected(item)) {
//            the app icon button has been pressed so return true and don't process any further
            return true;
        }
        // it's the overflow menu item
        switch(item.getItemId()) {
            case R.id.dummy_menu_item:
                showToast("Dummy Menu item selected");
                return true;
            case R.id.dummy_action_item:
                showToast("Dummy Action item selected");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // show Toast message when one of the menu items has been clicked
    private void showToast(String toastMessage) {
        Toast toast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);
        TextView text = (TextView) toast.getView().findViewById(android.R.id.message);
        text.setTextColor(Color.YELLOW);
//        text.setTextSize(20);
//        text.setGravity(Gravity.CENTER_HORIZONTAL);
        toast.show();
    }

    /** The click listener for ListView in the navigation drawer
     * - gets the selected item's position in the list */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    // use the selected item's position to create and display the fragment
    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new QuotationFragment();
        Bundle args = new Bundle();
       /* pass on selected list item's position so new fragment contains the selected quote*/
        args.putInt(QuotationFragment.ARG_QUOTE_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
//        swop the fragments
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        listView.setItemChecked(position, true);
        setTitle(listItemsArray[position]);
        drawerLayout.closeDrawer(listView);
    }

    // sets the title in the action bar
    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        getSupportActionBar().setTitle(this.title);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after the activity has been restored
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggle
        drawerToggle.onConfigurationChanged(newConfig);
    }

}