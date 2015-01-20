package com.example.test;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

import com.example.test.model.Song;
public class DetailsWindow extends Activity{

	private RatingBar ratingBar;
	Song song;
	double raiting = 0.5;
	
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
    	
    	//if rating value is changed,
    	//display the current rating value in the result (textview) automatically
    	ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
    		
    		public void onRatingChanged(RatingBar ratingBar, float rating,	boolean fromUser) {
     
    			raiting = (double)ratingBar.getRating();
    			Toast.makeText(getBaseContext(), String.valueOf(raiting), Toast.LENGTH_LONG).show();
    			//send the raiting to server
    		}
    	});
    	
    }  
}
