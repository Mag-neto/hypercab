'use strict';
angular.module('hypercab').service('settingsService', function ($http, hypercabApiUrl) {

    var settings = {};

    function fetchSettings() {
        $http.get(hypercabApiUrl+'settings')
            .success(function (data) {
                settings.settings = data;
                console.log('Stored hyperpin settings');
            });
    }

    this.getSettings = function () {
        if (!settings.settings) {
            fetchSettings();
        }
        return settings;
    };

    this.saveSettings = function (settings) {
        $http.put(hypercabApiUrl+'settings', settings)
            .success(function () {
                console.log('Saved settings');
            });
    };
});
