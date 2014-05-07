'use strict';

/* The controller for the header information */

myApp.controller('c_HeaderController', ['$scope', function($scope) {
	
	var max = 5;
	var min = 1;
	var random = Math.floor(Math.random() * (max - min + 1)) + min;
	
	switch(random)
	{
	case 1: $scope.random = "Message 1"; break;
	case 2: $scope.random = "Message 2"; break;
	case 3: $scope.random = "Message 3"; break;
	case 4: $scope.random = "Message 4"; break;
	case 5: $scope.random = "Message 5"; break;
	default: $scope.random = "Default";
	}

}]);
