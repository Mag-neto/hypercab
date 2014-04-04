'use strict';
angular.module('hypercab').factory('ServerService', function ($resource, hypercabApiUrl) {

    var moduleResource = $resource(hypercabApiUrl + 'server');

    var modules = {};

    var getHypercabModules = function () {
        if (!modules.data) {
            modules.data = moduleResource.query();
        }
        return modules;
    };

    return{
        getHypercabModules: getHypercabModules
    };
});
