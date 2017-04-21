/// <reference path='../../_allChandni.ts' />

module DemoChandni {
    'use strict';

    export class FileUploadChandniService implements IFileUploadChandniService {

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
            }).success(function() { })
                .error(function(data) { });
        }
    }

    angular.module("DemoChandni").service("fileUploadChandniService", FileUploadChandniService);

}