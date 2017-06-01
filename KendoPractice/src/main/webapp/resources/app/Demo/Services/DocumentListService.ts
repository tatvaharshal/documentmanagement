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

        //Getting The list
        public GetDocumentList($scope: IDocumentListScope): ng.IHttpPromise<doc[]> {
            debugger;
            return this._http.get('./documentList')
                .then(this.success)
                .catch(this.fail);
        }

        //Get Document By Id
        public GetDocumentByID(id:number): ng.IHttpPromise<any> {
            debugger;
            return this._http.get('./documentListByID/'+id)
                .then(this.success)
                .catch(this.fail);
        }

        //Saving The document
        public SaveDocument($scope: IDocumentListScope, documentMaster : MasterDocumentDTO): ng.IHttpPromise<MasterDocumentDTO> {
            debugger;
            return this._http.post("./DocumentAdd/", documentMaster)
                .then(this.success)
                .catch(this.fail);
        }

        //Update The Document
        public UpdateDocument($scope: IDocumentListScope, documentMaster : MasterDocumentDTO): ng.IHttpPromise<string> {
            debugger;
            return this._http.post("./updateDocument/", documentMaster)
                .then(this.success)
                .catch(this.fail);
        }

        //define Succses And fail
        private success: (response: any) => {} = (response) => response.data;
        private fail: (error: any) => {} = (error) => error.data;

        // private fail: (error: any) => {} = (error) => {
        //     var msg = error.data.message;
        //     var reason = 'query for people failed.';
        //     return this.$q.reject(msg);
        // }
    }
    angular.module("DocumentList").service("DocumentListService", DocumentListService);
}
