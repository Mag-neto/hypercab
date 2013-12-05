'use strict';
angular.module('hypercab').controller('SettingsController', function ($scope, SettingsService) {

    $scope.settings = SettingsService.getSettings();

    $scope.save = function (settings) {
        SettingsService.saveSettings(settings);
    };

});
