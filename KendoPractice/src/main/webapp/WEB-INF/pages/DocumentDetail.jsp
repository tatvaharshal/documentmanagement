<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Document Details</title>

    <%--Load css--%>
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/bootstrap-select.css" rel="stylesheet"/>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.css" rel="stylesheet" />
    <link rel="stylesheet" href="//cdn.kendostatic.com/2014.2.716/styles/kendo.common.min.css">
    <link rel="stylesheet" href="//cdn.kendostatic.com/2014.2.716/styles/kendo.default.min.css">
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/custom.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/font-awesome.css" rel="stylesheet"/>
    <link href="https://bootswatch.com/cerulean/bootstrap.min.css" rel="stylesheet" />

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
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadService.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseDocumentListController.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/DocumentPopupController.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/DocumentListController.js"></script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<%
    String DocumentId = request.getParameter("DocumentId");
%>
<div id="wrapper" ng-app="DocumentList">
    <header id="header1"></header>
    <section id="content">
        <div class="page-content">
                <div class="page-padding" ng-controller="DocumentListController as ctlr" ng-init="ctlr.initialiseEditPage(<%=DocumentId%>)">
                <form ng-submit="Documentform.$valid && ctlr.updateDocument()" name="Documentform">
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
                                                    <button class="form-control" style="height:100%;width:100%;background-color:#F0F0F0;padding-left:25px;" type="submit" ng-click="ctlr.updateDocument()">Publish</button>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="input-group form-group" style="height:48px;">
                                                        <span class="input-group-btn">
                                                            <span style="height:48px;background-color:#E6E6E6;"><img src="${pageContext.request.contextPath}/resources/content/Document/Images/delete-icon.png" class="doc-import-button" /></span>
                                                        </span>
                                                    <input type="button"  class="form-control" style="height:100%;width:100%;background-color:#F0F0F0;padding-left:25px;" ng-click="ctlr.onDelete(doc.documentId,doc.documentName)" value="Delete"></input>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="input-group form-group" style="height:48px;">
                                                        <span class="input-group-btn">
                                                            <span style="height:48px;background-color:#E6E6E6;"><img src="${pageContext.request.contextPath}/resources/content/Document/Images/Download.png" class="doc-import-button" /></span>
                                                        </span>
                                                    <%--<input type="button" class="form-control" style="height:100%;width:100%;background-color:#F0F0F0;padding-left:25px;"><a style="text-decoration:none;color: black" ng-href="./download/{{doc.documentId}}">Download</a></input>--%>
                                                    <a class="form-control" style="text-decoration:none;color: black" ng-href="./download/{{doc.documentId}}">Download</a>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="doc-tab">
                                <h6 class="doc-header" style="font-size: 2.1rem;">Document data</h6>
                            </div>

                            <div class="doc-form-div">
                                  <div class="form-group">
                                      <label for="ddlAddressScope">Address Scope<span>*</span></label>
                                      <div class="form-group">
                                          <select class="custom-style" id="ddlAddressScope" ng-model="doc.addressScope" ng-disabled="flag">
                                           <option selected="selected" value="None">None</option>
                                              <option value="UserId">UserId</option>
                                              <option value="Group">Group</option>
                                              <option value="Role">Role</option>
                                              </select>
                                      </div>
                                  </div>
                                <hr style="color: #CFCFCF;border: 1px solid;" />

                                <div class="row">

                                    <div class="col-md-3 col-lg-3" id="divUserid" style="display: none">
                                        <label for="txtUserId">UserID<span>*</span></label>
                                        <div class="form-group">
                                            <input type="text" id="txtUserId" class="custom-style" placeholder="Please Enter.." ng-model="docLink.userId"/>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3" id="divGroup" style="display: none">
                                        <label for="groupDetails">Groups<span>*</span></label>
                                        <div class="form-group">
                                            <select kendo-multi-select id="groupDetails" k-ng-model="docLink.groupDetails" data-placeholder="Please select ..."  multiple="multiple">
                                                <option value="GermanyCards">GermanyCards</option>
                                                <option value="ItalyMULTIVERSAIFP">ItalyMULTIVERSAIFP</option>
                                                <option value="FranceCards">FranceCards</option>
                                                <option value="GrermanyMULTIVERSAIFP">GrermanyMULTIVERSAIFP</option>
                                                <option value="AustriaMULTIVERSAIFP">AustriaMULTIVERSAIFP</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3" id="divRole" style="display: none">
                                        <label for="roleDetails">Role<span>*</span></label>
                                        <div class="form-group">
                                            <select kendo-multi-select id="roleDetails" k-ng-model="docLink.roleDetails" data-placeholder="Please select ..." multiple="multiple">
                                                <option value="ALLUsers">ALLUsers</option>
                                                <option value="Attentionwidget">Attentionwidget</option>
                                                <option value="Balancewidget">Balancewidget</option>
                                                <option value="Favouritewidget">Favouritewidget</option>
                                                <option value="Liquiditywidget">Liquiditywidget</option>
                                                <option value="Paymentcreation">Paymentcreation</option>
                                                <option value="Paymentwidget">Paymentwidget</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-lg-3">
                                        <label for="documenttype">Document type</label>
                                        <div class="form-group">
                                            <select class="custom-style" id="documenttype" ng-model="doc.documentType" ng-disabled="flag">
                                              <option selected="selected" value="">None</option>
                                                <option value="Contract">Contract</option>
                                                <option value="Account_Statement">Account_Statement</option>
                                                <option value="Information">Information</option>
                                                <option value="Offer">Offer</option>
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
                                            <input type="text" id="txtcreationDate" class="custom-style" placeholder="Creation date" ng-model="creationDate" ng-disabled="true" />
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label for="txtvalidFromDate">Valid from</label>
                                        <div class="form-group">
                                            <input type="text" id="txtvalidFromDate" class="custom-style" placeholder="Valid from" ng-model="validFrom"/>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label for="txtvalidToDate">Valid to</label>
                                        <div class="form-group">
                                            <input type="text" id="txtvalidToDate" class="custom-style" placeholder="Valid To" ng-model="validTo"/>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label>File Size(KB)</label>
                                        <div class="form-group">
                                            <b>{{doc.fileSize}}</b>
                                        </div>
                                    </div>

                                </div>
                                <hr style="color: #CFCFCF;border: 1px solid;" />

                                <div class="form-group">
                                    <label for="txtdocumentName">Document Name<span>*</span></label>
                                    <div class="form-group">
                                        <input  type="text" class="custom-style"  name="docName" ng-model="doc.documentName" data-ng-pattern="/^[a-zA-Z0-9]*$/" id="txtdocumentName" placeholder="Document Name" data-ng-minlength="0" ng-disabled="flag" data-ng-maxlength="255" maxlength="255">
                                        <%--ng-class="{'doc-red-btn': !(Documentform.docName.$valid)}"--%>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="txtDescription">Description</label>
                                    <div class="form-group">
                                        <textarea type="text" style="width: 100%;" name="docName" ng-model="doc.documentDescription" id="txtDescription" placeholder="Description" data-ng-minlength="0" data-ng-maxlength="1000" maxlength="1000" ></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="selectDocumentTags">Document tags</label>
                                    <div class="form-group">
                                        <select kendo-multi-select k-ng-model="documentTags" data-placeholder="Please select ..." id="selectDocumentTags" multiple="multiple">
                                            <option value="ProjectA" >ProjectA</option>
                                            <option value="ProjectB">ProjectB</option>
                                            <option value="ProjectC">ProjectC</option>
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
    $('#txtcreationDate').datepicker({
        dateFormat: 'yy-mm-dd',
        autoclose: true,
        maxDate: 0
    });
    $('#txtvalidFromDate').datepicker({
        dateFormat: 'yy-mm-dd',
        autoclose: true,
        minDate: 0
    });
    $('#txtvalidToDate').datepicker({
        dateFormat: 'yy-mm-dd',
        autoclose: true,
        maxDate: 0
    });


    $("#ddlAddressScope").change(function () {
        $("select option:selected").each(function () {
            if ($(this).attr("value") == "UserId") {
                $("#divUserid").show();
                $("#divGroup").hide();
                $("#divRole").hide();
            }
            if ($(this).attr("value") == "Group") {
                $("#divUserid").hide();
                $("#divGroup").show();
                $("#divRole").hide();
            }
            if ($(this).attr("value") == "Role") {
                $("#divUserid").hide();
                $("#divGroup").hide();
                $("#divRole").show();
            }
            if ($(this).attr("value") == "None") {
                $("#divUserid").hide();
                $("#divGroup").hide();
                $("#divRole").hide();
            }
        } );
    }).change();

</script>
</body>
</html>
