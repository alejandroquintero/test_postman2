/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module("clientModule");

    mod.controller("clientEditCtrl", ["$scope", "$state", "client",
        function ($scope, $state, client) {
            $scope.currentRecord = client;
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.clientForm.$valid) {
                            $scope.currentRecord.put().then(function (rc) {
                                $state.go('clientDetail', {clientId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('clientDetail');
                    }
                }
            };
        }]);
})(window.angular);
