hypercab.service('tablesService', ['$http',function($http){

    var tableData = {};

    this.getTables = function(){
        if(!tableData.tables){
            console.log('Loading table data...');
            fetchTables();
        }
        return tableData;
    }

    function fetchTables(){
        $http.get('../hypercab-rest/tables').success(function(data){
            tableData.tables = data;
            console.log('Stored table data');
        })
    }
}]);
