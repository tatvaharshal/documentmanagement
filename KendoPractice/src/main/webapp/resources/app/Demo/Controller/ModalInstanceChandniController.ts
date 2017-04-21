/// <reference path='../../_allChandni.ts' />

module DemoChandni {
    export class ModalInstanceChandniController extends BaseChandniController {
        'use strict';

        public static $inject = ['$scope', '$modalInstance', '$window', 'userChandniService'];

        constructor(private $scope: ICOCOChandniScope, private $modalInstance: ng.ui.bootstrap.IModalServiceInstance, private $window: ng.IWindowService, private userChandniService: IUserChandniService) {
            super($scope);
        }


        onConfirm(id: any): void {
            this.userChandniService.deleteUser(this.$scope, id).then((data) => {
                this.$modalInstance.dismiss('cancel');
                if (data == "Deleted") {
                    localStorage.setItem("success", "User Deleted Successfully.");
                    this.$window.location.href = "/chandni/chandni-user-list";
                }
            });
        };

        close(): void {
            this.$modalInstance.dismiss('cancel');
        };

    }

    angular.module("DemoChandni").controller("modalInstanceChandniController", ModalInstanceChandniController);
}