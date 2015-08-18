angular.module('CucumberReporter', []);

angular.module('CucumberReporter').controller('ReportCtrl', ['$scope', '$http', '$filter',

	function($scope, $http, $filter) {

        $scope.generated = {
            on: ''
        };

        $scope.showing = {
            category: 'kids'
        };

		$scope.errors = {
		    kids: {},
		    women: {},
		    men: {},
            lingerie: {}
		};

		$scope.isShowing = function(categoryName) {
		    return $scope.showing.category === categoryName;
		};

		$scope.show = function(categoryName) {
		    $scope.showing.category = categoryName;
		};

		(function init() {
            setErrors($scope.errors);
            setGeneratedDate($scope.generated);
		})();

		function setErrors(errorObject) {
		    $http.get('kids-report.json').then(function(response) {
                $scope.errors.kids = response.data;
            });

            $http.get('women-report.json').then(function(response) {
                $scope.errors.women = response.data;
            });

            $http.get('men-report.json').then(function(response) {
                $scope.errors.men = response.data;
            });

            $http.get('lingerie-report.json').then(function(response) {
                $scope.errors.lingerie = response.data;
            });
		}

		function setGeneratedDate(dateObject) {
		    $http.get('generated.json').then(function(dateData) {
		        dateObject.on = dateData.data;
		    });
		}
}]);

angular.module('CucumberReporter').directive('tableErrors', function() {

    return {
        templateUrl: 'tableErrors.html',
        scope: {
            errors: '=',
            category: '@'
        },
        link: function(scope) {

            scope.hasTableVisibilityErrors = function(errors) {
                return errors.tableVisibilityErrors.length > 0;
            };
        }
    };
});

angular.module('CucumberReporter').directive('buttonErrors', function() {

    return {
        templateUrl: 'buttonErrors.html',
        scope: {
            errors: '=',
            category: '@'
        },
        link: function(scope) {

            scope.hasButtonVisibilityErrors = function(errors) {
                return errors.buttonVisibilityErrors.length > 0;
            };
        }
    };
});