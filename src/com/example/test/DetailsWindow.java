package com.example.test;
import com.example.test.model.*;

import android.app.Activity;

import android.os.Bundle;
import android.widget.TextView;
public class DetailsWindow extends Activity{

	Song song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_details);
          
          TextView textView = (TextView) findViewById(R.id.textViewSong);
          if(MainActivity.foundSongs.size() > 1)
        	  song = SongsListWindow.songDetails;
          else
        	  if(MainActivity.foundSongs.size() == 1) //one song found
        		  song = MainActivity.foundSongs.get(0);
          
	      textView.setText("Details for "+song.getTitle());
	       		
    }
}
