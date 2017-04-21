/// <reference path='../../_all_harshal.ts' />

module HarshalDemo {
    'use strict';

    export class UserService_harshal implements IUserService_harshal {

        private _http: ng.IHttpService;
        private $q: ng.IQService;
        private _url : any; 


        static $inject = ["$http", "$q"];

        constructor(http: ng.IHttpService, $q: ng.IQService) {
            this._http = http;
            this.$q = $q;
            this._url = localStorage._url;
        }



        public getUserList_harshal($scope: ICOCOScope_harshal): ng.IHttpPromise<UserMaster_harshal[]> {
            return this._http.get(this._url + '/user_harshal/usersListData_harshal')
                .then(this.success)
                .catch(this.fail);
        }
        
        
        public saveUser_harshal($scope: ICOCOScope_harshal, user : UserMaster_harshal): ng.IHttpPromise<string> {
        	return this._http.post(this._url + '/user_harshal/userSave_harshal', user)
        		.then(this.success)
        		.catch(this.fail);
	    }
        
        public deleteUser_harshal($scope: ICOCOScope_harshal, userId : any): ng.IHttpPromise<string> {
        	return this._http.post(this._url + '/user_harshal/deleteUser_harshal', userId)
        		.then(this.success)
        		.catch(this.fail);
	    }
        
        public getUserDetailById_harshal($scope: ICOCOScope_harshal, userId : any): ng.IHttpPromise<UserMaster_harshal> {
        	return this._http.post(this._url + '/user_harshal/getUserDetailById_harshal', userId)
    		.then(this.success)
    		.catch(this.fail);
        }
        
        public updateUser_harshal($scope: ICOCOScope_harshal, user : UserMaster_harshal): ng.IHttpPromise<string> {
        	return this._http.post(this._url + '/user_harshal/userUpdate_harshal', user)
        		.then(this.success)
        		.catch(this.fail);
	    }
        
        public getUserCountPerCity_harshal($scope: ICOCOScope_harshal): ng.IHttpPromise<string[]> {
            return this._http.get(this._url + '/user_harshal/getUserCountPerCity_harshal')
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

    angular.module("HarshalDemo").service("userService_harshal", UserService_harshal);

}