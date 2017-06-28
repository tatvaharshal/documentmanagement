var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
/**new
 * Created by pca43 on 11-May-17.
 */
/// <reference path='../../_allDocumentList.ts' />
var DocumentList;
(function (DocumentList) {
    var DocumentListController = (function (_super) {
        __extends(DocumentListController, _super);
        /// Conctructor
        function DocumentListController($scope, $location, $window, $modal, DocumentListService, $filter, fileUploadService) {
            var _this = _super.call(this, $scope) || this;
            _this.$scope = $scope;
            _this.$location = $location;
            _this.$window = $window;
            _this.$modal = $modal;
            _this.DocumentListService = DocumentListService;
            _this.$filter = $filter;
            _this.fileUploadService = fileUploadService;
            _this.isUploaded = false;
            $scope.vm = _this;
            $scope.isImageRequired = true;
            /*  _http = $http = ng.IHttpService;*/
            _this.$scope.success_message = false;
            _this.$scope.failure_message = false;
            var msg = localStorage.getItem("success");
            if (msg != "null") {
                localStorage.setItem("success", "null");
                _this.$scope.statusMessage = msg;
                _this.$scope.success_message = true;
                setTimeout(function () {
                    this.$scope.success_message = false;
                    this.$scope.$apply();
                }.bind(_this), 2000);
            }
            var msg = localStorage.getItem("failure");
            if (msg != "null") {
                localStorage.setItem("failure", "null");
                _this.$scope.statusMessage = msg;
                _this.$scope.failure_message = true;
                setTimeout(function () {
                    this.$scope.failure_message = false;
                    this.$scope.$apply();
                }.bind(_this), 5000);
            }
            _this.status = _this.$location.search().status;
            if (!angular.isUndefined(_this.status) && _this.status != null && _this.status != "") {
                _this.$scope.status = _this.status.trim();
            }
            _this.docList = _this.$scope.docList = new Array();
            _this.docLinkList = _this.$scope.docLinkList = new Array();
            _this.DocumentListService.GetDocumentList(_this.$scope).then(function (data) {
                $scope.mainGridOptions = {
                    dataSource: {
                        type: "json",
                        data: data,
                        pageSize: 5
                    },
                    columnMenu: true,
                    filterable: {
                        extra: false,
                        operators: {
                            string: {
                                contains: "Contains",
                                isempty: "Is empty"
                            }
                        }
                    },
                    groupable: true,
                    sortable: true,
                    pageable: true,
                    selectable: "raw",
                    columns: [{
                            field: "documentName",
                            title: "Document name"
                        }, {
                            field: "creationDate",
                            title: "Creation date",
                            filterable: {
                                ui: "datetimepicker",
                                extra: false,
                                operators: {
                                    string: {
                                        gte: "is After or equel to",
                                        lte: "is Before or equel to"
                                    }
                                }
                            },
                            template: function (dataitem) {
                                if (dataitem.creationDate == null) {
                                    return "";
                                }
                                var d = new Date(dataitem.creationDate);
                                var year = d.getFullYear();
                                var month = (1 + d.getMonth()).toString();
                                month = month.length > 1 ? month : '0' + month;
                                var day = d.getDate().toString();
                                day = day.length > 1 ? day : '0' + day;
                                var formattedDate = year + "-" + month + "-" + day;
                                return formattedDate;
                            }
                        }, {
                            field: "importDate",
                            title: "import Date",
                            filterable: {
                                ui: "datetimepicker",
                                extra: false,
                                operators: {
                                    string: {
                                        gte: "is After or equel to",
                                        lte: "is Before or equel to"
                                    }
                                }
                            },
                            template: function (dataitem) {
                                if (dataitem.importDate == null) {
                                    return "";
                                }
                                var d = new Date(dataitem.importDate);
                                var year = d.getFullYear();
                                var month = (1 + d.getMonth()).toString();
                                month = month.length > 1 ? month : '0' + month;
                                var day = d.getDate().toString();
                                day = day.length > 1 ? day : '0' + day;
                                var formattedDate = year + "-" + month + "-" + day;
                                return formattedDate;
                            }
                        }, {
                            field: "fileSize",
                            title: "File size(KB)"
                        }, {
                            field: "documentDescription",
                            title: "description"
                        },
                        {
                            field: "documentStatus",
                            title: "Document status"
                        },
                        {
                            field: "documentType",
                            filterable: {
                                multi: true
                            },
                            title: "Document Type"
                        },
                        {
                            field: "addressScope",
                            filterable: {
                                multi: true
                            },
                            title: "Addres scope"
                        }, {
                            field: "verticalData",
                            title: "Vertical Id"
                        }, {
                            field: "validFrom",
                            title: "Valid from",
                            filterable: {
                                ui: "datetimepicker",
                                extra: false,
                                operators: {
                                    string: {
                                        gte: "is After or equel to",
                                        lte: "is Before or equel to"
                                    }
                                }
                            },
                            template: function (dataitem) {
                                if (dataitem.validFrom == null) {
                                    return "";
                                }
                                var d = new Date(dataitem.validFrom);
                                var year = d.getFullYear();
                                var month = (1 + d.getMonth()).toString();
                                month = month.length > 1 ? month : '0' + month;
                                var day = d.getDate().toString();
                                day = day.length > 1 ? day : '0' + day;
                                var formattedDate = year + "-" + month + "-" + day;
                                return formattedDate;
                            }
                        }, {
                            field: "validTo",
                            title: "Valid to",
                            filterable: {
                                ui: "datetimepicker",
                                extra: false,
                                operators: {
                                    string: {
                                        gte: "is After or equel to",
                                        lte: "is Before or equel to"
                                    }
                                }
                            },
                            template: function (dataitem) {
                                if (dataitem.validTo == null) {
                                    return "";
                                }
                                var d = new Date(dataitem.validTo);
                                var year = d.getFullYear();
                                var month = (1 + d.getMonth()).toString();
                                month = month.length > 1 ? month : '0' + month;
                                var day = d.getDate().toString();
                                day = day.length > 1 ? day : '0' + day;
                                var formattedDate = year + "-" + month + "-" + day;
                                return formattedDate;
                            }
                        },
                        {
                            field: "documentTag",
                            title: "Document tag"
                        }
                    ]
                };
            });
            return _this;
        }
        DocumentListController.prototype.validateFile = function (files) {
            if (!files) {
                return true;
            }
            var regExAlphabetic = /^[a-zA-Z0-9-.\s]*$/;
            var fileName = files.name.match(regExAlphabetic);
            var ext = files.name.match(/\.(.+)$/)[1];
            if (angular.lowercase(ext) === 'pdf' && files.size < 5242880 && files.name.match(regExAlphabetic)) {
                $(this).removeClass("error");
                $(".errorFile").text("");
                return true;
            }
            else {
                $(this).addClass("error");
                $(".errorFile").text("Only pdf with valid name allow with max 5 MB");
                return false;
            }
        };
        // Init
        DocumentListController.prototype.Init = function () {
            _super.prototype.BaseInit.call(this);
            this.$scope.docTagOption = [
                { text: "ProjectA", value: "ProjectA" },
                { text: "ProjectB", value: "ProjectB" },
                { text: "ProjectC", value: "ProjectC" },
            ];
            this.$scope.scopeOption = [
                { text: "UserId", value: "UserId" },
                { text: "Group", value: "Group" },
                { text: "Role", value: "Role" },
            ];
            this.$scope.docTypeOption = [
                { text: "Contract", value: "Contract" },
                { text: "Account_Statement", value: "Account_Statement" },
                { text: "Information", value: "Information" },
                { text: "Offer", value: "Offer" },
            ];
            this.$scope.groupOption = [
                { text: "GermanyCards", value: "GermanyCards" },
                { text: "ItalyMULTIVERSAIFP", value: "ItalyMULTIVERSAIFP" },
                { text: "FranceCards", value: "FranceCards" },
                { text: "GrermanyMULTIVERSAIFP", value: "GrermanyMULTIVERSAIFP" },
                { text: "AustriaMULTIVERSAIFP", value: "AustriaMULTIVERSAIFP" },
            ];
            this.$scope.roleOption = [
                { text: "ALLUsers", value: "ALLUsers" },
                { text: "Attentionwidget", value: "Attentionwidget" },
                { text: "Balancewidget", value: "Balancewidget" },
                { text: "Favouritewidget", value: "Favouritewidget" },
                { text: "Liquiditywidget", value: "Liquiditywidget" },
                { text: "Paymentcreation", value: "Paymentcreation" },
                { text: "Paymentwidget", value: "Paymentwidget" },
            ];
        };
        //Methods
        // Init
        DocumentListController.prototype.initialiseEditPage = function (id, $scope) {
            var _this = this;
            debugger;
            this.DocumentListService.GetDocumentByID(id).then(function (data) {
                debugger;
                _this.$scope.docTagOption = [
                    { text: "ProjectA", value: "ProjectA" },
                    { text: "ProjectB", value: "ProjectB" },
                    { text: "ProjectC", value: "ProjectC" },
                ];
                _this.$scope.scopeOption = [
                    { text: "UserId", value: "UserId" },
                    { text: "Group", value: "Group" },
                    { text: "Role", value: "Role" },
                ];
                _this.$scope.scopeOptionEdit = [
                    { text: "None", value: "None" },
                    { text: "UserId", value: "UserId" },
                    { text: "Group", value: "Group" },
                    { text: "Role", value: "Role" },
                ];
                _this.$scope.docTypeOption = [
                    { text: "Contract", value: "Contract" },
                    { text: "Account_Statement", value: "Account_Statement" },
                    { text: "Information", value: "Information" },
                    { text: "Offer", value: "Offer" },
                ];
                _this.$scope.groupOption = [
                    { text: "GermanyCards", value: "GermanyCards" },
                    { text: "ItalyMULTIVERSAIFP", value: "ItalyMULTIVERSAIFP" },
                    { text: "FranceCards", value: "FranceCards" },
                    { text: "GrermanyMULTIVERSAIFP", value: "GrermanyMULTIVERSAIFP" },
                    { text: "AustriaMULTIVERSAIFP", value: "AustriaMULTIVERSAIFP" },
                ];
                _this.$scope.roleOption = [
                    { text: "ALLUsers", value: "ALLUsers" },
                    { text: "Attentionwidget", value: "Attentionwidget" },
                    { text: "Balancewidget", value: "Balancewidget" },
                    { text: "Favouritewidget", value: "Favouritewidget" },
                    { text: "Liquiditywidget", value: "Liquiditywidget" },
                    { text: "Paymentcreation", value: "Paymentcreation" },
                    { text: "Paymentwidget", value: "Paymentwidget" },
                ];
                if (data.documentId == 0) {
                    //alert("No data found for this ID");
                    localStorage.setItem("success", "No Data Found For This Id");
                    _this.$window.location.href = "./DocumentList";
                }
                _this.doc = data;
                _this.$scope.doc = data;
                if (_this.doc.documentName != null) {
                    _this.$scope.doc.documentName = _this.$scope.doc.documentName;
                }
                if (_this.doc.documentDescription != null) {
                    _this.$scope.doc.documentDescription = _this.$scope.doc.documentDescription;
                }
                if (_this.doc.creationDate == null) {
                    _this.$scope.doc.creationDate = "";
                }
                else {
                    _this.$scope.doc.creationDate = _this.convertDate(_this.doc.creationDate);
                }
                if (_this.doc.importDate == null) {
                    _this.$scope.doc.importDate = "";
                }
                else {
                    _this.$scope.doc.importDate = _this.convertDate(_this.doc.importDate);
                }
                if (_this.doc.validFrom == null) {
                    _this.$scope.doc.validFrom = "";
                }
                else {
                    _this.$scope.doc.validFrom = _this.convertDate(_this.doc.validFrom);
                }
                if (_this.doc.validTo == null) {
                    _this.$scope.doc.validTo = "";
                }
                else {
                    _this.$scope.doc.validTo = _this.convertDate(_this.doc.validTo);
                    // this.doc.validTo = this.$filter('date')(data.validTo, 'yyyy-MM-dd');
                }
                if (_this.$scope.doc.documentTag != null) {
                    var temp = _this.$scope.doc.documentTag.split(",");
                    _this.$scope.documentTag = _this.$scope.doc.documentTag.split(",");
                }
                if (_this.doc.addressScope == "None") {
                    _this.$scope.flag = false;
                }
                else {
                    _this.$scope.flag = true;
                }
            });
        };
        DocumentListController.prototype.getAddressScope = function (value) {
            this.$scope.doc.addressScope = value;
        };
        DocumentListController.prototype.getdocType = function (value) {
            this.$scope.doc.documentType = value;
        };
        DocumentListController.prototype.getdocTagDetails = function (value) {
            this.$scope.doc.documentTag = value;
        };
        DocumentListController.prototype.getgroupDetails = function (value) {
            this.$scope.docLink.groupDetails = value;
        };
        DocumentListController.prototype.getroleDetails = function (value) {
            this.$scope.docLink.roleDetails = value;
        };
        DocumentListController.prototype.validationcheck = function (DOClink) {
            var doc = this.$scope.doc;
            var docLink = DOClink;
            var addressscope = doc.addressScope;
            if (addressscope != "None" && addressscope == "Group") {
                var group = ['GermanyCards', 'ItalyMULTIVERSAIFP', 'FranceCards', 'GrermanyMULTIVERSAIFP', 'AustriaMULTIVERSAIFP'];
                var check = docLink.groupDetails;
                for (var i = 0; i < check.length; i++) {
                    if (group.indexOf(check[i]) > -1) {
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                    }
                    else {
                        $(this).addClass("error");
                        $(".errorAddressScope").text("Group Details are not as per options");
                        this.$scope.docLink.groupDetails = null;
                        return false;
                    }
                }
            }
            if (addressscope != "None" && addressscope == "Role") {
                var role = ['ALLUsers', 'Attentionwidget', 'Balancewidget', 'Favouritewidget', 'Liquiditywidget', 'Paymentcreation', 'Paymentwidget'];
                var check = docLink.roleDetails;
                for (var i = 0; i < check.length; i++) {
                    if (role.indexOf(check[i]) > -1) {
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                    }
                    else {
                        /*  alert("Role Details are not as per options");*/
                        $(this).addClass("error");
                        $(".errorAddressScope").text("Role Details are not as per options");
                        this.$scope.docLink.roleDetails = null;
                        return false;
                    }
                }
            }
            if (addressscope != "None" && addressscope == "UserId") {
                var userId = ['Bhavin', 'Harshal', 'Savan', 'Vimal'];
                var check = docLink.userId;
                for (var i = 0; i < userId.length; i++) {
                    if (userId.indexOf(check) > -1) {
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                    }
                    else {
                        /* alert("Select any User from Bhavin,Harshal,Vimal,Savan only");*/
                        $(this).addClass("error");
                        $(".errorAddressScope").text("Select any User from Bhavin,Harshal,Vimal,Savan only");
                        this.$scope.docLink.userId = null;
                        return false;
                    }
                }
            }
        };
        DocumentListController.prototype.validationLinkcheck = function (DOClink) {
            var doc = this.$scope.doc;
            // var docLink = this.$scope.docLink;
            var docLink = DOClink;
            var addressscope = doc.addressScope;
            if (addressscope != "None" && addressscope == "Group") {
                var group = ['GermanyCards', 'ItalyMULTIVERSAIFP', 'FranceCards', 'GrermanyMULTIVERSAIFP', 'AustriaMULTIVERSAIFP'];
                var check = docLink.groupDetails;
                var grouparray = check.split(",");
                for (var i = 0; i < grouparray.length; i++) {
                    if (group.indexOf(grouparray[i]) > -1) {
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                    }
                    else {
                        /*alert("Group Details are not as per options");*/
                        $(this).addClass("error");
                        $(".errorAddressScope").text("Group Details are not as per options");
                        this.$scope.docLink.groupDetails = null;
                        return false;
                    }
                }
            }
            if (addressscope != "None" && addressscope == "Role") {
                var role = ['ALLUsers', 'Attentionwidget', 'Balancewidget', 'Favouritewidget', 'Liquiditywidget', 'Paymentcreation', 'Paymentwidget'];
                var check = docLink.roleDetails;
                var rolearray = check.split(",");
                for (var i = 0; i < rolearray.length; i++) {
                    if (role.indexOf(rolearray[i]) > -1) {
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                    }
                    else {
                        /*alert("Role Details are not as per options");*/
                        $(this).addClass("error");
                        $(".errorAddressScope").text("Role Details are not as per options");
                        this.$scope.docLink.roleDetails = null;
                        return false;
                    }
                }
            }
            if (addressscope != "None" && addressscope == "UserId") {
                var userId = ['Bhavin', 'Harshal', 'Savan', 'Vimal'];
                var check = docLink.userId;
                for (var i = 0; i < userId.length; i++) {
                    if (userId.indexOf(check) > -1) {
                        // this.$window.location.href = "./DocumentAdd";*/
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                    }
                    else {
                        /*alert("Select any User from Bhavin,Harshal,Vimal,Savan only");*/
                        $(this).addClass("error");
                        $(".errorAddressScope").text("Select any User from Bhavin,Harshal,Vimal,Savan only");
                        this.$scope.docLink.userId = null;
                        return false;
                    }
                }
            }
        };
        DocumentListController.prototype.docvalidcheck = function (Doc) {
            var doc = Doc;
            var documentType = doc.documentType;
            if (documentType == "") {
                $(this).addClass("error");
                $(".errorDocType").text("DocumentType is required ");
                return false;
            }
            if (documentType != "" && documentType != undefined) {
                var type = ['None', 'Contract', 'Account_Statement', 'Information', 'Offer'];
                var check = documentType;
                for (var i = 0; i < type.length; i++) {
                    if (type.indexOf(check) > -1) {
                        $(this).removeClass("error");
                        $(".errorDocType").text("");
                    }
                    else {
                        /*alert("Select DocumentType from option");*/
                        $(this).addClass("error");
                        $(".errorDocType").text("Select Proper DocumentType from option");
                        this.$scope.doc.documentType = null;
                        return false;
                    }
                }
            }
            var addressScope = doc.addressScope;
            if (addressScope != "" && addressScope != undefined) {
                var scope = ['None', 'Group', 'Role', 'UserId'];
                var check = addressScope;
                for (var i = 0; i < scope.length; i++) {
                    if (scope.indexOf(check) > -1) {
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                    }
                    else {
                        /*alert("Select Addressscope from option");*/
                        $(this).addClass("error");
                        $(".errorAddressScope").text("Select Proper Addressscope  from option");
                        this.$scope.doc.addressScope = null;
                        return false;
                    }
                }
            }
            var documentStatus = doc.documentStatus;
            if (documentStatus != "" && documentStatus != undefined) {
                var status = ['None', 'ForYourInformation'];
                var check = documentStatus;
                for (var i = 0; i < status.length; i++) {
                    if (status.indexOf(check) > -1) {
                        $(this).removeClass("error");
                        $(".errorDocStatus").text("");
                    }
                    else {
                        /* alert("Select Document Status from option");*/
                        $(this).addClass("error");
                        $(".errorDocStatus").text("Select Proper Document Status  from option");
                        this.$scope.doc.documentStatus = null;
                        return false;
                    }
                }
            }
            var creationDate = this.doc.creationDate;
            if (creationDate != null && creationDate != undefined) {
                var today = new Date();
                var year = today.getFullYear();
                var month = (1 + today.getMonth()).toString();
                month = month.length > 1 ? month : '0' + month;
                var day = today.getDate().toString();
                day = day.length > 1 ? day : '0' + day;
                var todayDate = year + "-" + month + "-" + day;
                if (creationDate != null && creationDate <= todayDate) {
                    $(this).removeClass("error");
                    $(".errorCreationDate").text("");
                }
                else {
                    /*alert("CreationDate  should be <=today's date");*/
                    $(this).addClass("error");
                    $(".errorCreationDate").text("CreationDate  should be <=today's date");
                    this.$scope.doc.creationDate = null;
                    $('#txtcreationDate').focus();
                    return false;
                }
            }
            var validFrom = this.doc.validFrom;
            if (validFrom != null && validFrom != undefined) {
                var importDate = this.doc.importDate;
                if (validFrom != null && validFrom >= importDate) {
                    $(this).removeClass("error");
                    $(".errorValidFrom").text("");
                }
                else {
                    /* alert("ValidFrom Date should be >=importDate or today");*/
                    $(this).addClass("error");
                    $(".errorValidFrom").text("ValidFrom Date should be >=importDate or today");
                    this.$scope.doc.validFrom = null;
                    $('#txtvalidFromDate').focus();
                    return false;
                }
            }
            var validTo = this.doc.validTo;
            if (validTo != null && validTo != undefined) {
                if (validFrom != null && validTo >= validFrom) {
                    $(this).removeClass("error");
                    $(".errorValidTo").text("");
                }
                else {
                    /*alert("Enter ValidFromDate First & validTo must be >=validFrom");*/
                    $(this).addClass("error");
                    $(".errorValidTo").text("Enter ValidFromDate First & validTo must be >=validFrom");
                    this.$scope.doc.validTo = null;
                    $('#txtvalidToDate').focus();
                    return false;
                }
            }
            var documentTag = doc.documentTag;
            if (documentTag != "" && documentTag != undefined) {
                var tag = ['ProjectA', 'ProjectB', 'ProjectC'];
                var check = documentTag;
                for (var i = 0; i < check.length; i++) {
                    if (tag.indexOf(check[i]) > -1) {
                        $(this).removeClass("error");
                        $(".errorDocTag").text("");
                    }
                    else {
                        /* alert("DocumentTag Details are not as per options");*/
                        $(this).addClass("error");
                        $(".errorDocTag").text("DocumentTag Details are not as per options");
                        this.$scope.doc.documentTag = null;
                        return false;
                    }
                }
            }
            var documentName = doc.documentName;
            if (documentName != "" && documentName != undefined) {
                var regExAlphabetic = /^[a-zA-Z0-9-.\s]*$/;
                if (documentName.match(regExAlphabetic) && documentName != "") {
                    $(this).removeClass("error");
                    $(".errorDocName").text("");
                }
                else {
                    /*alert("Enter Valid DocumentName");*/
                    $(this).addClass("error");
                    $(".errorDocName").text("Enter Valid DocumentName");
                    return false;
                }
            }
            var documentDescription = doc.documentDescription;
            if (documentDescription != "" && documentDescription != undefined) {
                var regExAlphabetic = /^[a-zA-Z0-9-.\s]*$/;
                if (documentDescription.match(regExAlphabetic) && documentDescription != "") {
                    $(this).removeClass("error");
                    $(".errorDocDescription").text("");
                }
                else {
                    /* alert("Enter Valid DocumentDescription");*/
                    $(this).addClass("error");
                    $(".errorDocDescription").text("Enter Valid DocumentDescription");
                    return false;
                }
            }
        };
        //SaveDocument
        DocumentListController.prototype.saveDocument = function () {
            var _this = this;
            debugger;
            this.doc = this.$scope.doc;
            this.docLink = this.$scope.docLink;
            if (this.docLink != null) {
                var abc = this.validationcheck(this.docLink);
                if (abc == false) {
                    return false;
                }
            }
            if (this.$scope.doc.documentName == "") {
                this.doc.documentName = undefined;
            }
            if (this.$scope.doc.documentDescription == "") {
                this.doc.documentDescription = undefined;
            }
            if (this.$scope.doc.addressScope == "" || this.$scope.doc.addressScope == undefined) {
                this.doc.addressScope = "None";
            }
            if (this.$scope.doc.creationDate == null || this.$scope.doc.creationDate == undefined) {
                this.doc.creationDate = this.convertDate(new Date());
            }
            if (this.$scope.doc.importDate == null || this.$scope.doc.importDate == undefined) {
                this.doc.importDate = this.convertDate(new Date());
            }
            if (this.doc != null) {
                var bcd = this.docvalidcheck(this.doc);
                if (bcd == false) {
                    return false;
                }
            }

            var file = this.$scope.file;
            if (file != null || file != undefined) {
                this.fileUploadService.uploadFile(file, "./fileUpload").then(function (data) {
                    debugger;
                    var fileData = JSON.parse(data);
                    _this.doc = _this.$scope.doc;
                    _this.docLink = _this.$scope.docLink;
                    _this.$scope.doc.creationDate = _this.convertDate(_this.$scope.doc.creationDate);
                    _this.doc.documentId = fileData.documentId;
                    _this.doc.filePath = fileData.filePath;
                    _this.doc.fileSize = fileData.fileSize;
                    if (_this.$scope.doc.addressScope == "UserId") {
                        if (_this.$scope.docLink != undefined) {
                            _this.docLink.userId = _this.$scope.docLink.userId;
                            var objToString = JSON.stringify(_this.docLink);
                            _this.doc.documentLinkDTO = JSON.parse(objToString);
                        }
                    }
                    if (_this.$scope.doc.addressScope == "Group") {
                        if (_this.$scope.docLink != undefined) {
                            _this.docLink.groupDetails = _this.$scope.docLink.groupDetails.join(",");
                            var something = _this.docLink.groupDetails;
                            var formated = "{" + '"groupDetails"' + ":" + '"' + something + '"' + "}";
                            _this.doc.documentLinkDTO = JSON.parse(formated);
                        }
                    }
                    if (_this.$scope.doc.addressScope == "Role") {
                        if (_this.$scope.docLink != undefined) {
                            _this.docLink.roleDetails = _this.$scope.docLink.roleDetails.join(",");
                            var something = _this.docLink.roleDetails;
                            var formated = "{" + '"roleDetails"' + ":" + '"' + something + '"' + "}";
                            _this.doc.documentLinkDTO = JSON.parse(formated);
                        }
                    }
                    if (_this.$scope.doc.documentTag != undefined) {
                        _this.doc.documentTag = _this.$scope.doc.documentTag.join(",");
                    }
                    _this.DocumentListService.SaveDocument(_this.$scope, _this.doc).then(function (response) {
                        debugger;
                        if (response.status != undefined) {
                            alert("Error Code : " + response.error);
                            localStorage.setItem("failure", "Error Code : " + response.error);
                            return false;
                        }
                        if (response.fieldErrorDTO != null) {
                            for (var i = 0; i < response.fieldErrorDTO.length; i++) {
                                var field = response.fieldErrorDTO[i].field.toString();
                                var message = response.fieldErrorDTO[i].message.toString();
                                var id = "#" + field;
                                $(id).html(message);
                            }
                        }
                        if (response.documentId != 0) {
                            localStorage.setItem("success", "Data added successfully");
                            _this.$window.location.href = "./DocumentList";
                        }
                        else {
                            var msg = "Error During Data save";
                            $('#error_message').fadeIn().html(msg);
                            setTimeout(function () {
                                $('#error_message').fadeOut("slow");
                            }, 9000);
                            /*  alert("Something Went Wrong");*/
                        }
                    })["catch"](function (error) {
                        debugger;
                        alert("error: " + error);
                        localStorage.setItem("failure", "error : " + error);
                    });
                });
            }
            else {
                alert("File is undefined");
                localStorage.setItem("failure", "File is undefined");
            }
        };
        //updateDocument
        DocumentListController.prototype.updateDocument = function () {
            var _this = this;
            debugger;
            this.doc = this.$scope.doc;
            this.doc.documentLinkDTO = this.$scope.doc.documentLinkDTO;
            this.doc.documentTag = this.$scope.documentTag;
            this.doc.creationDate = this.convertDate(this.$scope.doc.creationDate);
            this.doc.importDate = this.convertDate(this.$scope.doc.importDate);
            if (this.$scope.doc.validFrom == "") {
                this.$scope.doc.validFrom = null;
            }
            // this.doc.validFrom=new Date(this.$scope.validFrom);
            // this.$scope.validFrom=this.convertDate(this.$scope.doc.validFrom);
            /*  this.$scope.doc.validFrom=new Date(this.$scope.validFrom);
             this.doc.validFrom=this.$scope.doc.validFrom;*/
            // this.doc.validFrom=this.$scope.validFrom;
            this.doc.validFrom = this.$scope.doc.validFrom;
            if (this.$scope.doc.validTo == "") {
                this.$scope.doc.validTo = null;
            }
            this.doc.validTo = this.$scope.doc.validTo;
            if (this.doc.validTo < this.doc.validFrom) {
                this.doc.validTo = this.doc.validFrom;
            }
            this.doc = this.$scope.doc;
            this.docLink = this.$scope.docLink;
            if (this.$scope.doc.addressScope == "" || this.$scope.doc.addressScope == undefined) {
                this.doc.addressScope = "None";
            }
            if (this.doc != null) {
                var error = this.docvalidcheck(this.doc);
                if (error == false) {
                    return false;
                }
            }
            if (this.docLink != null) {
                var error = this.validationcheck(this.docLink);
                if (error == false) {
                    return false;
                }
            }
            if (this.docLink == null) {
                this.$scope.docLink = this.doc.documentLinkDTO;
                this.docLink = this.$scope.docLink;
                var error = this.validationLinkcheck(this.docLink);
                if (error == false) {
                    return false;
                }
            }
            if (this.$scope.doc.addressScope == "UserId" && this.$scope.docLink != undefined) {
                this.docLink.userId = this.$scope.docLink.userId;
                var objToString = JSON.stringify(this.docLink);
                this.doc.documentLinkDTO = JSON.parse(objToString);
            }
            if (this.$scope.doc.addressScope == "Group" && this.$scope.docLink != undefined) {
                var something = this.docLink.groupDetails;
                var formated = "{" + '"groupDetails"' + ":" + '"' + something + '"' + "}";
                this.doc.documentLinkDTO = JSON.parse(formated);
            }
            if (this.$scope.doc.addressScope == "Role" && this.$scope.docLink != undefined) {
                var something = this.docLink.roleDetails;
                var formated = "{" + '"roleDetails"' + ":" + '"' + something + '"' + "}";
                this.doc.documentLinkDTO = JSON.parse(formated);
            }
            if (this.doc.documentTag != undefined) {
                this.doc.documentTag = this.$scope.documentTag.join(",");
            }
            this.DocumentListService.UpdateDocument(this.$scope, this.doc).then(function (response) {
                debugger;
                if (response.status != undefined) {
                    alert("Error Code : " + response.error);
                    return false;
                }
                if (response.fieldErrorDTO != null) {
                    debugger;
                    for (var i = 0; i < response.fieldErrorDTO.length; i++) {
                        var field = response.fieldErrorDTO[i].field.toString();
                        var message = response.fieldErrorDTO[i].message.toString();
                        var id = "#" + field;
                        $(id).html(message);
                    }
                }
                if (response.documentId != 0) {
                    localStorage.setItem("success", "Data Updted successfully");
                    _this.$window.location.href = "./DocumentList";
                }
                else {
                    var msg = "Error in Data Update";
                    $('#error_message').fadeIn().html(msg);
                    setTimeout(function () {
                        $('#error_message').fadeOut("slow");
                    }, 9000);
                }
            })["catch"](function (err) {
                alert("error ocured : " + err);
            });
        };
        //onDelete
        DocumentListController.prototype.onDelete = function (id, name) {
            var _this = this;
            var options = {
                template: '<div class="modal-header"><h3 class="modal-title">Delete Document</h3></div><div class="modal-body">The Document With Name ' + name + ' will be deleted.Are you sure you want to continue?</div><div class="modal-footer"><button class="btn btn-primary" type="button" ng-click="Dctrl.save(' + id + ')">Ok</button><button class="btn btn-default" type="button" ng-click="Dctrl.cancel()">Cancel</button></div>',
                controller: 'DocumentPopupController as Dctrl',
                windowClass: 'app-modal-window',
                resolve: {}
            };
            this.$modal.open(options).result
                .then(function (updatedItem) { return _this.onConfirm(updatedItem); });
        };
        DocumentListController.prototype.onConfirm = function (item) { };
        DocumentListController.prototype.convertDate = function (timestamp) {
            var d = new Date(timestamp);
            var year = d.getFullYear();
            var month = (1 + d.getMonth()).toString();
            month = month.length > 1 ? month : '0' + month;
            var day = d.getDate().toString();
            day = day.length > 1 ? day : '0' + day;
            var formattedDate = year + "-" + month + "-" + day;
            return formattedDate;
        };
        //handle Click Event on grid raw
        DocumentListController.prototype.onRawClick = function (num) {
            var grid = $("#kGrid").data().kendoGrid;
            var selectedRow = grid.select();
            var selectedDataItem = grid.dataItem(selectedRow);
            var docId = selectedDataItem.documentId;
            this.$window.location.href = "DocumentDetail?DocumentId=" + docId + "";
        };
        return DocumentListController;
    }(DocumentList.BaseDocumentListController));
    DocumentListController.$inject = [
        '$scope',
        '$location',
        '$window',
        '$modal',
        'DocumentListService',
        '$filter',
        'fileUploadService'
    ];
    DocumentList.DocumentListController = DocumentListController;
    angular.module("DocumentList").controller("DocumentListController", DocumentListController);
})(DocumentList || (DocumentList = {}));
//# sourceMappingURL=DocumentListController.js.map