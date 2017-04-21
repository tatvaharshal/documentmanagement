/// <reference path='../../_allChandni.ts' />

module DemoChandni {
    export class UserAddEditChandniController extends BaseChandniController {
        'use strict';

        file: any;
        data: UserChandni;
        public _location: any;
        public static $inject = [
            '$scope',
            '$location',
            '$window',
            'userChandniService',
            'fileUploadChandniService'
        ];

        /// Conctructor
        constructor(private $scope: ICOCOChandniScope, private $location: ng.ILocationService, private $window: ng.IWindowService, private userChandniService: IUserChandniService, private fileUploadChandniService: IFileUploadChandniService) {
            super($scope);
            this._location = $location;
            this.$scope.flag = false;
        }

        // Init
        public Init() {
            super.BaseInit();
            this.$scope.genderOptions = [
                { genderId: 0, name: "Male" },
                { genderId: 1, name: "Female" }
            ];

            this.$scope.cityOptions = [
                { text: "Ahmedabad", value: "Ahmedabad" },
                { text: "Gandhinagar", value: "Gandhinagar" },
                { text: "Rajkot", value: "Rajkot" },
                { text: "Mehsana", value: "Mehsana" },
                { text: "Surat", value: "Surat" },
                { text: "Delhi", value: "Delhi" },
                { text: "Chennai", value: "Chennai" },
            ];

            this.$scope.hobbiesOption = [
                { text: "Cricket", value: "Cricket" },
                { text: "Football", value: "Football" },
                { text: "Tennis", value: "Tennis" },
                { text: "Chess", value: "Chess" },
            ];
            this.$scope.arrayHobbies = [];
        }

        public convertDate(timestamp: any) {
            var d = new Date(timestamp);
            var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
            return formattedDate;
        }

        public onSave(formIsValid: any) {
            this.$scope.showErrorsCheckValidity = true;
            if (!this.$scope.flag && this.$scope.userForm.$valid && !this.$scope.imageError) {
                this.data = this.$scope.userChandni;

                if (this.$scope.userChandni.userId == "") {
                    this.data.userId = 0;
                }
                else {
                    this.data.userId = this.$scope.userChandni.userId;
                }

                if (this.$scope.userChandni.userGender == "") {
                    this.data.userGender = 0;
                }

                this.data.userDob = new Date(document.getElementById("txtDob").value);

                if (this.$scope.userChandni.userFullName != "" && this.$scope.userChandni.userFullName != undefined) {
                    var fullname = this.$scope.userChandni.userFullName.split(' ');
                    this.data.userFirstName = fullname[0] == undefined ? "First" : fullname[0];
                    this.data.userLastName = fullname[1] == undefined ? " " : fullname[1];
                }
                this.data.userHobbies = this.$scope.arrayHobbies.join(",").replace(/^,|,$/g, '');

                var file = this.$scope.profileimagefile;
                if (file != null || file != undefined) {
                    var uploadUrl = "/chandni/userImageUpload";
                    this.fileUploadChandniService.uploadFileToUrl(file, uploadUrl);
                    this.data.userImage = file.name;
                }

                this.userChandniService.addUpdateUser(this.$scope, this.data).then((response) => {
                    if (response == 'success') {
                        // Store
                        if (this.$scope.userChandni.userId == "") {
                            localStorage.setItem("success", "User added successfully");
                        }
                        else {
                            localStorage.setItem("success", "User updated successfully");
                        }
                        this.$window.location.href = "/chandni/chandni-user-list";
                    }
                });
            }
        }

        public getHobbies(value, item) {
            if (this.$scope.arrayHobbies.indexOf(item) === -1) {
                this.$scope.arrayHobbies.push(item);
            }
            else {
                var index = this.$scope.arrayHobbies.indexOf(item);
                this.$scope.arrayHobbies.splice(index, 1);
            }
        }

        public getFullName(firstName, lastName) {
            if (this.$scope.userChandni.userId != 0) {
                this.$scope.userChandni.userFullName = firstName + " " + lastName;
            }
        }

        public getUserCity(value) {
            this.$scope.userChandni.userCity = value;
        }

        public getHobbiesInit(value) {
            this.$scope.arrayHobbies = value.split(',');
        }

        public checkItem(item) {
            var checked = false;
            for (var i = 0; i < this.$scope.arrayHobbies.length; i++) {
                if (item == this.$scope.arrayHobbies[i]) {
                    checked = true;
                }
            }
            return checked;
        }

        public onEmailAddressChange(email: string) {
            this.userChandniService.FindUserByEmail(this.$scope, email).then((response) => {
                this.$scope.flag = response;

            });
        }

        public getFormattedDate(date) {
            var year = date.getFullYear();
            var month = (1 + date.getMonth()).toString();
            month = month.length > 1 ? month : '0' + month;
            var day = date.getDate().toString();
            day = day.length > 1 ? day : '0' + day;
            return month + '/' + day + '/' + year;
        }

        public setDob(dob: string) {
            if (dob != "") {
                this.$scope.userChandni.userDateofbirth = this.getFormattedDate(new Date(dob));
            }
        }

        public setGender(value: number) {
            if (value != null) {
                this.$scope.userChandni.userGender = value;
            }
        }
    }
    angular.module("DemoChandni").controller("userAddEditChandniController", UserAddEditChandniController);
}