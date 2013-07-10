hypercab.service('tablesService', ['$http', function ($http) {

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

    this.activateTable = function (description) {
        console.log("activating " + description);
        $http.get('../hypercab-rest/tables/' + description + '/activate');
    };

    this.deactivateTable = function (description) {
        console.log("deactivating " + description);
        $http.get('../hypercab-rest/tables/' + description + '/deactivate');
    };

    this.createImageLink = function (table, type) {
        var link;
        if (table.platform === 'VISUAL_PINBALL') {
            link = "../hypercab-rest/media/visualpinball/" + table.description + "/" + type;
        } else {
            link = "../hypercab-rest/media/futurepinball/" + table.description + "/" + type;
        }
        return link;
    };

    this.save = function () {
        console.log("saving databases...");
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
}]);
