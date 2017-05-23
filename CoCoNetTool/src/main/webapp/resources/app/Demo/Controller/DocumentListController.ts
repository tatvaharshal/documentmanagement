/**
 * Created by pca43 on 11-May-17.
 */
/// <reference path='../../_allDocumentList.ts' />
module DocumentList {
    import Upload = kendo.ui.Upload;
    //  import BaseDocumentListController = DocumentList.BaseDocumentListController;

    export class DocumentListController extends BaseDocumentListController {
        //variables
        'use strict';
        doc : MasterDocumentDTO;
        status : any;
        list:Multivalue;
        isUploaded:any = false;
        //  file:any;
        public _window: any;
        public _filter: any;
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
                debugger;
                $scope.mainGridOptions = {
                    dataSource: {
                        type:"json",
                        data:data,
                        pageSize: 5,
                    },
                    columnMenu: true,
                    filterable:true,
                    groupable: true,
                    sortable: true,
                    pageable: true,
                    columns: [{
                        field: "documentName",
                        title:"Document name"
                    },{
                        field: "creationDate",
                        title:"Creation date",
                        template:function (dataitem) {
                            var d = new Date(dataitem.creationDate);
                            var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                            return formattedDate;
                        }
                    },{
                        field: "importDate",
                        title:"import Date",
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
                            title:"Document Type"
                        },
                        {
                            field: "addressScope",
                            title:"Addres scope"
                        },{
                            field: "verticalData",
                            title:"Vertical Id"
                        },{
                            field: "validFrom",
                            title:"Valid from",
                            template:function (dataitem) {
                                var d = new Date(dataitem.validFrom);
                                var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                                return formattedDate;
                            }
                        },{
                            field: "validTo",
                            title:"Valid to",
                            template:function (dataitem) {
                                var d = new Date(dataitem.validTo);
                                var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                                return formattedDate;
                            }
                        },
                        {
                            field: "documentTag",
                            title:"Document tag"
                        },
                        {
                            title:"Edit",
                            template: '<a href="./DocumentDetail?DocumentId=#: documentId #">Edit</a>'
                        }
                    ]

                };
            });
        }


        //Methods

        //convert date
        public convertDate(timestamp: any) {
            var d = new Date(timestamp);
            var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
            return formattedDate;
        }

        // Init
        initialiseEditPage(id:number,$scope) {
            debugger;
            this.DocumentListService.GetDocumentByID(id).then((data) => {
                debugger;
                this.doc = data;
                this.$scope.doc = data;
                //this.$scope.list.documentTags = this.doc.documentTag.split(",");
            });
        }

        //SaveDocument
        saveDocument() {
            debugger;
            this.doc = this.$scope.doc;
            this.list = this.$scope.list;
            if(this.list!=undefined) {
                this.doc.documentTag = this.$scope.list.documentTags.join(", ");
            }

          /*  var file = this.$scope.myFile;
            if (file != null || file != undefined) {
                this.fileUploadService.uploadFile(file, "./fileUpload");
            }*/

           /* this.DocumentListService.SaveDocument(this.$scope, this.doc).then((data) =>{
                this.doc = data;
                alert(this.doc.documentId);
                if(data.success == 'success'){
                    alert("file uploadede succsesfully");
                    // this.$window.location.href="/userChirag/userList#/?status=save";
                } else{alert("fail");}
            }).catch(err =>{
                if(err == 409){
                    alert("erro ocured");
                    // this.$window.location.href="/userChirag/userList#/?status=conflict";
                }
            });*/


          /*  var file = this.$scope.myFile;
            if (file != null || file != undefined) {
                this.fileUploadService.uploadFile(file, "./fileUpload");
            }*/
        }


        //updateDocument
        updateDocument(){
            debugger;
            alert("in save docmethod");
            this.doc = this.$scope.doc;
            this.list = this.$scope.list;
            if(this.list!=undefined) {
                this.doc.documentTag = this.$scope.list.documentTags.join(", ");
            }

            this.DocumentListService.UpdateDocument(this.$scope, this.doc).then((data) =>{
                debugger;
                if(data == 'success'){
                    alert("file uploadede succsesfully");
                    // this.$window.location.href="/userChirag/userList#/?status=save";
                } else{alert("fail");}
            }).catch(err =>{
                if(err == 409){
                    alert("erro ocured");
                    // this.$window.location.href="/userChirag/userList#/?status=conflict";
                }
            });
        }



        //uloadPDF Validation
        validateFiles = function (file:any) {
            debugger;
            if(file.size>5242880)
            {
                alert("File Size Should Not bE greter then 5 MB")
            }
            else{
                //var file = this.$scope.file;
                if (file != null || file != undefined) {
                    this.fileUploadService.uploadFile(file, "./fileUpload");

                    //Step-2 fill up dto with other details
                }
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

    }
    angular.module("DocumentList").controller("DocumentListController", DocumentListController);

}