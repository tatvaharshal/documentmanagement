/// <reference path='../../_all.ts' />

module Demo {
    export class NgFileModelDirective implements ng.IDirective {
        'use strict';

        public link;
        restrict = "A";

        constructor(private $parse: ng.IParseService) {
            this.link = this.unboundLink.bind(this, $parse);
        }

        public unboundLink($parse: ng.IParseService, scope: any, element: any, attrs: any) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }

        static factory(): ng.IDirectiveFactory {
            var directive = ($parse: ng.IParseService) =>
                new NgFileModelDirective($parse);
            return directive;
        }       
    }

    angular.module("Demo").directive('fileModel', NgFileModelDirective.factory());
}