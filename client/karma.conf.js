'use strict';
module.exports = function (config) {
    config.set({

        // base path, that will be used to resolve files and exclude
        basePath: '',


        // frameworks to use
        frameworks: ['jasmine'],


        // list of files / patterns to load in the browser
        files: [
            // Application Files
            // Bower Components
            'src/components/angular/angular.js',
            'src/components/angular-mocks/angular-mocks.js',
            'src/components/ng-file-upload/angular-file-upload.js',

            // Hypercab Specific Modules
            'src/modules/*/src/**/*Module.js',
            'src/modules/*/src/**/*.js',

            // Application scripts
            'src/scripts/**/*Module.js',
            'src/scripts/**/*.js',

            // Test Files
            'test/**/*.spec.js',

            // Views
          //  'src/**/partials/*.html',

            // Helper
           // 'test/_helper/*.js'

        ],


        // list of files to exclude
        exclude: [

        ],


        // test results reporter to use
        // possible values: 'dots', 'progress', 'junit', 'growl', 'coverage'
        // 'coverage' can simply be added here to produce code coverage statistics

        reporters: ['progress', 'coverage'],

        coverageReporter: {
            type : 'html',
            dir : 'test/coverage/'
        },

        preprocessors: {
            // define files to be scanned by karma-coverage plugin
            'src/scripts/**/*.js': 'coverage',
            'src/modules/**/src/**/*.js': 'coverage',
            // define pattern for ng-html2js
            'src/**/views/*.html': 'ng-html2js'
        },

        ngHtml2JsPreprocessor: {
            stripPrefix: 'src/',
            moduleName: 'templates'
        },

        // web server port
        port: 9876,


        // enable / disable colors in the output (reporters and logs)
        colors: true,


        // level of logging
        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,


        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: false,


        // Start these browsers, currently available:
        // - Chrome
        // - ChromeCanary
        // - Firefox
        // - Opera
        // - Safari (only Mac)
        // - PhantomJS
        // - IE (only Windows)
        browsers: ['Chrome'],


        // If browser does not capture in given timeout [ms], kill it
        captureTimeout: 60000,


        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: true
    });
};
