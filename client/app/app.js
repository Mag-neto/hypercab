/* Defines the main application module and configures routing */

var hypercab = angular.module('hypercabApp', []);

hypercab.config(function($routeProvider) {
	$routeProvider
    .when('/', {
		templateUrl : 'app/partials/home.html'
	})
    .when('/server', {
        templateUrl : 'app/partials/server.html'
    })
    .when('/settings', {
        templateUrl : 'app/partials/settings.html',
        controller: 'SettingsController'
    })
    .when('/tables', {
        templateUrl : 'app/partials/tables.html',
        controller: 'TablesController'
    })
    .otherwise({
		redirectTo : '/'
	});
	
});