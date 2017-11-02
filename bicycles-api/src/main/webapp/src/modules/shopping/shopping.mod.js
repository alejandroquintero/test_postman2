(function (ng) {
    var mod = ng.module('shoppingModule', ['ngCrud', 'ui.router']);

    mod.constant('shoppingModel', {
        name: 'shopping',
        displayName: 'Compras',
        url: 'shopping',
        fields: {
            status: {
                displayName: 'Estado',
                type: 'String',
                required: true
            },
            dateOfPurchase: {
                displayName:  'Fecha de Compra',
                type: 'Date',
                required:  true 
            },
            totalPrice: {
                displayName:  'Total Compra',
                type: 'Double',
                required:  true 
            }
        }
    });

    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/shopping/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('shopping', {
                url: '/shopping?clientId&page&limit',
                abstract: true,
                views: {
                     mainView: {
                        templateUrl: basePath + 'shopping.tpl.html',
                        controller: 'shoppingCtrl'
                    }
                },
                resolve: {
                    model: 'shoppingModel',
                    clients: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('shoppingListTo', {
                url: '/list',
                parent: 'shopping',
                views: {
                    shoppingView: {
                        templateUrl: basePath + 'list/shopping.list.tpl.html',
                        controller: 'shoppingListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('shoppingNew', {
                url: '/new',
                parent: 'shopping',
                views: {
                    'bicycleInstanceView@bicycleInstance': {
                        templateUrl: basePath + 'new/shopping.new.tpl.html',
                        controller: 'shopppingNewCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                  resolve:{
						model: 'shoppingModel'
					},
                  ncyBreadcrumb: {
                        parent :'shoppingList', 
                        label: 'new'
                   }
            });
            $sp.state('shoppingInstance', {
                url: '/{shoppingId:int}',
                abstract: true,
                parent: 'shopping',
                views: {
                    'clientInstanceView@clientInstance': {
                        template: '<div ui-view="shoppingInstanceView"></div>'
                    }
                },
                resolve: {
                    shopping: ['shopping', '$stateParams', function (shopping, $params) {
                            return shopping.get($params.shoppingId);
                        }]
                }
            });
            $sp.state('shoppingDetail', {
                url: '/details',
                parent: 'shoppingInstance',
                views: {
                    shoppingInstanceView: {
                        templateUrl: baseInstancePath + 'detail/shopping.detail.tpl.html',
                        controller: 'shoppingDetailCtrl'
                    }
                },
                resolve: {
                    model: 'shoppingModel'
                },
                ncyBreadcrumb: {
                    parent: 'shoppingList',
                    label: 'details'
                }
            });
        }]);
})(window.angular);
