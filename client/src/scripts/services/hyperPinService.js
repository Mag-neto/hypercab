'use strict';
angular.module('hypercab').factory('HyperPinService', function ($http, hypercabApiUrl) {

    var files = {};

    var fetchFiles = function(){
        $http.get(hypercabApiUrl + 'hyperpinfiles')
            .success(function (data) {
                files.list = data;
                console.log('Fetched hyperpin files');
            });
    };

    var getFiles = function(){
        if (!files.list) {
            fetchFiles();
        }
        return files;
    };

    var reloadFiles = function(){
        fetchFiles();
        return files;
    };

    var deleteFile = function(filename){
        $http.delete(hypercabApiUrl + 'hyperpinfiles/'+filename)
            .success(function(){
                console.log('file '+ filename + ' deleted');
                reloadFiles();
            });
    };

    var uploadSuccess = function(){
        // file is uploaded successfully
        console.log('upload complete');
        reloadFiles();
    };

    return {
        getFiles: getFiles,
        reloadFiles: reloadFiles,
        deleteFile: deleteFile,
        uploadSuccess: uploadSuccess
    };

});
