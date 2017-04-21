<!DOCTYPE html>
<html>
<head>
    <title>Demo Application</title>
    <link href="${pageContext.request.contextPath}/resources/content/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/content/custom.min.css" rel="stylesheet" />
    
    <script src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/bootstrap.min.js"></script>  
	<script src="${pageContext.request.contextPath}/resources/app/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-chart.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/app/Demo/DemoAppChandni.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/UserChandniService.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseChandniController.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/UserDashboardChandniController.js"></script>
    
</head>
<body ng-app="DemoChandni">
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a href="chandni-user-list" class="navbar-brand">Demo Application</a>
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="chandni-user-list">Manage Users</a>
                    </li>
                    <li>
                        <a href="chandni-charts">Statistics</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container" ng-controller="userDashboardChandniController as ctrl" ng-init="ctrl.usersList()">
    	<div class="row">
            <div class="col-lg-12">
                <h2>Statistics</h2>
                 <hr />
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
              <div class="panel panel-primary">
      			<div class="panel-heading">Users per city (Pie Chart)</div>
      			<div class="panel-body">     
      				<canvas id="pieChartUser" class="chart chart-pie" chart-data="pieChartUserData" chart-labels="pieChartUserLabel" chart-options="pieChartUserOption" height="240">
					</canvas>
				</div>
    		  </div>
            </div>
            <div class="col-lg-6">
             <div class="panel panel-primary">
      			<div class="panel-heading">Users per city (Bar Chart)</div>
      			<div class="panel-body">     
      				<canvas id="barChartUser" class="chart chart-bar" chart-data="barChartUserData" chart-labels="barChartUserLabel" chart-series="barChartUserSeries" chart-colors="colors" chart-options="barChartUserOption" height="240">
					</canvas>
				</div>
    		  </div>
            </div>
        </div>
    </div>
</body>
</html> 