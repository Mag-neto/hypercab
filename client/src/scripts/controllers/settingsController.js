'use strict';
angular.module('hypercab').controller('SettingsController', function ($scope, settingsService) {

    $scope.settings = settingsService.getSettings();

    $scope.save = function (settings) {
        settingsService.saveSettings(settings);
    };

});
