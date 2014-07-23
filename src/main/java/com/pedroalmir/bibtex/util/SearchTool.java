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
		if(executionModel.getUrl() != null && !executionModel.getUrl().isEmpty()){
			this.driver.get(executionModel.getUrl());
		}else{
			this.driver.get("http://www.sciencedirect.com/");
			
			WebElement query = this.driver.findElement(By.id("qs_all"));
			query.clear(); query.sendKeys(executionModel.getSearch());
			this.driver.findElement(By.id("submit_search")).click();
			
			WebDriverWait wait = new WebDriverWait(this.driver, 5);
			wait.until(ExpectedConditions.titleContains("Results"));
		}
		
		List<WebElement> articleList = this.driver.findElement(By.className("articleList")).findElements(By.className("detail"));
		ArrayList<SearchEngineResultModel> results = new ArrayList<SearchEngineResultModel>();
		
		for(WebElement article : articleList){
			WebElement titleElement = article.findElement(By.className("title")).findElement(By.tagName("h2")).findElement(By.tagName("a"));
			
			String title = titleElement.getText();
			String link = titleElement.getAttribute("href");
			String year = "N/A";
			
			results.add(new SearchEngineResultModel(title, "Science Direct", year, link));
		}
		
		/*for(SearchEngineResultModel result : results){
			this.driver.get(result.getLink());
		}*/
		
		return results;
	}
	
	/**
	 * @param executionModel
	 * @return
	 */
	private ArrayList<SearchEngineResultModel> searchScholar(SearchEngineExecutionModel executionModel){
		this.driver = new HtmlUnitDriver(true);
		
		/* Go to Google Scholar page */
		if(executionModel.getUrl() != null && !executionModel.getUrl().isEmpty()){
			this.driver.get(executionModel.getUrl());
		}else{
			this.driver.get("http://scholar.google.com.br/scholar?hl=pt-BR&q=" + executionModel.getSearch().replaceAll(" ", "+"));
		}
		
		WebDriverWait wait = new WebDriverWait(this.driver, 5);
		List<WebElement> papers = driver.findElements(By.className("gs_rt"));
		ArrayList<SearchEngineResultModel> results = new ArrayList<SearchEngineResultModel>();
		
		int aux = executionModel.getResultsPerSearchEngine(), count = 0;
		while(true){
			for(WebElement paper : papers){
				WebElement titleElement = paper.findElement(By.tagName("a"));
				
				String title = titleElement.getText();
				String link = titleElement.getAttribute("href");
				String year = "N/A";
				
				paper.findElement(By.xpath("..")).findElement(By.linkText("Citar")).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Importe para o BibTeX")));
				
				
				this.driver.get(this.driver.findElement(By.id("gs_citd")).findElement(By.linkText("Importe para o BibTeX")).getAttribute("href"));
				String bibtex = this.driver.getPageSource();
				
				results.add(new SearchEngineResultModel(title, "Google Scholar", year, link, bibtex));
			}
			
			aux -= 10; count += 10;
			if(aux <= 0){
				break;
			}else{
				driver.get("http://scholar.google.com.br/scholar?hl=pt-BR&start=" + count + "&q=" + executionModel.getSearch().replaceAll(" ", "+"));
				papers = driver.findElements(By.className("gs_rt"));
			}
		}
		
		this.driver = new HtmlUnitDriver();
		return results;
	}
	
	/**
	 * @param executionModel
	 * @return
	 */
	private ArrayList<SearchEngineResultModel> searchACM(SearchEngineExecutionModel executionModel){
		/* Go to ACM digital library page */
		if(executionModel.getUrl() != null && !executionModel.getUrl().isEmpty()){
			this.driver.get(executionModel.getUrl());
		}else{
			this.driver.get("http://dl.acm.org/");
			
			WebElement query = this.driver.findElement(By.name("query"));
			query.clear(); query.sendKeys(executionModel.getSearch());
			this.driver.findElement(By.name("Go")).click();
			
			WebDriverWait wait = new WebDriverWait(this.driver, 5);
			wait.until(ExpectedConditions.titleContains("Results"));
		}
		
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
		
		String idPaper = null;
		for(SearchEngineResultModel result : results){
			this.driver.get(result.getLink());
			idPaper = this.driver.findElement(By.linkText("BibTeX")).getAttribute("href").split("id=")[1].split("&")[0]; 
			this.driver.get("http://dl.acm.org/exportformats.cfm?id=" + idPaper + "&expformat=bibtex&_cf_containerId=theformats_body&_cf_nodebug=true&_cf_nocache=true&_cf_rc=2");
			result.setBibtex(this.driver.findElement(By.id(idPaper)).getText());
		}
		
		return results;
	}
	
	/**
	 * @param executionModel
	 * @return
	 */
	private ArrayList<SearchEngineResultModel> searchIEEE(SearchEngineExecutionModel executionModel){
		/* Go to IEEE digital library page */
		if(executionModel.getUrl() != null && !executionModel.getUrl().isEmpty()){
			this.driver.get(executionModel.getUrl());
		}else{
			String query = executionModel.getSearch().replaceAll(" ", "+");
			if(executionModel.getResultsPerSearchEngine() > 0 && executionModel.getResultsPerSearchEngine() < 100){
				query += "&rowsPerPage=" + executionModel.getResultsPerSearchEngine() + "&pageNumber=1&resultAction=ROWS_PER_PAGE";
			}else if(executionModel.getResultsPerSearchEngine() > 100){
				query += "&rowsPerPage=100&pageNumber=1&resultAction=ROWS_PER_PAGE";
			}else{
				query += "&rowsPerPage=25&pageNumber=1&resultAction=ROWS_PER_PAGE";
			}
			this.driver.get("http://ieeexplore.ieee.org/search/searchresult.jsp?newsearch=true&queryText=" + query);
		}
		
		
		WebElement resultList = this.driver.findElement(By.className("Results"));
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
		
		for(SearchEngineResultModel result : results){
			this.driver.get(result.getLink());
			
			this.driver.findElement(By.id("action-download-document-citations")).findElement(By.tagName("a")).click();
			this.driver.findElement(By.id("download-bibtex")).click();
			this.driver.findElement(By.id("download_citations_form")).submit();
			
			for(String window : this.driver.getWindowHandles()){
				this.driver.switchTo().window(window);
				System.out.println(this.driver.getPageSource());
			}
		}
		return results;
	}
}
