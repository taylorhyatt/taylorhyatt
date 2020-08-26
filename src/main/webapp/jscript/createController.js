/**
 * Access the previously created module 'guitarapp'
 */

(function() {
	var guitarapp = angular.module('guitarapp');

	guitarapp.controller('createController', function($scope, $http) {

		$scope.brand = [ 'Ibanez', 'Gibson', 'Epiphone', 'Fender' ];

		$scope.createGuitar = function() {
			$http.post("/guitarshop/webapi/guitars", $scope.guitar).then(
					function(response) {
						$scope.createStatus = 'create successful';
						$scope.disableCreate = true;
					},
					function(response) {
						$scope.createStatus = 'error trying to create guitar';
						console.log('error http POST guitars: '
								+ response.status);
					});
		}

		$scope.clear = function() {
			$scope.guitar.model = '';
			$scope.guitar.bodyStyle = '';
			$scope.guitar.releaseYear = '';
			$scope.guitar.brand = '';
			$scope.disableCreate = false;
		}

	});

})()

