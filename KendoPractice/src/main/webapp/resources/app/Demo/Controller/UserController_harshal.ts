/// <reference path='../../_all_harshal.ts' />

module HarshalDemo {
    export class UserController_harshal extends BaseController_harshal {
        'use strict';

        private users: UserMaster_harshal[];
        private user: UserMaster_harshal;
        private status : any;
        private _url : any;
        
        public static $inject = [
            '$scope',
            '$location',
            '$window',
            'userService_harshal',
            'fileUploadService_harshal',
            '$modal',
            '$filter'
        ];

        /// Conctructor
        constructor(private $scope: ICOCOScope_harshal, private $location: ng.ILocationService,
        				private $window: ng.IWindowService, private userService_harshal: IUserService_harshal,
        					private fileUploadService_harshal: IFileUploadService_harshal, private $modal: ng.ui.bootstrap.IModalService,
        						private $filter: ng.IFilterService) {
            super($scope);
            this._url = localStorage._url;
            $scope.hobbies = [];
            
            console.log(this._url);
        }
        
        selectHobby(hobby : string) {            
            if (this.$scope.hobbies.indexOf(hobby) > -1) {
                this.$scope.hobbies = this.$scope.hobbies.filter(x => x != hobby);
            }
            else {
                this.$scope.hobbies.push(hobby);
            }
        }
        
        userList() {
            this.status = this.$location.search().status;
            
            if(!angular.isUndefined(this.status) && this.status != null && this.status != "") {
                this.$scope.status = this.status.trim();
            }
            
        	this.users = this.$scope.users = new Array<UserMaster_harshal>();
            this.userService_harshal.getUserList_harshal(this.$scope).then((data) => {
		        this.users = data;
		        this.$scope.users = data;
		        console.log(' users = ' + this.$scope.users);
		      });
        }

        
        userSaveUpdate(userId : any) {
         	if(!angular.isUndefined(this.$scope.userForm_harshal)) {
	        	this.user = this.$scope.user;
	        	
	        	this.user.hobby = this.$scope.hobbies.join(", "); 
	        	
	        	var file = this.$scope.profileimagefile;
	            if (file != null || file != undefined) {
	                var name = file.name;
	                var uploadUrl = this._url + "/user_harshal/userImageUpload_harshal";
	                this.fileUploadService_harshal.uploadFileToUrl(file, uploadUrl);
	                this.user.profileimage = name;
	            }
	           
                if(!angular.isUndefined(userId) && userId != null && userId != "" && userId > 0) {
    	        	this.userService_harshal.updateUser_harshal(this.$scope, this.user).then((data) => {
                         if(data == 'success'){
                            this.$window.location.href= this._url + "/user_harshal/usersList_harshal#/?status=update";
                        }
                    });
                } else {
                   this.userService_harshal.saveUser_harshal(this.$scope, this.user).then((data) => {
                         if(data.success == 'success'){
                            this.$window.location.href= this._url + "/user_harshal/usersList_harshal#/?status=save";
                        }
                    });
                }
                
        	}
        }
        
        userDelete(userId : any) {
        	this.$modal.open({
                template: '<div class="modal-header"><h3 class="modal-title">Confirm</h3></div><div class="modal-body">Are you sure you want to delete this user ?</div><div class="modal-footer"><button class="btn btn-primary" type="button" ng-click="modal.onConfirm(' + userId + ')">Ok</button><button class="btn btn-default" type="button" ng-click="modal.close()">Cancel</button></div>',
                controller: 'modalInstanceController_harshal',
                controllerAs : 'modal',
            });
        } 
        
        getUserDetailById(userId : any) {
        	if(!angular.isUndefined(userId) && userId != null && userId != "" && userId > 0){
	        	this.userService_harshal.getUserDetailById_harshal(this.$scope, userId).then((data) => {
			        this.user = data;
			        
			        this.$scope.hobbies = this.user.hobby.split(", ");
			        this.user.dateofbirth = this.$filter('date')(data.dateofbirth, 'yyyy-MM-dd');
			        
			        this.$scope.user = this.user;
			        console.log(' users = ' + this.$scope.user);
			      });
        	}
        }
        
        
        
        userGraphData() {
            this.userService_harshal.getUserCountPerCity_harshal(this.$scope).then((data) => {
                // pie chart configuration
                this.$scope.pieChartLabel_harshal = JSON.parse(data[0]);
                this.$scope.pieChartData_harshal = JSON.parse(data[1]);
                this.$scope.pieChartOption_harshal = {
                  title: {
                        display: true,
                        text: 'User per City Pie Chart'
                    }
                };
                
                // bar chart configuration
                this.$scope.barChartLabel_harshal = JSON.parse(data[0]);
                this.$scope.barChartSeries_harshal = ['Users'];
                this.$scope.barChartData_harshal= JSON.parse(data[1]);
                this.$scope.barChartOption_harshal = {
                  title: {
                        display: true,
                        text: 'User per City Bar Chart'
                    },
                    scales: {
                    yAxes: [{
                            ticks: {
                                min: 0,
                                stepSize: 1
                            }
                        }]
                    }
                };
            
            });
        }
        
        // Init
        public Init() {
            super.BaseInit();
        }

        
    }
    
    angular.module("HarshalDemo").controller("userController_harshal", UserController_harshal);
}