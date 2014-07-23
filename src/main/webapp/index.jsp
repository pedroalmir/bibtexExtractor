<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
    	
    	<meta name="author" content="Pedro Almir">
    	<meta name="description" content="Bibtex Extractor">
    	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico">
    	
		<!-- Bootstrap core CSS -->
	    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	    <!-- Custom styles for this template -->
	    <link href="${pageContext.request.contextPath}/css/sticky-footer-navbar.css" rel="stylesheet">
	
	    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    
	    <!-- ========================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
	    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/jquery.easing.1.3.js"></script>
	    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/docs.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/ejs_production.js"></script>
	    
	    <style type="text/css">
	    	.truncate {
	    		max-width: 100px;
			  	white-space: nowrap;
			  	overflow: hidden;
			  	text-overflow: ellipsis;
			}
	    </style>
	</head>
	<body>
		<!-- Fixed navbar -->
    	<div class="navbar navbar-default navbar-fixed-top">
      		<div class="container">
        		<div class="navbar-header">
          			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	           			<span class="sr-only">Toggle navigation</span>
	            		<span class="icon-bar"></span>
	            		<span class="icon-bar"></span>
	            		<span class="icon-bar"></span>
          			</button>
          			<a class="navbar-brand" href="${pageContext.request.contextPath}/">Bibtex Extractor</a>
        		</div>
        		<div class="collapse navbar-collapse">
          			<ul class="nav navbar-nav">
            			<li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
            			<li><a href="http://github.com/pedroalmir/bibtex-extractor">Git Repository</a></li>
            			<li><a href="mailto:petrus.cc@gmail.com">Contact</a></li>
          			</ul>
        		</div><!--/.nav-collapse -->
      		</div>
    	</div>
    	<!-- Begin page content -->
    	<div class="container">
      		<div class="page-header" style="margin: 0px 0 20px;">
        		<h2>Bibtex Extractor</h2>
      		</div>
      		<p class="lead text-justify">Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
      		Fusce quis tellus metus. Aliquam id ligula at erat consequat hendrerit. Nulla facilisi. 
      		Maecenas vitae elit ut turpis egestas tempor. Praesent imperdiet rhoncus purus quis sagittis. 
      		Nulla ultricies eros id augue dictum tincidunt. Nunc egestas aliquet fringilla.</p>
      		<div class="row">
      			<div class="col-md-6">
					<div class="panel panel-primary">
						<div class="panel-heading">
					    	<h3 class="panel-title">Search</h3>
					  	</div>
					  	<div class="panel-body">
					  		<form action="">
					  			<div class="col-sm-12">
						  			<div class="form-group">
								    	<label for="search">String*</label>
								    	<input type="text" class="form-control" id="search" placeholder="What do you want search?" value="Fuzzy System Framework">
								  	</div>
								  	<div class="form-group">
								  		<label for="searchEngines">Search engines*</label>
										<select multiple class="form-control" id="searchEngines">
											<option value="IEEE">IEEE Digital Library</option>
											<option value="ACM">ACM Library</option>
											<option value="Scholar" selected="selected">Google Scholar</option>
											<option value="ScienceDirect">Science Direct</option>
										</select>
									</div>
								  	<!--div class="form-group">
								    	<label for="resultsPerSearchEngine">Results per Search Engine*</label>
								    	<input type="number" class="form-control" id="resultsPerSearchEngine" placeholder="" value="50">
								  	</div-->
							  	</div>
						  		<!-- div class="col-sm-6">
						  			<div class="form-group">
							    		<label for="start">From</label>
							    		<input type="date" class="form-control" id="fromYear">
							    	</div>
						  		</div>
							  	<div class="col-sm-6">
						  			<div class="form-group">
							    		<label for="end">To</label>
							    		<input type="date" class="form-control" id="toYear">
							    	</div>
						  		</div-->
						  		<div class="col-sm-12">
						  			<div class="form-group" style="margin-top: 28px;">
										<button id="doit" type="button" class="btn btn-success pull-right" style="width: 100%">
											DoIt !
										</button>
									</div>
						  		</div>
					  		</form>
					  	</div>
					</div>
      			</div>
      			<div class="col-md-6">
      				<div class="panel panel-primary">
						<div class="panel-heading">
					    	<h3 class="panel-title">Export based on a URL</h3>
					  	</div>
					  	<div class="panel-body">
					  		<form action="">
					  			<div class="col-sm-12">
					  				<div class="form-group">
								    	<label for="searchURL">String*</label>
								    	<input type="text" class="form-control" id="searchURL" placeholder="What do you want search?" value="Fuzzy System Framework">
								  	</div>
								  	
						  			<div class="form-group">
								  		<label for="searchEnginesURL">Search engines*</label>
										<select class="form-control" id="searchEnginesURL">
											<option value="IEEE">IEEE Digital Library</option>
											<option value="ACM">ACM Library</option>
											<option value="Scholar">Google Scholar</option>
											<option value="ScienceDirect" selected="selected">Science Direct</option>
										</select>
									</div>
									
									<div class="form-group">
								    	<label for="url">URL*</label>
								    	<input type="text" class="form-control" id="url" placeholder="URL" value="http://www.sciencedirect.com/science?_ob=ArticleListURL&_method=tag&sort=d&sisrterm=&_ArticleListID=-611123689&view=c&_chunk=0&count=1000&_st=&refsource=&md5=b5a790f86015c3e8a26217dd4f7b97a6&searchtype=a&originPage=rslt_list&filterType=">
								  	</div>
									
								  	<div class="form-group">
								  		<button id="exportBasedOnURL" type="button" class="btn btn-primary pull-right" style="width: 100%">
											Export !
										</button>
									</div>
							  	</div>
					  		</form>
					  	</div>
					</div>
      			</div>
      		</div>
      		<div class="row">
      			<div class="col-sm-12">
					<div id="resultsPanel" class="panel panel-default" style="display: none;">
						<div class="panel-heading">
							<h3 class="panel-title"><strong>Results</strong></h3>
						</div>
						<div class="panel-body">
							<div class="col-sm-12">
								<table class="table table-bordered">
									<caption><p><strong id="tableCaption">Fuzzy System Framework</strong></p></caption>
						      		<thead>
						        		<tr>
						          			<th class="">#</th>
						          			<th class="col-sm-6">Title</th>
						          			<th class="col-sm-3">Database</th>
						          			<th class="col-sm-2">Year</th>
						          			<th class="col-sm-2">Options</th>
						        		</tr>
						      		</thead>
	      							<tbody id="tableContent">
							      	</tbody>
							    </table>
							</div>
							<div class="col-sm-offset-9 col-sm-3">
								<button type="button" class="btn btn-primary" style="width: 100%" data-toggle="modal" data-target="#finalBibTeXModal"><span class="glyphicon glyphicon-download"></span> Export to Bibtex File</button>
							</div>
						</div>
					</div>
				</div>
      		</div>
    	</div>
    	<div id="modals"></div>
    	<div id="footer">
      		<div class="container">
        		<p class="text-muted">Developed by <a href="http://pedroalmir.com">Pedro Almir</a> © 2014</p>
      		</div>
	    </div>
	    <script type="text/javascript">
		    function parseJSON(data) {
		        return window.JSON && window.JSON.parse ? window.JSON.parse(data) : (new Function("return " + data))(); 
		    }
	
		    function sendRequest(urlToSend, dataToSend, target){
		    	$.ajax({
		    		url: urlToSend,
		    		type: 'POST',
		    		data: {json: JSON.stringify(dataToSend)},
		    		beforeSend: function(){
		    			/* Active the loader */
		    			target.button('loading');
		    		}, success: function(data, textStatus, jqXHR){
		    			var result = parseJSON(data);
		    			
		    			/* Load a template file, then render it with data */
		    			var html = new EJS({url: '${pageContext.request.contextPath}/template/resultTableContent.ejs'}).render({result: result});
		    			var modals = new EJS({url: '${pageContext.request.contextPath}/template/bibtexModal.ejs'}).render({result: result});
		    			var finalBibTex = new EJS({url: '${pageContext.request.contextPath}/template/finalBibtexModal.ejs'}).render({result: result}); 
		    			
		    			$('#modals').html(modals);
		    			$('#modals').append(finalBibTex);
		    			
		    			$('#tableContent').html(html);
		    			$('#tableCaption').text(dataToSend.search);
		    			
		    			$('#resultsPanel').show("slow","swing");
		    		}, error: function(jqXHR, textStatus, errorThrown){
		    			console.log(textStatus);
		    		}, complete: function(jqXHR, textStatus){
		    			/* Disable the loader*/
		    			target.button('reset');
		    		}
		    	});
		    }
	
		    $(document).ready(function() {
		    	$('#doit').click(function(){
		    		$('#resultsPanel').fadeOut("slow","swing");
		    		var json = {
		    				search: $('#search').val(),
		    				searchEngines: $('#searchEngines').val(),
		    			};
		    		console.log(json);
		    		sendRequest('${pageContext.request.contextPath}/search', json, $('#doit'));
		    	});
		    	$('#exportBasedOnURL').click(function(){
		    		$('#resultsPanel').fadeOut("slow","swing");
		    		
		    		var options = [];
		    		options.push($('#searchEnginesURL').val());
		    		
		    		var json = {
		    				search: $('#searchURL').val(),
		    				searchEngines: options,
		    				url: $('#url').val(),
		    			};
		    		console.log(json);
		    		sendRequest('${pageContext.request.contextPath}/search', json, $('#exportBasedOnURL'));
		    	});
		    });
	    </script>
	</body>
</html>