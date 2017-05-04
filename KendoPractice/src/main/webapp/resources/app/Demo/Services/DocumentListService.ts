/// <reference path='../../_allDocumentList.ts' />
module DocumentList {
    'use strict';
    export class DocumentListService implements IDocumentListService {
        private _http: ng.IHttpService;
        private $q: ng.IQService;
        static $inject = ["$http", "$q"];
        constructor(http: ng.IHttpService, $q: ng.IQService) {
            this._http = http;
            this.$q = $q;
        }

        public GetUserList($scope: IDocumentListScope): ng.IHttpPromise<UserChandni[]> {
            return this._http.get('/documentList')
                .then(this.success)
                .catch(this.fail);
        }


        public SaveDocument($scope: IDocumentListScope, documentMaster : DocumentListModel): ng.IHttpPromise<string> {
            debugger;
            return this._http.post("/saveDocumentVimal/", documentMaster)
                .then(this.success)
                .catch(this.fail);
        }

        //define Succses And fail
        private success: (response: any) => {} = (response) => response.data;
        private fail: (error: any) => {} = (error) => {
            var msg = error.data.message;
            var reason = 'query for people failed.';
            return this.$q.reject(msg);
        }
    }
    angular.module("DocumentList").service("DocumentListService", DocumentListService);
}
