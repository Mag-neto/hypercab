'use strict';
angular.module('hypercab').controller('TablesController', function ($scope, TablesService, uploadService,$upload,hypercabApiUrl) {

    $scope.tableData = TablesService.getTables();
    $scope.selectedTable = TablesService.getSelectedTable();
    $scope.newTable = {machineType: 'SS', platform: 'VISUAL_PINBALL'};

    $scope.platforms = ['VISUAL_PINBALL','FUTURE_PINBALL'];
    $scope.machineTypes = ['SS','EM'];

    $scope.toggleActive = function (table) {
        table.active = !table.active;
        TablesService.updateTable(table.description, table);
    };

    $scope.displayImage = function (table, type) {
        var link = TablesService.createImageLink(table, type);
        window.open(link);
    };

    $scope.getImageLink = function (table, type) {
        return TablesService.createImageLink(table, type);
    };

    $scope.save = function () {
        TablesService.save();
    };

    $scope.setSelectedTable = function (table) {
        TablesService.setSelectedTable(table);
    };

    $scope.addTable = function (table) {
        TablesService.addTable(table);
        $scope.tableData = TablesService.forceTablesReload();
    };

    $scope.removeTable = function (table) {
        TablesService.removeTable(table);
        $scope.tableData = TablesService.forceTablesReload();
    };

    $scope.uploadFiles = function (table, mediatype, files) {
        $scope.uploadResult = {};
        uploadService.uploadFile(table, mediatype, files[0], function () {
            $scope.uploadResult = this;

        }, function () {
            console.log('Error uploading file');
        });
    };

    $scope.setValues = function(){
        if(angular.isString($scope.textareaContent) && $scope.textareaContent.length > 0){
            TablesService.convertToTable($scope.textareaContent, $scope.newTable);
        }
    };

    $scope.onFileSelect = function($files) {
        for (var i = 0; i < $files.length; i++) {
            var $file = $files[i];
            $scope.upload = $upload.upload({
                url: hypercabApiUrl + 'media/mediapack',
                file: $file
            })
                .success(TablesService.uploadSuccess);
        }
    };

});

