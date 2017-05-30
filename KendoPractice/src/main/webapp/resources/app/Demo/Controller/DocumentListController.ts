/**
 * Created by pca43 on 11-May-17.
 */
/// <reference path='../../_allDocumentList.ts' />
module DocumentList {
    import Upload = kendo.ui.Upload;
    export class DocumentListController extends BaseDocumentListController {
        //variables
        'use strict';
        doc : MasterDocumentDTO;
        docLink:DocumentLinkDTO;
        list:Multivalue;
        isUploaded:any = false;
        documentTags:string[];

        public _window: any;
        public _filter: any;
        status : any;
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
        constructor(private $scope: IDocumentListScope, private $location: ng.ILocationService, private $window: ng.IWindowService, private $modal: ng.ui.bootstrap.IModalService, private DocumentListService: IDocumentListService, private $filter: ng.IFilterService,private fileUploadService: IFileUploadService)
        {
            super($scope);
            $scope.vm = this;
            this.DocumentListService.GetDocumentList(this.$scope).then((data) => {
                $scope.mainGridOptions = {
                    dataSource: {
                        type:"json",
                        data:data,
                        pageSize: 5,
                    },
                    columnMenu: true,
                    filterable:{
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
                        title:"Document name"
                    },{
                        field: "creationDate",
                        title:"Creation date",
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
                        template:function (dataitem) {
                            var d = new Date(dataitem.creationDate);
                            var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                            return formattedDate;
                        }
                    },{
                        field: "importDate",
                        title:"import Date",
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
                        template:function (dataitem) {
                            var d = new Date(dataitem.importDate);
                            var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                            return formattedDate;
                        }
                    },{
                        field: "fileSize",
                        title:"File size(KB)"
                    },{
                        field: "documentDescription",
                        title:"description"
                    },
                        {
                            field: "documentStatus",
                            title:"Document status"
                        },
                        {
                            field: "documentType",
                            filterable: {
                                multi: true
                            },
                            title:"Document Type"
                        },
                        {
                            field: "addressScope",
                            filterable: {
                                multi: true
                            },
                            title:"Addres scope"
                        },{
                            field: "verticalData",
                            title:"Vertical Id"
                        },{
                            field: "validFrom",
                            title:"Valid from",
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
                            template:function (dataitem) {
                                var d = new Date(dataitem.validFrom);
                                var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                                return formattedDate;
                            }
                        },{
                            field: "validTo",
                            title:"Valid to",
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
                            template:function (dataitem) {
                                var d = new Date(dataitem.validTo);
                                var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                                return formattedDate;
                            }
                        },
                        {
                            field: "documentTag",
                            title:"Document tag"
                        }
                    ]
                };
            });
        }

        //Methods

        // Init
        initialiseEditPage(id:number,$scope) {
            debugger;
            this.DocumentListService.GetDocumentByID(id).then((data) => {
                debugger;
                this.doc = data;
                this.$scope.doc = data;

                this.$scope.creationDate = this.convertDate(this.doc.creationDate);
                this.$scope.validFrom = this.convertDate(this.doc.validFrom);
                this.$scope.validTo = this.convertDate(this.doc.validTo);

                var temp = this.$scope.doc.documentTag.split(",");
                this.$scope.documentTags =this.$scope.doc.documentTag.split(",");
                if(this.doc.addressScope=="None") {
                    this.$scope.flag= false;
                }
                else {
                    this.$scope.flag= true;
                }
            });
        }

        //SaveDocument
        saveDocument() {
            debugger;
            let file = this.$scope.file;
            if (file != null || file != undefined) {
                this.fileUploadService.uploadFile(file, "./fileUpload").then((data:string) => {
                    debugger;
                    let fileData:MasterDocumentDTO = JSON.parse(data);
                    this.doc = this.$scope.doc;
                    this.docLink =this.$scope.docLink;

                    this.doc.documentId = fileData.documentId;
                    this.doc.filePath = fileData.filePath;
                    this.doc.fileSize = fileData.fileSize;

                    if(this.$scope.doc.addressScope =="UserId") {
                        this.docLink.userId = this.$scope.docLink.userId;
                        var objToString= JSON.stringify(this.docLink);
                        // var final ="["+objToString+"]";
                        this.doc.documentLinkDTO = JSON.parse(objToString);
                    }

                    if(this.$scope.doc.addressScope =="Group") {
                        this.docLink.groupDetail = this.$scope.docLink.groupDetails.join(",");
                        var something = this.docLink.groupDetail;
                        var formated = "{"+'"groupDetails"'+":"+'"'+something+'"'+"}";
                        //   var final ="["+formated+"]";
                        this.doc.documentLinkDTO  = JSON.parse(formated);
                    }

                    if(this.$scope.doc.addressScope =="Role") {
                        this.docLink.roleDetail = this.$scope.docLink.roleDetails.join(",");
                        var something = this.docLink.roleDetail;
                        var formated = "{"+'"roleDetails"'+":"+'"'+something+'"'+"}";
                        //  var final ="["+formated+"]";
                        this.doc.documentLinkDTO  = JSON.parse(formated);
                    }

                    if(this.$scope.list!=undefined) {
                        this.doc.documentTag = this.$scope.list.documentTags.join(",");
                    }

                    this.DocumentListService.SaveDocument(this.$scope, this.doc).then((response) =>{
                        debugger;
                        alert(response.status);
                        if(data !=undefined) {
                            alert("Data Submitted Succsesfully!");
                            this.$window.location.href="./DocumentList";
                        }else {
                            alert("Somwthing Went Wrong");
                        }
                    }).catch((err:string) =>{
                        debugger;
                        alert("error: "+err);
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
            this.list = this.$scope.list;
            this.docLink =this.$scope.docLink;
            this.documentTags = this.$scope.documentTags;

            var creationDate = new Date(this.$scope.creationDate);
            var validFrom = new Date(this.$scope.validFrom);
            var validTo = new Date(this.$scope.validTo);

            this.doc.creationDate = creationDate;
            this.doc.validFrom = validFrom;
            this.doc.validTo = validTo;

            if(this.$scope.doc.addressScope =="UserId" && this.$scope.docLink != undefined) {
                this.docLink.userId = this.$scope.docLink.userId;
                var objToString= JSON.stringify(this.docLink);
                this.doc.documentLinkDTO = JSON.parse(objToString);
            }

            if(this.$scope.doc.addressScope =="Group" && this.$scope.docLink != undefined) {
                this.docLink.groupDetail = this.$scope.docLink.groupDetails.join(",");
                var something = this.docLink.groupDetail;
                var formated = "{"+'"groupDetails"'+":"+'"'+something+'"'+"}";
                this.doc.documentLinkDTO  = JSON.parse(formated);
            }

            if(this.$scope.doc.addressScope =="Role" && this.$scope.docLink != undefined) {
                this.docLink.roleDetail = this.$scope.docLink.roleDetails.join(",");
                var something = this.docLink.roleDetail;
                var formated = "{"+'"roleDetails"'+":"+'"'+something+'"'+"}";
                this.doc.documentLinkDTO  = JSON.parse(formated);
            }

            if(this.documentTags!=undefined) {
                this.doc.documentTag = this.$scope.documentTags.join(",");
            }

            this.DocumentListService.UpdateDocument(this.$scope, this.doc).then((data) =>{
                debugger;
                if(data != undefined){
                    alert("Changes updated succesesfully");
                    this.$window.location.href="./DocumentList";
                } else{alert("Something went wrong");}
            }).catch(err =>{
                debugger;
                alert("erro ocured : "+err);
            });
        }

        //uloadPDF Validation
        public validateFiles = function (file:any) {
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
        public convertDate(timestamp: any) {
            var d = new Date(timestamp);
            var formattedDate =  d.getFullYear()+ "-" + (d.getMonth() + 1) + "-" + d.getDate();
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



