(function (ng) {
    var mod = ng.module('reviewModule');

    mod.controller('reviewCtrl', ['$scope', 'model','Restangular',
        function ($scope, model,Restangular) {
            $scope.model = model;
            //Alertas
            $scope.alerts = [];
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            Restangular.setErrorInterceptor(function(response, deferred, responseHandler) {
                switch(response.status) {
                    case 400:
                        $scope.showError(response.data);
                        return false; 
                        break;
                    case 401:
                        $scope.showError(response.data);
                        return false; 
                        break;
                    case 403:
                        $scope.showError(response.data);
                        return false; 
                        break;
                    case 404:
                        $scope.showError(response.data);
                        return false; 
                        break;
                    case 405:
                        $scope.showError(response.data);
                        return false; 
                        break;
                    case 413:
                        $scope.showError(response.data);
                        return false; 
                        break;
                    case 500:
                        $scope.showError(response.data);
                        return false; 
                        break;
                    case 503:
                        $scope.showError(response.data);
                        return false; 
                        break;  
                    case 412:
                        $scope.showError(response.data);
                        return false; 
                        break; 
                }

                return true; // error not handled
            });

            /* Funci�n showMessage: Recibe el mensaje en String y
             * su tipo con el fin de almacenarlo en el array $scope.alerts.
             */
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            $scope.showError = function (msg) {
                showMessage(msg, "danger");
            };

            $scope.showSuccess = function (msg) {
                showMessage(msg, "success");
            };
        }]);

})(window.angular);
