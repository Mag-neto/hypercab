'use strict';
angular.module('hypercab').factory('ServerService', function ($resource, hypercabApiUrl) {

    var moduleResource = $resource(hypercabApiUrl + 'server');

    var modules = {};

    var getHypercabModules = function () {
        if (!modules.data) {
            moduleResource.query().$promise.then(function (data) {
                modules.data = data;
            });
        }
        return modules;
    };

    return{
        getHypercabModules: getHypercabModules
    };
});
