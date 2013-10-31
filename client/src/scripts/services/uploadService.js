'use strict';
angular.module('hypercab').service('uploadService',function ($http) {
        this.uploadFile = function (table, mediatype, file, successCallback, errorCallback) {
            $http({
                method: 'POST',
                url: '../hypercab-rest/media/' + table.platform + '/' + table.description + '/' + mediatype,
                headers: { 'Content-Type': false },
                transformRequest: function (data) {
                    var formData = new FormData();
                    formData.append('file', data.file);
                    return formData;
                },
                data: { file: file }
            }).
                success(function (data) {
                    if (_.isFunction(successCallback)) {
                        successCallback.apply(data);
                    }
                }).
                error(function () {
                    if (_.isFunction(errorCallback)) {
                        errorCallback.apply();
                    }
                });
        };
    });