angular.module('CucumberReporter', []);

angular.module('CucumberReporter').controller('ReportCtrl', [
	'$scope', '$http', '$filter', 'SizeGuideViewErrorFactory', 'ButtonVisibilityErrorFactory',

	function($scope, $http, $filter, SizeGuideViewErrorFactory, ButtonVisibilityErrorFactory) {

	$scope.buttonVisibilityErrorUrls = [];
	$scope.sizeGuideDisplayErrorUrls = [];

	(function init() {
		$http.get('report-original.json').then(function(reportData) {
			setErrorUrls(reportData.data);
		});
	})();

	function setErrorUrls(features) {
		angular.forEach(features, function(feature) {
			angular.forEach(feature.elements, function(element) {
				angular.forEach(element.steps, function(step) {
					
					if (step.result.status === 'failed') {
						if (!$scope.buttonVisibilityErrorUrls[element.line]) {
							$scope.buttonVisibilityErrorUrls[element.line] = {
								name: element.steps[0].name,
								urls: []
							};
						}

						if (!$scope.sizeGuideDisplayErrorUrls[element.line]) {
							$scope.sizeGuideDisplayErrorUrls[element.line] = {
								name: element.steps[0].name,
								urls: []
							};
						}
						
						var sizeGuideErrors = SizeGuideViewErrorFactory.errorsToArray(step.result.error_message);
						var buttonVisibilityErrors = ButtonVisibilityErrorFactory.errorsToArray(step.result.error_message);

						if(buttonVisibilityErrors) {
							angular.forEach(buttonVisibilityErrors, function(error) {
								$scope.buttonVisibilityErrorUrls[element.line].urls.push(error);
							});
						}

						if(sizeGuideErrors) {
							angular.forEach(sizeGuideErrors, function(error) {
								$scope.sizeGuideDisplayErrorUrls[element.line].urls.push(error);
							});
						}
					}
				});
			});
		});
	};
}]);

angular.module('CucumberReporter').factory('SizeGuideViewErrorFactory', function() {

	function errorsToArray(errorMessage) {
		var urls = errorMessage.match(/--SIZE_GUIDE_VIEW_ERROR--\[(.*?)\]--SIZE_GUIDE_VIEW_ERROR--/);

		if (urls) {
			return urls[1].split(', ');
		} else {
			return null;
		}
	}

	return {
		errorsToArray: errorsToArray
	}
});

angular.module('CucumberReporter').factory('ButtonVisibilityErrorFactory', function() {

	function errorsToArray(errorMessage) {
		var urls = errorMessage.match(/--BUTTON_VISIBILITY_ERROR--\[(.*?)\]--BUTTON_VISIBILITY_ERROR--/);

		if (urls) {
			return urls[1].split(', ');
		} else {
			return null;
		}
	}

	return {
		errorsToArray: errorsToArray
	}
});
