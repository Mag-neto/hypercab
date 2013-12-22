'use strict';
angular.module('hypercab').factory('VpinMameService', function($http,hypercabApiUrl){

    var roms = {};

    var fetchRoms = function(){
        $http.get(hypercabApiUrl + 'vpinmame/roms')
            .success(function (data) {
                roms.map = data;
            });
    };

    var getRoms = function(){
        if (!roms.map) {
            fetchRoms();
        }
        return roms;
    };

    var reloadRoms = function(){
        fetchRoms();
        return roms;
    };

    var updateRomSettings = function(rom){
        $http.put(hypercabApiUrl + 'vpinmame/roms/' + rom.name, rom)
            .success(function(){
                console.log('Updated rom');
            });
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

    var uploadSuccess = function(){
        // file is uploaded successfully
        console.log('upload complete');
        reloadRoms();
    };

    return{
        getRoms: getRoms,
        reloadRoms: reloadRoms,
        updateRegistry: updateRegistry,
        updateRomSettings: updateRomSettings,
        uploadSuccess: uploadSuccess
    };
});
