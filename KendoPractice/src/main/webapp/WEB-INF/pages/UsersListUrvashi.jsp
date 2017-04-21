<!-- saved from url=(0022)http://internet.e-mail -->
<!DOCTYPE html>
<html>
<head>
    <title>Demo Application</title>
    <link href="https://bootswatch.com/cerulean/bootstrap.min.css" rel="stylesheet" />
    <link href="https://bootswatch.com/assets/css/custom.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
    
    <link href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.css" rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/bootstrap.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
	
	<script src="http://vitalets.github.io/checklist-model/checklist-model.js"></script>
	<script
	src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/angular-route.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>
	<script src="https://angular-file-upload.appspot.com/js/ng-file-upload-shim.js"></script>
	<script src="https://angular-file-upload.appspot.com/js/ng-file-upload.js"></script>
	
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/DemoAppUrvashi.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadServiceUrvashi.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Services/UserServiceUrvashi.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Directive/FileUploadDirectiveUrvashi.js"></script>
	
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseControllerUrvashi.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Controller/UserControllerUrvashi.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/ModalInstanceControllerUrvashi.js"></script>
	
    
</head>
<body ng-app="DemoUrvashi" ng-controller="userControllerUrvashi as ctrl"  ng-init="ctrl.getListOfUser()">
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a href="UsersList.html" class="navbar-brand">Demo Application</a>
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="UsersListUrvashi">Manage Users</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h2>Manage Users</h2>
                <hr />
                <a class="btn btn-info" href="addUserUrvashi">Add new</a>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-lg-12">

                <table class="table table-striped table-hover table-responsive">
                    <thead>
                        <tr>
                            <th>Full name</th>
                            <th>Email </th>
                            <th>Date Of Birth</th>
                            <th>Phone number</th>
                            <th>Technology</th>
                            <th>Address</th>
                            <th>User Image</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="user in userList">
                            <td>{{user.username}}</td>
                            <td>{{user.email}}</td>
                            <td>{{user.dateOfBirth}}</td>
                            <td>{{user.phone}}</td>
                            <td>{{user.technology}}</td>
                            <td>{{user.address}}</td>
                            <td><img src="${pageContext.request.contextPath}/resources/image_urvashi/{{user.image}}" class="img-thumbnail" style="width: 50px;height: 50px;" /></td>
                            <td><a class="btn btn-primary" href="addUserUrvashi?id={{user.id}}">Edit</a> &nbsp; <button class="btn" ng-click="ctrl.deleteuserById(user.id)">Delete</button></td>
                        </tr> <!-- onEdit(id : any) {
        	if(id != null) {
        		this.$window.location.href="/config/customize-widget?widgetId="+id;
        	}
        } -->
        
                      
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html> 