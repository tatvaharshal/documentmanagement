/// <reference path='../../_allChandni.ts' />

module DemoChandni {
    export class NgFileUploadDirective implements ng.IDirective {
        'use strict';

        public link;
        restrict = "A";

        constructor(private $parse: ng.IParseService) {
            this.link = this.unboundLink.bind(this, $parse);
        }

        public unboundLink($parse: ng.IParseService, scope: any, element: any, attrs: any, formCtrl: any) {
            var model = $parse(attrs.fileUpload);
            var modelSetter = model.assign;

            element.bind('change', function() {
                scope.$apply(function() {
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

        }

        static factory(): ng.IDirectiveFactory {
            var directive = ($parse: ng.IParseService) =>
                new NgFileUploadDirective($parse);
            return directive;
        }
    }

    angular.module("DemoChandni").directive('fileUpload', NgFileUploadDirective.factory());
}