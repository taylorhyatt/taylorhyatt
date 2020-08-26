/**
 * 
 */
(function() {
	var guitarapp = angular.module('guitarapp');
	guitarapp.controller('stringsController', function($scope, $http, $location) {
		$scope.showQ1 = true;
		$scope.showQ2 = false;
		$scope.showQ3 = false;
		$scope.showQ4 = false;
		$scope.answer = false;
		$scope.Q1 = "";
		$scope.Q2 = "";
		$scope.Q3 = "";
		$scope.Q4 = "";
		$scope.sAcoustic1 = 'Martin Bronze Medium-Gauge';
		$scope.sAcoustic2 = 'Elixir Nanoweb Phosphor-Bronze Medium-Gauge'; 
		$scope.sAcoustic3 = 'DAddario EJ16-3D Phoshor-Bronze Light-Gauge';
		$scope.sAcoustic4 = 'Ernie Ball Earthwood Phosphor-Bronze Extra-Light-Gauge';
		$scope.sElectric1 = 'Ernie Ball 3221 Slinky Nickel-Wound Medium-Gauge';
		$scope.sElectric2 = 'Gibson Vintage Reissue Steel Custom-Medium-Gauge'; 
		$scope.sElectric3 = 'DAddario XTE0942-XT Nickel-Plated-Steel Super-Light-Gauge';
		$scope.sElectric4 = 'Elixir Optiweb Nickel-Plated-Steel Light-Gauge';
		$scope.previousQuestion = function() {
			if ($scope.showQ4) {
				$scope.showQ4 = false;
				$scope.showQ3 = true;
			} else if ($scope.showQ3) {
				$scope.showQ3 = false;
				$scope.showQ2 = true;
			} else if ($scope.showQ2) {
				$scope.showQ2 = false;
				$scope.showQ1 = true;
			} else if (!$scope.showQ1) {
				$scope.showQ1 = true;
			}
		}
		$scope.nextQuestion = function() {
			if ($scope.showQ1) {
				$scope.showQ1 = false;
				$scope.showQ2 = true;
			} else if ($scope.showQ2) {
				$scope.showQ2 = false;
				$scope.showQ3 = true;
			} else if ($scope.showQ3) {
				$scope.showQ3 = false;
				$scope.showQ4 = true;
			} else if ($scope.showQ4) {
				$scope.showQ4 = false;
				$scope.showQ5 = true;
				$scope.done();
			}
		}
		$scope.done = function() {
			$scope.answer = true;
			if(($scope.Q1 == 'Rock' || 'Folk' || 'Blues') && $scope.Q2 == 'Acoustic' && $scope.Q3 == 'Rhythym' && $scope.Q4 == 'Warm') {
				$scope.finalAnswer = $scope.sAcoustic1;
			}
			else if(($scope.Q1 == 'Country' || 'Folk' || 'Rock') && $scope.Q2 == 'Acoustic' && $scope.Q3 == 'Rhythym' && $scope.Q4 == 'Bright') {
				$scope.finalAnswer = $scope.sAcoustic2;	
			}	
			else if(($scope.Q1 == 'Blues' || 'Rock' || 'Country') && $scope.Q2 == 'Acoustic' && $scope.Q3 == 'Lead' && $scope.Q4 == 'Relaxed') {
				$scope.finalAnswer = $scope.sAcoustic3;	
			}	
			else if(($scope.Q1 == 'Blues' || 'Rock') && $scope.Q2 == 'Acoustic' && $scope.Q3 == 'Lead' && $scope.Q4 == 'Bright') {
				$scope.finalAnswer = $scope.sAcoustic4;	
			}
			else if(($scope.Q1 == 'Country' || 'Folk' || 'Rock') && $scope.Q2 == 'Electric' && $scope.Q3 == 'Rhythym' && $scope.Q4 == 'Warm') {
				$scope.finalAnswer = $scope.sElectric1;	
			}	
			else if(($scope.Q1 == 'Blues' || 'Rock' || 'Country') && $scope.Q2 == 'Electric' && $scope.Q3 == 'Rhythym' && $scope.Q4 == 'Bright') {
				$scope.finalAnswer = $scope.sElectric2;	
			}	
			else if(($scope.Q1 == 'Blues' || 'Rock' || 'Country') && $scope.Q2 == 'Electric' && $scope.Q3 == 'Lead' && $scope.Q4 == 'Relaxed') {
				$scope.finalAnswer = $scope.sElectric3;
			}	
			else if(($scope.Q1 == 'Blues' || 'Rock' || 'Country') && $scope.Q2 == 'Electric' && $scope.Q3 == 'Lead' && $scope.Q4 == 'Bright') {
				$scope.finalAnswer = $scope.sElectric4;	
				
			} else {
				$scope.finalAnswer = $scope.sAcoustic3;
			
			}
		}
		$scope.reset = function() {
			$scope.showQ1 = true;
			$scope.showQ2 = false;
			$scope.showQ3 = false;
			$scope.showQ4 = false;
			$scope.answer = false;
			$scope.Q1 = "";
			$scope.Q2 = "";
			$scope.Q3 = "";
			$scope.Q4 = "";
		}
	})
})()