/// <reference path='../../_all_harshal.ts' />
var HarshalDemo;
(function (HarshalDemo) {
    var NgFileUploadDirective = (function () {
        function NgFileUploadDirective($parse) {
            this.$parse = $parse;
            this.restrict = "A";
            this.link = this.unboundLink.bind(this, $parse);
        }
        NgFileUploadDirective.prototype.unboundLink = function ($parse, scope, element, attrs) {
            var model = $parse(attrs.fileUpload);
            var modelSetter = model.assign;
            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
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
    HarshalDemo.NgFileUploadDirective = NgFileUploadDirective;
    angular.module("HarshalDemo").directive('fileUpload', NgFileUploadDirective.factory());
})(HarshalDemo || (HarshalDemo = {}));
