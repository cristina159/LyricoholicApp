package com.example.test;

import java.util.ArrayList;
import java.util.List;

import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.model.Song;

public class MainActivity extends Activity {

	static String lyrics;
	static ArrayList<Song> foundSongs = new ArrayList<Song>();
	static boolean isPressed;
	static MusixMatch musicMatch = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        musicMatch = new MusixMatch("a16c65500d72a8f47240c87c3c091929");
           	 	
        OnClickListener searchBtnListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
            	 EditText eText = (EditText) findViewById(R.id.editTextLyrics);
            	 lyrics =  eText.getText().toString();
            	 new MusicMatchTask().execute("");
            	
            }
        };
        new ConnectionTask().execute();
        Button btn =(Button) findViewById(R.id.searchButton);
        btn.setOnClickListener(searchBtnListener);
    }
	
	@Override
	public void onStop() {
		super.onStop();
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	getMenuInflater().inflate(R.layout.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    protected class MusicMatchTask extends AsyncTask<String, Void, String> {
    	
    	private String errorMessage = "No lyrics found";
    	
		@Override
		protected String doInBackground(String... arg0) {
			foundSongs.clear();
			
			List<Track> tracks = null;
			try{
				tracks = musicMatch.searchTracks(null, null, null, lyrics, "DESC", "DESC", 1, 15, true);
			}catch (Exception e){
				errorMessage = "Encountered a problem";
				e.printStackTrace();
				return null;
			}
			
			for (Track track : tracks) {
				TrackData trackData = track.getTrack();
				Lyrics lyricResponse = null;
				try{
					lyricResponse = musicMatch.getLyrics(track.getTrack().getTrackId());
				}catch(Exception e){
					continue;
				}
				String photoName = "ic_launcher";
	        	int imageID = getResources().getIdentifier(photoName, "drawable", getPackageName());
	        	Song recv_song = new Song(trackData.getTrackName(), lyricResponse.getLyricsBody(), 
	        			trackData.getAlbumName(), trackData.getArtistName(), trackData.getTrackId(), imageID);
				foundSongs.add(recv_song); 	
			}
			return null;
		}
		
	    @Override
	    protected void onPostExecute(String result) {
			
			if (foundSongs.size() > 1){
	    		startActivity(new Intent(getApplicationContext(), SongsListWindow.class));
	    	}
	    	else if(foundSongs.size() == 1){
	    		startActivity(new Intent(getApplicationContext(), DetailsWindow.class));
	    	}
	    	else  
	    		Toast.makeText(getBaseContext(), errorMessage, Toast.LENGTH_LONG).show();
	    }
    }
    
}
