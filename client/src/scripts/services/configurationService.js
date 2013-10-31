'use strict';
angular.module('hypercab').service('configService', function ($http) {

    var serverConfig = {};

    function fetchConfig() {
        $http.get('../config-rest/server/config').success(function (data) {
            serverConfig.config = data;
            console.log('Stored server configuration');
        });
    }

    this.getConfig = function () {
        if (!serverConfig.config) {
            console.log('Loading server configuration...');
            fetchConfig();
        }
        return serverConfig;
    };

});

