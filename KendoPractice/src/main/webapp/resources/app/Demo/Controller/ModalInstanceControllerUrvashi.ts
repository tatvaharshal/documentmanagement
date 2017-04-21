/// <reference path='../../_allUrvashi.ts' />

module DemoUrvashi {
    export class ModalInstanceControllerUrvashi extends BaseControllerUrvashi{
        'use strict';
        
        public static $inject = ['$scope', '$modalInstance', '$window', 'userServiceUrvashi'];
        
        constructor(private $scope: IUrvashiScope, private $modalInstance: ng.ui.bootstrap.IModalServiceInstance, private $window: ng.IWindowService, private userServiceUrvashi: IUserServiceUrvashi) {
            super($scope);
        }

        onConfirm(id : any): void {
            this.userServiceUrvashi.deleteUser(id).then((data) => {
            this.$modalInstance.dismiss('cancel');
				if(data === 'success'){
					this.$window.location.href="UsersListUrvashi";
				}
           	});
        };

        close(): void {
            this.$modalInstance.dismiss('cancel');
        };
       
    }
    
    angular.module("DemoUrvashi").controller("modalInstanceControllerUrvashi", ModalInstanceControllerUrvashi);
}