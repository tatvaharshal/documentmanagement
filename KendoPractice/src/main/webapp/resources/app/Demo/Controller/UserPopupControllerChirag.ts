/// <reference path='../../_all.ts' />

module Demo {
    export class UserPopupControllerChirag {
    	'use strict';
    	
    	static $inject = ['$modalInstance', "$http", "$q",'$window'];
        private _http: ng.IHttpService;
    	private $q: ng.IQService;
        
        constructor(private $modalInstance: ng.ui.bootstrap.IModalServiceInstance, http: ng.IHttpService,$q: ng.IQService,private $window: ng.IWindowService) {
        	this._http = http;
            this.$q = $q;
        }

        save(id:number) {
        	this._http.post("/userChirag/deleteUser",id);
            //this.$modalInstance.close(this.item);
            this.$window.location.href="/userChirag/userList/#/?status=delete";
        }

        cancel() {
            this.$modalInstance.dismiss('cancel');
        }
       
    }
    
    angular.module("Demo").controller("userPopupControllerChirag", UserPopupControllerChirag);
}