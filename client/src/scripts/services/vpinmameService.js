'use strict';
angular.module('hypercab').factory('VpinMameService', function($http,hypercabApiUrl){

    var roms = {};

    var getRomsList = function(){
        if (!roms.list) {
            $http.get(hypercabApiUrl + 'vpinmame/roms')
                .success(function (data) {
                    roms.list = data;
                });
        }
        return roms;
    };

    return{
        getRomsList: getRomsList
    };
});
