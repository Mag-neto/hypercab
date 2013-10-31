'use strict';
angular.module('hypercab').controller('ServerController', function($scope,ServerService){

    $scope.serverModules = ServerService.getHypercabModules();

});
