/**
 * Created by pca43 on 09-May-17.
 */
/// <reference path='../../_allDocumentList.ts' />
module DocumentList	 {
    'use strict';

    export class FileUploadService implements IFileUploadService {

        private _http: ng.IHttpService;

        static $inject = ["$http"];

        constructor(http: ng.IHttpService) {
            this._http = http;
        }

        public uploadFile(file: any, uploadUrl: any) {
            var fd = new FormData();
            fd.append('file', file);
            alert("fd "+uploadUrl);
            this._http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: { 'Content-Type': undefined }
            }).success(function () { })
                .error(function (data) { });
        }
    }
    angular.module("DocumentList").service("fileUploadService", FileUploadService);
}