/// <reference path='../../_all.ts' />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var Demo;
(function (Demo) {
    var ModalInstanceController = (function (_super) {
        __extends(ModalInstanceController, _super);
        function ModalInstanceController($scope, $modalInstance, $window, configService) {
            var _this = _super.call(this, $scope) || this;
            _this.$scope = $scope;
            _this.$modalInstance = $modalInstance;
            _this.$window = $window;
            _this.configService = configService;
            return _this;
        }
        ModalInstanceController.prototype.onConfirm = function (id) {
            var _this = this;
            this.configService.deleteWidget(this.$scope, id).then(function (data) {
                _this.$modalInstance.dismiss('cancel');
                if (data == "Deleted") {
                    _this.$window.location.href = "/config/config-widget";
                }
            });
        };
        ;
        ModalInstanceController.prototype.close = function () {
            this.$modalInstance.dismiss('cancel');
        };
        ;
        return ModalInstanceController;
    }(Demo.BaseController));
    ModalInstanceController.$inject = ['$scope', '$modalInstance', '$window', 'configService'];
    Demo.ModalInstanceController = ModalInstanceController;
    angular.module("Demo").controller("modalInstanceController", ModalInstanceController);
})(Demo || (Demo = {}));
