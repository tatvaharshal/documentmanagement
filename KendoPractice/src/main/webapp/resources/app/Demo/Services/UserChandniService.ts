/// <reference path='../../_allChandni.ts' />

module DemoChandni {
    'use strict';

    export class UserChandniService implements IUserChandniService {

        private _http: ng.IHttpService;
        private $q: ng.IQService;


        static $inject = ["$http", "$q"];

        constructor(http: ng.IHttpService, $q: ng.IQService) {
            this._http = http;
            this.$q = $q;
        }

        public GetUserList($scope: ICOCOChandniScope): ng.IHttpPromise<UserChandni[]> {
            return this._http.get('/chandni/chandniGetUserList')
                .then(this.success)
                .catch(this.fail);
        }

        public deleteUser($scope: ICOCOChandniScope, userId: any): ng.IHttpPromise<string> {
            return this._http.post('/chandni/deleteUser', userId)
                .then(this.success)
                .catch(this.fail);
        }


        public addUpdateUser($scope: ICOCOChandniScope, user: any): ng.IHttpPromise<string> {
            if ($scope.userChandni.userId == 0) {
                return this._http.post("/chandni/createUser", user)
                    .then(this.success)
                    .catch(this.fail);
            }
            else {
                return this._http.post("/chandni/updateUser", user)
                    .then(this.success)
                    .catch(this.fail);
            }
        }

        public FindUserByEmail($scope: ICOCOChandniScope, email: string): ng.IHttpPromise<boolean> {
            return this._http.get('/chandni/findUserByEmail?email=' + email)
                .then(this.success)
                .catch(this.fail);
        }


        public GetUsersByCity($scope: ICOCOChandniScope): ng.IHttpPromise<UserChandni> {
            return this._http.get('/chandni/getUsersByCity')
                .then(this.success)
                .catch(this.fail);
        }

        private success: (response: any) => {} = (response) => response.data;

        private fail: (error: any) => {} = (error) => {
            var msg = error.data.message;
            var reason = 'query for people failed.';
            return this.$q.reject(msg);
        }
    }

    angular.module("DemoChandni").service("userChandniService", UserChandniService);
}