/// <reference path='../../_all.ts' />
var Demo;
(function (Demo) {
    'use strict';
    var FileUploadServiceChirag = (function () {
        function FileUploadServiceChirag(http) {
            this._http = http;
        }
        FileUploadServiceChirag.prototype.uploadFile = function (file, uploadUrl) {
            var fd = new FormData();
            fd.append('file', file);
            this._http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: { 'Content-Type': undefined }
            }).success(function () { })
                .error(function (data) { });
        };
        FileUploadServiceChirag.$inject = ["$http"];
        return FileUploadServiceChirag;
    }());
    Demo.FileUploadServiceChirag = FileUploadServiceChirag;
    angular.module("Demo").service("fileUploadServiceChirag", FileUploadServiceChirag);
})(Demo || (Demo = {}));
