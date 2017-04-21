/// <reference path='../../_all.ts' />

module Demo {
    'use strict';

    export class UserServiceChirag implements IUserServiceChirag {

        private _http: ng.IHttpService;
        private $q: ng.IQService;


        static $inject = ["$http", "$q"];

        constructor(http: ng.IHttpService, $q: ng.IQService) {
            this._http = http;
            this.$q = $q;
        }


		

        
        public GetEditPage(id:number): ng.IHttpPromise<any> {
        	
            return this._http.get('/userChirag/editUser/'+id)
                .then(this.success)
                .catch(this.fail);
        }
        
        
        public GetUserByID(id:number): ng.IHttpPromise<any> {
        	
            return this._http.get('/userChirag/getUser/'+id)
                .then(this.success)
                .catch(this.fail);
        }
        
        
        public GetUserList(): ng.IHttpPromise<Array<UserMasterChirag>> {
            return this._http.get('/userChirag/getUserList')
                .then(this.success)
                .catch(this.fail);
        }

      
        public SaveUser($scope: ICOCOScope, userMaster : UserMasterChirag): ng.IHttpPromise<string> {
        	return this._http.post("/userChirag/saveUser/", userMaster)
        		.then(this.success)
        		.catch(this.fail);
	    }
       
        public UpdateUser($scope: ICOCOScope, userMaster : UserMasterChirag): ng.IHttpPromise<string> {
        	return this._http.post("/userChirag/updateUser/",userMaster)
        		.then(this.success)
        		.catch(this.fail);
	    }
        
       
        public DeleteUser($scope: ICOCOScope, id : number): ng.IHttpPromise<string> {
        	return this._http.post('/userChirag/deleteUser',id)
        		.then(this.success)
        		.catch(this.fail);
	    }
        
        public getCharts($scope: ICOCOScope): ng.IHttpPromise<string[]> {
            return this._http.get('/userChirag/getCharts')
                .then(this.success)
                .catch(this.fail);
        }
        
        private success: (response: any) => {} = (response) => response.data;

        private fail: (error: any) => {} = (error) => {
            return this.$q.reject(error.status);
        }
    }

    angular.module("Demo").service("userServiceChirag", UserServiceChirag);

}