/**
 * Created by pca43 on 12-May-17.
 */
/// <reference path='../../_allDocumentList.ts' />

module DocumentList {
    export class DocumentPopupController {
        'use strict';
        static $inject = ['$modalInstance', "$http", "$q",'$window'];
        private _http: ng.IHttpService;
        private $q: ng.IQService;

        constructor(private $modalInstance: ng.ui.bootstrap.IModalServiceInstance, http: ng.IHttpService,$q: ng.IQService,private $window: ng.IWindowService) {
            this._http = http;
            this.$q = $q;
        }

        ///Onsave
        save(id:number) {
            debugger;
            this._http.get("./deleteDocument/"+id.toString());
            localStorage.setItem("success", "Data Deleted successfully");
          /*  var msg="File Deleted successfully"
            $('#file_delete').fadeIn().html(msg);
            setTimeout(function() {
                $('#file_delete').fadeOut("slow");
            }, 80000 );*/
            this.$window.location.href="./DocumentList";
        }

        //On Cancel
        cancel() {
            this.$modalInstance.dismiss('cancel');
        }

    }

    angular.module("DocumentList").controller("DocumentPopupController", DocumentPopupController);
}