'use strict';

/* The controller for the achievement information */


myApp.controller('c_Achievements', function($scope, $http) {
    var bpmHost = 'http://uk-spoon:8084';
 
    $scope.bpm = bpmHost;

    $http.get(bpmHost + '/GamificationPOC/rest/achivements/tibco-admin').
    success(function (data, status) {
        $scope.response = status;   
        $scope.aData = data;
    }).
    error(function(data, status, headers, config) {
    	alert("ERROR geting page data : " + data + status);
    	$scope.response = status;
    });
});
