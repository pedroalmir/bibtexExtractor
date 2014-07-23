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
	private String url;
	private int resultsPerSearchEngine;
	
	/**
	 * Default constructor
	 */
	public SearchEngineExecutionModel() {
		this.resultsPerSearchEngine = 50;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchEngineExecutionModel [search=" + search + ", searchEngines=" + Arrays.toString(searchEngines) + "]";
	}

	/**
	 * @return the resultsPerSearchEngine
	 */
	public int getResultsPerSearchEngine() {
		return resultsPerSearchEngine;
	}

	/**
	 * @param resultsPerSearchEngine the resultsPerSearchEngine to set
	 */
	public void setResultsPerSearchEngine(int resultsPerSearchEngine) {
		this.resultsPerSearchEngine = resultsPerSearchEngine;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
