module.exports = function(grunt) {

	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		
		connect: {
			report: {
				port: 1337
			}
		}
	});

	grunt.loadNpmTasks('grunt-connect');

	grunt.registerTask('default', 'connect:report');
};