package com.example.test;
import io.socket.SocketIO;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.model.Song;
public class DetailsWindow extends Activity{

	private RatingBar ratingBar;
	Song song;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	  
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        
          if(MainActivity.foundSongs.size() > 1)
        	  song = SongsListWindow.songDetails;
          else
        	  if(MainActivity.foundSongs.size() == 1) //one song found
        		  song = MainActivity.foundSongs.get(0);
          
          
          TextView textTrackName = (TextView) findViewById(R.id.textTrackName);
          String text ="<font color=#000000>Track Name: </font> <font color=#0000ff>" + song.getTitle() + "</font>";
          textTrackName.setText(Html.fromHtml(text));
                   
	      TextView textArtist = (TextView) findViewById(R.id.textArtistName);
	      text = "<font color=#000000>Artist/Band Name: </font> <font color=#0000ff>" + song.getArtistName() + "</font>";
	      textArtist.setText(Html.fromHtml(text));
	     
          TextView textAlbum = (TextView) findViewById(R.id.textAlbumName);
          text = "<font color=#000000>Album Name: </font> <font color=#0000ff>" + song.getAlbumName() + "</font>";
          textAlbum.setText(Html.fromHtml(text));
          
          TextView textLyrics = (TextView) findViewById(R.id.textLyrics);
	      textLyrics.setText(song.getLyrics());
	      textLyrics.setMovementMethod(new ScrollingMovementMethod());
	      
	      
	      addListenerOnRatingBar();

    }
    
    public void addListenerOnRatingBar() {
    	 
    	ratingBar = (RatingBar) findViewById(R.id.ratingBar);

    	ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
    		
    		public void onRatingChanged(RatingBar ratingBar, float rating,	boolean fromUser) {
     
                try {  
                	SocketIO socket = ConnectionTask.getSocket();
                	JSONObject json = new JSONObject();           
					json.putOpt("artist", song.getArtistName());
	                json.putOpt("song", song.getTitle());
					json.putOpt("rating", rating);  
					socket.emit("user rating", json);
				} catch (Exception e) {
					Toast.makeText(getBaseContext(), "Error sending rating", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
    			Toast.makeText(getBaseContext(), "Rating " + String.valueOf(rating) + " sent", Toast.LENGTH_LONG).show();
    		}
    	});
    	
    }  
}
