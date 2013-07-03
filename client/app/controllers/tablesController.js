hypercab.controller('TablesController', ['$scope','tablesService',function($scope,tablesService){

    $scope.tableData = tablesService.getTables();

    $scope.toggleActive = function(table){
        if(table.active){
            tablesService.deactivateTable(table.description);
        }else{
            tablesService.activateTable(table.description);
        }
        table.active = !table.active;
    }

    $scope.displayImage = function(table,type){
        var link = tablesService.createImageLink(table,type);
        window.open(link);
    }
}])

