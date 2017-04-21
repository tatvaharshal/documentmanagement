<!-- saved from url=(0022)http://internet.e-mail -->
<!DOCTYPE html>
<html>
<head>
    <title>_HarShaL_ Application</title>
    <link href="https://bootswatch.com/cerulean/bootstrap.min.css" rel="stylesheet" />
    <link href="https://bootswatch.com/assets/css/custom.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-chart.js"></script>
	<script src="${pageContext.request.contextPath}/resources/scripts/angular-route.js"></script>
	<script src="https://angular-file-upload.appspot.com/js/ng-file-upload-shim.js"></script>
	<script src="https://angular-file-upload.appspot.com/js/ng-file-upload.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/app/Demo/HarshalAPP.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadService_harshal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/UserService_harshal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Directive/FileUploadDirective_harshal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseController_harshal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/UserController_harshal.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/ModalInstanceController_harshal.js"></script>
	
	<script>localStorage._url = "${pageContext.request.contextPath}";</script>
</head>
<body ng-app="HarshalDemo">
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a href="${pageContext.request.contextPath}/user_harshal/userDashboard_harshal" class="navbar-brand">_HarShaL_ Dashboard</a>
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/user_harshal/usersList_harshal">Manage Users</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container" ng-controller="userController_harshal as ctrl" ng-init="ctrl.userList()">
        <div class="row">
            <div class="col-lg-12">
                <h2>Manage Users</h2>
                <hr />
                <div class="alert alert-success" ng-show="status != undefined && status != '' && status != null">
                  <strong>Success!</strong> <span ng-show="status == 'save'">User created successfully.</span>
                  <span ng-show="status == 'update'">User updated successfully.</span>
                  <span ng-show="status == 'delete'" ng-init="ctrl.userList()">User deleted successfully.</span>
				</div>	
                <hr />
                <a class="btn btn-info" href="${pageContext.request.contextPath}/user_harshal/userForm_harshal"">Add new</a>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-lg-12">

                <table class="table table-striped table-hover table-responsive" >
                    <thead>
                        <tr>
                            <th>Full name</th>
                            <th>Email </th>
                            <th>Date Of Birth</th>
                            <th>Phone number</th>
                            <th>Hobbies</th>
                            <th>City</th>
                            <th>Address</th>
                            <th>User Image</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<tr ng-repeat="user in users">
                            <td>{{user.fullname}}</td>
                            <td>{{user.email}}</td>
                            <td>{{user.dateofbirth | date:'dd-MM-yyyy'}}</td>
                            <td>{{user.mobilenumber}}</td>
                            <td>{{user.hobby}}</td>
                            <td>{{user.city}}</td>
                            <td>{{user.address}}</td>
                            <td><img src="${pageContext.request.contextPath}/resources/images_harshal/{{user.profileimage}}" class="img-thumbnail" style="width: 50px;height: 50px;" alt="userImage" /></td>
                            <td><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/user_harshal/editUserForm_harshal?userId={{user.id}}">Edit</a> &nbsp; <a class="btn btn-sm btn-danger" ng-click="ctrl.userDelete(user.id);" >Delete</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html> 