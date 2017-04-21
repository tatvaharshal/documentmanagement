/// <reference path='../../_all_harshal.ts' />

module HarshalDemo {
    export class ModalInstanceController_harshal extends BaseController_harshal {
        'use strict';
        
        private _url : any;
        public static $inject = ['$scope', '$location','$modalInstance', '$window', 'userService_harshal'];
        
        constructor(private $scope: ICOCOScope_harshal, private $location: ng.ILocationService, private $modalInstance: ng.ui.bootstrap.IModalServiceInstance, private $window: ng.IWindowService, private userService_harshal: IUserService_harshal) {
            super($scope);
            this._url = localStorage._url;
        }

        onConfirm(userId : any): void {
            this.userService_harshal.deleteUser_harshal(this.$scope, userId).then((data) => {
                this.$modalInstance.dismiss('cancel');
                if(data == "Deleted") {
                   //this.$window.location.href= this._url + "/user_harshal/usersList_harshal";
                   this.$window.location.href= this._url + "/user_harshal/usersList_harshal/#/?status=delete";
                   //this.$scope.status="delete";
                }
            });
        };

        close(): void {
            this.$modalInstance.dismiss('cancel');
        };
       
    }
    
    angular.module("HarshalDemo").controller("modalInstanceController_harshal", ModalInstanceController_harshal);
}