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
        $httpBackend.flush();
        expect(settings.settings).toEqual(settingsData);
    }));

    it('should cache settings data', inject(function (SettingsService, hypercabApiUrl) {
        SettingsService.getSettings();
        $httpBackend.flush();
        // second call shall return cached data
        SettingsService.getSettings();
        // so there must not be outstanding requests
        $httpBackend.verifyNoOutstandingRequest();
    }));

    it('should send save request', inject(function (SettingsService, hypercabApiUrl) {
        $httpBackend.expectPUT(hypercabApiUrl + 'settings');
        SettingsService.saveSettings(settingsData);
        $httpBackend.flush();
    }));

});
