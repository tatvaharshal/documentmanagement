/// <reference path='../../_allUrvashi.ts' />


module DemoUrvashi {
    export class UserControllerUrvashi extends BaseControllerUrvashi {
        'use strict';


        public static $inject = [
            '$scope',
            '$location',
            '$window',
            '$modal',
            'userServiceUrvashi',
            'fileUploadServiceUrvashi',
            '$filter'
        ];

        /// Conctructor
        constructor(private $scope: IUrvashiScope, private $location: ng.ILocationService, private $window: ng.IWindowService, private $modal: ng.ui.bootstrap.IModalService, 
        			private userServiceUrvashi: IUserServiceUrvashi,private fileUploadServiceUrvashi: IFileUploadServiceUrvashi,
					private $filter: ng.IFilterService) {
            super($scope);

           $scope.vm = this;
           $scope.gender = ["male","female"]; 
           $scope.techArray = ["JAVA",".Net","Android","React"];
           $scope.country = ["India","UK"];
        }
        
        saveUser(){
        	if(this.$scope.user.id != ""){
        		
        		var file = this.$scope.profileimagefile;
                if (file != null || file != undefined) {
                    var name = file.name;
                    var lastDot = name.lastIndexOf(".");
                    var lastValue = name.slice(lastDot, name.length);
                    if(lastValue == '.png' || lastValue == '.jpg' || lastValue == '.gif' || lastValue == '.jpeg'){
                    	  var uploadUrl = "/userImageUploadUrvashi";
                          this.fileUploadServiceUrvashi.uploadFileToUrl(file, uploadUrl);
                          this.$scope.user.image = name; 
                          this.userServiceUrvashi.updateUser(this.$scope.user).then((data) => {
              				if(data.success == 'success'){
              					this.$window.location.href="UsersListUrvashi";
              		    	}
                         });
                    }else{
						this.$scope.errorMessage = "Please Upload Valid User image"
	                        this.$scope.error = true;
					}
                  
                }else if(file == undefined){
             	   this.userServiceUrvashi.updateUser(this.$scope.user).then((data) => {
        				if(data == 'success'){
        					this.$window.location.href="UsersListUrvashi";
        		    	}
              });
           	
			}else{
                	this.$scope.errorMessage = "Please Upload User image"
                		this.$scope.error = true;
                }
        		
        		
        	}else{
        		
        		var file = this.$scope.profileimagefile;
                if (file != null || file != undefined) {
                    var name = file.name; var lastDot = name.lastIndexOf(".");
                    var lastValue = name.slice(lastDot, name.length);
                    if(lastValue == '.png' || lastValue == '.jpg' || lastValue == '.gif' || lastValue == '.jpeg'){
                    	  var uploadUrl = "/userImageUploadUrvashi";
                          this.fileUploadServiceUrvashi.uploadFileToUrl(file, uploadUrl);
                          this.$scope.user.image = name; 
                          this.userServiceUrvashi.saveUser(this.$scope.user).then((data) => {
              				if(data == 'success'){
              					this.$window.location.href="UsersListUrvashi";
              		    	}
                         });
                    }else {
                    	this.$scope.errorMessage = "Please Upload Valid User image"
                        this.$scope.error = true;
					}
                  
                }else{
                	this.$scope.errorMessage = "Please Upload User image"
                		this.$scope.error = true;
                }
                   /* var uploadUrl = "/userImageUploadUrvashi";
                    this.fileUploadServiceUrvashi.uploadFileToUrl(file, uploadUrl);
                    this.$scope.user.image = name; 
                }
        		
        		this.userServiceUrvashi.saveUser(this.$scope.user).then((data) => {
					if(data == 'success'){
						this.$window.location.href="UsersListUrvashi";
		    		}
           		});*/
        	}
        }
        
        getListOfUser(){
        	this.userServiceUrvashi.listUser().then((data) => {
				if(data != undefined){
					this.$scope.userList = data as UserUrvashi[];
		    	}
           	});
        }
        
        getUserImage(image:any){
        	this.$scope.user.image=image;
        }
        
        deleteuserById(id:any){
        	this.$modal.open({
                template: '<div class="modal-header"><h3 class="modal-title">Confirm</h3></div><div class="modal-body">Are you sure you want to delete this user ?</div><div class="modal-footer"><button class="btn btn-primary" type="button" ng-click="modal.onConfirm(' + id + ')">Ok</button><button class="btn btn-default" type="button" ng-click="modal.close()">Cancel</button></div>',
                controller: 'modalInstanceControllerUrvashi',
                controllerAs : 'modal'
            });
        }
        
        setArray(arr:any){
        	if(arr!=""){
        		arr=arr.replace(" ", "");;
        		arr=arr.slice(1, -1);
        		var temp = arr.split(",");
            	this.$scope.user.techArray = temp;
        	}
        }
        
}
    angular.module("DemoUrvashi").controller("userControllerUrvashi", UserControllerUrvashi);
}