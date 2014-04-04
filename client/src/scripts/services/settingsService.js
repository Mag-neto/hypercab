'use strict';
angular.module('hypercab').factory('SettingsService', function ($http, $resource, hypercabApiUrl) {

    var settingsResource = $resource(hypercabApiUrl + 'settings');

    var settings = {};

    var fetchSettings = function () {
        settings.settings = settingsResource.query();
    };

    var getSettings = function () {
        if (!settings.settings) {
            fetchSettings();
        }
        return settings;
    };

    var saveSettings = function (settings) {
        $http.put(hypercabApiUrl + 'settings', settings)
            .success(function () {
                console.log('Saved settings');
            });
    };

    return{
        getSettings: getSettings,
        saveSettings: saveSettings
    };
});
