hypercab.controller('TablesController', ['$scope','tablesService',function($scope,tablesService){

    $scope.tableData = tablesService.getTables();

}])

