/// <reference path='../../_allUrvashi.ts' />

module DemoUrvashi {
    'use strict';

    export class UserServiceUrvashi implements IUserServiceUrvashi {

        private _http: ng.IHttpService;
        private $q: ng.IQService;


        static $inject = ["$http", "$q"];

        constructor(http: ng.IHttpService, $q: ng.IQService) {
            this._http = http;
            this.$q = $q;
        }
        
        public saveUser(user: UserUrvashi): ng.IHttpPromise<string> {
        	return this._http.post("/saveUser",user)
        		.then(this.success)
        		.catch(this.fail);
	    }
        
        public listUser():ng.IHttpPromise<UserUrvashi[]>{
        	return this._http.get("/listUser")
    		.then(this.success)
    		.catch(this.fail);
        }
        
        public deleteUser(id:any):ng.IHttpPromise<string>{
        	console.log("inside service + "+ id);
        	return this._http.post("/deleteUser",id)
    		.then(this.success)
    		.catch(this.fail);
        }
        
        public updateUser(user:UserUrvashi):ng.IHttpPromise<string>{
        	return this._http.post("/updateUser",user)
    		.then(this.success)
    		.catch(this.fail);
        }
        
        public getUserById(id:number):ng.IHttpPromise<UserUrvashi>{
        	return this._http.get("/getUserById",id)
    		.then(this.success)
    		.catch(this.fail);
        }
        
        private success: (response: any) => {} = (response) => response.data;

        private fail: (error: any) => {} = (error) => {
            var msg = error.data.description;
            var reason = 'query for people failed.';
            return this.$q.reject(msg);
        }
    }
    
    angular.module("DemoUrvashi").service("userServiceUrvashi", UserServiceUrvashi);
}
