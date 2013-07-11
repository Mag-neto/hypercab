angular.module('UIfileUpload', [])
    .directive('fileUpload', function () {
        return {
            link: function (scope, el, attrs) {
                el.bind('change', function (event) {
                    var files = event.target.files;
                    scope.uploadFiles(attrs.table,attrs.mediatype,files);
                });
            }
        };
    });






