/// <reference path='../../_all_harshal.ts' />

module HarshalDemo {
    export class NgFileUploadDirective implements ng.IDirective {
        'use strict';

        public link;
        restrict = "A";

        constructor(private $parse: ng.IParseService) {
            this.link = this.unboundLink.bind(this, $parse);
        }

        public unboundLink($parse: ng.IParseService, scope: any, element: any, attrs: any) {
            var model = $parse(attrs.fileUpload);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }

        static factory(): ng.IDirectiveFactory {
            var directive = ($parse: ng.IParseService) =>
                new NgFileUploadDirective($parse);
            return directive;
        }       
    }

    angular.module("HarshalDemo").directive('fileUpload', NgFileUploadDirective.factory());
}