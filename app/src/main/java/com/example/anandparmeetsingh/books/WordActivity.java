package com.example.anandparmeetsingh.books;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WordActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Word>> {
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private static final String LOG_TAG = WordActivity.class.getName();
    /**
     * URL for earthquake data from the USGS dataset
     */
    public WordAdapter mAdapter;
    int counter1 = 0;
    //private static final String USGS_REQUEST_URL =
    //      "https://www.googleapis.com/books/v1/volumes?q=" + "harry";
    ListView earthquakeListView;
    ArrayList<Word> words;
    private TextView mEmptyStateTextView;
    private String mQuery = "";
    private String USGS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private String mRequestUrl = "https://www.googleapis.com/books/v1/volumes?q=";

    public void displayForTeamA() {

        EditText search = (EditText) findViewById(R.id.search_go_btn);
        search.getText().toString();
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_word);
        // Get a reference to the LoaderManager, in order to interact with loaders.
        earthquakeListView = (ListView) findViewById(R.id.list);
        words = new ArrayList<>();
        mAdapter = new WordAdapter(WordActivity.this, words);

        //earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //  @Override
        //public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        // Find the current earthquake that was clicked on
        //  Word currentBook = (Word) mAdapter.getItem(position);

        // Convert the String URL into a URI object (to pass into the Intent constructor)
        //Uri bookUri = Uri.parse(currentBook.getUrl());

        // Create a new intent to view the earthquake URI
        //Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);

        // Send the intent to launch a new activity
        //startActivity(websiteIntent);
        //}
        //});
        //mEmptyStateTextView = (TextView) findViewById(R.id.no_items_view);
        //bookListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new WordAdapter(this, new ArrayList<Word>());
        earthquakeListView.setAdapter(mAdapter);

        // Create a new adapter that takes an empty list of earthquakes as input


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface

        earthquakeListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
// Start the AsyncTask to fetch the earthquake data
        //mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        //earthquakeListView.setEmptyView(mEmptyStateTextView);
        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            //View loadingIndicator = findViewById(R.id.loading_indicator);
            //loadingIndicator.setVisibility(View.GONE);

            //Update empty state with no connection error message
            //mEmptyStateTextView.setText("No Connection");
        }

        final EditText mEditText = (EditText) findViewById(R.id.search_go_btn);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.notifyDataSetChanged();
                // Get the text from the EditText and update the mQuery value.
                mQuery = mEditText.getText().toString().replaceAll(" ", "+");
                // If it's empty don't proceed.
                if (mQuery.isEmpty()) {
                    Toast.makeText(WordActivity.this, "Nothing to search", Toast.LENGTH_SHORT).show();
                }
                // Update the mRequestUrl value with the new mQuery.
                mRequestUrl = mRequestUrl + mQuery + "&maxResults=15";
                Log.i("onQueryTextSubmit", "mRequestUrl value is: " + mRequestUrl);
                // Restart the loader.
                LoaderManager loaderManager = getLoaderManager();
                loaderManager.restartLoader(1, null, WordActivity.this);
                getLoaderManager().restartLoader(1, null, WordActivity.this);
                getLoaderManager().restartLoader(1, null, WordActivity.this);
                Log.i("onClick", "loader restarted");

                // Try to make the progress bar appear again (not working)
                //View progressBar = findViewById(R.id.progress_bar);
                //progressBar.setVisibility(View.VISIBLE);
                // Update mRequestUrl back to its original value. TODO: change it to global variable somehow.
                mRequestUrl = "https://www.googleapis.com/books/v1/volumes?q=";
                // This is what makes the ListView update with new info.
                mAdapter = new WordAdapter(WordActivity.this, new ArrayList<Word>());
                earthquakeListView.setAdapter(mAdapter);
            }
        });


    }

    public Loader<List<Word>> onCreateLoader(int i, Bundle bundle) {
        return new WordLoader(this, mRequestUrl);
    }

    /**
     * {@link } to perform the network request on a background thread, and then
     * update the UI with the list of earthquakes in the response.
     * <p>
     * AsyncTask has three generic parameters: the input type, a type used for progress updates, and
     * an output type. Our task will take a String URL, and return an EarthquakeAdapter. We won't do
     * progress updates, so the second generic is just Void.
     * <p>
     * We'll only override two of the methods of AsyncTask: doInBackground() and onPostExecute().
     * The doInBackground() method runs on a background thread, so it can run long-running code
     * (like network activity), without interfering with the responsiveness of the app.
     * Then onPostExecute() is passed the result of doInBackground() method, but runs on the
     * UI thread, so it can use the produced data to update the UI.
     */

    @Override
    public void onLoadFinished(Loader<List<Word>> loader, List<Word> earthquakes) {
        // Clear the adapter of previous earthquake data
        //View loadingIndicator = findViewById(R.id.loading_indicator);
        //loadingIndicator.setVisibility(View.GONE);
        //mEmptyStateTextView.setText(R.string.no_earthquakes);
//        mAdapter.clear();
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.

        if (earthquakes != null && !earthquakes.isEmpty()) {
            words.addAll(earthquakes);
        }
        mAdapter.notifyDataSetChanged();

      /*  words.addAll(earthquakes);
        mAdapter.notifyDataSetChanged();*/
    }

    @Override
    public void onLoaderReset(Loader<List<Word>> loader) {
        // Loader reset, so we can clear out our existing data.
//        mAdapter.clear();
    }


    /**
     * This method runs on a background thread and performs the network request.
     * We should not update the UI from a background thread, so we return a list of
     * {@link Book}s as the result.
     */

    /**
     * This method runs on the main UI thread after the background work has been
     * completed. This method receives as input, the return value from the doInBackground()
     * method. First we clear out the adapter, to get rid of earthquake data from a previous
     * query to USGS. Then we update the adapter with the new list of earthquakes,
     * which will trigger the ListView to re-populate its list items.
     */
}






