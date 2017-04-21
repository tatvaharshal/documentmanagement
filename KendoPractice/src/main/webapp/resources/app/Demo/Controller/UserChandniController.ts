/// <reference path='../../_allChandni.ts' />

module DemoChandni {
    export class UserChandniController extends BaseChandniController {
        'use strict';

        public _window: any;
        public _filter: any;
        public static $inject = [
            '$scope',
            '$location',
            '$window',
            '$modal',
            'userChandniService',
            '$filter'
        ];

        /// Conctructor
        constructor(private $scope: ICOCOChandniScope, private $location: ng.ILocationService, private $window: ng.IWindowService, private $modal: ng.ui.bootstrap.IModalService, private userChandniService: IUserChandniService, private $filter: ng.IFilterService) {
            super($scope);

            this.$scope.pageArray = [5, 10, 20];
            this._window = $window;
            this.$scope.statusMessageFlag = false;
            this.$scope.viewby = 5;
            this.$scope.itemsPerPage = $scope.viewby;

            var msg = localStorage.getItem("success");
            if (msg != "null") {
                localStorage.setItem("success", "null");
                this.$scope.statusMessage = msg;
                this.$scope.statusMessageFlag = true;
                setTimeout(function() {
                    this.$scope.statusMessageFlag = false;
                    this.$scope.$apply();
                }.bind(this), 3000);
            }

            this.userChandniService.GetUserList(this.$scope).then((data) => {
                $scope.userChandniList = data;
                $scope.sortType = 'userFirstName'; // set the default sort type
                $scope.sortReverse = false;  // set the default sort order
                this.$scope.viewby = 5;
                this.$scope.itemsPerPage = $scope.viewby;
                //----------------------------------

                $scope.totalItems = $scope.userChandniList.length;
                $scope.currentPage = 1;
                $scope.maxSize = 5; //Number of pager buttons to show
            });
        }

        // Init
        public Init() {
            super.BaseInit();
        }

        public onDelete(id: any) {
            this.$modal.open({
                template: '<div class="modal-header"><h3 class="modal-title">Confirm</h3></div><div class="modal-body">Are you sure want to delete this record ?</div><div class="modal-footer"><button class="btn btn-primary" type="button" ng-click="modal.onConfirm(' + id + ')">Ok</button><button class="btn btn-default" type="button" ng-click="modal.close()">Cancel</button></div>',
                controller: 'modalInstanceChandniController',
                controllerAs: 'modal'
            });
        }

        public convertDate(timestamp: any) {
            var d = new Date(timestamp);
            var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
            return formattedDate;
        }

        public onEdit(id: number) {
            this._window.location.href = '/chandni/chandni-user-form?id=' + id;
        }

        public OnCountChange(users: any) {
            if (users.length < this.$scope.totalItems && users.length <= this.$scope.itemsPerPage) {
                this.$scope.totalItems = users.length;
            }
            else if (this.$scope.search == "") {
                this.$scope.totalItems = this.$scope.userChandniList.length;
                this.$scope.currentPage = 1;
            }
        }
    }
    angular.module("DemoChandni").controller("userChandniController", UserChandniController);
}