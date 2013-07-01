hypercab.controller('SettingsController', ['$scope','settingsService',function($scope,settingsService){

    $scope.settings = settingsService.getSettings();

}])
