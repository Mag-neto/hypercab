'use strict';
angular.module('hypercab').controller('HyperPinController', function($scope, HyperPinService, $upload, hypercabApiUrl){

    $scope.files = HyperPinService.getFiles();

    $scope.onFileSelect = function($files) {
        for (var i = 0; i < $files.length; i++) {
            var $file = $files[i];
            $scope.upload = $upload.upload({
                url: hypercabApiUrl + 'hyperpinfiles',
                file: $file
            })
                .success(function() {
                    // file is uploaded successfully
                    console.log('upload complete');
                    HyperPinService.reloadFiles();
                });
        }
    };

});
