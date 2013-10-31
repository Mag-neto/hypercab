'use strict';
angular.module('hypercab').service('tablesService', function ($http,hypercabApiUrl) {

    var tableData = {}, selectedTable = {};

    function fetchTables() {
        $http.get(hypercabApiUrl+'tables').success(function (data) {
            tableData.tables = data;
        });
    }

    function buildTransportTableObject(table){
        var newTable = {};
        newTable.platform = table.platform;
        newTable.fileName = table.fileName;
        newTable.year = table.year;
        newTable.description = table.description;
        newTable.machineType = table.machineType;
        newTable.manufacturer = table.manufacturer;
        newTable.active = table.active;

        return newTable;
    }

    this.getTables = function () {
        if (!tableData.tables) {
            fetchTables();
        }
        return tableData;
    };

    this.forceTablesReload = function () {
        fetchTables();
    };

    this.updateTable = function (description, table) {
        $http.put(hypercabApiUrl+'tables/' + description, buildTransportTableObject(table))
            .success(function () {
                console.log('updated table ' + table.description);
            })
            .error(function () {
                console.log('error updating table ' + table.description);
            });
    };

    this.createImageLink = function (table, type) {
        return hypercabApiUrl+'media/' + table.platform + '/' + table.description + '/' + type;
    };

    this.addTable = function (table) {
        $http.post(hypercabApiUrl+'tables', table)
            .success(function () {
                console.log('added table ' + table.description);
            })
            .error(function () {
                console.log('error adding table ' + table.description);
            });
    };

    this.removeTable = function (table) {
        $http.delete(hypercabApiUrl+'tables', table)
            .success(function () {
                console.log('removed table ' + table.description);
            })
            .error(function () {
                console.log('error removing table ' + table.description);
            });
    };

    this.save = function () {
        console.log('saving databases...');
        $http.get(hypercabApiUrl+'tables/save').success(function () {
            console.log('saved databases.');
        });
    };

    this.setSelectedTable = function (table) {
        selectedTable = table;
    };

    this.getSelectedTable = function () {
        return selectedTable;
    };

});
