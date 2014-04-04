'use strict';
angular.module('hypercab').factory('SettingsService', function ($resource, hypercabApiUrl) {

    var settingsResource = $resource(hypercabApiUrl + 'settings', null,
        {
            'update': {method: 'PUT'}
        }
    );
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
        settingsResource.update(settings);
    };

    return{
        getSettings: getSettings,
        saveSettings: saveSettings
    };
});
