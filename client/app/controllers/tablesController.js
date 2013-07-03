hypercab.controller('TablesController', ['$scope','tablesService',function($scope,tablesService){

    $scope.tableData = tablesService.getTables();

    $scope.toggleActive = function(table){
        table.active = !table.active;
    }
}])

