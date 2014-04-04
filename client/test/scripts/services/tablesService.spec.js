'use strict';
describe('TablesService', function () {

    var $httpBackend;

    var tablesData = [
        {
            "platform": "VISUAL_PINBALL",
            "description": "Xenon (Bally 1980)",
            "fileName": "Xenon.v1.1.uw.FS",
            "year": "1980",
            "machineType": "SS",
            "manufacturer": "Bally",
            "active": true,
            "additionals": {
                "tableImage": "true",
                "tableFile": "true",
                "backglassImage": "true",
                "tableVideo": "false",
                "wheelImage": "true"
            }
        }
    ];

    beforeEach(module('hypercab'));

    beforeEach(inject(function ($injector, hypercabApiUrl) {
        $httpBackend = $injector.get('$httpBackend');

        //setup the mock response
        $httpBackend.when('GET', hypercabApiUrl + 'tables').respond(tablesData);
        $httpBackend.when('PUT',hypercabApiUrl + 'tables').respond(200,'');
        $httpBackend.when('PUT',hypercabApiUrl + 'tables/Xenon (Bally 1980)').respond(200,'');
        $httpBackend.when('POST',hypercabApiUrl+'tables').respond(201,'');
        $httpBackend.when('DELETE',hypercabApiUrl+'tables').respond(200,'');
    }));

    afterEach(function(){
       $httpBackend.verifyNoOutstandingExpectation();
       $httpBackend.verifyNoOutstandingRequest();
    });

    it('should be defined', inject(function (TablesService) {
        expect(TablesService).toBeDefined();
    }));

    it('should define a getTables function', inject(function (TablesService) {
        expect(TablesService.getTables).toBeDefined();
        expect(typeof TablesService.getTables).toBe('function');
    }));

    it('should define a forceTablesReload function', inject(function (TablesService) {
        expect(TablesService.forceTablesReload).toBeDefined();
        expect(typeof TablesService.forceTablesReload).toBe('function');
    }));

    it('should define a addTable function', inject(function (TablesService) {
        expect(TablesService.addTable).toBeDefined();
        expect(typeof TablesService.addTable).toBe('function');
    }));

    it('should define a updateTable function', inject(function (TablesService) {
        expect(TablesService.updateTable).toBeDefined();
        expect(typeof TablesService.updateTable).toBe('function');
    }));

    it('should define a removeTable function', inject(function (TablesService) {
        expect(TablesService.removeTable).toBeDefined();
        expect(typeof TablesService.removeTable).toBe('function');
    }));

    it('should define a save function', inject(function (TablesService) {
        expect(TablesService.save).toBeDefined();
        expect(typeof TablesService.save).toBe('function');
    }));

    it('should define a getSelectedTable function', inject(function (TablesService) {
        expect(TablesService.getSelectedTable).toBeDefined();
        expect(typeof TablesService.getSelectedTable).toBe('function');
    }));

    it('should define a setSelectedTable function', inject(function (TablesService) {
        expect(TablesService.setSelectedTable).toBeDefined();
        expect(typeof TablesService.setSelectedTable).toBe('function');
    }));

    it('should define a createImageLink function', inject(function (TablesService) {
        expect(TablesService.createImageLink).toBeDefined();
        expect(typeof TablesService.createImageLink).toBe('function');
    }));

    it('should fetch table data', inject(function (TablesService, hypercabApiUrl) {
        $httpBackend.expectGET(hypercabApiUrl + 'tables');
        var tables = TablesService.getTables();
        expect(tables.tables.length).toBe(0);
        $httpBackend.flush(1);
        expect(tables.tables.length).toBe(1);
    }));

    it('should cache table data', inject(function (TablesService) {
        TablesService.getTables();
        $httpBackend.flush(1);
        TablesService.getTables();
    }));

    it('should force reload of table data', inject(function (TablesService, hypercabApiUrl) {
        TablesService.getTables();
        $httpBackend.flush(1);
        $httpBackend.expectGET(hypercabApiUrl + 'tables');
        TablesService.forceTablesReload();
        $httpBackend.flush(1);
    }));

    it('should store selected table', inject(function(TablesService){
        expect(TablesService.getSelectedTable()).toEqual({});
        TablesService.setSelectedTable(tablesData[0]);
        expect(TablesService.getSelectedTable()).toEqual(tablesData[0]);
    }));

    it('should send save request to server', inject(function(TablesService,hypercabApiUrl){
        $httpBackend.expectPUT(hypercabApiUrl+'tables');
        TablesService.save();
        $httpBackend.flush(1);
    }));

    it('should create an image link',inject(function(TablesService, hypercabApiUrl){
        var imageLink = TablesService.createImageLink(tablesData[0],'backglass');
        expect(imageLink).toEqual(hypercabApiUrl + 'media/' + tablesData[0].platform + '/' + tablesData[0].description + '/backglass');
        expect(typeof imageLink).toBe('string');
    }));

    it('should send update request to server', inject(function(TablesService,hypercabApiUrl){
        $httpBackend.expectPUT(hypercabApiUrl+'tables/Xenon (Bally 1980)');
        TablesService.updateTable(tablesData[0].description,tablesData[0]);
        $httpBackend.flush(1);
    }));

    it('should add a table', inject(function(TablesService, hypercabApiUrl){
        $httpBackend.expectPOST(hypercabApiUrl+'tables');
        TablesService.addTable(tablesData[0]);
        $httpBackend.flush(1);
    }));

    it('should delete a table',inject(function(TablesService, hypercabApiUrl){
        $httpBackend.expectDELETE(hypercabApiUrl+'tables');
        TablesService.removeTable(tablesData[0]);
        $httpBackend.flush(1);
    }));
});
