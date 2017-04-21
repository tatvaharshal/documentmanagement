<!DOCTYPE html>
<html>
<head>
    <title>CoCoNet DEMO</title>   
    
   	<link href="${pageContext.request.contextPath}/resources/content/bootstrap.min.css" rel="stylesheet" />
    <link href="https://bootswatch.com/assets/css/custom.min.css" rel="stylesheet" />
    <link href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.css" rel="stylesheet" />
    <script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
    
    
    <script src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-chart.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-chart.js"></script>
	<script src="${pageContext.request.contextPath}/resources/scripts/angular-route.js"></script>
	
    <script src="${pageContext.request.contextPath}/resources/app/Demo/DemoAPP.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/UserServiceChirag.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Directive/FileUploadChirag.js"></script>     
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadServiceChirag.js"></script>     
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/IFileUploadServiceChirag.js"></script> 
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseController.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/UserControllerChirag.js"></script>
    
    
    
   
</head>
<body ng-app="Demo">
<%
String userid = request.getParameter("userid");
int flag = 1;
if(userid!=null && userid!=""){
	flag = 0;
}
%>
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
    <div class="container" ng-controller="userControllerChirag as ctrl" ng-init="ctrl.initialiseEditPage(<%=userid%>)">
								
        <div class="row">
            <div class="col-lg-10">
                <div class="well bs-component">
                    <form class="form-horizontal" ng-submit="ctrl.validateFile(this.myFile) && userForm.$valid && ctrl.onSave(<%=flag%>)" name="userForm">
                        <fieldset>
                            <legend>Add or Edit User Details</legend>

							<div class="form-group">
								<div class="col-lg-2 col-lg-offset-2">
										<a href="${pageContext.request.contextPath}/resources/images/{{user.imageFilePath}}" target="_blank" ng-if="user.userid != undefined"><img src="${pageContext.request.contextPath}/resources/images/{{user.imageFilePath}}" class="img-thumbnail" /></a>
								</div>
							</div>
                            <div class="form-group">
                                <label for="txtFullname" class="col-lg-2 control-label"><span style="color:red;">*</span>Full name :</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" name="fname" ng-model="user.fname" data-ng-pattern="/^[a-zA-Z \d]+$/" id="txtFullname" placeholder="Full name" required="true" data-ng-minlength="3" data-ng-maxlength="30">
                                </div>
                                <div class="has-error"   data-ng-show="userForm.$dirty">
									<span   data-ng-show="userForm.fname.$error.required">This is a
										required field</span> <span   data-ng-show="userForm.fname.$error.minlength">Minimum
										length required is 3</span> <span   data-ng-show="userForm.fname.$invalid">Full name accept only alphabetic value with max 30 characters</span>
								</div>
                            </div>

                            <div class="form-group">
                                <label for="txtPassword" class="col-lg-2 control-label"><span style="color:red;">*</span>Password :</label>
                                <div class="col-lg-10">
                                    <input type="password" name="Password" ng-model="user.pwd" class="form-control" id="txtPassword" placeholder="Password" required="true" data-ng-minlength="8" data-ng-maxlength="20"  data-ng-pattern="/(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z])/">
                                </div>
                                <div class="has-error"   data-ng-show="userForm.$dirty">
									<span   data-ng-show="userForm.Password.$error.required">This is a
										required field</span> <span   data-ng-show="userForm.Password.$error.minlength">Minimum
										length required is 8</span> <span   data-ng-show="userForm.Password.$invalid">Password accept 8 to 20 characters, 1 Uppercase, 1 Lower cases, 1 special character should mandatory </span>
								</div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-2 control-label"><span style="color:red;">*</span>Gender :</label>
                                <div class="col-lg-10">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="gender" ng-model="user.gender" id="genderMale" value="1" checked="" required="true">
                                            Male
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="gender" ng-model="user.gender" id="genderFemale" value="2">
                                            Female
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="txtEmail" class="col-lg-2 control-label"><span style="color:red;">*</span>Email :</label>
                                <div class="col-lg-10">
                                    <input type="email" class="form-control" ng-model="user.emailid"  id="txtEmail" placeholder="Email Address"  required="true">
                                </div>
                            </div>

                          
                            
                            <div class="form-group">
                                <label for="txtDob" class="col-lg-2 control-label"><span style="color:red;">*</span>Date Of Birth :</label>
                                <div class="col-lg-10">
                                    <input type="text" id="txtDob" class="form-control" placeholder="yyyy-mm-dd" ng-model="user.birthdate"  required="true"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="txtPhoneNumber" class="col-lg-2 control-label"><span style="color:red;">*</span>Phone number :</label>
                                <div class="col-lg-10">
                                    <input type="number" id="txtPhoneNumber" ng-model="user.phone" class="form-control" placeholder="Phone number"  required="true"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="hobbies" class="col-lg-2 control-label">Hobbies :</label>
                                <div class="col-lg-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="hobbies" value="Cricket" ng-checked="hobbies.indexOf('Cricket') > -1" ng-click="ctrl.selectHobbies('Cricket')"> Cricket
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="hobbies" value="Football" ng-checked="hobbies.indexOf('Football') > -1" ng-click="ctrl.selectHobbies('Football')"> Football
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="hobbies" value="Tennis" ng-checked="hobbies.indexOf('Tennis') > -1" ng-click="ctrl.selectHobbies('Tennis')"> Tennis
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="hobbies" value="Chess" ng-checked="hobbies.indexOf('Chess') > -1" ng-click="ctrl.selectHobbies('Chess')"> Chess
                                        </label>
                                    </div>
                                </div>
                            </div>

	                            <div class="form-group">
	                                <label for="ddlCity" class="col-lg-2 control-label"><span style="color:red;">*</span>City :</label>
	                                <div class="col-lg-10">
	                                    <select class="form-control" id="ddlCity" ng-model="user.city" required>
	                                        <option value="">--Select--</option>
	                                        <option selected="selected">Ahmedabad</option>
	                                        <option>Gandhinagar</option>
	                                        <option>Rajkot</option>
	                                        <option>Mehsana</option>
	                                        <option>Surat</option>
	                                    </select>
	                                </div>
	                            </div>
	
	                            <div class="form-group">
	                                <label for="textAddress" class="col-lg-2 control-label"><span style="color:red;">*</span>Address :</label>
	                                <div class="col-lg-10">
	                                    <textarea ng-model="user.address" class="form-control" rows="3" id="textAddress"  required="true"></textarea>
	                                </div>
	                            </div>
	
	                            <div class="form-group">
	                                <label for="fileImage" class="col-lg-2 control-label"><span style="color:red;">*</span>Image :</label>
	                                <div class="col-lg-10">
	                                	<input type="file" name="file" id="fileUserImage" class="form-control" file-upload="myFile" ng-accept="image/*"  ng-required="isImageRequired">
	                                </div>
	                            </div>
	
	                            <div class="form-group">
	                                <div class="col-lg-10 col-lg-offset-2">
	                                    <button type="submit" class="btn btn-primary">Submit</button>
	                                    <a href="${pageContext.request.contextPath}/userChirag/userList" class="btn btn-default">Cancel</a>
	                                </div>
	                            </div>

                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
   
 	<script>
        $('#txtDob').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true
        });
    </script>
</body>
</html>