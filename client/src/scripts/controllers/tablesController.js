'use strict';
angular.module('hypercab').controller('TablesController', function ($scope, tablesService, uploadService) {

    $scope.tableData = tablesService.getTables();
    $scope.selectedTable = tablesService.getSelectedTable();

    $scope.toggleActive = function (table) {
        table.active = !table.active;
        tablesService.updateTable(table.description, table);
    };

    $scope.displayImage = function (table, type) {
        var link = tablesService.createImageLink(table, type);
        window.open(link);
    };

    $scope.getImageLink = function (table, type) {
        return tablesService.createImageLink(table, type);
    };

    $scope.save = function () {
        tablesService.save();
    };

    $scope.setSelectedTable = function (table) {
        tablesService.setSelectedTable(table);
    };

    $scope.addTable = function (table) {
        tablesService.addTable(table);
        $scope.tableData = tablesService.forceTablesReload();
    };

    $scope.removeTable = function (table) {
        tablesService.removeTable(table);
        $scope.tableData = tablesService.forceTablesReload();
    };

    $scope.uploadFiles = function (table, mediatype, files) {
        $scope.uploadResult = {};
        uploadService.uploadFile(table, mediatype, files[0], function () {
            $scope.uploadResult = this;

        }, function () {
            console.log('Error uploading file');
        });
    };

});

