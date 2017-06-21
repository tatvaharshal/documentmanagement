<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Document Add</title>
    <%--Load css--%>
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/bootstrap-select.css" rel="stylesheet"/>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/content/style.css" rel="stylesheet" />
    <link rel="stylesheet" href="//cdn.kendostatic.com/2014.2.716/styles/kendo.common.min.css">
    <link rel="stylesheet" href="//cdn.kendostatic.com/2014.2.716/styles/kendo.default.min.css">
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/custom.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/font-awesome.css" rel="stylesheet"/>
    <%--Load Js--%>
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
    <%--Load App JS Files--%>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/DocumentListApp.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/DocumentListService.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadService.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseDocumentListController.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/DocumentListController.js"></script>
</head>
<body  ng-app="DocumentList">

<div id="wrapper">
    <header id="header1"></header>

    <section id="content">
        <div class="page-content">
           <%-- <div id="success_message" style="color:green;float:right; margin: 0 1000px 0 0";></div>
            <div id="error_message" style="color:red;float:right; margin: 0 1000px 0 0";></div>--%>
            <div class="page-padding" ng-controller="DocumentListController as ctlr" ng-init="ctlr.Init()">
                <form ng-submit="ctlr.validateFile(this.file) && Documentform.$valid && ctlr.saveDocument()" name="Documentform" id="submit">

                    <div class="row">
                        <div class="col-md-12">
                            <%--back discard and import portion--%>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-md-7 col-lg-7">
                                        <h4 class="doc-header"><a href="./DocumentList"><img
                                                src="${pageContext.request.contextPath}/resources/content/Document/Images/back.png"
                                                class="doc-back-image"/></a>Document upload
                                        </h4>
                                    </div>
                                    <div class="col-md-5 col-lg-5">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="input-group form-group" style="height:48px;">
                                                        <span class="input-group-btn">
                                                            <span style="height:48px;background-color:#E6E6E6;"><img
                                                                    src="${pageContext.request.contextPath}/resources/content/Document/Images/delete-icon.png"
                                                                    class="doc-import-button"/></span>
                                                        </span>
                                                    <button class="form-control" type="reset" style="height:100%;width:100%;background-color:#F0F0F0;padding-left:120px;" value="Reset">Discard</button>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="input-group form-group" style="height:48px;"
                                                     ng-class="{'disable-btn': !(ctlr.Isvalid)}">
                                                        <span class="input-group-btn">
                                                            <span style="height:48px;background-color:#E6E6E6;"><img src="${pageContext.request.contextPath}/resources/content/Document/Images/Tick.png" class="doc-import-button"/></span>
                                                        </span>
                                                    <span>{{ctlr.Isvalid}}</span>
                                                    <button class="form-control"
                                                            style="height:100%;width:100%;background-color:#F0F0F0;padding-left:120px;"
                                                            type="submit"  ng-disabled="Documentform.$invalid">Import
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <%--headerline--%>
                            <div class="col-md-2 col-md-offset-10 doc-tab">
                                <h6 class="doc-header" style="font-size:1.1rem">Document data</h6>
                            </div>
                            <%--Form    --%>
                            <div class="doc-form-div">

                                <div class="form-group" style="width: 250px;">
                                    <label for="file">File*</label><br/>
                                    <span style="color: red;" id="fileSize"></span>
                                    <div>
                                        <input class="form-control" type="file" name="file" id="file" file-upload="file" ng-model="file"
                                               ngf-select="ctlr.validateFile(file)" ng-required="isImageRequired"  accept="application/pdf">
                                        </input>
                                        <div class="has-error" data-ng-show="Documentform.$dirty" >
                                            <span style="color: red;" data-ng-show="Documentform.file.$error.required">This is a required field</span>
                                        </div>
                                        <div class="errorFile" style="color: red;" >   </div>
                                    </div>

                                </div>

                                <hr style="color: #CFCFCF;border: 1px solid;"/>

                                <div class="form-group" id="divAddressScope">
                                    <label for="ddlAddressScope">Address Scope<span>*</span></label>
                                    <span style="color: red;" id="addressScope"></span>
                                    <div class="form-group">
                                        <select class="custom-style" id="ddlAddressScope" ng-model="doc.addressScope">
                                           <option value="" selected="selected">None</option>
                                            <option value="{{item.value}}" ng-repeat="item in scopeOption">{{item.text}}</option>
                                        </select>
                                        <div class="errorAddressScope"></div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-3 col-lg-3" id="divUserid" style="display: none" >
                                        <label for="txtUserId">UserID<span>*</span></label>
                                        <div class="form-group">
                                            <input type="text" id="txtUserId" name="userId" class="custom-style" placeholder="Please Enter.." ng-model="docLink.userId" />
                                        </div>

                                    </div>

                                    <div class="col-md-3 col-lg-3" id="divGroup" style="display: none">
                                        <label for="groupDetails">Groups<span>*</span></label>
                                        <div class="form-group">
                                            <select kendo-multi-select id="groupDetails" name="groupDetails" k-ng-model="docLink.groupDetails" data-placeholder="Please select ..." multiple="multiple">
                                                <option value="{{item.value}}" ng-repeat="item in groupOption">{{item.text}}</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3" id="divRole" style="display: none" >
                                        <label for="roleDetails">Role<span>*</span></label>
                                        <div class="form-group">
                                            <select kendo-multi-select id="roleDetails" name="roleDetails" k-ng-model="docLink.roleDetails" data-placeholder="Please select ..." multiple="multiple">
                                                <option value="{{item.value}}" ng-repeat="item in roleOption">{{item.text}}</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label for="documenttype">Document type<span>*</span></label>
                                        <span style="color: red;" id="documentType"></span>
                                        <div class="form-group">
                                            <select class="custom-style" id="ddldocumenttype" name="documentType" ng-model="doc.documentType" required>
                                                <option selected="selected" value="">None</option>
                                                <option value="{{item.value}}" ng-repeat="item in docTypeOption">{{item.text}}</option>
                                            </select>
                                            <div class="has-error" data-ng-show="Documentform.$dirty">
                                                <span style="color: red;" data-ng-show="Documentform.documentType.$error.required">This is a required field</span>
                                            </div>
                                            <div class="errorDocType" style="color: red;"></div>
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
                                    <div class="col-md-3 col-lg-3">
                                        <label for="txtcreationDate">Creation date</label>
                                        <span style="color: red;" id="creationDate"></span>
                                        <div class="form-group">
                                            <input type="text" id="txtcreationDate" class="custom-style"  placeholder="Creation date" readonly ng-model="doc.creationDate"/>
                                            <div class="errorCreationDate"></div>
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label for="txtvalidFromDate">Valid from</label>
                                        <span style="color: red;" id="validFrom"></span>
                                        <div class="form-group">
                                            <input type="text" id="txtvalidFromDate" class="custom-style" placeholder="Valid from" readonly ng-model="doc.validFrom"/>
                                            <div class="errorValidFrom"></div>
                                        </div>

                                    </div>

                                    <div class="col-md-3 col-lg-3">
                                        <label for="txtvalidToDate">Valid to</label>
                                        <span style="color: red;" id="validTo"></span>
                                        <div class="form-group">
                                            <input type="text" name= "validTo" id="txtvalidToDate" class="custom-style" placeholder="Valid To" readonly ng-model="doc.validTo"/>
                                            <div class="errorValidTo"></div>
                                        </div>

                                    </div>

                                </div>

                                <hr style="color: #CFCFCF;border: 1px solid;"/>
                                <div class="form-group">
                                    <label for="txtName">Document Name</label>
                                    <span style="color: red;" id="documentName"></span>
                                    <div class="form-group">
                                        <input  type="text" class="custom-style"  name="docName" ng-model="doc.documentName" data-ng-pattern="/^[a-zA-Z0-9-.\s]*$/" id="txtName" placeholder="Document Name" data-ng-minlength="0" data-ng-maxlength="255">

                                        <div class="has-error" data-ng-show="Documentform.$dirty">
                                            <span style="color: red;" data-ng-show="Documentform.docName.$error.maxlength">Maximun documentname length required is 255 letters only</span>
                                        </div>
                                        <div class="errorDocName"></div>
                                    </div>


                                </div>

                                <div class="form-group">
                                    <label for="txtDescription">Description</label>
                                    <span style="color: red;" id="documentDescription"></span>
                                    <div class="form-group">
                                        <input type="text" style="width: 100%;" name="docDesc" ng-model="doc.documentDescription"  data-ng-pattern="/^[a-zA-Z0-9-.\s]*$/" id="txtDescription" placeholder="Description" data-ng-minlength="0" data-ng-maxlength="1000"></input>

                                        <div class="has-error"   data-ng-show="Documentform.$dirty">
                                            <span  style="color: red;"  data-ng-show="Documentform.docDesc.$error.maxlength">Maximun documentDescription length required is 1000 letters only</span>
                                        </div>
                                        <div class="errorDocDescription"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="documentTag">Document Tags</label>
                                    <span style="color: red;" id="documentTag"></span>
                                    <div class="form-group">
                                        <select kendo-multi-select id="ddldocumentTag" name="documentTag" k-ng-model="doc.documentTag" data-placeholder="Please select ..." multiple="multiple">
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
<script src="${pageContext.request.contextPath}/resources/scripts/validate.js"></script>
<%--<script>
    var data_save = false;  /* set this to true if the login was a success */

if(data_save == false)
{
document.getElementById("data_save").innerHTML = "Error in Data Save.";
}
if(data_save == true)
{
document.getElementById("data_save").innerHTML = "Data Save successfully";
}


    $("#submit").submit(function(e) {
        e.preventDefault();
        var name = $("#name").val();
        var comment = $("#comment").val();

        if(name == "" || comment == "" ) {
            $("#error_message").show().html("All Fields are Required");
        } else {
            $("#error_message").html("").hide();
            $.ajax({
                type: "POST",
                url: "post-form.php",
                data: "name="+name+"&comment="+comment,
                success: function(data){
                    $('#success_message').fadeIn().html(data);
                    setTimeout(function() {
                        $('#success_message').fadeOut("slow");
                    }, 2000 );

                }
            });
        }
    })
</script>--%>
</body>
</html>
