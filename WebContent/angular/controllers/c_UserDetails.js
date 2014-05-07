'use strict';

/* The controller for the user deatils information */


myApp.controller('c_UserDetails', function($scope, $http) {
    var bpmHost = 'http://uk-spoon:8084';
 
    $scope.bpm = bpmHost;

    $http.get(bpmHost + '/GamificationPOC/rest/userdetails/tibco-admin?userGuid=tibco-admin&supplyImages=true').
    success(function (data, status) {
        $scope.response = status;   
        $scope.userData = data;
    }).
    error(function(data, status, headers, config) {
    	alert(data + status);
    	$scope.response = status;
    });
	

	
});