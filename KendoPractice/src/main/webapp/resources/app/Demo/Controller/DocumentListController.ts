/// <reference path='../../_allDocumentList.ts' />
module DocumentList {
    import Upload = kendo.ui.Upload;

    export class DocumentListController extends BaseDocumentListController {
        'use strict';
        doc : DocumentListModel;
        status : any;
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
            this.DocumentListService.GetUserList(this.$scope).then((data) => {
                $scope.userChandniList = data;
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
                        field: "documentData.documentName",
                        title:"Document name"
                    },{
                        field: "documentData.creationDate",
                        title:"Creation date"
                    },{
                        field: "documentData.validFrom",
                        title:"Valid From"
                    },{
                        field: "documentData.fileSize",
                        title:"File size(KB)"
                    },{
                        field: "documentData.documentDescription",
                        title:"description"
                    },
                        {
                            field: "documentData.documentStatus",
                            title:"Document status"
                        },
                        {
                            field: "documentData.documentType",
                            title:"Document Type"
                        },
                        {
                            field: "documentData.addressScope",
                            title:"Addres scope"
                        },{
                            field: "documentData.verticalData",
                            title:"Vertical Id"
                        },{
                            field: "documentData.validFrom",
                            title:"Valid from"
                        },{
                            field: "documentData.validTo",
                            title:"Valid to"
                        },
                        {
                            field: "documentData.documentTag",
                            title:"Document tag"
                        },
                        {
                            field: "documentData.importDate",
                            title:"import Date"
                        }
                    ]
                };
            });
            // $scope.vm.uploadFiles = function (file, errFiles) {
            //     alert("File size: "+file.size + "Bit");
            // }
        }
        // saving Document
        saveDocument() {
            debugger;
            this.doc = this.$scope.doc;
            this.DocumentListService.SaveDocument(this.$scope, this.doc).then((data) =>{
                debugger;
                if(data == 'CREATED'){
                    alert("file uploadede succsesfully");
                } else{alert("fail");}
            }).catch(err =>{
                if(err == 409){
                    alert("erro ocured");
                }
            });
        }

        //uloadPDF
       // uploadFiles = function (file) {
       //      debugger;
       //      if(file.size>5242880)
       //      {
       //          alert("File Size Should Not bE greter then 5 MB")
       //      }
       //      else {
       //          alert("File size: "+file.size + "Bit");
       //          this.file = file;
       //          alert(file);
       //          this.DocumentListService.UploadDocument(this.$scope, this.file).then((data) =>{
       //              debugger;
       //              if(data == 'success'){
       //              }
       //          }).catch(err =>{
       //              if(err == 409){
       //              }
       //          });
       //      }
       //  }




        // saveDocument(){
        //     debugger;
        //     this.doc = this.$scope.doc;
        //
        //     this.DocumentListService.SaveDocument(this.$scope, this.doc).then((data) =>{
        //         if(data.success == 'success'){
        //             alert("Succses");
        //             //this.$window.location.href="/userChirag/userList#/?status=save";
        //         }
        //     }).catch(err =>{
        //         alert("Fail");
        //         if(err == 409){
        //             //this.$window.location.href="/userChirag/userList#/?status=conflict";
        //         }
        //     });
        // }
        //uloadPDF
        uploadFiles = function (file:any) {
            debugger;
            if(file.size>5242880)
            {
                alert("File Size Should Not bE greter then 5 MB")
            }
            else {
                if (file != null || file != undefined) {
                    // var name = file.name;
                    // var uploadUrl = "./fileUpload";
                    // file.filePath = file;
                    // file.fileSize = file.size;
                    // file.documentName = file.name;
                    // file.documentDescription = file.type;
                    this.fileUploadService.uploadFile(file, "./fileUpload");
                }
                }
            }
        }
    angular.module("DocumentList").controller("DocumentListController", DocumentListController);

    }



