var argv = require('yargs').argv;

module.exports = function(grunt) {

	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		
		connect: {
			report: {
				port: argv.port || 9090
			}
		}
	});

	grunt.loadNpmTasks('grunt-connect');

	grunt.registerTask('default', 'connect:report');
};