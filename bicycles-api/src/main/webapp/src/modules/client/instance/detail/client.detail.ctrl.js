/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("clientModule");

    mod.controller("clientDetailCtrl", ['$scope', "$state", "client", "model", "shoppingModel",
        function ($scope, $state, client, model, shoppingModel) {
            $scope.currentRecord = client;
            $scope.model = model;
            $scope.buttons = ['none'];
            $scope.shopping = client.getList(shoppingModel.url).$object;
            $scope.shoppingModel = shoppingModel;

            $scope.actions = {
                create: {
                    displayName: 'Create',
                    icon: 'plus',
                    fn: function () {
                        $state.go('clientNew');
                    }
                },
                edit: {
                    displayName: 'Edit',
                    icon: 'edit',
                    fn: function () {
                        $state.go('clientEdit');
                    }
                },
                delete: {
                    displayName: 'Delete',
                    icon: 'minus',
                    fn: function () {
                        $state.go('clientDelete');
                    }
                },
                refresh: {
                    displayName: 'Refresh',
                    icon: 'refresh',
                    fn: function () {
                        $state.reload();
                    }
                },
                list: {
                    displayName: 'List',
                    icon: 'th-list',
                    fn: function () {
                        $state.go('clientList');
                    }
                },
                shopping: {
                    displayName: 'Comprar',
                    icon: 'usd',
                    fn: function () {
                        $state.go('shoppingList');
                    }
                }
            };

            $scope.recordActions = {
                detail: {
                    displayName: 'Detail',
                    icon: 'eye-open',
                    fn: function (rc) {
                        $state.go('shoppingDetail', {shoppingId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                edit: {
                    displayName: 'Edit',
                    icon: 'edit',
                    fn: function (rc) {
                        $state.go('shoppingEdit', {shoppingId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                delete: {
                    displayName: 'Delete',
                    icon: 'minus',
                    fn: function (rc) {
                        $state.go('shoppingDelete', {shoppingId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                }
            };
        }]);
})(window.angular);
