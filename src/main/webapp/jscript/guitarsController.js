/**
 * reference access to the newly created module in guitar.js
 */

(function() {
	var guitarapp = angular.module('guitarapp');

	guitarapp.controller('guitarsController',
			function($scope, $http, $location) {

				$scope.getAllGuitars = function() {
					$http.get("/guitarshop/webapi/guitars").then(
							function(response) {
								$scope.guitars = response.data;
								console.log('number of guitars: '
										+ $scope.guitars.length);
							},
							function(response) {
								console.log('error http GET guitars: '
										+ response.status);
							});
				}

				$scope.goToUpdateView = function(guitarId) {
					console.log('go to update view');
					console.log('guitarId: ' + guitarId);
					$location.path('/update/' + guitarId);

				}

				$scope.getAllGuitars(); 

			});
})()

		