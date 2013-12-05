'use strict';
describe('VpinMameService', function () {

    var $httpBackend;

    var romData = {};

    beforeEach(module('hypercab'));

    beforeEach(inject(function ($injector, hypercabApiUrl) {
        $httpBackend = $injector.get('$httpBackend');

        //setup the mock response
        $httpBackend.when('GET', hypercabApiUrl + 'vpinmame/roms').respond(romData);
    }));

    it('should be defined', inject(function (VpinMameService) {
        expect(VpinMameService).toBeDefined();
    }));

    it('should define a getRoms function', inject(function (VpinMameService) {
        expect(VpinMameService.getRoms).toBeDefined();
        expect(typeof VpinMameService.getRoms).toBe('function');
    }));

    it('should fetch rom data', inject(function (VpinMameService, hypercabApiUrl) {
        $httpBackend.expectGET(hypercabApiUrl + 'vpinmame/roms');
        var roms = VpinMameService.getRoms();
        $httpBackend.flush();
        expect(roms.map).toEqual(romData);
    }));

    it('should cache rom data', inject(function (VpinMameService) {
        VpinMameService.getRoms();
        $httpBackend.flush();
        VpinMameService.getRoms();
        $httpBackend.verifyNoOutstandingRequest();
    }));
});

