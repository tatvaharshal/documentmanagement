/// <reference path='../../_allChandni.ts' />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var DemoChandni;
(function (DemoChandni) {
    var ModalInstanceChandniController = (function (_super) {
        __extends(ModalInstanceChandniController, _super);
        function ModalInstanceChandniController($scope, $modalInstance, $window, userChandniService) {
            var _this = _super.call(this, $scope) || this;
            _this.$scope = $scope;
            _this.$modalInstance = $modalInstance;
            _this.$window = $window;
            _this.userChandniService = userChandniService;
            return _this;
        }
        ModalInstanceChandniController.prototype.onConfirm = function (id) {
            var _this = this;
            this.userChandniService.deleteUser(this.$scope, id).then(function (data) {
                _this.$modalInstance.dismiss('cancel');
                if (data == "Deleted") {
                    localStorage.setItem("success", "User Deleted Successfully.");
                    _this.$window.location.href = "/chandni/chandni-user-list";
                }
            });
        };
        ;
        ModalInstanceChandniController.prototype.close = function () {
            this.$modalInstance.dismiss('cancel');
        };
        ;
        return ModalInstanceChandniController;
    }(DemoChandni.BaseChandniController));
    ModalInstanceChandniController.$inject = ['$scope', '$modalInstance', '$window', 'userChandniService'];
    DemoChandni.ModalInstanceChandniController = ModalInstanceChandniController;
    angular.module("DemoChandni").controller("modalInstanceChandniController", ModalInstanceChandniController);
})(DemoChandni || (DemoChandni = {}));
