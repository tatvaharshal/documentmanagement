/// <reference path='../../_allChandni.ts' />
var DemoChandni;
(function (DemoChandni) {
    'use strict';
    var FileUploadChandniService = (function () {
        function FileUploadChandniService(http) {
            this._http = http;
        }
        FileUploadChandniService.prototype.uploadFileToUrl = function (file, uploadUrl) {
            var fd = new FormData();
            fd.append('file', file);
            this._http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: { 'Content-Type': undefined }
            }).success(function () { })
                .error(function (data) { });
        };
        return FileUploadChandniService;
    }());
    FileUploadChandniService.$inject = ["$http"];
    DemoChandni.FileUploadChandniService = FileUploadChandniService;
    angular.module("DemoChandni").service("fileUploadChandniService", FileUploadChandniService);
})(DemoChandni || (DemoChandni = {}));
