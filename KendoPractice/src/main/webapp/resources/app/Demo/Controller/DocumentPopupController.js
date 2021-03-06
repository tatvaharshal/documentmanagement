/**
 * Created by pca43 on 12-May-17.
 */
/// <reference path='../../_allDocumentList.ts' />
var DocumentList;
(function (DocumentList) {
    var DocumentPopupController = (function () {
        function DocumentPopupController($modalInstance, http, $q, $window) {
            this.$modalInstance = $modalInstance;
            this.$window = $window;
            this._http = http;
            this.$q = $q;
        }
        ///Onsave
        DocumentPopupController.prototype.save = function (id) {
            debugger;
            this._http.get("./deleteDocument/" + id.toString());
            localStorage.setItem("success", "Data Deleted successfully");
            /*  var msg="File Deleted successfully"
              $('#file_delete').fadeIn().html(msg);
              setTimeout(function() {
                  $('#file_delete').fadeOut("slow");
              }, 80000 );*/
            this.$window.location.href = "./DocumentList";
        };
        //On Cancel
        DocumentPopupController.prototype.cancel = function () {
            this.$modalInstance.dismiss('cancel');
        };
        return DocumentPopupController;
    }());
    DocumentPopupController.$inject = ['$modalInstance', "$http", "$q", '$window'];
    DocumentList.DocumentPopupController = DocumentPopupController;
    angular.module("DocumentList").controller("DocumentPopupController", DocumentPopupController);
})(DocumentList || (DocumentList = {}));
//# sourceMappingURL=DocumentPopupController.js.map