/// <reference path='../../_all_harshal.ts' />

module HarshalDemo {
    'use strict';

    export class FileUploadService_harshal implements IFileUploadService_harshal {

        private _http: ng.IHttpService;

        static $inject = ["$http"];

        constructor(http: ng.IHttpService) {
            this._http = http;
        }

        public uploadFileToUrl(file: any, uploadUrl: any) {            
            var fd = new FormData();
            fd.append('file', file);

            this._http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: { 'Content-Type': undefined }
            }).success(function () { })
                .error(function (data) { });
        }
    }

    angular.module("HarshalDemo").service("fileUploadService_harshal", FileUploadService_harshal);

}