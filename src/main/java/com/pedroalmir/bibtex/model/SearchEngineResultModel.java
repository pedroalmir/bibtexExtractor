/**
 * 
 */
package com.pedroalmir.bibtex.model;

/**
 * @author Pedro Almir
 *
 */
public class SearchEngineResultModel {
	
	private int id;
	private String title;
	private String database;
	private String year;
	private String link;
	private String bibtex;
	
	/**
	 * @param title
	 * @param database
	 * @param year
	 * @param link
	 */
	public SearchEngineResultModel(String title, String database, String year, String link) {
		super();
		this.title = title;
		this.database = database;
		this.year = year;
		this.link = link;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}
	/**
	 * @param database the database to set
	 */
	public void setDatabase(String database) {
		this.database = database;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the bibtex
	 */
	public String getBibtex() {
		return bibtex;
	}
	/**
	 * @param bibtex the bibtex to set
	 */
	public void setBibtex(String bibtex) {
		this.bibtex = bibtex;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchEngineResultModel [id=" + id + ", title=" + title + ", database=" + database + ", year=" + year + "]";
	}
}
