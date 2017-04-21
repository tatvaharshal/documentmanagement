/// <reference path='../../_allUrvashi.ts' />
var DemoUrvashi;
(function (DemoUrvashi) {
    'use strict';
    var FileUploadServiceUrvashi = (function () {
        function FileUploadServiceUrvashi(http) {
            this._http = http;
        }
        FileUploadServiceUrvashi.prototype.uploadFileToUrl = function (file, uploadUrl) {
            var fd = new FormData();
            fd.append('file', file);
            this._http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: { 'Content-Type': undefined }
            }).success(function () { })
                .error(function (data) { });
        };
        FileUploadServiceUrvashi.$inject = ["$http"];
        return FileUploadServiceUrvashi;
    }());
    DemoUrvashi.FileUploadServiceUrvashi = FileUploadServiceUrvashi;
    angular.module("DemoUrvashi").service("fileUploadServiceUrvashi", FileUploadServiceUrvashi);
})(DemoUrvashi || (DemoUrvashi = {}));
