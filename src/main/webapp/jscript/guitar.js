/**
 * create new module named guitarapp, getting the location for the html and js to show
 */
(function() {
	var guitarapp = angular.module('guitarapp', [ 'ngRoute' ]);

	guitarapp.config(function($routeProvider) {
		
		$routeProvider.when("/search", {
			templateUrl : "search.html",
		})
		.when("/guitars", {
			templateUrl : "guitars.html",
			controller : "guitarsController"
		})
		.when("/resource", {
			templateUrl : "resource.html"
		})
		.when("/link1", {
			templateUrl : "link1.html"
		})
		.when("/link2", {
			templateUrl : "link2.html"
		})
		.when("/strings", {
			templateUrl : "strings.html",
			controller : "stringsController"
		})
		.when("/subscribe", {
			templateUrl : "subscribe.html",
			controller : "subscribeController"
		})
		.when("/update/:guitarId", {
			templateUrl : "update.html",
			controller : "updateController"
		})
		.when("/create", {
			templateUrl : "create.html",
			controller : "createController"
		})
		.otherwise({
			templateUrl : "main.html"
		});
	});

})()	