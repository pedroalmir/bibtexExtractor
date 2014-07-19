/**
 * 
 */
package com.pedroalmir.bibtex.model;

import java.util.Arrays;

/**
 * @author Pedro Almir
 *
 */
public class SearchEngineExecutionModel {
	
	private String search;
	private String[] searchEngines;
	private String fromYear;
	private String toYear;
	
	/**
	 * Default constructor
	 */
	public SearchEngineExecutionModel() {
		
	}
	
	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}
	/**
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}
	/**
	 * @return the searchEngines
	 */
	public String[] getSearchEngines() {
		return searchEngines;
	}
	/**
	 * @param searchEngines the searchEngines to set
	 */
	public void setSearchEngines(String[] searchEngines) {
		this.searchEngines = searchEngines;
	}

	/**
	 * @return the fromYear
	 */
	public String getFromYear() {
		return fromYear;
	}

	/**
	 * @param fromYear the fromYear to set
	 */
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	/**
	 * @return the toYear
	 */
	public String getToYear() {
		return toYear;
	}

	/**
	 * @param toYear the toYear to set
	 */
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchEngineExecutionModel [search=" + search + ", searchEngines=" + Arrays.toString(searchEngines)
				+ ", fromYear=" + fromYear + ", toYear=" + toYear + "]";
	}
}
