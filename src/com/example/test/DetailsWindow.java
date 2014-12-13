package com.example.test;
import com.example.test.model.*;

import android.app.Activity;
import android.graphics.Color;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
public class DetailsWindow extends Activity{

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
          String text ="<font color=#000000>Track Name: </font> <font color=#0000ff>"+song.getTitle()+"</font>";
          textTrackName.setText(Html.fromHtml(text));
                   
	      TextView textArtist = (TextView) findViewById(R.id.textArtistName);
	      text = "<font color=#000000>Artist/Band Name: </font> <font color=#0000ff>"+song.getArtistName()+"</font>";
	      textArtist.setText(Html.fromHtml(text));
	     
          TextView textAlbum = (TextView) findViewById(R.id.textAlbumName);
          text = "<font color=#000000>Album Name: </font> <font color=#0000ff>"+song.getAlbumName()+"</font>";
          textAlbum.setText(Html.fromHtml(text));
          
          TextView textGenre = (TextView) findViewById(R.id.textMusicGenre);
          text = "<font color=#000000>Genre: </font> <font color=#0000ff>"+" Genul melodiei"+"</font>";
	      textGenre.setText(Html.fromHtml(text));//+ song.getGenre());
	    
          
          TextView textLyrics = (TextView) findViewById(R.id.textLyrics);
	      textLyrics.setText(song.getLyrics()+"\n"+song.getLyrics());
	      textLyrics.setMovementMethod(new ScrollingMovementMethod());	       		
    }
}
