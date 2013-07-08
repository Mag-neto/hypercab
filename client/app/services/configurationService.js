hypercab.service('configService', ['$http', function ($http) {

    var serverConfig = {};

    this.getConfig = function () {
        if (!serverConfig.config) {
            console.log('Loading server configuration...');
            fetchConfig();
        }
        return serverConfig;
    };

    function fetchConfig() {
        $http.get("../config-rest/server/config").success(function (data) {
            serverConfig.config = data;
            console.log('Stored server configuration');
        });
    }

}]);

