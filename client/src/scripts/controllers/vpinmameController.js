'use strict';
angular.module('hypercab').controller('VPinMameController', function($scope, VpinMameService, $upload, hypercabApiUrl){

    $scope.roms = VpinMameService.getRoms();
    $scope.updateRegistry = VpinMameService.updateRegistry;
    $scope.updateRomSettings = VpinMameService.updateRomSettings;

    $scope.onFileSelect = function($files) {
        for (var i = 0; i < $files.length; i++) {
            var $file = $files[i];
            $scope.upload = $upload.upload({
                url: hypercabApiUrl + 'vpinmame/roms',
                file: $file
            })
            .success(function() {
                // file is uploaded successfully
                console.log('upload complete');
            });
        }
    };
});
