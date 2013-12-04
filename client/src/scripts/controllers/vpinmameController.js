'use strict';
angular.module('hypercab').controller('VPinMameController', function($scope, VpinMameService){

    $scope.roms = VpinMameService.getRoms();
});
