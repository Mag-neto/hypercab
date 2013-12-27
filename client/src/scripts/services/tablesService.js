'use strict';
angular.module('hypercab').factory('TablesService', function ($http, hypercabApiUrl) {

    var tableData = {}, selectedTable = {};

    var fetchTables = function () {
        $http.get(hypercabApiUrl + 'tables').success(function (data) {
            tableData.tables = data;
        });
    };

    var toTableTO = function (table) {
        var newTable = {};
        newTable.platform = table.platform;
        newTable.fileName = table.fileName;
        newTable.year = table.year;
        newTable.description = table.description;
        newTable.machineType = table.machineType;
        newTable.manufacturer = table.manufacturer;
        newTable.active = table.active;

        return newTable;
    };

    var getTables = function () {
        if (!tableData.tables) {
            fetchTables();
        }
        return tableData;
    };

    var forceTablesReload = function () {
        fetchTables();
        return tableData;
    };

    var updateTable = function (description, table) {
        return $http.put(hypercabApiUrl + 'tables/' + description, toTableTO(table))
            .success(function () {
                console.log('updated table ' + table.description);
            });
    };

    var createImageLink = function (table, type) {
        return hypercabApiUrl + 'media/' + table.platform + '/' + table.description + '/' + type;
    };

    var addTable = function (table) {
        $http.post(hypercabApiUrl + 'tables', table)
            .success(function () {
                console.log('added table ' + table.description);
            });
    };

    var removeTable = function (table) {
        $http.delete(hypercabApiUrl + 'tables', table)
            .success(function () {
                console.log('removed table ' + table.description);
            });
    };

    var save = function () {
        console.log('saving databases...');
        $http.put(hypercabApiUrl + 'tables').success(function () {
            console.log('saved databases.');
        });
    };

    var convertToTable = function (xmlData, targetTable) {
        $http.get(hypercabApiUrl + 'convert/table?xml='+xmlData).success(function (data) {
            targetTable.description = data.description;
            targetTable.fileName = data.fileName;
            targetTable.manufacturer = data.manufacturer;
            targetTable.machineType = data.machineType;
            targetTable.year = data.year;
        });
    };

    var setSelectedTable = function (table) {
        selectedTable = table;
    };

    var getSelectedTable = function () {
        return selectedTable;
    };

    var uploadSuccess = function(){
        // file is uploaded successfully
        console.log('media pack upload complete');
    };

    return{
        getTables: getTables,
        forceTablesReload: forceTablesReload,
        addTable: addTable,
        updateTable: updateTable,
        removeTable: removeTable,
        save: save,
        getSelectedTable: getSelectedTable,
        setSelectedTable: setSelectedTable,
        createImageLink: createImageLink,
        convertToTable: convertToTable,
        uploadSuccess: uploadSuccess
    };

});
