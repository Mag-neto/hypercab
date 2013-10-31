/* Defines the main application module and configures routing */
'use strict';
angular.module('hypercab', []);

angular.module('hypercab', []).constant('hypercabApiUrl','../../hypercab-rest/');

angular.module('hypercab').config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'partials/home.html'
        })
        .when('/server', {
            templateUrl: 'partials/server.html',
            controller: 'ServerController'
        })
        .when('/settings', {
            templateUrl: 'partials/settings.html',
            controller: 'SettingsController'
        })
        .when('/tables', {
            templateUrl: 'partials/tables.html',
            controller: 'TablesController'
        })
        .when('/tabledetail', {
            templateUrl: 'partials/tabledetail.html',
            controller: 'TablesController'
        })
        .when('/addTable', {
            templateUrl: 'partials/addTable.html',
            controller: 'TablesController'
        })
        .otherwise({
            redirectTo: '/'
        });
});