package com.example.test;
import com.example.test.model.Song;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;
import com.example.test.adapter.*;

public class SongsListWindow extends ListActivity{

	SongsAdapter songsAdapter;
	
	static Song songDetails = new Song();
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView (R.layout.activity_songs_list_view);
		
		songsAdapter = new SongsAdapter(this);
		setListAdapter(songsAdapter);
		
		// add found songs to songAdapter 
		for(int i=0;i<MainActivity.foundSongs.size();i++){
			Song iSong = MainActivity.foundSongs.get(i);
			songsAdapter.addSong(iSong);
		}
		
        registerForContextMenu(getListView()); //menu
	}
	
	@Override
    public void onListItemClick (ListView list, View v, int position, long id)
    {
        // afisam numele melodiei pe care s-a dat click folosind un Toast
		Song currentSong = (Song)songsAdapter.getItem (position);
        String strName = currentSong.getTitle();
        Toast.makeText(SongsListWindow.this, strName, Toast.LENGTH_LONG).show();
        
    }
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	  		
	  		 super.onCreateContextMenu(menu, v, menuInfo);
	  		 AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;
	  		 
	  		  // We know that each row in the adapter is a Song
	  		  songDetails =  (Song) songsAdapter.getItem(aInfo.position);
	  		 
	  		  menu.setHeaderTitle("Options for " + songDetails.getTitle());
	  		  menu.add(1, 1, 1, "Details");
	  		  menu.add(1, 2, 2, "Delete");
	  		 
	  	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		  AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
	      
	      switch(item.getItemId()){
	          case 1:{
	        	  	songDetails = (Song)songsAdapter.getItem(info.position);
	        	  		   	       		
	   	       		//open details window
	   	       		startActivity(new Intent(getApplicationContext(), DetailsWindow.class));
	             
	   	       		break;
	          }
	          case 2:{
	        	  songDetails = (Song)songsAdapter.getItem(info.position);
	        	  songsAdapter.deleteSong(songDetails);
		          
	        	  break;
		       }
	      }
 
	      return true;
	  }
}

