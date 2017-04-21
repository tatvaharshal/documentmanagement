/// <reference path='../../_all.ts' />
var Demo;
(function (Demo) {
    'use strict';
    var FileUploadService = (function () {
        function FileUploadService(http) {
            this._http = http;
        }
        FileUploadService.prototype.uploadFileToUrl = function (file, uploadUrl) {
            var fd = new FormData();
            fd.append('file', file);
            this._http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: { 'Content-Type': undefined }
            }).success(function () { })
                .error(function (data) { });
        };
        FileUploadService.$inject = ["$http"];
        return FileUploadService;
    }());
    Demo.FileUploadService = FileUploadService;
    angular.module("Demo").service("fileUploadService", FileUploadService);
})(Demo || (Demo = {}));
