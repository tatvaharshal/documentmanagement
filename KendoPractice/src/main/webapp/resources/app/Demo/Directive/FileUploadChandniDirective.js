/// <reference path='../../_allChandni.ts' />
var DemoChandni;
(function (DemoChandni) {
    var NgFileUploadDirective = (function () {
        function NgFileUploadDirective($parse) {
            this.$parse = $parse;
            this.restrict = "A";
            this.link = this.unboundLink.bind(this, $parse);
        }
        NgFileUploadDirective.prototype.unboundLink = function ($parse, scope, element, attrs, formCtrl) {
            var model = $parse(attrs.fileUpload);
            var modelSetter = model.assign;
            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                    var sizeInMB = (element[0].files[0].size / (1024 * 1024)).toFixed(2);
                    if (sizeInMB > 3) {
                        scope.ctrl.$scope.imageError = true;
                        scope.ctrl.imageError = true;
                    }
                    else {
                        scope.ctrl.$scope.imageError = false;
                        scope.ctrl.imageError = false;
                    }
                });
            });
        };
        NgFileUploadDirective.factory = function () {
            var directive = function ($parse) {
                return new NgFileUploadDirective($parse);
            };
            return directive;
        };
        return NgFileUploadDirective;
    }());
    DemoChandni.NgFileUploadDirective = NgFileUploadDirective;
    angular.module("DemoChandni").directive('fileUpload', NgFileUploadDirective.factory());
})(DemoChandni || (DemoChandni = {}));
