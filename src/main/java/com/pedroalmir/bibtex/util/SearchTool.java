/**
 * 
 */
package com.pedroalmir.bibtex.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pedroalmir.bibtex.model.SearchEngineExecutionModel;
import com.pedroalmir.bibtex.model.SearchEngineResultModel;

/**
 * @author Pedro Almir
 */
public class SearchTool {
	/** Web driver */
	private WebDriver driver;
	
	/**
	 * Default constructor
	 */
	public SearchTool() {
		/* Create a new instance of the html unit driver */
		this.driver = new HtmlUnitDriver();
	}

	/**
	 * @param executionModel
	 * @return
	 */
	public ArrayList<SearchEngineResultModel> execute(SearchEngineExecutionModel executionModel){
		List<String> list = Arrays.asList(executionModel.getSearchEngines());
		ArrayList<SearchEngineResultModel> result = new ArrayList<SearchEngineResultModel>();
		
		if(list.contains("IEEE")){
			result.addAll(this.searchIEEE(executionModel));
		}
		if(list.contains("ACM")){
			result.addAll(this.searchACM(executionModel));
		}
		if(list.contains("Scholar")){
			result.addAll(this.searchScholar(executionModel));
		}
		if(list.contains("ScienceDirect")){
			result.addAll(this.searchScienceDirect(executionModel));
		}
		
		return result;
	}
	
	/**
	 * @param executionModel
	 * @return
	 */
	private ArrayList<SearchEngineResultModel> searchScienceDirect(SearchEngineExecutionModel executionModel){
		/* Go to ScienceDirect digital library page */
		this.driver.get("http://www.sciencedirect.com/");
		
		WebElement query = this.driver.findElement(By.id("qs_all"));
		query.clear(); query.sendKeys(executionModel.getSearch());
		this.driver.findElement(By.id("submit_search")).click();
		
		WebDriverWait wait = new WebDriverWait(this.driver, 5);
		wait.until(ExpectedConditions.titleContains("Results"));
		
		List<WebElement> articleList = this.driver.findElement(By.className("articleList")).findElements(By.className("detail"));
		ArrayList<SearchEngineResultModel> results = new ArrayList<SearchEngineResultModel>();
		
		for(WebElement article : articleList){
			WebElement titleElement = article.findElement(By.className("title")).findElement(By.tagName("h2")).findElement(By.tagName("a"));
			
			String title = titleElement.getText();
			String link = titleElement.getAttribute("href");
			String year = "N/A";
			
			results.add(new SearchEngineResultModel(title, "ACM Digital Library", year, link));
		}
		return results;
	}
	
	/**
	 * @param executionModel
	 * @return
	 */
	private ArrayList<SearchEngineResultModel> searchScholar(SearchEngineExecutionModel executionModel){
		/* Go to Google Scholar page */
		driver.get("http://scholar.google.com.br/scholar?hl=pt-BR&q=" + executionModel.getSearch().replaceAll(" ", "+"));
		
		List<WebElement> papers = driver.findElements(By.className("gs_rt"));
		ArrayList<SearchEngineResultModel> results = new ArrayList<SearchEngineResultModel>();
		
		for(WebElement paper : papers){
			WebElement titleElement = paper.findElement(By.tagName("a"));
			
			String title = titleElement.getText();
			String link = titleElement.getAttribute("href");
			String year = "N/A";
			
			results.add(new SearchEngineResultModel(title, "Google Scholar", year, link));
		}
		return results;
	}
	
	/**
	 * @param executionModel
	 * @return
	 */
	private ArrayList<SearchEngineResultModel> searchACM(SearchEngineExecutionModel executionModel){
		/* Go to ACM digital library page */
		this.driver.get("http://dl.acm.org/");
		
		WebElement query = this.driver.findElement(By.name("query"));
		query.clear(); query.sendKeys(executionModel.getSearch());
		this.driver.findElement(By.name("Go")).click();
		
		WebDriverWait wait = new WebDriverWait(this.driver, 5);
		wait.until(ExpectedConditions.titleContains("Results"));
		
		List<WebElement> links = this.driver.findElements(By.tagName("a"));
		ArrayList<SearchEngineResultModel> results = new ArrayList<SearchEngineResultModel>();
		for(WebElement l : links){
			if(l.getAttribute("href").startsWith("http://dl.acm.org/citation.cfm?id=")){
				String title = l.getText();
				String link = l.getAttribute("href");
				String year = l.findElement(By.xpath("..")).findElement(By.xpath("..")).findElement(By.xpath("..")).findElements(By.tagName("tr")).get(1).getText().split(" ")[1];
				results.add(new SearchEngineResultModel(title, "ACM Digital Library", year, link));
			}
		}
		return results;
	}
	
	/**
	 * @param executionModel
	 * @return
	 */
	private ArrayList<SearchEngineResultModel> searchIEEE(SearchEngineExecutionModel executionModel){
		/* Go to IEEE digital library page */
		driver.get("http://ieeexplore.ieee.org/search/searchresult.jsp?newsearch=true&queryText=" + executionModel.getSearch().replaceAll(" ", "+"));
		
		WebElement resultList = driver.findElement(By.className("Results"));
		List<WebElement> papers = resultList.findElements(By.className("noAbstract"));
		
		ArrayList<SearchEngineResultModel> results = new ArrayList<SearchEngineResultModel>();
		for(WebElement paper : papers){
			WebElement detail = paper.findElement(By.className("detail"));
			WebElement titleElement = detail.findElement(By.tagName("h3")).findElement(By.tagName("a"));
			
			String title = titleElement.getText();
			String link = titleElement.getAttribute("href");
			String year = detail.getText().split("Publication Year:")[1].trim().split(" ")[0].trim().substring(0, 4);
			
			results.add(new SearchEngineResultModel(title, "IEEE Digital Library", year, link));
		}
		return results;
	}
}
