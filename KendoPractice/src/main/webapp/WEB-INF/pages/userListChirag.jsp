<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>TatvaSoft_CoCoNet</title>
<!-- Bootstrap Core CSS -->
<script src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/angular-route.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-chart.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>	
	<link href="https://bootswatch.com/cerulean/bootstrap.min.css" rel="stylesheet" />
    <link href="https://bootswatch.com/assets/css/custom.min.css" rel="stylesheet" />
    <link href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.css" rel="stylesheet" />
    <script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/DemoAPP.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/UserServiceChirag.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Directive/FileUploadChirag.js"></script>     
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadServiceChirag.js"></script>     
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/IFileUploadServiceChirag.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseController.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/UserPopupControllerChirag.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/UserControllerChirag.js"></script>
</head>
<body>
<body ng-app="Demo">
   <div class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a href="${pageContext.request.contextPath}/userChirag/dashboard" class="navbar-brand">User Dashboard</a>
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/userChirag/userList">Manage Users</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container" ng-controller="userControllerChirag as ctrl">
        <div class="row">
            <div class="col-lg-12">
                <h2>Manage Users</h2>
                <hr />
                <div class="alert alert-success" ng-show="status != undefined && status != '' && status !='conflict' && status != null">
                  <strong>Success!</strong> <span ng-show="status == 'save'">User created successfully.</span>
                  <span ng-show="status == 'update'">User updated successfully.</span>
                  <span ng-show="status == 'delete'">User deleted successfully.</span>
				</div>
				<div class="alert alert-danger" ng-show="status != undefined && status != '' && status !='success' && status !='delete' && status !='save' && status !='update' && status != null">
					<strong>ERROR! </strong><span ng-show="status == 'conflict'">User is already exist.</span>
				</div>
                <a href="${pageContext.request.contextPath}/userChirag/addUser" class="btn btn-info"
						style="float: right; margin: 10px;">Add User</a>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4">
                <input type="text" ng-model="search" class="form-control" placeholder="Search">
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-lg-12">
            	<table class="table table-striped table-hover table-responsive">
                    <thead>
                        <tr>
                            <th style="min-width:110px">
                              <a href="#" ng-click="sortType = 'fname'; sortReverse = !sortReverse" class="no-text-decoration">
        						Full name
        						<span ng-show="sortType == 'fname' && !sortReverse">&#x2193;</span>
						        <span ng-show="sortType == 'fname' && sortReverse">&#x2191;</span>
						      </a>
                            </th>
                             <th style="min-width:110px">
                              <a href="#" ng-click="sortType = 'gender'; sortReverse = !sortReverse" class="no-text-decoration">
        						Gender
        						<span ng-show="sortType == 'gender' && !sortReverse">&#x2193;</span>
						        <span ng-show="sortType == 'gender' && sortReverse">&#x2191;</span>
						      </a>
                            </th>
                            <th style="min-width:110px">
                              <a href="#" ng-click="sortType = 'emailid'; sortReverse = !sortReverse" class="no-text-decoration">
        						Email
        						<span ng-show="sortType == 'emailid' && !sortReverse">&#x2193;</span>
						        <span ng-show="sortType == 'emailid' && sortReverse">&#x2191;</span>
						      </a>
                            </th>
                            <th style="min-width:110px">
                              <a href="#" ng-click="sortType = 'birthdate'; sortReverse = !sortReverse" class="no-text-decoration">
        						Date Of Birth
        						<span ng-show="sortType == 'birthdate' && !sortReverse">&#x2193;</span>
						        <span ng-show="sortType == 'birthdate' && sortReverse">&#x2191;</span>
						      </a>
                            </th>
                            <th style="min-width:120px">
                              <a href="#" ng-click="sortType = 'phone'; sortReverse = !sortReverse" class="no-text-decoration">
        						Phone number
        						<span ng-show="sortType == 'phone' && !sortReverse">&#x2193;</span>
						        <span ng-show="sortType == 'phone' && sortReverse">&#x2191;</span>
						      </a>
                            </th>
                            <th style="min-width:110px">
	                            <a href="#" ng-click="sortType = 'hobbies'; sortReverse = !sortReverse" class="no-text-decoration">
	        						Hobbies
	        						<span ng-show="sortType == 'hobbies' && !sortReverse">&#x2193;</span>
							        <span ng-show="sortType == 'hobbies' && sortReverse">&#x2191;</span>
							    </a>
                            </th>
                            <th style="min-width:110px">
                               <a href="#" ng-click="sortType = 'city'; sortReverse = !sortReverse" class="no-text-decoration">
	        						City
	        						<span ng-show="sortType == 'city' && !sortReverse">&#x2193;</span>
							        <span ng-show="sortType == 'city' && sortReverse">&#x2191;</span>
							    </a>
                            </th>
                            <th style="min-width:110px">
                               <a href="#" ng-click="sortType = 'address'; sortReverse = !sortReverse" class="no-text-decoration">
	        						Address
	        						<span ng-show="sortType == 'address' && !sortReverse">&#x2193;</span>
							        <span ng-show="sortType == 'address' && sortReverse">&#x2191;</span>
							    </a>
							</th>
                            <th style="min-width:110px">User Image</th>
                            <th style="min-width:110px">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                       <tr ng-repeat="user in ctrl.userMasterList.slice(((currentPage-1)*itemsPerPage), ((currentPage)*itemsPerPage)) | orderBy:sortType:sortReverse | filter: search">
								<td>{{user.fname}}</td>
								<td>{{ctrl.getGender(user.gender)}}</td>
								<td>{{user.emailid}}</td>
								<td>{{user.birthdate | date:'dd-MM-yyyy'}}</td>
								<td>{{user.phone}}</td>
								<td>{{user.hobbies}}</td>
								<td>{{user.city}}</td>
								<td>{{user.address}}</td>
								<td><img ng-show='user.imageFilePath == ""' ng-src="${pageContext.request.contextPath}/resources/images_chandni/default.png" class="img-thumbnail" style="width: 50px;height: 50px;" />
								<img ng-show='user.imageFilePath!=null' ng-src="${pageContext.request.contextPath}/resources/images/{{user.imageFilePath}}" class="img-thumbnail" style="width: 50px;height: 50px;" /></td>
								<td><a class="btn btn-primary btn-xs" href="#" ng-click="ctrl.onEdit(user.userid)">Edit</a>&nbsp;<a
									class="btn btn-danger btn-xs" href="#" ng-click="ctrl.onDelete(user.userid)">Delete</a></td>
						</tr>
						<tr ng-show="(ctrl.userMasterList| filter:search).length == 0">
        					<td colspan="9" style="text-align: center;font-weight: bold;">
            					No record to display               
        					</td>
    					</tr>
                    </tbody>
                </table>
                <div class="text-center">
            		 <pagination total-items="totalItems" ng-model="currentPage" max-size="maxSize" class="pagination-sm" boundary-links="true" rotate="false" num-pages="numPages" items-per-page="itemsPerPage"></pagination>
          		</div>	
            </div>
        </div>
    </div>
</body>
</html>

