'use strict';

module.exports = function (grunt) {
    // load all tasks defined in node_modules starting with 'grunt-'
    require('matchdep').filterDev('grunt-*').forEach(grunt.loadNpmTasks);

    var buildConfig = {
        src: 'src/',
        dist: 'dist/',
        name: 'hypercab-client',
        componentFiles: [
            '<%= buildConfig.src %>modules/**/*Module.js',
            '<%= buildConfig.src %>modules/**/*.js',
            '!' + '<%= buildConfig.src %>modules/**/*.spec.js'
        ],
        applicationFiles: [
            '<%= buildConfig.src %>scripts/*Module.js',
            '<%= buildConfig.src %>scripts/**/*.js',
            '!' + '<%= buildConfig.src %>scripts/**/*.spec.js'
        ],
        watchFiles: [
            '<%= buildConfig.src %>index.tpl.html',
            '<%= buildConfig.src %>scripts/*Module.js',
            '<%= buildConfig.src %>scripts/**/*.js',
            '<%= buildConfig.src %>modules/**/*Module.js',
            '<%= buildConfig.src %>modules/**/*.js'
        ]
    };

    grunt.initConfig({
            buildConfig: buildConfig,

            // defines files to be watched and tasks to be executed on change of any file matching the file spec
            watch: {
                dev: {
                    files: buildConfig.watchFiles,
                    tasks: ['htmlbuild', 'jshint']
                }
            },

            // delete all files from previous builds
            clean: {
                dist: [
                    '<%= buildConfig.dist %>*'
                ]
            },

            /*  Generate index.html
                Inserts all project scripts and styles into 'htmlbuild:' prefixed html comment blocks from 'index.tpl.html'.
            */
            htmlbuild: {
                dev: {
                    src: '<%= buildConfig.src %>index.tpl.html',
                    dest: '<%= buildConfig.src %>index.html',
                    options: {
                        relative: true,
                        prefix: 'scripts/',
                        parseTag: 'htmlbuild',
                        scripts: {
                            hypercabModules: {
                                files: buildConfig.componentFiles

                            },
                            src: {
                                files: buildConfig.applicationFiles
                            }
                        },
                        styles:{
                            bundle:[
                                '<%= buildConfig.src %>css/**/*.css'
                            ]
                        }
                    }
                }
            },

            // check source files for violations of jshint rules defined in .jshintrc
            jshint: {
                options: {
                    jshintrc: '.jshintrc'
                },
                all: [
                    '<%= buildConfig.src %>scripts/{,*/}*.js',
                    '<%= buildConfig.src %>modules/{,*/}*.js'
                ]
            },

            // Prepare angular js files for minification (prevent dependency injection from breaking)
            ngmin: {
                dist: {
                    files: [
                        {
                            expand: true,
                            cwd: buildConfig.dist,
                            src: '**/*.js',
                            dest: buildConfig.dist
                        }
                    ]
                }
            },

            // copy all .html files to dist folder
            copy: {
                dist: {
                    files: [
                        {
                            expand: true,
                            cwd: buildConfig.src,
                            src: ['index.html',
                                '**/*.html', '!index.tpl.html', '!components/**/*'],
                            dest: buildConfig.dist
                        }
                    ]
                }
            },

            // add revision numbers to js and css files to avoid caching problems
            rev: {
                files: {
                    src: ['<%= buildConfig.dist %>*.{js,css}']
                }
            },

            /*  gather all files for concatination -> resolved from build:js, build:css comments in index.html
                This task also generates configurations for 'concat', 'cssmin' and 'uglify' tasks referenced in build task
             */
            useminPrepare: {
                html: 'src/index.html',
                options: {
                    dest: buildConfig.dist
                }
            },

            // replaces js and css includes in index.html
            usemin: {
                html: '<%= buildConfig.dist %>/index.html'
            }
        }
    );

    grunt.registerTask('dev', [
        'htmlbuild',
        'watch'
    ]);

    grunt.registerTask('default', [
        'dev'
    ]);

    grunt.registerTask('build', [
        'clean',
        'htmlbuild',
        'useminPrepare',
        'copy',
        'concat',
        'cssmin',
        'ngmin',
        'uglify',
        'rev',
        'usemin'
    ]);
};