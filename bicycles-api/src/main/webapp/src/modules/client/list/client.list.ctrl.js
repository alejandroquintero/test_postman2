/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module("clientModule");

    mod.controller("clientListCtrl", ["$scope", '$state', 'clients', '$stateParams',
        function ($scope, $state, clients, $params) {
            $scope.records = clients;
            $scope.shopping = [];

            //Paginación
            this.itemsPerPage = $params.limit;
            this.currentPage = $params.page;
            this.totalItems = clients.totalRecords;

            this.loadShopping = function () {
                for (var i = 0; i < $scope.records.length; i++) {
                    $scope.records[i].getList('shopping').then(function (shopping) {
                        var image = shopping.plain()[Math.floor((Math.random() * shopping.plain().length))];
                        if (image) {
                            $scope.shopping.push({paymentStatus: image.paymentStatus,
                                dateOfPurchase: image.dateOfPurchase ,id: image.client.id});
                        }
                    });
                }
            };

            this.loadShopping();
            
            this.pageChanged = function () {
                $state.go('clientList', {page: this.currentPage});
            };

            $scope.actions = {
                create: {
                    displayName: 'Create',
                    icon: 'plus',
                    fn: function () {
                        $state.go('clientNew');
                    }
                },
                refresh: {
                    displayName: 'Refresh',
                    icon: 'refresh',
                    fn: function () {
                        $state.reload();
                    }
                }            };
            $scope.recordActions = {
                detail: {
                    displayName: 'Detail',
                    icon: 'eye-open',
                    fn: function (rc) {
                        $state.go('clientDetail', {clientId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                edit: {
                    displayName: 'Edit',
                    icon: 'edit',
                    fn: function (rc) {
                        $state.go('clientEdit', {clientId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                delete: {
                    displayName: 'Delete',
                    icon: 'minus',
                    fn: function (rc) {
                        $state.go('clientDelete', {clientId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                }
            };
        }]);
})(window.angular);
