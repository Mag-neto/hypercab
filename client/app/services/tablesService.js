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

    this.activateTable = function(description){
        console.log("activating "+description);
        $http.get('../hypercab-rest/tables/'+description+'/activate');
    }

    this.deactivateTable = function(description){
        console.log("deactivating "+description);
        $http.get('../hypercab-rest/tables/'+description+'/deactivate');
    }

    this.createImageLink = function(table, type){
        if(table.platform == 'VISUAL_PINBALL'){
            return "../hypercab-rest/media/visualpinball/"+table.description+"/"+type;
        }
    }
}]);
