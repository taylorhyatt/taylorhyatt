/**
 * reference access to the newly created module in guitar.js
 */

/* the NEW Guitars are showing up in the database and in postman, 
 * 
 */

(function() {
	var guitarapp = angular.module('guitarapp');

	guitarapp.controller('updateController', function($scope, $http, $location, $routeParams) {
		var guitarId = $routeParams.guitarId;
		$scope.brand = ['Gibson','Ibanez','Epiphone','Fender'];
		
		$scope.getGuitarsbyId = function() {
			$http.get("/guitarshop/webapi/guitars/" + $routeParams.guitarId)
			.then(function(response) {
				var guitars = response.data;
				if (guitars.length == 1) {
					$scope.guitar = guitars[0];
				} else {
					// error message
				}
			}, function(response) {
				console.log('error http GET guitars: ' + response.status);
			});
		}
		
		$scope.updateGuitar = function() {
			$http.put("/guitarshop/webapi/guitars/", $scope.guitar)
			.then(function(response) {				
				$scope.updateStatus = 'update successful';	
			}, function(response) {
				$scope.updateStatus = 'error trying to update guitar';	
				console.log('error http PUT guitars: ' + response.status);
			});
		}
		
		$scope.deleteGuitar = function() {
			$http.delete("/guitarshop/webapi/guitars/" + $scope.guitar.id)
			.then(function(response) {				
				$scope.updateStatus = 'delete successful';	
				$scope.disableUpdate = true;
			}, function(response) {
				$scope.updateStatus = 'error trying to delete guitar';	
				console.log('error http DELETE guitars: ' + response.status);
			});
		}
		
		$scope.goToGuitarView = function() {
			console.log('go to guitar view');
			$location.path('/guitars');
		}
		
		$scope.getGuitarsbyId();
	
	});
})()

