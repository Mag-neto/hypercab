'use strict';
angular.module('hypercab').controller('VisualPinballController', function($scope, VisualPinballService, $upload, hypercabApiUrl){

    $scope.files = VisualPinballService.getFiles();

    $scope.deleteFile = VisualPinballService.deleteFile;

    $scope.onFileSelect = function($files) {
        for (var i = 0; i < $files.length; i++) {
            var $file = $files[i];
            $scope.upload = $upload.upload({
                url: hypercabApiUrl + 'vpfiles',
                file: $file
            })
                .success(VisualPinballService.uploadSuccess);
        }
    };

});

