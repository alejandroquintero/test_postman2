/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module("clientModule");

    mod.controller("clientNewCtrl", ["$scope", "$state", "clients",
        function ($scope, $state, clients) {
            $scope.currentRecord = {};
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.clientForm.$valid) {
                            clients.post($scope.currentRecord).then(function (rc) {
                                $state.go('clientDetail', {clientId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('clientList');
                    }
                }
            };
        }]);
})(window.angular);
