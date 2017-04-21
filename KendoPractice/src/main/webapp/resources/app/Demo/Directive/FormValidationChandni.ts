/// <reference path='../../_allChandni.ts' />


module DemoChandni {
    export class NgFormValidationChandni implements ng.IDirective {
        'use strict';

        public link;
        restrict = "A";
        require = '^form';

        static instance(): ng.IDirective {
            return new NgFormValidationChandni();
        }

        link(scope: any, el: any, attrs: any, formCtrl: any) {
            // find the text box element, which has the 'name' attribute
            var inputEl = el[0].querySelector("[name]");
            // convert the native text box element to an angular element
            var inputNgEl = angular.element(inputEl);
            // get the name on the text box
            var inputName = inputNgEl.attr('name');
        
            // only apply the has-error class after the user leaves the text box
            inputNgEl.bind('blur', function() {
                el.toggleClass('has-error', formCtrl[inputName].$invalid);
            });

            scope.$watch(function() {
                return scope.showErrorsCheckValidity;
            }, function(newVal, oldVal) {
                if (!newVal) { return; }
                if (inputName != undefined) {
                    el.toggleClass('has-error', formCtrl[inputName].$invalid);
                }
            });
        }
    }

    angular.module("DemoChandni").directive('showErrors', NgFormValidationChandni.instance);
}
