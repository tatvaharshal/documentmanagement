/// <reference path='../../_all_harshal.ts' />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var HarshalDemo;
(function (HarshalDemo) {
    var ModalInstanceController_harshal = (function (_super) {
        __extends(ModalInstanceController_harshal, _super);
        function ModalInstanceController_harshal($scope, $location, $modalInstance, $window, userService_harshal) {
            _super.call(this, $scope);
            this.$scope = $scope;
            this.$location = $location;
            this.$modalInstance = $modalInstance;
            this.$window = $window;
            this.userService_harshal = userService_harshal;
            this._url = localStorage._url;
        }
        ModalInstanceController_harshal.prototype.onConfirm = function (userId) {
            var _this = this;
            this.userService_harshal.deleteUser_harshal(this.$scope, userId).then(function (data) {
                _this.$modalInstance.dismiss('cancel');
                if (data == "Deleted") {
                    //this.$window.location.href= this._url + "/user_harshal/usersList_harshal";
                    _this.$window.location.href = _this._url + "/user_harshal/usersList_harshal/#/?status=delete";
                }
            });
        };
        ;
        ModalInstanceController_harshal.prototype.close = function () {
            this.$modalInstance.dismiss('cancel');
        };
        ;
        ModalInstanceController_harshal.$inject = ['$scope', '$location', '$modalInstance', '$window', 'userService_harshal'];
        return ModalInstanceController_harshal;
    }(HarshalDemo.BaseController_harshal));
    HarshalDemo.ModalInstanceController_harshal = ModalInstanceController_harshal;
    angular.module("HarshalDemo").controller("modalInstanceController_harshal", ModalInstanceController_harshal);
})(HarshalDemo || (HarshalDemo = {}));
