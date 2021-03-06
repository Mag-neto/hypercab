'use strict';
describe('ServerService', function () {

    var $httpBackend;

    var modulesData = [
        {
            "name": "Hyperpin Module",
            "moduleVersion": "1.0-SNAPSHOT",
            "moduleDescription": "Core module providing read/write access to Hyperpin databases and the Hyperpin Settings.ini file."
        },
        {
            "name": "Hyperpin Media Module",
            "moduleVersion": "1.0-SNAPSHOT",
            "moduleDescription": "Optional media module enabling access to Hyperpin table media files such as images,flyers and videos"
        },
        {
            "name": "VPinMame Module",
            "moduleVersion": "1.0-SNAPSHOT",
            "moduleDescription": "Optional module allowing rom management and adjustment"
        }
    ];

    beforeEach(module('hypercab'));

    beforeEach(inject(function ($injector, hypercabApiUrl) {
        $httpBackend = $injector.get('$httpBackend');

        //setup the mock response
        $httpBackend.when('GET', hypercabApiUrl + 'server').respond(modulesData);
    }));

    afterEach(function(){
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should be defined', inject(function (ServerService) {
        expect(ServerService).toBeDefined();
    }));

    it('should define a getHypercabModules function', inject(function (ServerService) {
        expect(ServerService.getHypercabModules).toBeDefined();
        expect(typeof ServerService.getHypercabModules).toBe('function');
    }));

    it('should fetch module data', inject(function (ServerService, hypercabApiUrl) {
        $httpBackend.expectGET(hypercabApiUrl + 'server');
        var modules = ServerService.getHypercabModules();
        expect(modules.data.length).toBe(0);
        $httpBackend.flush(1);
        expect(modules.data.length).toBe(3);
    }));

    it('should cache module data', inject(function (ServerService) {
        ServerService.getHypercabModules();
        $httpBackend.flush(1);
        ServerService.getHypercabModules();
    }));
});
