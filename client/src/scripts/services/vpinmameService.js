'use strict';
angular.module('hypercab').factory('VpinMameService', function($http,hypercabApiUrl){

    var roms = {};

    var getRoms = function(){
        if (!roms.map) {
            $http.get(hypercabApiUrl + 'vpinmame/roms')
                .success(function (data) {
                    roms.map = data;
                });
        }
        return roms;
    };

    var updateRegistry = function(){
        $http.put(hypercabApiUrl + 'vpinmame/registry')
            .success(function(){
                console.log('Wrote registry');
            })
            .error(function(){
                console.log('Update failed');
            });
    };

    return{
        getRoms: getRoms,
        updateRegistry: updateRegistry
    };
});
