hypercab.directive('fileUpload', function () {
        return {
            link: function (scope, el, attrs) {
                el.bind('change', function (event) {
                    var files = event.target.files;
                    scope.uploadFiles(scope.selectedTable,attrs.mediatype,files);
                });
            }
        };
    });






