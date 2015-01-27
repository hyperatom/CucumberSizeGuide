angular.module('CucumberReporter', []);

angular.module('CucumberReporter').controller('ReportCtrl', [
	'$scope', '$http', '$filter', 'ErrorExtractor',

	function($scope, $http, $filter, ErrorExtractor) {

		$scope.errors = {
			buttonVisibility: [],
			tableVisibility:  []
		};

		(function init() {
			$http.get('report.json').then(function(reportData) {
				var errorObject = extractErrorObject(reportData.data);
				setScopeErrors(errorObject);				
			});
		})();

		function setScopeErrors(errorObject) {
			$scope.errors.buttonVisibility = errorObject.buttonVisibility;
			$scope.errors.tableVisibility  = errorObject.tableVisibility;
		}

		function forEachFailedStep(features, callback) {
			angular.forEach(features, function(feature) {
				angular.forEach(feature.elements, function(element) {
					angular.forEach(element.steps, function(step) {
						step.result.status === 'failed' ? callback(element, step) : null;
					});
				});
			});
		}

		function initErrorCollection(collection, element) {
			if (!collection[element.line]) {
				collection[element.line] = {
					name: element.steps[0].name,
					urls: []
				};
			}
		}

		function addErrorsToCollection(collection, errors, element) {
			if(errors) {
				angular.forEach(errors, function(error) {
					collection[element.line].urls.push(error);
				});
			}
		}

		function extractErrorObject(features) {
			var errorObject = {
				buttonVisibility: [],
				tableVisibility:  []
			};

			forEachFailedStep(features, function(element, step) {

				initErrorCollection(errorObject.buttonVisibility, element);
				initErrorCollection(errorObject.tableVisibility, element);

				var exceptionMessage = step.result.error_message;
				
				var tableVisibilityErrors  = ErrorExtractor.tableErrorsToArray(exceptionMessage);
				var buttonVisibilityErrors = ErrorExtractor.buttonErrorsToArray(exceptionMessage);

				addErrorsToCollection(errorObject.buttonVisibility, buttonVisibilityErrors, element);
				addErrorsToCollection(errorObject.tableVisibility,  tableVisibilityErrors, element);
			});

			return errorObject;
		}
}]);

angular.module('CucumberReporter').factory('ErrorExtractor', function() {

	function extractExceptionContent(thrownErrorMessage) {
		return thrownErrorMessage.match(/java.lang.Exception: {(.*)}/);
	}

	function errorStringToObject(thrownErrorMessage) {
		var content = extractExceptionContent(thrownErrorMessage);
		return JSON.parse('{' + content[1] + '}');
	}

	function buttonErrorsToArray(thrownErrorMessage) {
		return errorStringToObject(thrownErrorMessage)
					.buttonVisibilityErrors;
	}

	function tableErrorsToArray(thrownErrorMessage) {
		return errorStringToObject(thrownErrorMessage)
					.tableVisibilityErrors;
	}

	return {
		buttonErrorsToArray: buttonErrorsToArray,
		tableErrorsToArray:  tableErrorsToArray
	}
});
