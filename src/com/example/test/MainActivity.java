package com.example.test;

import java.util.ArrayList;


import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;

import com.example.test.adapter.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;
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

	MusixMatch mm = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mm = new MusixMatch("a16c65500d72a8f47240c87c3c091929");
   	 	foundSongs.clear();
        
        OnClickListener searchBtnListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
            	 //SECOND WINDOW
            	 EditText eText = (EditText) findViewById(R.id.editTextLyrics);
            	 lyrics =  eText.getText().toString();
            	 
            	//call api and put the results into foundSongs list
            	 new LongOperation().execute("");
            	 
//            	 foundSongs.add(new Song("Always 1", R.drawable.ic_launcher, "", 1990));
//            	 foundSongs.add(new Song("Always 2", R.drawable.ic_launcher, "", 1991));
//            	 foundSongs.add(new Song("Always 3", R.drawable.ic_launcher, "", 1992));
//                 foundSongs.add(new Song("Always 4", R.drawable.ic_launcher, "", 1993));
//                 foundSongs.add(new Song("Always 5", R.drawable.ic_launcher, "", 1990));
//                 foundSongs.add(new Song("Always 6", R.drawable.ic_launcher, "", 1991));
//                 foundSongs.add(new Song("Always 7", R.drawable.ic_launcher, "", 1992));
//                 foundSongs.add(new Song("Always 8", R.drawable.ic_launcher, "", 1993));
//                 foundSongs.add(new Song("Always 9", R.drawable.ic_launcher, "", 1990));
//                 foundSongs.add(new Song("Always 10", R.drawable.ic_launcher, "", 1991));
//                 foundSongs.add(new Song("Always 11", R.drawable.ic_launcher, "", 1992));
//                 foundSongs.add(new Song("Always 12", R.drawable.ic_launcher, "", 1993));
//                 foundSongs.add(new Song("Always 13", R.drawable.ic_launcher, "", 1991));
//                 foundSongs.add(new Song("Always 14", R.drawable.ic_launcher, "", 1992));
//                 foundSongs.add(new Song("Always 15", R.drawable.ic_launcher, "", 1993));
                 
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
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
    
    protected class LongOperation extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... arg0) {
			Track track_rsp = null;
			 try{
				 track_rsp = mm.getMatchingTrack("Let It Be", "The Beatles");
			 }catch (Exception e){
				 
			 }
			 
			 System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!11");
			 System.out.println(track_rsp.getTrack().getTrackName());
			 System.out.println(track_rsp.getTrack().getAlbumName());
			 System.out.println(track_rsp.getTrack().getArtistName());
		
			 Lyrics lyrics_rsp = null;
			 try{
				lyrics_rsp = mm.getLyrics(track_rsp.getTrack().getTrackId());
			 }catch(Exception e){
				 
			 }
			 System.out.println("--!!--" + lyrics_rsp.getLyricsBody());
			 
			Song recv_song = new Song(track_rsp.getTrack().getTrackName(), lyrics_rsp.getLyricsBody(), track_rsp.getTrack().getAlbumName(), track_rsp.getTrack().getArtistName(), track_rsp.getTrack().getTrackId());
			 
			for (Song s : foundSongs)
				if (s.getTrackId() != recv_song.getTrackId())
					foundSongs.add(recv_song);
			
			if (foundSongs.size() == 0)
				foundSongs.add(recv_song);
			
			return null;
		}

    }
}
