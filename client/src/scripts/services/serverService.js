'use strict';
angular.module('hypercab').factory('ServerService', function ($http, hypercabApiUrl) {

    var modules = {};

    var getHypercabModules = function () {
        if (!modules.data) {
            $http.get(hypercabApiUrl + 'server')
                .success(function (data) {
                    modules.data = data;
                });
        }
        return modules;
    };

    return{
        getHypercabModules: getHypercabModules
    };
});
