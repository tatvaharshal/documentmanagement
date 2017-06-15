/**new
 * Created by pca43 on 11-May-17.
 */
/// <reference path='../../_allDocumentList.ts' />
module DocumentList {
    //import Upload = kendo.ui.Upload;
    export class DocumentListController extends BaseDocumentListController {
        //variables
        'use strict';
        doc: MasterDocumentDTO;
        docLink: DocumentLinkDTO;
        docList: Array<MasterDocumentDTO>;
        docLinkList: Array<DocumentLinkDTO>;
        list: Multivalue;
        documentTag:string;
        groupDetails:string;
        roleDetails:string;
        isUploaded: any = false;
        /* documentTags:string[];
         groupDetails:string[];*/
        public _window: any;
        public _filter: any;
        status: any;
        public static $inject = [
            '$scope',
            '$location',
            '$window',
            '$modal',
            'DocumentListService',
            '$filter',
            'fileUploadService'
        ];
        /// Conctructor
        constructor(private $scope: IDocumentListScope, private $location: ng.ILocationService, private $window: ng.IWindowService, private $modal: ng.ui.bootstrap.IModalService, private DocumentListService: IDocumentListService, private $filter: ng.IFilterService, private fileUploadService: IFileUploadService) {
            super($scope);
            $scope.vm = this;
           /* $scope.groupDetails = ["GermanyCards,ItalyMULTIVERSAIFP,FranceCards,GrermanyMULTIVERSAIFP,AustriaMULTIVERSAIFP"];
            $scope.roleDetails = ["ALLUsers,Attentionwidget,Balancewidget,Favouritewidget,Liquiditywidget,Paymentcreation,Paymentwidget"];*/
            $scope.isImageRequired = true;
            this.status = this.$location.search().status;
            if (!angular.isUndefined(this.status) && this.status != null && this.status != "") {
                this.$scope.status = this.status.trim();
            }
            this.docList = this.$scope.docList = new Array<MasterDocumentDTO>();
            this.docLinkList = this.$scope.docLinkList = new Array<DocumentLinkDTO>();

            this.DocumentListService.GetDocumentList(this.$scope).then((data) => {
                $scope.mainGridOptions = {
                    dataSource: {
                        type: "json",
                        data: data,
                        pageSize: 5,
                    },
                    columnMenu: true,
                    filterable: {
                        extra: false,
                        operators: {
                            string: {
                                contains: "Contains",
                                isempty: "Is empty",
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
                                    lte: "is Before or equel to",
                                }
                            }
                        },
                        template: function (dataitem) {
                            if (dataitem.creationDate == null) {
                                return "";
                            }
                            /*   var d = new Date(dataitem.creationDate);
                             var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                             return formattedDate;*/
                            var d = new Date(dataitem.creationDate);
                            if (d.getMonth() <= 9) {
                                var curMonth = d.getMonth() + 1;
                                var month = "0" + curMonth;
                            }
                            else {
                                var month = d.getMonth() + 1;
                            }
                            if (d.getDate() <= 9) {
                                var curDay = d.getDate();
                                var day = "0" + curDay;
                            }
                            else {
                                var day = d.getDate();
                            }
                            var formattedDate = d.getFullYear() + "-" + month + "-" + day;
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
                                    lte: "is Before or equel to",
                                }
                            }
                        },
                        template: function (dataitem) {
                            if (dataitem.importDate == null) {
                                return "";
                            }
                            /*  var d = new Date(dataitem.importDate);
                             var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                             return formattedDate;*/
                            var d = new Date(dataitem.importDate);
                            if (d.getMonth() <= 9) {
                                var curMonth = d.getMonth() + 1;
                                var month = "0" + curMonth;
                            }
                            else {
                                var month = d.getMonth() + 1;
                            }
                            if (d.getDate() <= 9) {
                                var curDay = d.getDate();
                                var day = "0" + curDay;
                            }
                            else {
                                var day = d.getDate();
                            }
                            var formattedDate = d.getFullYear() + "-" + month + "-" + day;
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
                                        lte: "is Before or equel to",
                                    }
                                }
                            },
                            template: function (dataitem) {
                                if (dataitem.validFrom == null) {
                                    return "";
                                }
                                /*   var d = new Date(dataitem.validFrom);
                                 var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                                 return formattedDate;*/
                                var d = new Date(dataitem.validFrom);
                                if (d.getMonth() <= 9) {
                                    var curMonth = d.getMonth() + 1;
                                    var month = "0" + curMonth;
                                }
                                else {
                                    var month = d.getMonth() + 1;
                                }
                                if (d.getDate() <= 9) {
                                    var curDay = d.getDate();
                                    var day = "0" + curDay;
                                }
                                else {
                                    var day = d.getDate();
                                }
                                var formattedDate = d.getFullYear() + "-" + month + "-" + day;
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
                                        lte: "is Before or equel to",
                                    }
                                }
                            },
                            template: function (dataitem) {
                                if (dataitem.validTo == null) {
                                    return "";
                                }
                                /*  var d = new Date(dataitem.validTo);
                                 var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                                 return formattedDate;*/
                                //this.dataitem.validTo= this.convertDate(dataitem.validTo);
                                //  convertDate(dataitem.validTo)
                                var d = new Date(dataitem.validTo);
                                if (d.getMonth() <= 9) {
                                    var curMonth = d.getMonth() + 1;
                                    var month = "0" + curMonth;
                                }
                                else {
                                    var month = d.getMonth() + 1;
                                }
                                if (d.getDate() <= 9) {
                                    var curDay = d.getDate();
                                    var day = "0" + curDay;
                                }
                                else {
                                    var day = d.getDate();
                                }
                                var formattedDate = d.getFullYear() + "-" + month + "-" + day;
                                return formattedDate;
                            }
                        },
                        {
                            field: "documentTag",
                            title: "Document tag"
                        }
                    ]
                }
            });
        }

      /*  selectGroupDetails(groupDetails: string) {
            if (this.$scope.groupDetails.indexOf(groupDetails) > -1) {
                this.$scope.groupDetails = this.$scope.groupDetails.filter(x => x != groupDetails);
            }
            else {
                this.$scope.groupDetails.push(groupDetails);
            }
        }*/
        public validateFile(files) {
            if (!files) {
                return true;
            }
            // var ext = files.name.match(/\.(.+)$/)[1];
            var regExAlphabetic = /^[a-zA-Z0-9-.\s]*$/;
            var fileName=files.name.match(regExAlphabetic);
            var ext = files.name.match(/\.(.+)$/)[1];
            if (angular.lowercase(ext) === 'pdf' && files.size<5242880 && files.name.match(regExAlphabetic)) {

                $(this).removeClass("error");
                $(".errorFile").text("");
                return true;
            }
            else {
                // alert("Only pdf allow with max 5 MB");
                $(this).addClass("error");
                $(".errorFile").text("Only pdf with valid name allow with max 5 MB");
                return false;
            }
        }
        // Init
        public Init() {
            super.BaseInit();
            this.$scope.docTagOption = [
                { text: "ProjectA", value: "ProjectA" },
                { text: "ProjectB", value: "ProjectB" },
                { text: "ProjectC", value: "ProjectC" },
            ];
            this.$scope.scopeOption = [
               /*{ text: "None", value: "None", default:"None"}*/
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
        }
        //Methods
        // Init
        initialiseEditPage(id: number, $scope) {
            debugger;
            this.DocumentListService.GetDocumentByID(id).then((data) => {
                debugger;
                this.$scope.docTagOption = [
                    { text: "ProjectA", value: "ProjectA" },
                    { text: "ProjectB", value: "ProjectB" },
                    { text: "ProjectC", value: "ProjectC" },
                ];
                this.$scope.scopeOption = [
                 /*   { text: "None", value: "None", default:"None"}*/

                    { text: "UserId", value: "UserId" },
                    { text: "Group", value: "Group" },
                    { text: "Role", value: "Role" },
                ];
                this.$scope.scopeOptionEdit = [
               /*  { text: "None", value: "None" },*/
                    { text: "None", value: "None" },
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
                if (data.documentId == 0) {
                    //   alert("No data found for this ID");
                    this.$window.location.href = "./DocumentList";
                }
                this.doc = data;
                this.$scope.doc = data;

                if (this.doc.creationDate == null) {
                    this.$scope.creationDate = "";
                } else {
                    this.$scope.creationDate = this.convertDate(this.doc.creationDate);
                    //this.doc.creationDate = this.$filter('date')(data.creationDate, 'yyyy-MM-dd');
                }
                if (this.doc.importDate == null) {
                    this.$scope.importDate = "";
                } else {
                    this.$scope.importDate = this.convertDate(this.doc.importDate);
                    //this.doc.validFrom = this.$filter('date')(data.validFrom, 'yyyy-MM-dd');
                }
                if (this.doc.validFrom == null) {
                    this.$scope.validFrom = "";
                } else {
                    this.$scope.validFrom = this.convertDate(this.doc.validFrom);
                    //this.doc.validFrom = this.$filter('date')(data.validFrom, 'yyyy-MM-dd');
                }
                if (this.doc.validTo == null) {
                    this.$scope.validTo = "";
                } else {
                    this.$scope.validTo = this.convertDate(this.doc.validTo);
                    // this.doc.validTo = this.$filter('date')(data.validTo, 'yyyy-MM-dd');
                }
                if (this.$scope.doc.documentTag != null) {
                    var temp = this.$scope.doc.documentTag.split(",");
                    this.$scope.documentTag = this.$scope.doc.documentTag.split(",");
                }
                if (this.doc.addressScope == "None") {
                 //   this.doc.addressScope = this.doc.addressScope;
                    this.$scope.flag = false;
                }
                else {
                    this.$scope.flag = true;
                }
            });
        }
        public getAddressScope(value) {
            this.$scope.doc.addressScope = value;
        }
        public getdocType(value) {
            this.$scope.doc.documentType = value;
        }
        public getdocTagDetails(value) {
            this.$scope.doc.documentTag = value;
        }
        public getgroupDetails(value) {
            this.$scope.docLink.groupDetails = value;
        }
        public getroleDetails(value) {
            this.$scope.docLink.roleDetails = value;
        }
        public validationcheck(DOClink) {
            var doc = this.$scope.doc;
           // var docLink = this.$scope.docLink;
            var docLink = DOClink;
            var addressscope = doc.addressScope;
            if (addressscope != "None" && addressscope == "Group") {
                var group = ['GermanyCards', 'ItalyMULTIVERSAIFP', 'FranceCards', 'GrermanyMULTIVERSAIFP', 'AustriaMULTIVERSAIFP'];
                var check = docLink.groupDetails;
                for (var i = 0; i < check.length; i++) {
                    if (group.indexOf(check[i]) > -1){
                    /*    alert("Group Details are not as per options")
                        this.$window.location.href = "./DocumentAdd";*/
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                       // return true;
                    }
                    else
                    {   $(this).addClass("error");
                        $(".errorAddressScope").text("Group Details are not as per options");
                        this.$scope.docLink.groupDetails=null;
                        return false;
                    }
                }
            }
            if (addressscope != "None" && addressscope == "Role") {
                var role = ['ALLUsers', 'Attentionwidget', 'Balancewidget', 'Favouritewidget', 'Liquiditywidget','Paymentcreation','Paymentwidget'];
                var check = docLink.roleDetails;
                for (var i = 0; i < check.length; i++) {
                    if (role.indexOf(check[i]) > -1){
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                        //return true;
                    }
                    else
                    {   $(this).addClass("error");
                        $(".errorAddressScope").text("Role Details are not as per options");
                        this.$scope.docLink.roleDetails=null;
                        return false;
                    }
                }
            }
            if (addressscope != "None" && addressscope == "UserId") {
                var userId = ['Bhavin', 'Harshal', 'Savan', 'Vimal'];

                var check = docLink.userId;
                for (var i = 0; i < userId.length; i++) {
                    if (userId.indexOf(check) > -1){
                        /*    alert("Group Details are not as per options")
                         this.$window.location.href = "./DocumentAdd";*/
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                        //return true;
                    }
                    else
                    {
                        $(this).addClass("error");
                        $(".errorAddressScope").text("Select any User from Bhavin,Harshal,Vimal,Savan only");
                        this.$scope.docLink.userId=null;
                        return false;
                    }
                }
            }
        }

        public validationLinkcheck(DOClink) {
            var doc = this.$scope.doc;
            // var docLink = this.$scope.docLink;
            var docLink = DOClink;
            var addressscope = doc.addressScope;
            if (addressscope != "None" && addressscope == "Group") {
                var group = ['GermanyCards', 'ItalyMULTIVERSAIFP', 'FranceCards', 'GrermanyMULTIVERSAIFP', 'AustriaMULTIVERSAIFP'];
                var check = docLink.groupDetails;
                var grouparray=check.split(",");
                for (var i = 0; i < grouparray.length; i++) {
                    if (group.indexOf(grouparray[i]) > -1){
                        /*    alert("Group Details are not as per options")
                         this.$window.location.href = "./DocumentAdd";*/
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                        // return true;
                    }
                    else
                    {   $(this).addClass("error");
                        $(".errorAddressScope").text("Group Details are not as per options");
                        this.$scope.docLink.groupDetails=null;
                        return false;
                    }
                }
            }
            if (addressscope != "None" && addressscope == "Role") {
                var role = ['ALLUsers', 'Attentionwidget', 'Balancewidget', 'Favouritewidget', 'Liquiditywidget','Paymentcreation','Paymentwidget'];
                var check = docLink.roleDetails;
                var rolearray=check.split(",");
                for (var i = 0; i < rolearray.length; i++) {
                    if (role.indexOf(rolearray[i]) > -1){
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                        //return true;
                    }
                    else
                    {   $(this).addClass("error");
                        $(".errorAddressScope").text("Role Details are not as per options");
                        this.$scope.docLink.roleDetails=null;
                        return false;
                    }
                }
            }
            if (addressscope != "None" && addressscope == "UserId") {
                var userId = ['Bhavin', 'Harshal', 'Savan', 'Vimal'];

                var check = docLink.userId;
                for (var i = 0; i < userId.length; i++) {
                    if (userId.indexOf(check) > -1){
                        /*    alert("Group Details are not as per options")
                         this.$window.location.href = "./DocumentAdd";*/
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                        //return true;
                    }
                    else
                    {
                        $(this).addClass("error");
                        $(".errorAddressScope").text("Select any User from Bhavin,Harshal,Vimal,Savan only");
                        this.$scope.docLink.userId=null;
                        return false;
                    }
                }
            }
        }

        public docvalidcheck(Doc) {
            var doc = Doc;
            // var docLink = this.$scope.docLink;
            var documentType = doc.documentType;
            if (documentType != "" && documentType !=undefined) {
                var type = ['None', 'Contract', 'Account_Statement', 'Information', 'Offer'];
                var check = documentType;
                for (var i = 0; i < type.length; i++) {
                    if (type.indexOf(check) > -1){
                            /*alert("Group Details are not as per options")
                         this.$window.location.href = "./DocumentAdd";*/
                        $(this).removeClass("error");
                        $(".errorDocType").text("");
                      //  return true;
                    }
                    else
                    {
                        $(this).addClass("error");
                        $(".errorDocType").text("Select DocumentType from option");
                        this.$scope.doc.documentType=null;
                        return false;
                    }
                }
            }
            var addressScope = doc.addressScope;
           /* if (addressScope == "" || addressScope ==undefined) {
                this.$scope.doc.addressScope="None";
            }*/
            if (addressScope!="" && addressScope!=undefined) {
                var scope = ['None', 'Group', 'Role', 'UserId'];
                var check = addressScope;
                for (var i = 0; i < scope.length; i++) {
                    if (scope.indexOf(check) > -1){
                        /*alert("Group Details are not as per options")
                         this.$window.location.href = "./DocumentAdd";*/
                        $(this).removeClass("error");
                        $(".errorAddressScope").text("");
                        //return true;
                    }
                    else
                    {
                        $(this).addClass("error");
                        $(".errorAddressScope").text("Select Addressscope  from option");
                        this.$scope.doc.addressScope=null;
                        return false;
                    }
                }
            }
            var documentStatus = doc.documentStatus;
            if (documentStatus!="" && documentStatus!=undefined) {
                var status = ['None','ForYourInformation'];
                var check = documentStatus;
                for (var i = 0; i < status.length; i++) {
                    if (status.indexOf(check) > -1){
                        /*alert("Group Details are not as per options")
                         this.$window.location.href = "./DocumentAdd";*/
                        $(this).removeClass("error");
                        $(".errorDocStatus").text("");
                        //return true;
                    }
                    else
                    {
                        $(this).addClass("error");
                        $(".errorDocStatus").text("Select Document Status  from option");
                       // $('#ddldocumentStatus').focus();
                        this.$scope.doc.documentStatus=null;
                        return false;
                    }
                }
            }
            var creationDate = doc.creationDate;
            if (creationDate!="" && creationDate!=undefined) {
                var today = new Date();
                var dd = today.getDate();
                var mm = today.getMonth()+1; //January is 0!

                var yyyy = today.getFullYear();
                if(dd<10){
                    dd='0'+dd;
                }
                if(mm<10){
                    mm='0'+mm;
                }
                var today = yyyy+'-'+mm+'-'+dd;
                if(creationDate!="" && creationDate <=today) {
                    /!* alert("Please Select ValidFrom date first");*!/
                    $(this).removeClass("error");
                    $(".errorCreationDate").text("");
                }
                else {
                    $(this).addClass("error");
                    $(".errorCreationDate").text("CreationDate  should be <=today's date");
                    $('#txtcreationDate').focus();
                    return false;
                }
            }
            var validFrom = doc.validFrom;
            if (validFrom!="" && validFrom!=undefined) {
                var importDate = doc.importDate;

                if(validFrom!="" && validFrom >=importDate) {
                    /* alert("Please Select ValidFrom date first");*/
                    $(this).removeClass("error");
                    $(".errorValidFrom").text("");
                }
                else {
                    $(this).addClass("error");
                    $(".errorValidFrom").text("ValidFrom Date should be >=importDate or today");
                    $('#txtvalidFromDate').focus();
                    return false;
                }
            }
            var validTo = doc.validTo;
            if (validTo!="" && validTo!=undefined) {
                    if(validFrom!=null && validTo>=validFrom){

                    /* alert("Please Select ValidFrom date first");*/
                    $(this).removeClass("error");
                    $(".errorValidTo").text("");
                }
                else {
                    $(this).addClass("error");
                    $(".errorValidTo").text("Enter ValidFromDate First & validTo must be >=validFrom");
                    $('#txtvalidToDate').focus();
                    return false;
                }
            }
            var documentTag=doc.documentTag;
            if (documentTag!="" && documentTag!=undefined) {
                var tag = ['ProjectA', 'ProjectB', 'ProjectC'];
                var check = doc.documentTag;
                var doctagarray=check.split(",");
                for (var i = 0; i < doctagarray.length; i++) {
                    if (tag.indexOf(doctagarray[i]) > -1){
                        $(this).removeClass("error");
                        $(".errorDocTag").text("");
                    }
                    else
                    {
                        $(this).addClass("error");
                        $(".errorDocTag").text("DocumentTag Details are not as per options");
                        this.$scope.doc.documentTag=null;
                        return false;
                    }
                }
            }
            var documentName = doc.documentName;
            if (documentName!="" && documentName!=undefined) {
                var regExAlphabetic = /^[a-zA-Z0-9-.\s]*$/;
                if (documentName.match(regExAlphabetic)) {
                    $(this).removeClass("error");
                    $(".errorDocName").text("");
                } else {
                    $(this).addClass("error");
                    $(".errorDocName").text("Enter Valid DocumentName");
                    return false;
                }
            }
            var documentDescription = doc.documentDescription;
            if (documentDescription!="" && documentDescription!=undefined) {
                var regExAlphabetic = /^[a-zA-Z0-9-.\s]*$/;
                if (documentDescription.match(regExAlphabetic)) {
                    $(this).removeClass("error");
                    $(".errorDocDescription").text("");
                } else {
                    $(this).addClass("error");
                    $(".errorDocDescription").text("Enter Valid DocumentDescription");
                    return false;
                }
            }
        }
        //SaveDocument
        saveDocument() {
            debugger;
            this.doc = this.$scope.doc;
            this.docLink =this.$scope.docLink;

            if(this.docLink!=null){
            var abc = this.validationcheck(this.docLink);
            if(abc==false)
            {
                return false;
            }
            }
            if(this.$scope.doc.addressScope=="" || this.$scope.doc.addressScope==undefined)
            {
                  this.doc.addressScope="None";
                //this.doc=this.doc.addressScope;
            }
            if(this.$scope.doc.importDate ==null || this.$scope.doc.importDate==undefined)
            {
                this.doc.importDate=this.doc.creationDate;
                //this.doc=this.doc.addressScope;
            }

            if(this.doc!=null){
                var bcd = this.docvalidcheck(this.doc);
                if(bcd==false)
                {
                    return false;
                }
            }
      /*      var data = this.$scope.doc.addressScope;

            var link =this.$scope.docLink;


            if(data!="None" && link==undefined)
            {
                $(this).addClass("error");
                $(".errorAddressScope").text("Select Group or Role or UserId Details");
                return false;
            }
            else
            {
                $(this).removeClass("error");
                $(".errorAddressScope").text("");
                return true;
            }*/

            /*if(addressscope!="None" && addressscope=="Group")
            {
                if(groupDetails!="")
                {
                    $(this).removeClass("error");
                    $(".errorAddressScope").text("");
                    return true;
                }
                else
                {
                    $(this).addClass("error");
                    $(".errorAddressScope").text("Select Group Details");
                    return false;
                }
            }
            if(addressscope!="None" && addressscope=="Role")
            {
                if(roleDetails!="")
                {
                    $(this).removeClass("error");
                    $(".errorAddressScope").text("");
                    return true;
                }
                else
                {
                    $(this).addClass("error");
                    $(".errorAddressScope").text("Select Role Details");
                    return false;
                }
            }
            if(addressscope!="None" && addressscope=="Group")
            {
                if(userId!="")
                {
                    $(this).removeClass("error");
                    $(".errorAddressScope").text("");
                    return true;
                }
                else
                {
                    $(this).addClass("error");
                    $(".errorAddressScope").text("Enter proper User Details");
                    return false;
                }
            }*/


            /*  this.doc = this.$scope.doc;
             var file = this.$scope.file;
             this.doc.documentTag = this.$scope.documentTag.join(", ");
             this.doc.groupDetails = this.$scope.groupDetails.join(", ");
             this.doc.roleDetails = this.$scope.roleDetails.join(", ");


             if (file != null || file != undefined) {
             var name = file.name;
             var uploadUrl = "./fileUpload";
             this.fileUploadService.uploadFile(file, uploadUrl);
             this.doc.documentName = name;
             }
             */
            let file = this.$scope.file;

            if (file != null || file != undefined) {
                this.fileUploadService.uploadFile(file, "./fileUpload").then((data:string) => {
                    debugger;
                    let fileData:MasterDocumentDTO = JSON.parse(data);
                    this.doc = this.$scope.doc;
                    this.docLink =this.$scope.docLink;

                    this.$scope.creationDate = this.convertDate(this.$scope.doc.creationDate);

                    this.doc.documentId = fileData.documentId;
                    this.doc.filePath = fileData.filePath;
                    this.doc.fileSize = fileData.fileSize;
                    //this.doc.documentName = fileData.documentName;

                    if(this.$scope.doc.addressScope =="UserId") {
                        if (this.$scope.docLink != undefined) {
                            this.docLink.userId = this.$scope.docLink.userId;
                            var objToString = JSON.stringify(this.docLink);
                            this.doc.documentLinkDTO = JSON.parse(objToString);
                        }
                    }
                /*    if(this.$scope.doc.addressScope =="UserId") {
                        if(this.$scope.docLink != undefined) {
                            this.docLink.userId = this.$scope.docLink.userId;
                            var objToString = JSON.stringify(this.docLink);
                            this.doc.documentLinkDTO = JSON.parse(objToString);
                        }
                    }*/
                    if(this.$scope.doc.addressScope =="Group") {
                        //alert(this.$scope.docLink);
                        //alert(this.$scope.docLink.groupDetails);
                        if(this.$scope.docLink != undefined)
                        {
                            this.docLink.groupDetails = this.$scope.docLink.groupDetails.join(",");
                            var something = this.docLink.groupDetails;
                            var formated = "{"+'"groupDetails"'+":"+'"'+something+'"'+"}";
                            this.doc.documentLinkDTO  = JSON.parse(formated);
                        }
                    }
                    if(this.$scope.doc.addressScope =="Role") {
                        if(this.$scope.docLink != undefined) {
                            this.docLink.roleDetails = this.$scope.docLink.roleDetails.join(",");
                            var something = this.docLink.roleDetails;
                            var formated = "{" + '"roleDetails"' + ":" + '"' + something + '"' + "}";
                            this.doc.documentLinkDTO = JSON.parse(formated);
                        }
                    }
                    if(this.$scope.doc.documentTag!=undefined) {
                        this.doc.documentTag = this.$scope.doc.documentTag.join(",");
                    }
                    this.DocumentListService.SaveDocument(this.$scope, this.doc).then((response) =>{
                        debugger;
                        if(response.status != undefined) {
                            alert("Error Code : " +response.error);
                            return false;
                        }
                        if(response.fieldErrorDTO != null) {
                            for (var i = 0; i < response.fieldErrorDTO.length; i++) {
                                var field = response.fieldErrorDTO[i].field.toString();
                                var message = response.fieldErrorDTO[i].message.toString();
                                var id ="#"+field ;
                                $(id).html (message);
                            }
                        }
                        if(response.documentId != 0) {
                            alert("Data Submitted Succsesfully!");
                            this.$window.location.href="./DocumentList";
                        }else {
                            alert("Somwthing Went Wrong");
                        }
                    }).catch((error) =>{
                        debugger;
                        alert("error: "+error);
                    });
                });
            }
            else{
                alert("File is undefined");
            }
        }
        //updateDocument
        updateDocument(){
            debugger;
            alert("in updaet");
            this.doc = this.$scope.doc;
            //this.list = this.$scope.list;
            this.doc.documentLinkDTO = this.$scope.doc.documentLinkDTO;
           // this.$scope.docLink = this.doc.documentLinkDTO;
            /*
             this.docLink = this.doc.documentLinkDTO;
            this.$scope.docLink = this.doc.documentLinkDTO;
            this.docLink = this.$scope.doc.documentLinkDTO;
            this.$scope.docLink = this.$scope.doc.documentLinkDTO;*/

            this.doc.documentTag = this.$scope.doc.documentTag;
           // this.doc.creationDate=new Date("yyyy/MM/dd").parse(this.$scope.creationDate);
            this.doc.creationDate= this.convertDate(new Date(this.$scope.creationDate));
            this.doc.importDate= this.convertDate(new Date(this.$scope.importDate));
            this.doc.validFrom= this.convertDate(new Date(this.$scope.validFrom));
            this.doc.validTo= this.convertDate(new Date(this.$scope.validTo));


           /* this.doc.validFrom=new Date(this.$scope.validFrom);
            this.doc.validTo=new Date(this.$scope.validTo);
*/

            this.doc = this.$scope.doc;

            this.docLink =this.$scope.docLink;
            if(this.docLink!=null){
                var abc = this.validationcheck(this.docLink);
                if(abc==false)
                {
                    return false;
                }
            }
            if(this.docLink == null)
            {
                this.$scope.docLink = this.doc.documentLinkDTO;
                this.docLink =this.$scope.docLink;
                var abc = this.validationLinkcheck(this.docLink);
                if(abc==false)
                {
                    return false;
                }
            }

          /*  if(this.docLink!=null){
                var abc = this.validationcheck(this.docLink);
                if(abc==false)
                {
                    return false;
                }
            }*/
            if(this.$scope.doc.addressScope=="" || this.$scope.doc.addressScope==undefined)
            {
                this.doc.addressScope="None";
                //this.doc=this.doc.addressScope;
            }
            if(this.doc!=null){
                var bcd = this.docvalidcheck(this.doc);
                if(bcd==false)
                {
                    return false;
                }
            }

            /*this.doc.creationDate = this.$scope.creationDate;
            this.doc.validFrom = this.$scope.validFrom;
            this.doc.validTo = this.$scope.validTo;*/

            //this.$scope.doc.creationDate=this.$scope.creationDate;
          //  this.$scope.validFrom = this.convertDate(this.$scope.validFrom);
           // this.$scope.validTo = this.convertDate(this.$scope.validTo);

           // this.$scope.doc.creationDate=this.$scope.creationDate;
            /*     var creationDate = this.$scope.creationDate;
                 var validFrom = this.$scope.doc.validFrom;
                 var validTo = this.$scope.doc.validTo;

                 //name and descriptiom


                 this.doc.creationDate = creationDate;
                 this.doc.validFrom = validFrom;
                 this.doc.validTo = validTo;*/

            if(this.$scope.doc.addressScope =="UserId" && this.$scope.docLink != undefined) {
                this.docLink.userId = this.$scope.docLink.userId;
                var objToString= JSON.stringify(this.docLink);
                this.doc.documentLinkDTO = JSON.parse(objToString);
            }
            if(this.$scope.doc.addressScope =="Group" && this.$scope.docLink != undefined) {
               // this.docLink.groupDetails = this.$scope.docLink.groupDetails.join(",");
                var something = this.docLink.groupDetails;
                var formated = "{"+'"groupDetails"'+":"+'"'+something+'"'+"}";
                this.doc.documentLinkDTO  = JSON.parse(formated);
            }
            if(this.$scope.doc.addressScope =="Role" && this.$scope.docLink != undefined) {
               // this.docLink.roleDetails = this.$scope.docLink.roleDetails.join(",");
                var something = this.docLink.roleDetails;
                var formated = "{"+'"roleDetails"'+":"+'"'+something+'"'+"}";
                this.doc.documentLinkDTO  = JSON.parse(formated);
            }
            if(this.doc.documentTag!=undefined) {
                this.doc.documentTag = this.$scope.documentTag.join(",");
            }


            this.DocumentListService.UpdateDocument(this.$scope, this.doc).then((response) =>{
                // debugger;
                // if(data != undefined){
                //     alert("Changes updated succesesfully");
                //     this.$window.location.href="./DocumentList";
                // } else{alert("Something went wrong");


                debugger;
                if(response.status != undefined) {
                    alert("Error Code : " +response.error);
                    return false;
                }
                if(response.fieldErrorDTO != null) {
                    debugger;
                    for (var i = 0; i < response.fieldErrorDTO.length; i++) {
                        var field = response.fieldErrorDTO[i].field.toString();
                        var message = response.fieldErrorDTO[i].message.toString();
                        var id ="#"+field ;
                        alert(id);
                        $(id).html (message);
                    }
                }
                if(response.documentId != 0) {
                    alert("Data Submitted Succsesfully!");
                    this.$window.location.href="./DocumentList";
                }else {
                    alert("Somwthing Went Wrong");
                }
            }).catch(err =>{
                alert("erro ocured : "+err);
            });
        }
        //uloadPDF Validation
        public validateFiles = function (file) {
            debugger;
            if(file.size>5242880) {
                alert("File Size Should Not bE greter then 5 MB")
            }
        }
        //onDelete
        onDelete(id,name) {
            var options: ng.ui.bootstrap.IModalSettings = {
                template:'<div class="modal-header"><h3 class="modal-title">Delete Document</h3></div><div class="modal-body">The Document With Name ' + name + ' will be deleted.Are you sure you want to continue?</div><div class="modal-footer"><button class="btn btn-primary" type="button" ng-click="Dctrl.save(' + id + ')">Ok</button><button class="btn btn-default" type="button" ng-click="Dctrl.cancel()">Cancel</button></div>',
                controller: 'DocumentPopupController as Dctrl',
                windowClass: 'app-modal-window',
                resolve: {}
            };
            this.$modal.open(options).result
                .then(updatedItem => this.onConfirm(updatedItem));
        }
        onConfirm(item:any):void {}

        //convert date
        /*   public convertDate(timestamp: any) {
         var d = new Date(timestamp);
         var formattedDate =  d.getFullYear()+ "-" + (d.getMonth() + 1) + "-" + d.getDate();
         return formattedDate;
         }*/
        public convertDate(timestamp: any) {
            var d = new Date(timestamp);

            if(d.getMonth()<=9) {
                var curMonth = d.getMonth() +1;
                var month = "0"+ curMonth;
            }
            else{
                var  month = d.getMonth() +1;
            }
            if(d.getDate()<=9) {
                var curDay = d.getDate();
                var day = "0"+ curDay;
            }
            else{
                var  day =d.getDate();
            }
            var formattedDate =  d.getFullYear()+ "-" + month + "-" + day;
            return formattedDate;
        }
        //handle Click Event on grid raw
        public onRawClick(num:any) {
            var grid = $("#kGrid").data().kendoGrid;
            var selectedRow = grid.select();
            var selectedDataItem = grid.dataItem(selectedRow);
            var docId = selectedDataItem.documentId;
            this.$window.location.href ="DocumentDetail?DocumentId="+docId+""
        }
    }
    angular.module("DocumentList").controller("DocumentListController", DocumentListController);
}