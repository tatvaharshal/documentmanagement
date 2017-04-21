/// <reference path='../../_allUrvashi.ts' />

module DemoUrvashi {
    export interface IUrvashiScope extends ng.IScope {
    	userList:UserUrvashi[];
    	user:UserUrvashi;
    	vm: any;
    	gender:String[];
    	techArray:String[];
   		country:String[];
  	profileimagefile : any;
    
    errorMessage:string
    error:boolean;
}
}