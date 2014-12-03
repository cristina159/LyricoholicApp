package com.example.test;

import java.util.ArrayList;
import com.example.test.adapter.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.adapter.SongsAdapter;
import com.example.test.model.*;

import android.view.View.OnClickListener;

import com.example.test.R;

public class MainActivity extends Activity {

	String lyrics; //searched lyrics
	static ArrayList<Song> foundSongs = new ArrayList<Song>(); //API results 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        OnClickListener searchBtnListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
            	 //SECOND WINDOW
            	 EditText eText = (EditText) findViewById(R.id.editTextLyrics);
            	 lyrics =  eText.getText().toString();
            	 
            	 foundSongs.clear();
            	 
            	 //call api and put the results into foundSongs list
            	 foundSongs.add(new Song("Always 1", R.drawable.ic_launcher, "", 1990));
            	 foundSongs.add(new Song("Always 2", R.drawable.ic_launcher, "", 1991));
            	 foundSongs.add(new Song("Always 3", R.drawable.ic_launcher, "", 1992));
                 foundSongs.add(new Song("Always 4", R.drawable.ic_launcher, "", 1993));
                 foundSongs.add(new Song("Always 5", R.drawable.ic_launcher, "", 1990));
                 foundSongs.add(new Song("Always 6", R.drawable.ic_launcher, "", 1991));
                 foundSongs.add(new Song("Always 7", R.drawable.ic_launcher, "", 1992));
                 foundSongs.add(new Song("Always 8", R.drawable.ic_launcher, "", 1993));
                 foundSongs.add(new Song("Always 9", R.drawable.ic_launcher, "", 1990));
                 foundSongs.add(new Song("Always 10", R.drawable.ic_launcher, "", 1991));
                 foundSongs.add(new Song("Always 11", R.drawable.ic_launcher, "", 1992));
                 foundSongs.add(new Song("Always 12", R.drawable.ic_launcher, "", 1993));
                 foundSongs.add(new Song("Always 13", R.drawable.ic_launcher, "", 1991));
                 foundSongs.add(new Song("Always 14", R.drawable.ic_launcher, "", 1992));
                 foundSongs.add(new Song("Always 15", R.drawable.ic_launcher, "", 1993));
                 
            	if (foundSongs.size() > 1)
            		startActivity(new Intent(getApplicationContext(), SongsListWindow.class));
            	else if(foundSongs.size() == 1)
            		startActivity(new Intent(getApplicationContext(), DetailsWindow.class));
            	else  
            		Toast.makeText(getBaseContext(), "Not found!", Toast.LENGTH_LONG).show();
            	
            }
        };
        
        Button btn =(Button) findViewById(R.id.searchButton);
        btn.setOnClickListener(searchBtnListener);
    }

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onPostResume();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	getMenuInflater().inflate(R.layout.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
