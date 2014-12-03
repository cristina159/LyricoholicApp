package com.example.test.model;
// Alecs - complete
public class Song {
	    private String title, thumbnailUrl;
	    private int imageID;
		private int year;
		
		public Song(){this.title = "";}
	    public Song(String name, int imageID, String thumbnailUrl, int year){
	    	this.title = name;
	    	this.imageID = imageID;
	    	this.thumbnailUrl = thumbnailUrl;
	    	this.year = year;
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

		public String getThumbnailUrl() {
			return thumbnailUrl;
		}

		public void setThumbnailUrl(String thumbnailUrl) {
			this.thumbnailUrl = thumbnailUrl;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

	    public int getImageResource(){
	    	return this.imageID;
	    }
	    
	    public void setImageResource(int imageID){
	    	 this.imageID = imageID;
	    }
}
