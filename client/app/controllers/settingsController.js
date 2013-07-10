hypercab.controller('SettingsController', ['$scope', 'settingsService', function ($scope, settingsService) {

    $scope.settings = settingsService.getSettings();

    $scope.save = function (settings) {
        settingsService.saveSettings(settings);
    };

}]);
