/// <reference path='../../_allChandni.ts' />
var DemoChandni;
(function (DemoChandni) {
    var NgFormValidationChandni = (function () {
        function NgFormValidationChandni() {
            this.restrict = "A";
            this.require = '^form';
        }
        NgFormValidationChandni.instance = function () {
            return new NgFormValidationChandni();
        };
        NgFormValidationChandni.prototype.link = function (scope, el, attrs, formCtrl) {
            // find the text box element, which has the 'name' attribute
            var inputEl = el[0].querySelector("[name]");
            // convert the native text box element to an angular element
            var inputNgEl = angular.element(inputEl);
            // get the name on the text box
            var inputName = inputNgEl.attr('name');
            // only apply the has-error class after the user leaves the text box
            inputNgEl.bind('blur', function () {
                el.toggleClass('has-error', formCtrl[inputName].$invalid);
            });
            scope.$watch(function () {
                return scope.showErrorsCheckValidity;
            }, function (newVal, oldVal) {
                if (!newVal) {
                    return;
                }
                if (inputName != undefined) {
                    el.toggleClass('has-error', formCtrl[inputName].$invalid);
                }
            });
        };
        return NgFormValidationChandni;
    }());
    DemoChandni.NgFormValidationChandni = NgFormValidationChandni;
    angular.module("DemoChandni").directive('showErrors', NgFormValidationChandni.instance);
})(DemoChandni || (DemoChandni = {}));
