/**
 * Created by pca43 on 09-May-17.
 */
/// <reference path='../../_allDocumentList.ts' />
module DocumentList	 {
    'use strict';
    export class FileUploadService implements IFileUploadService {
        private _http: ng.IHttpService;
        private $q: ng.IQService;
        static $inject = ["$http", "$q"];
        doc : MasterDocumentDTO;
        constructor(http: ng.IHttpService, $q: ng.IQService) {
            this._http = http;
            this.$q = $q;
        }
        public uploadFile(file: any, uploadUrl: any):ng.IHttpPromise<MasterDocumentDTO> {
            let xhr: XMLHttpRequest = new XMLHttpRequest();
            xhr.onreadystatechange = () => {
                if (xhr.readyState === 4) {
                    let test:MasterDocumentDTO = xhr.response;
                    deferred.resolve(test);
                }
            };
            xhr.open("POST", uploadUrl, true);
            let formData = new FormData();
            formData.append("file", file);
            xhr.send(formData);
            let deferred = this.$q.defer();
            return deferred.promise;

        }

        //define Succses And fail
        private success: (response: any) => {} = (response) => response.data;
        private fail: (error: any) => {} = (error) => {
            var msg = error.data.message;
            var reason = 'query for people failed.';
            return this.$q.reject(msg);
        }
    }
    angular.module("DocumentList").service("fileUploadService", FileUploadService);
}