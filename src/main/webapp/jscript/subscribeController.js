/**
 * reference access to the newly created module in guitar.js
 */
(function() {
	var guitarapp = angular.module('guitarapp');	
					
		guitarapp.controller('subscribeController', function($scope, $http) {	
			
			$scope.subscribe = function() {
				var isEmail = true;
				var endpoint = '';
				
				if ($scope.phoneNumber.toString().length == 10) {
					isEmail = false;
					endpoint = $scope.phoneNumber;
				} else {
					endpoint = $scope.emailAddress;
				}
				
				var subscribeMessage = {
						endpoint : endpoint,
						isEmail : isEmail				
				};						
					
				$http.post("/guitarshop/webapi/guitars/subscribe", subscribeMessage)
				.then(
						function success(response) {
							$scope.subscribeStatus = "subscribe success. ";								
						},
						function error(response) {
							console.log('error subscribing, status: ' + response.status);
							$scope.subscribeStatus = "error. press 'Clear' to try again";						
						}				
				);			
			};	
			
			$scope.clear = function() {
				$scope.emailAddress = '';
				$scope.phoneNumber = ''
				$scope.subscribeStatus = '';				
			}	
			
		});
		
	})()
