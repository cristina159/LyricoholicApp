package com.example.test.adapter;
import  com.example.test.model.*;
import  com.example.test.*;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SongsAdapter extends BaseAdapter{

	private Activity context;
	ArrayList<Song> songs;
	
	public SongsAdapter(Activity context){
		
		this.context = context;
		songs = new ArrayList<Song>();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return songs.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return songs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View element;
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater)context.getLayoutInflater();//context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			element = inflater.inflate(R.layout.row_songs, null);
			
			SongsHolder tag = new SongsHolder();
			tag.songsName = (TextView) element.findViewById(R.id.songName);
			tag.songsImage = (ImageView) element.findViewById(R.id.songImage);
			
			element.setTag(tag);
			
		}
		else element = convertView;
		
		SongsHolder tag = (SongsHolder) element.getTag();
		tag.songsName.setText(songs.get(position).getArtistName() + " - " + songs.get(position).getTitle());
		tag.songsImage.setImageResource(songs.get(position).getImageResource());
		
				
		return element;
	}//reciclare
	
	public void deleteSong (Song delSong){
		
		songs.remove(delSong);
		this.notifyDataSetChanged();
	}
	

	public void addSong (Song newSong)
	{
		//Song newSong = new Song(nume);
	    songs.add (newSong);
	    // acesta functie determina adaptorul sa ceara listei sa reafiseze continutul
	    this.notifyDataSetChanged();
	}
}
