<!DOCTYPE html>
<html>
<head>
<title>Demo Application</title>
<link href="https://bootswatch.com/cerulean/bootstrap.min.css"
	rel="stylesheet" />
<link href="https://bootswatch.com/assets/css/custom.min.css"
	rel="stylesheet" />
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.css"
	rel="stylesheet" />
<script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Chart.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/angular-chart.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/DemoAPP.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Interface/IEmployeeScope.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Interface/ICOCOScope.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Directive/fileModel.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadService.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Services/IFileUploadService.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Services/EmployeeService.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Services/IEmployeeService.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseController.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Controller/EmployeeController.js"></script>
<style>
.ng-touched.ng-invalid.ng-invalid-required {
	border: 1px solid red;
}
</style>
</head>
<body ng-app="Demo">
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="UsersList.html" class="navbar-brand">Demo Application</a>
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target="#navbar-main">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse" id="navbar-main">
				<ul class="nav navbar-nav">
					<li><a href="UsersList.html">Manage Users</a></li>
					<li><a href="/employeelist">Manage Employees</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container" ng-controller="employeeController">
		<div class="row">
			<div class="col-lg-12">
				<h2>Manage Employees</h2>
				<hr />
				<a class="btn btn-info" href="/addemployee">Add new</a>
			</div>
		</div>
		<br />
		<div class="row">

			<div class="col-lg-12">
				<canvas id="doughnut" class="chart chart-doughnut" chart-data="data"
					chart-labels="labels"></canvas>

				<canvas id="pie" class="chart chart-pie" chart-data="data"
					chart-labels="labels" chart-options="options"></canvas>

				<canvas id="polar-area" class="chart chart-polar-area"
					chart-data="data" chart-labels="labels" chart-options="options"></canvas>
			</div>
		</div>
	</div>
</body>
</html>