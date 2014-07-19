package com.pedroalmir.bibtex.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pedroalmir.bibtex.model.SearchEngineExecutionModel;
import com.pedroalmir.bibtex.model.SearchEngineResultModel;
import com.pedroalmir.bibtex.util.SearchTool;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Gson gson = new Gson();
		String json = request.getParameter("json");
		SearchEngineExecutionModel executionModel = gson.fromJson(json, SearchEngineExecutionModel.class);
		System.out.println(executionModel);
		
		/* Set content type of the response so that jQuery knows what it can expect. */
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		
		if(executionModel != null && executionModel.getSearch() != null && executionModel.getSearchEngines() != null
				&& !executionModel.getSearch().isEmpty() && executionModel.getSearchEngines().length > 0){
			/* Search tool */
			ArrayList<SearchEngineResultModel> result = new SearchTool().execute(executionModel);
			for(int i = 0; i < result.size(); i++){
				result.get(i).setId(i+1);
			}
			/* Write response body. */
			response.getWriter().write(gson.toJson(result));
		}else{
			/* Write response body. */
			response.getWriter().write(gson.toJson("Problems!"));
		}
	}
}
