/// <reference path='../../_all_harshal.ts' />
var HarshalDemo;
(function (HarshalDemo) {
    'use strict';
    var FileUploadService_harshal = (function () {
        function FileUploadService_harshal(http) {
            this._http = http;
        }
        FileUploadService_harshal.prototype.uploadFileToUrl = function (file, uploadUrl) {
            var fd = new FormData();
            fd.append('file', file);
            this._http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: { 'Content-Type': undefined }
            }).success(function () { })
                .error(function (data) { });
        };
        FileUploadService_harshal.$inject = ["$http"];
        return FileUploadService_harshal;
    }());
    HarshalDemo.FileUploadService_harshal = FileUploadService_harshal;
    angular.module("HarshalDemo").service("fileUploadService_harshal", FileUploadService_harshal);
})(HarshalDemo || (HarshalDemo = {}));
