/// <reference path='../../_all_harshal.ts' />

module HarshalDemo {
    export interface ICOCOScope_harshal extends ng.IScope {
        users: UserMaster_harshal[];
        
    	user : UserMaster_harshal;
        vm: any;
    
    	profileimagefile : any;
    	hobbies : string[];
    
    	userForm_harshal : any;
        
        status : any;
        
        barChartData_harshal : any;
        barChartLabel_harshal : any;
        barChartSeries_harshal : any;
        barChartOption_harshal : any;
        
        pieChartData_harshal : any;
        pieChartLabel_harshal : any;
        pieChartOption_harshal : any;
    }
}