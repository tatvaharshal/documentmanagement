/**
 * Created by pca48 on 6/8/2017.
 */
var DocumentList;
(function (DocumentList) {
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
    DocumentList.NgFileUploadDirective = NgFileUploadDirective;
    angular.module("DocumentList").directive('ngFileUpload', NgFileUploadDirective.factory());
})(DocumentList || (DocumentList = {}));
//# sourceMappingURL=FileUploadDirective.js.map