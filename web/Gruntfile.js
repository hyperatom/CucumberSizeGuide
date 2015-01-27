module.exports = function(grunt) {

	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		
		connect: {
			report: {
				port: 9090
			}
		}
	});

	grunt.loadNpmTasks('grunt-connect');

	grunt.registerTask('default', 'connect:report');
};