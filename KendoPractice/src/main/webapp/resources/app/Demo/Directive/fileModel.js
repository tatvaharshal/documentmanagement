/// <reference path='../../_all.ts' />
var Demo;
(function (Demo) {
    var NgFileModelDirective = (function () {
        function NgFileModelDirective($parse) {
            this.$parse = $parse;
            this.restrict = "A";
            this.link = this.unboundLink.bind(this, $parse);
        }
        NgFileModelDirective.prototype.unboundLink = function ($parse, scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        };
        NgFileModelDirective.factory = function () {
            var directive = function ($parse) {
                return new NgFileModelDirective($parse);
            };
            return directive;
        };
        return NgFileModelDirective;
    }());
    Demo.NgFileModelDirective = NgFileModelDirective;
    angular.module("Demo").directive('fileModel', NgFileModelDirective.factory());
})(Demo || (Demo = {}));
