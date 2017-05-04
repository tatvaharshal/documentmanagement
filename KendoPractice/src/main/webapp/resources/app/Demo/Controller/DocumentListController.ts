/// <reference path='../../_allDocumentList.ts' />
module DocumentList {
    import Upload = kendo.ui.Upload;
    export class DocumentListController extends BaseDocumentListController  {
        'use strict';
        doc : DocumentListModel;
        status : any;
        public _window: any;
        public _filter: any;
        public static $inject = [
            '$scope',
            '$location',
            '$window',
            '$modal',
            'DocumentListService',
            '$filter'
        ];
        /// Conctructor
        constructor(private $scope: IDocumentListScope, private $location: ng.ILocationService, private $window: ng.IWindowService, private $modal: ng.ui.bootstrap.IModalService, private DocumentListService: IDocumentListService, private $filter: ng.IFilterService)
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
                        field: "userFirstName",
                        title:"First Name"
                    },{
                        field: "userEmail",
                        title:"Email"
                    },{
                        field: "userPhoneNumber",
                        title:"PhoneNumber"
                    },{
                        field: "userAddress",
                        title:"Address"
                    },{
                        field: "userCity",
                        title:"City"
                    }]
                };
            });
            $scope.vm.uploadFiles = function (file, errFiles) {
                alert("File size: "+file.size + "KB");
            }
        }
        saveDocument() {
            debugger;
            this.doc = this.$scope.doc;

            debugger;

            this.DocumentListService.SaveDocument(this.$scope, this.doc).then((data) =>{
                debugger;
                if(data == 'success'){
                    /*this.$window.location.href="/saveDocumentVimal";*/
                }
            }).catch(err =>{
                debugger;
                if(err == 409){
                    /*this.$window.location.href="/listdocument#/?status=conflict";*/
                }
            });
        }
    }
    angular.module("DocumentList").controller("DocumentListController", DocumentListController);
}
