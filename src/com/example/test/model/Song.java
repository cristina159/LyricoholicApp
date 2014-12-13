package com.example.test.model;

public class Song {
	    
	private String title;
	private String albumName;
	private String artistName;
	private String lyrics;

	private int trackId; 
    private int imageID;
	
	
	public Song(){
		this.title = "";
	}

	public Song(String name, String lyrics, String albumName, String artistName, int trackId, int imageID){
    	this.title = name;
		this.lyrics = lyrics;
    	this.albumName = albumName;
    	this.artistName = artistName;
    	this.trackId = trackId;
    	this.imageID = imageID;
	}
	
	@Override
    public String toString ()
    {
        // acesta functie este apelata de catre ArrayAdapter pentru a transforma obiectul intr-un String ce
        // sa fie afisat in lista
        return title;
    } 
    
    public String getTitle() {
		return this.title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getArtistName(){
		return this.artistName;
	}
	
	public void setArtistName(String artistName){
		this.artistName = artistName;
	}
	
	public String getAlbumName(){
		return this.albumName;
	}
	
	public void setAlbumName(String albname){
		this.albumName = albname;
	}
	
	
	public String getLyrics(){
		return this.lyrics;
	}
	
	public void setLyrics(String lyrics){
		this.lyrics = lyrics;
	}
	
    public int getImageResource(){
    	return this.imageID;
    }
    
    public void setImageResource(int imageID){
    	 this.imageID = imageID;
    }
    
    public int getTrackId(){
    	return this.trackId;
    }
    
    public void setTrackId(int trackId){
    	 this.trackId = trackId;
    }
    
}
