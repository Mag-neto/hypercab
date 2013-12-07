'use strict';
angular.module('hypercab').controller('VPinMameController', function($scope, VpinMameService){

    $scope.roms = VpinMameService.getRoms();
    $scope.updateRegistry = VpinMameService.updateRegistry; 
});
