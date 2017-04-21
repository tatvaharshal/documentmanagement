/// <reference path='../../_all.ts' />

module Demo {
    export class UserControllerChirag extends BaseController {
        'use strict';

        userMasterList: Array<UserMasterChirag>;
        user : UserMasterChirag;
        status : any;

        public static $inject = [
            '$scope',
            '$location',
            'userServiceChirag',
            '$window',
            '$modal',
            '$filter',
            'fileUploadServiceChirag'
        ];
        
        
        

        /// Conctructor
        constructor(private $scope: ICOCOScope, private $location: ng.ILocationService, private userServiceChirag: IUserServiceChirag,private $window: ng.IWindowService,private $modal: ng.ui.bootstrap.IModalService, private $filter: ng.IFilterService, private fileUploadServiceChirag: IFileUploadServiceChirag) {
            super($scope);
           $scope.vm = this;
           $scope.hobbies = [];
           
           $scope.isImageRequired = true;
           
           this.status = this.$location.search().status;
           if(!angular.isUndefined(this.status) && this.status != null && this.status != "") {
               this.$scope.status = this.status.trim();
           }
           
           
           this.userMasterList = this.$scope.userMasterList = new Array<UserMasterChirag>();
           this.userServiceChirag.GetUserList().then((data) => {
		        this.userMasterList = data;
		        $scope.vm = data;
		        
		        
		        //----------------------------------
		        $scope.sortType = 'fname'; // set the default sort type
                $scope.sortReverse = false;  // set the default sort order
                $scope.viewby = 5;
                $scope.totalItems = data.length;
                $scope.currentPage = 1;
                $scope.itemsPerPage = $scope.viewby;
                $scope.maxSize = 5; 
		      });
          
        }

        
        
      
        
        selectHobbies(hobbies : string) {            
            if (this.$scope.hobbies.indexOf(hobbies) > -1) {
                this.$scope.hobbies = this.$scope.hobbies.filter(x => x != hobbies);
            }
            else {
                this.$scope.hobbies.push(hobbies);
            }
        }
        getGender(gender : any) {            
            if (gender === 1) {
            	return 'Male';
            }
            else {
            	return 'Female';
            }
        }
        
        onSave(flag : any) {
        	
	    		this.user = this.$scope.user;
	    		var file = this.$scope.myFile;
	    		this.user.hobbies = this.$scope.hobbies.join(", "); 
	            if (file != null || file != undefined) {
	                var name = file.name;
	                var uploadUrl = "/userChirag/fileUpload";
	                this.fileUploadServiceChirag.uploadFile(file, uploadUrl);
	                this.user.imageFilePath = name;
	            }
	            
	                      
	            
	            
	            
            	if(flag == 1){            			
            			this.userServiceChirag.SaveUser(this.$scope, this.user).then((data) =>{
        	                if(data.success == 'success'){
            					this.$window.location.href="/userChirag/userList#/?status=save";
            		    	}
        	               }).catch(err =>{
        	            	   if(err == 409){
               		    		this.$window.location.href="/userChirag/userList#/?status=conflict";
               		    	}
        	               });
	            } else if (flag == 0) {
	            		this.userServiceChirag.UpdateUser(this.$scope, this.user).then((data) => {
            				if(data == 'success'){
            					this.$window.location.href="/userChirag/userList#/?status=update";
            		    	}
                       	});
	            }
        }
        
        
        onEdit(id : any) {
        	if(id != null) {
        		this.userServiceChirag.GetEditPage(id).then((data) => {
    		        this.$window.location.href="/userChirag/addUser?userid="+data;
    		      });
        	}
        }
        
        
        
        onDelete(id:number) {
           
    		
            var options: ng.ui.bootstrap.IModalSettings = {
                    template:'<div class="modal-header"><h3 class="modal-title">Confirm</h3></div><div class="modal-body">Are you sure you want to delete this User ?</div><div class="modal-footer"><button class="btn btn-primary" type="button" ng-click="ctrl.save(' + id + ')">Ok</button><button class="btn btn-default" type="button" ng-click="ctrl.cancel()">Cancel</button></div>',
                    controller: 'userPopupControllerChirag as ctrl',
                    windowClass: 'app-modal-window',
                    resolve: {}
                };

                this.$modal.open(options).result
                    .then(updatedItem => this.onConfirm(updatedItem));
    		
    	}
    	
    	onConfirm(item:any):void {}
        
       
        // Init
        public initialiseEditPage(id:number) {
        	this.userServiceChirag.GetUserByID(id).then((data) => {
        		this.$scope.isImageRequired = false;
        		this.user = data;
        		this.$scope.user = data;
        		this.$scope.hobbies = this.user.hobbies.split(", ");
		        this.user.birthdate = this.$filter('date')(data.birthdate, 'yyyy-MM-dd');
		      });
        }
        
        
        userGraphData() {
            this.userServiceChirag.getCharts(this.$scope).then((data) => {
                // pie chart configuration
                this.$scope.pieChartLabelChirag = JSON.parse(data[0]);
                this.$scope.pieChartDataChirag = JSON.parse(data[1]);
                this.$scope.pieChartOptionChirag = {
                  title: {
                        display: true,
                        text: 'User count by Cities - Pie Chart'
                    }
                };
                
                // bar chart configuration
                this.$scope.barChartLabelChirag = JSON.parse(data[0]);
                this.$scope.barChartSeriesChirag = ['Users'];
                this.$scope.barChartDataChirag= JSON.parse(data[1]);
                this.$scope.barChartOptionChirag = {
                  title: {
                        display: true,
                        text: 'User count by Cities - Bar Chart'
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
        
        public validateFile(files) {  
        	if(!files){
        		return true;
        	}
            var ext = files.name.match(/\.(.+)$/)[1];
             if(angular.lowercase(ext) ==='jpg' || angular.lowercase(ext) ==='jpeg' || angular.lowercase(ext) ==='png'){
                 return true;
             }  
             else{
            	   alert("Only .jpg, .png, .jpeg allow with max 5 MB");
            	   return false;
             }
        }
           

    }

    angular.module("Demo").controller("userControllerChirag", UserControllerChirag);
}