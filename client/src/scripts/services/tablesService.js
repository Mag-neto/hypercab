'use strict';
angular.module('hypercab').factory('TablesService', function ($resource, $http, hypercabApiUrl) {

    var tableResource = $resource(hypercabApiUrl + 'tables', null,
        {
            'update': {method: 'PUT'}
        }
    );
    var tableData = {}, selectedTable = {};

    var fetchTables = function () {
        tableData.tables = tableResource.query();
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
        return tableResource.save(table);
    };

    var removeTable = function (table) {
        return $http.delete(hypercabApiUrl + 'tables', table)
            .success(function () {
                console.log('removed table ' + table.description);
            });
    };

    var save = function () {
        console.log('saving databases...');
        tableResource.update();
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
        fetchTables();
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
