/// <reference path='../../_allUrvashi.ts' />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var DemoUrvashi;
(function (DemoUrvashi) {
    var ModalInstanceControllerUrvashi = (function (_super) {
        __extends(ModalInstanceControllerUrvashi, _super);
        function ModalInstanceControllerUrvashi($scope, $modalInstance, $window, userServiceUrvashi) {
            _super.call(this, $scope);
            this.$scope = $scope;
            this.$modalInstance = $modalInstance;
            this.$window = $window;
            this.userServiceUrvashi = userServiceUrvashi;
        }
        ModalInstanceControllerUrvashi.prototype.onConfirm = function (id) {
            var _this = this;
            this.userServiceUrvashi.deleteUser(id).then(function (data) {
                _this.$modalInstance.dismiss('cancel');
                if (data === 'success') {
                    _this.$window.location.href = "UsersListUrvashi";
                }
            });
        };
        ;
        ModalInstanceControllerUrvashi.prototype.close = function () {
            this.$modalInstance.dismiss('cancel');
        };
        ;
        ModalInstanceControllerUrvashi.$inject = ['$scope', '$modalInstance', '$window', 'userServiceUrvashi'];
        return ModalInstanceControllerUrvashi;
    }(DemoUrvashi.BaseControllerUrvashi));
    DemoUrvashi.ModalInstanceControllerUrvashi = ModalInstanceControllerUrvashi;
    angular.module("DemoUrvashi").controller("modalInstanceControllerUrvashi", ModalInstanceControllerUrvashi);
})(DemoUrvashi || (DemoUrvashi = {}));
