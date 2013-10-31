'use strict';
angular.module('hypercab').service('tablesService', function ($http) {

    var tableData = {}, selectedTable = {};

    function fetchTables() {
        $http.get('../hypercab-rest/tables').success(function (data) {
            tableData.tables = data;
            console.log('Stored table data');
        });
    }

    this.getTables = function () {
        if (!tableData.tables) {
            console.log('Loading table data...');
            fetchTables();
        }
        return tableData;
    };

    this.forceTablesReload = function () {
        fetchTables();
    };

    this.updateTable = function (description, table) {
        console.log('updating ' + description);
        $http.put('../hypercab-rest/tables/' + description, table)
            .success(function () {
                console.log('updated table ' + table.description);
            })
            .error(function () {
                console.log('error updating table ' + table.description);
            });
    };

    this.createImageLink = function (table, type) {
        return '../hypercab-rest/media/' + table.platform + '/' + table.description + '/' + type;
    };

    this.addTable = function (table) {
        $http.post('../hypercab-rest/tables', table)
            .success(function () {
                console.log('added table ' + table.description);
            })
            .error(function () {
                console.log('error adding table ' + table.description);
            });
    };

    this.removeTable = function (table) {
        $http.delete('../hypercab-rest/tables', table)
            .success(function () {
                console.log('removed table ' + table.description);
            })
            .error(function () {
                console.log('error removing table ' + table.description);
            });
    };

    this.save = function () {
        console.log('saving databases...');
        $http.get('../hypercab-rest/tables/save').success(function () {
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
