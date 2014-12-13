package com.example.test;

import java.util.ArrayList;


import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;

import com.example.test.adapter.*;

import android.app.Activity;
import android.content.Intent;
import android.net.NetworkInfo.DetailedState;
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
	boolean isPressed;
	MusixMatch mm = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mm = new MusixMatch("a16c65500d72a8f47240c87c3c091929");
           	 	
        OnClickListener searchBtnListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
            	 //SECOND WINDOW
            	 EditText eText = (EditText) findViewById(R.id.editTextLyrics);
            	 lyrics =  eText.getText().toString();
            	 //clear foundSongs -
            	 //ca mearga ce are andreea trebuie scos ca merge doar la a doua apasare de buton;
            	 //so daca faci clear tocmai vei sterge ce a pus andreea din api
            	 //foundSongs.clear();
            	 
            	//call api and put the results into foundSongs list
            	 new LongOperation().execute("");
            	 
            	 //LongOperation op =  new LongOperation();
            	 //op.execute("");
            	 //while((AsyncTask.Status )op.getStatus() != AsyncTask.Status.FINISHED )
            	
            	 /*String photoName = "ic_launcher";
            	 int imageID = getResources().getIdentifier(photoName, "drawable", getPackageName());
            	 foundSongs.add (new Song("Always 1", "Versuri 1","Nume album 1", "Nume artist 1", 1, imageID));
            	 foundSongs.add (new Song("Always 2", "Versuri 2","Nume album 2", "Nume artist 2", 2, imageID));
            	 foundSongs.add (new Song("Always 3", "Versuri 3","Nume album 3", "Nume artist 3", 3, imageID));
            	 foundSongs.add (new Song("Always 4", "Versuri 4","Nume album 4", "Nume artist 4", 4, imageID));
            	 foundSongs.add (new Song("Always 5", "Versuri 5","Nume album 5", "Nume artist 5", 5, imageID));
            	 foundSongs.add (new Song("Always 6", "Versuri 6","Nume album 6", "Nume artist 6", 6, imageID));
            	 */
            	if (foundSongs.size() > 1){
            		startActivity(new Intent(getApplicationContext(), SongsListWindow.class));
            	}
            	else if(foundSongs.size() == 1){
            		startActivity(new Intent(getApplicationContext(), DetailsWindow.class));
            	}
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
				 System.out.println("exceptie");
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
			 System.out.println(" --!!--" + lyrics_rsp.getLyricsBody());
			
			String photoName = "ic_launcher";
        	int imageID = getResources().getIdentifier(photoName, "drawable", getPackageName());
			Song recv_song = new Song(track_rsp.getTrack().getTrackName(), lyrics_rsp.getLyricsBody(), track_rsp.getTrack().getAlbumName(), track_rsp.getTrack().getArtistName(), track_rsp.getTrack().getTrackId(), imageID);
			 
			for (Song s : foundSongs)
				if (s.getTrackId() != recv_song.getTrackId())
					foundSongs.add(recv_song);
			
			if (foundSongs.size() == 0)
				foundSongs.add(recv_song);   	 	
       	 	
	     	
			return null;
		}
    }
}
