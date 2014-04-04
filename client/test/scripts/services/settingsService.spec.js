'use strict';
describe('SettingsService', function () {

    var $httpBackend;

    var settingsData = [
        {
            "name": "Startup Program",
            "configs": [
                {
                    "key": "WinState",
                    "value": "HIDDEN"
                },
                {
                    "key": "Executable",
                    "value": "c:\\windows\\system32\\taskkill.exe"
                }
            ]
        }
    ];

    beforeEach(module('hypercab'));

    beforeEach(inject(function ($injector, hypercabApiUrl) {
        $httpBackend = $injector.get('$httpBackend');

        //setup the mock response
        $httpBackend.when('GET', hypercabApiUrl + 'settings').respond(settingsData);
        $httpBackend.when('PUT', hypercabApiUrl + 'settings').respond(200, '');
    }));

    afterEach(function(){
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should be defined', inject(function (SettingsService) {
        expect(SettingsService).toBeDefined();
    }));

    it('should define a getSettings function', inject(function (SettingsService) {
        expect(SettingsService.getSettings).toBeDefined();
        expect(typeof SettingsService.getSettings).toBe('function');
    }));

    it('should define a saveSettings function', inject(function (SettingsService) {
        expect(SettingsService.saveSettings).toBeDefined();
        expect(typeof SettingsService.saveSettings).toBe('function');
    }));

    it('should fetch settings data', inject(function (SettingsService, hypercabApiUrl) {
        $httpBackend.expectGET(hypercabApiUrl + 'settings');
        var settings = SettingsService.getSettings();
        expect(settings.settings.length).toBe(0);
        $httpBackend.flush(1);
        expect(settings.settings.length).toBe(1);
    }));

    it('should cache settings data', inject(function (SettingsService) {
        SettingsService.getSettings();
        $httpBackend.flush(1);
        // second call shall return cached data
        SettingsService.getSettings();
    }));

    it('should send save request', inject(function (SettingsService, hypercabApiUrl) {
        $httpBackend.expectPUT(hypercabApiUrl + 'settings');
        SettingsService.saveSettings(settingsData);
        $httpBackend.flush(1);
    }));

});
