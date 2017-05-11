<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Document details</title>

    <%--Load css--%>
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/bootstrap-select.css" rel="stylesheet"/>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.css" rel="stylesheet" />
    <link rel="stylesheet" href="//cdn.kendostatic.com/2014.2.716/styles/kendo.common.min.css">
    <link rel="stylesheet" href="//cdn.kendostatic.com/2014.2.716/styles/kendo.default.min.css">
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/custom.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/font-awesome.css" rel="stylesheet"/>

    <%--Load Js--%>
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

    <script src="${pageContext.request.contextPath}/resources/app/Demo/DocumentListApp.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/DocumentListService.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseDocumentListController.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/DocumentListController.js"></script>

</head>
<body>

<div id="wrapper" ng-app="DocumentList">
    <header id="header"></header>

    <section id="content">
        <div class="page-content">

            <div class="page-padding" ng-controller="DocumentListController as ctlr">
                <form ng-submit="Documentform.$valid && ctlr.saveDocument()" name="Documentform">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-md-7 col-lg-7">
                                        <h4 class="doc-header"><img src="${pageContext.request.contextPath}/resources/content/Document/Images/back.png" class="doc-back-image" /><a href="Javascript:void(0)"></a>Document : name.pdf</h4>
                                    </div>
                                    <div class="col-md-5 col-lg-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="input-group form-group" style="height:48px;" ng-class="{'disable-btn': !(ctlr.Isvalid)}">
                                                        <span class="input-group-btn">
                                                            <span style="height:48px;background-color:#E6E6E6;"><img src="${pageContext.request.contextPath}/resources/content/Document/Images/Tick.png" class="doc-import-button" /></span>
                                                        </span>
                                                    <span>{{ctlr.Isvalid}}</span>
                                                    <button class="form-control" style="height:100%;width:100%;background-color:#F0F0F0;padding-left:25px;" type="submit"  ng-disabled="Documentform.$invalid">Publish</button>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="input-group form-group" style="height:48px;">
                                                        <span class="input-group-btn">
                                                            <span style="height:48px;background-color:#E6E6E6;"><img src="${pageContext.request.contextPath}/resources/content/Document/Images/delete-icon.png" class="doc-import-button" /></span>
                                                        </span>
                                                    <button class="form-control" style="height:100%;width:100%;background-color:#F0F0F0;padding-left:25px;" ng-click="ctlr.TestData(ctlr)">Delete</button>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="input-group form-group" style="height:48px;" ng-class="{'disable-btn': !(ctlr.Isvalid)}">
                                                        <span class="input-group-btn">
                                                            <span style="height:48px;background-color:#E6E6E6;"><img src="${pageContext.request.contextPath}/resources/content/Document/Images/Download.png" class="doc-import-button" /></span>
                                                        </span>
                                                    <span>{{ctlr.Isvalid}}</span>
                                                    <button class="form-control" style="height:100%;width:100%;background-color:#F0F0F0;padding-left:25px;" type="submit"  ng-disabled="Documentform.$invalid">Download</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="col-md-2 col-md-offset-10 doc-tab">
                                <h6 class="doc-header" style="font-size:1.1rem">Document data</h6>
                            </div>

                            <div class="doc-form-div">
                                  <div class="form-group">
                                      <label for="ddlAddressScope">Address Scope<span>*</span></label>
                                      <div class="form-group">
                                          <select class="custom-style" id="ddlAddressScope" ng-model="doc.address" required>
                                              <option selected="selected" value="">None</option>
                                              <option>All_users</option>
                                              <option>User_ID</option>
                                              <option>Group</option>
                                              <option>Role</option>
                                          </select>
                                      </div>
                                  </div>
                                <hr style="color: #CFCFCF;border: 1px solid;" />

                                <div class="form-group" hidden>
                                    <label for="allUsers">All users<span>*</span></label>
                                    <div class="form-group">
                                        <select class="custom-style" id="allUsers" ng-model="doc.users">
                                            <option selected="selected" value="">None</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="row">

                                    <div class="col-md-3 col-lg-3">
                                        <label for="documenttype">Document type</label>
                                        <div class="form-group">
                                            <select class="custom-style" id="documenttype" ng-model="doc.type" required>
                                                <option selected="selected" value="">None</option>
                                                <option>Contract</option>
                                                <option>Account_Statement</option>
                                                <option>Information</option>
                                                <option>Offer</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label for="documentstauts">Document Status</label>
                                        <div class="form-group">
                                            <select class="custom-style" id="documentstauts" ng-model="doc.stauts">
                                                <option selected="selected" value="">For_Your_Information</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label for="txtcreationDate">Creation date</label>
                                        <div class="form-group">
                                            <input type="text" id="txtcreationDate" class="custom-style" placeholder="Creation date" ng-model="doc.creationDate"/>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label for="txtvalidFromDate">Valid from</label>
                                        <div class="form-group">
                                            <input type="text" id="txtvalidFromDate" class="custom-style" placeholder="Valid from" ng-model="doc.validFromDate"/>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label for="txtvalidToDate">Valid to</label>
                                        <div class="form-group">
                                            <input type="text" id="txtvalidToDate" class="custom-style" placeholder="Valid To" ng-model="doc.validToDate"/>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label for="txtvalidToDate">File Size(KB)</label>
                                        <div class="form-group">
                                            <b>3184</b>
                                        </div>
                                    </div>

                                </div>
                                <hr style="color: #CFCFCF;border: 1px solid;" />

                                <div class="form-group">
                                    <label for="txtdocumentName">Document Name<span>*</span></label>
                                    <div class="form-group">
                                        <input  type="text" class="custom-style"  name="docName" ng-model="doc.docName" data-ng-pattern="/^[a-zA-Z0-9]*$/" id="txtdocumentName" placeholder="Document Name" data-ng-minlength="0" data-ng-maxlength="255" maxlength="255">
                                        <%--ng-class="{'doc-red-btn': !(Documentform.docName.$valid)}"--%>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="txtDescription">Description</label>
                                    <div class="form-group">
                                        <textarea type="text" style="width: 100%;" name="docName" ng-model="doc.docDescription" id="txtDescription" placeholder="Description" data-ng-minlength="0" data-ng-maxlength="1000" maxlength="1000"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="selectDocumentTags">Document tags</label>
                                    <div class="form-group">
                                        <%--<select kendo-multi-select k-options="selectOptions" k-ng-model="selectedIds"></select>--%>
                                        <select kendo-multi-select k-ng-model="doc.documentTag" data-placeholder="Please select ..." id="selectDocumentTags">
                                            <option>TypeScript</option>
                                            <option>Kendo</option>
                                            <option>Angular</option>
                                            <option>MVC</option>
                                        </select>
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

<script>
    alert("test");
    $(document).ready(function(){

    $('#txtcreationDate').datepicker({
        minDate: -20, maxDate: "+1M +10D",
        autoclose: true
    });
    });

//    $(' #txtvalidFromDate, #txtvalidToDate').datepicker({
//        maxDate: new Date(currentYear, currentMonth, currentDate),
//        format: 'yyyy-mm-dd',
//        autoclose: true
//    });
</script>

</body>
</html>
