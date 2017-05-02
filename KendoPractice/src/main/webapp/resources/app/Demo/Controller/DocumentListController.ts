/// <reference path='../../_allDocumentList.ts' />
module DocumentList {
    export class DocumentListController extends BaseDocumentListController {
        'use strict';

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
        }
    }
    angular.module("DocumentList").controller("DocumentListController", DocumentListController);
}
