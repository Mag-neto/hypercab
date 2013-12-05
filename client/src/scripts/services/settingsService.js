'use strict';
angular.module('hypercab').factory('settingsService', function ($http, hypercabApiUrl) {

    var settings = {};

    var fetchSettings = function() {
        $http.get(hypercabApiUrl+'settings')
            .success(function (data) {
                settings.settings = data;
                console.log('Stored hyperpin settings');
            });
    }

    var getSettings = function () {
        if (!settings.settings) {
            fetchSettings();
        }
        return settings;
    };

    var saveSettings = function (settings) {
        $http.put(hypercabApiUrl+'settings', settings)
            .success(function () {
                console.log('Saved settings');
            });
    };

    return{
        getSettings: getSettings,
        saveSettings: saveSettings
    };
});
