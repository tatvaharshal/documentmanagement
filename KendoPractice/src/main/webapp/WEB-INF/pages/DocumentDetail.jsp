<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Document Details</title>

    <link href="${pageContext.request.contextPath}/resources/content/Document/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/bootstrap-select.css" rel="stylesheet"/>
    <%-- <link href="https://bootswatch.com/cerulean/bootstrap.min.css" rel="stylesheet" />--%>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.css" rel="stylesheet"/>

    <link href="${pageContext.request.contextPath}/resources/content/style.css" rel="stylesheet" />
    <link href="https://bootswatch.com/cerulean/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="//cdn.kendostatic.com/2014.2.716/styles/kendo.common.min.css">
    <link rel="stylesheet" href="//cdn.kendostatic.com/2014.2.716/styles/kendo.default.min.css">
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/custom.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/font-awesome.css" rel="stylesheet"/>
    <link href="http://cdn.kendostatic.com/2013.3.1119/styles/kendo.common.min.css" rel="stylesheet" type="text/css" />
    <link href="http://cdn.kendostatic.com/2013.3.1119/styles/kendo.default.min.css" rel="stylesheet" type="text/css" />
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="http://cdn.kendostatic.com/2013.3.1119/js/kendo.all.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Document/jquery-1.10.2.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Document/Kendo.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-chart.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-chart.js"></script>
    <script src="${pageContext.request.contextPath}/resources/scripts/angular-route.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Document/ng-file-upload.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Document/ng-file-upload-shim.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/DocumentListApp.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/DocumentListService.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadService.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseDocumentListController.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/DocumentPopupController.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/DocumentListController.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body ng-app="DocumentList">
<%
    String DocumentId = request.getParameter("DocumentId");
%>

<div id="wrapper">
    <header id="header1"></header>
    <section id="content">
        <div class="page-content">
            <div id="error_message" style="color:red;float:right; margin: 0 1000px 0 0";></div>
            <%--  <div id="success_message" style="color:green;float:right; margin: 0 1000px 0 0";></div>--%>
            <div class="page-padding" ng-controller="DocumentListController as ctlr" ng-init="ctlr.initialiseEditPage(<%=DocumentId%>)">
                <form  name="Documentform" ng-submit="ctlr.updateDocument()&& Documentform.$valid" name="Documentform" id="submit">
                    <div class="row">
                        <div class="col-md-12">

                            <div class="form-group">
                                <div class="row">
                                    <div class="col-md-7 col-lg-7">
                                        <h4 class="doc-header"><a href="./DocumentList"><img
                                                src="${pageContext.request.contextPath}/resources/content/Document/Images/back.png"
                                                class="doc-back-image"/></a>Document : {{doc.documentName}}
                                        </h4>
                                    </div>
                                    <div class="col-md-5 col-lg-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="input-group form-group" style="height:48px;">
                                                        <span class="input-group-btn">
                                                             <span style="height:48px;background-color:#E6E6E6;"><img src="${pageContext.request.contextPath}/resources/content/Document/Images/Tick.png" class="doc-import-button" /></span>
                                                        </span>
                                                    <button class="form-control" style="height:97%;width:100%;background-color:#F0F0F0;padding-left:55px;" type="submit" ng-disabled="Documentform.$invalid">Publish</button>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="input-group form-group" style="height:48px;">
                                                        <span class="input-group-btn">
                                                            <span style="height:48px;background-color:#E6E6E6;"><img src="${pageContext.request.contextPath}/resources/content/Document/Images/delete-icon.png" class="doc-import-button" /></span>
                                                        </span>
                                                    <input type="button"  class="form-control" style="height:97%;width:100%;background-color:#F0F0F0;padding-left:55px;" ng-click="ctlr.onDelete(doc.documentId,doc.documentName)" value="Delete"></input>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="input-group form-group" style="height:48px;">
                                                        <span class="input-group-btn">
                                                            <span style="height:48px;background-color:#E6E6E6;"><img src="${pageContext.request.contextPath}/resources/content/Document/Images/Download.png" class="doc-import-button" /></span>
                                                        </span>
                                                    <a class="form-control" style="text-decoration:none;background-color:#F0F0F0;padding-left:50px" ng-href="./download/{{doc.documentId}}">Download</a>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="doc-tab">
                                <h6 class="doc-header" style="font-size:1.1rem">Document Data</h6>
                            </div>

                            <div class="doc-form-div">
                                <div class="form-group" id="divAddressScope">
                                    <label for="ddlAddressScope">Address Scope<span>*</span></label>
                                    <span style="color: red;" id="addressScope"></span>
                                    <div class="form-group">
                                        <select class="custom-style" id="ddlAddressScope" ng-model="doc.addressScope" ng-disabled="flag">
                                            <%--<option value="" selected="selected">None</option>--%>
                                            <option value="{{item.value}}" ng-repeat="item in scopeOptionEdit">{{item.text}}</option>
                                        </select>

                                        <%--<div class="errorAddressScope"></div>--%>
                                    </div>
                                </div>
                                <hr style="color: #CFCFCF;border: 1px solid;" />

                                <div class="row">

                                    <div class="col-md-3 col-lg-3" id="divUserid" style="display: none" required>
                                        <label for="txtUserId">UserID<span>*</span></label>
                                        <div class="form-group">
                                            <input type="text" id="txtUserId" class="custom-style" placeholder="Please Enter.." ng-model="docLink.userId"/>
                                            <div class="errorAddressScope"></div>
                                        </div>

                                    </div>

                                    <div class="col-md-3 col-lg-3" id="divGroup" style="display: none" required>
                                        <label for="groupDetails">Groups Details<span>*</span></label>
                                        <div class="form-group">
                                            <select kendo-multi-select id="groupDetails" name="groupDetails" k-ng-model="docLink.groupDetails" data-placeholder="Please select ..." multiple="multiple">
                                                <option value="{{item.value}}" ng-repeat="item in groupOption">{{item.text}}</option>
                                            </select>
                                            <div class="errorAddressScope"></div>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3" id="divRole" style="display: none" required>
                                        <label for="roleDetails">Role Details<span>*</span></label>
                                        <div class="form-group">
                                            <select kendo-multi-select id="roleDetails" name="roleDetails" k-ng-model="docLink.roleDetails" data-placeholder="Please select ..." multiple="multiple">
                                                <option value="{{item.value}}" ng-repeat="item in roleOption">{{item.text}}</option>
                                            </select>
                                            <div class="errorAddressScope"></div>
                                        </div>
                                    </div>
                                  <%--  <div class="col-md-3 col-lg-3">
                                        <label for="documenttype">Document Type</label>
                                        <div class="form-group">
                                            <select class="custom-style" id="documenttype" name="documentType" ng-model="doc.documentType" ng-disabled="flag">
                                                <option selected="selected" value="">None</option>
                                                <option value="{{item.value}}" ng-repeat="item in docTypeOption">{{item.text}}</option>
                                            </select>

                                            <div class="has-error" data-ng-show="Documentform.$dirty">
                                                <span style="color: red;" data-ng-show="Documentform.documentType.$error.required">This is a required field</span>
                                            </div>
                                            <div class="errorDocType" style="color: red;"></div>
                                        </div>
                                    </div>--%>

                                    <div class="col-md-3 col-lg-3">
                                        <label for="documenttype">Document Type<span>*</span></label>
                                      <%--  <span style="color: red;" id="documentType"></span>--%>
                                        <div class="form-group">
                                            <select class="custom-style" id="documentType" name="documentType" ng-model="doc.documentType" ng-disabled="flag" required>
                                                <option selected="selected" value="">None</option>
                                                <option value="{{item.value}}" ng-repeat="item in docTypeOption">{{item.text}}</option>

                                            </select>
                                            <div class="errorDocType" style="color: red;">
                                                <div class="has-error" data-ng-show="Documentform.$dirty"></div>
                                            </div>
                                           <%-- <div class="has-error" data-ng-show="Documentform.$dirty">
                                                <span style="color: red;" data-ng-show="Documentform.documentType.$error.required">This is a required field</span>
                                            </div>--%>

                                        </div>

                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label for="documentStatus">Document Status<span>*</span></label>
                                        <span style="color: red;" id="documentStatus"></span>
                                        <div class="form-group">
                                            <select class="custom-style" id="ddldocumentStatus" ng-model="doc.documentStatus">
                                                <%--   <option value="">--Select--</option>
                                                   <option value="ForYourInformation" selected>ForYourInformation</option>--%>
                                                <option selected="selected" value="">None</option>
                                                <option value="ForYourInformation">ForYourInformation</option>
                                            </select>
                                            <div class="errorDocStatus" style="color: red;"></div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-lg-3" >
                                        <label for="txtcreationDate">Creation Date</label>
                                        <span style="color: red;" id="creationDate"></span>
                                        <div class="form-group">
                                            <input type="text" id="txtcreationDate" class="custom-style" placeholder="Creation date" readonly ng-model="doc.creationDate" ng-disabled="true"/>
                                            <div class="errorCreationDate"></div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-lg-3">
                                        <label for="txtvalidFromDate">Valid From</label>
                                        <span style="color: red;" id="validFrom"></span>
                                        <div class="form-group">
                                            <input type="text" id="txtvalidFromDate" class="custom-style" placeholder="Valid from" readonly  ng-model="doc.validFrom"/>
                                            <div class="errorValidFrom"></div>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label for="txtvalidToDate">Valid To</label>
                                        <span style="color: red;" id="validTo"></span>
                                        <div class="form-group">
                                            <input type="text" id="txtvalidToDate" class="custom-style" placeholder="Valid To" readonly ng-model="doc.validTo"/>
                                            <div class="errorValidTo"></div>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label>File Size(KB)</label>
                                        <div class="form-group">
                                            <b>{{doc.fileSize}}</b>
                                        </div>
                                    </div>

                                </div>
                             <%--   <hr style="color: #CFCFCF;border: 1px solid;" />
                                <div class="form-group">
                                    <label for="txtdocumentName">Document Name</label>
                                    <span style="color: red;" id="documentName"></span>
                                    <div class="form-group">
                                        <input  type="text" class="custom-style"  name="docName" ng-model="doc.documentName"
                                                ng-disabled="flag" data-ng-pattern="/^[a-zA-Z0-9-.\s]*$/"  id="txtdocumentName" placeholder="Document Name" data-ng-minlength="0"  data-ng-maxlength="255">
                                        <div class="has-error" data-ng-show="Documentform.$dirty">
                                            <span style="color: red;" data-ng-show="Documentform.docName.$error.maxlength">Maximun length required is 255</span>
                                        </div>
                                        <div class="errorDocName"></div>
                                    </div>
                                </div>--%>

                                <hr style="color: #CFCFCF;border: 1px solid;"/>
                                <div class="form-group">
                                    <label for="txtName">Document Name</label>
                                    <span style="color: red;" id="documentName"></span>
                                    <div class="form-group">
                                        <input  type="text" class="custom-style"  name="docName" ng-model="doc.documentName" data-ng-pattern="/^[a-zA-Z0-9-.\s]*$/" id="txtName" placeholder="Document Name" data-ng-minlength="0" data-ng-maxlength="255" ng-disabled="flag" required>

                                        <div class="has-error" data-ng-show="Documentform.$dirty">
                                            <span style="color: red;" data-ng-show="Documentform.docName.$error.maxlength">Maximun Document Name length required is 255 characters only</span>

                                        </div>
                                        <div class="errorDocName"></div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="txtDescription">Document Description</label>
                                    <span style="color: red;" id="documentDescription"></span>
                                    <div class="form-group">
                                        <textarea type="text" style="width: 100%;" name="docdesc" ng-model="doc.documentDescription" id="txtDescription" data-ng-pattern="/^[a-zA-Z0-9-.\s]*$/" placeholder="Document Description" data-ng-minlength="0" data-ng-maxlength="1000" ></textarea>
                                        <div class="has-error"   data-ng-show="Documentform.$dirty">
                                            <span  style="color: red;"  data-ng-show="Documentform.docDesc.$error.maxlength">Maximun Document Description length required is 1000 characters only</span>
                                        </div>
                                        <div class="errorDocDescription"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="documentTag">Document Tags</label>
                                    <span style="color: red;" id="documentTags"></span>
                                    <div class="form-group">
                                        <select kendo-multi-select k-ng-model="documentTag" name="documentTag" k-ng-model="documentTag" data-placeholder="Please select ..." id="documentTag" multiple="multiple">
                                            <option value="{{item.value}}" ng-repeat="item in docTagOption">{{item.text}}</option>
                                        </select>
                                        <div class="errorDocTag"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</div>

<script src="${pageContext.request.contextPath}/resources/scripts/updateValidation.js"></script>
</body>
</html>
