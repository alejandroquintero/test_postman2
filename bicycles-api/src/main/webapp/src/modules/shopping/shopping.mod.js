(function (ng) {
    var mod = ng.module('shoppingModule', ['ngCrud', 'ui.router']);

    mod.constant('shoppingModel', {
        name: 'shopping',
        displayName: 'Compras',
        url: 'shopping',
        fields: {
            paymentStatus: {
                displayName: 'Estado',
                type: 'String',
                required: true
            },
            client: {
                displayName: 'Marca',
                type: 'Reference',
                model: 'clientModel',
                options: [],
                required: true
            },
            /*bicycle: {
                displayName: 'Categoria',
                type: 'Reference',
                model: 'bicycleModel',
                options: [],
                required:  true 
            },*/
            dateOfPurchase: {
                displayName:  'Fecha de Compra',
                type: 'Date',
                options: [],
                required:  false 
            }
        }
    });

    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/shopping/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('shopping', {
                url: '/shopping?page&limit',
                abstract: true,
                parent: 'clientDetail',
                views: {
                    clientChieldView: {
                        templateUrl: basePath + 'shopping.tpl.html',
                        controller: 'shoppingCtrl'
                    }
                },
                resolve: {
                    model: 'shoppingModel',
                    shopping: ['client', '$stateParams', 'model', function (client, $params, model) {
                            return client.getList(model.url, $params);
                        }]                }
            });
            $sp.state('shoppingList', {
                url: '/list',
                parent: 'shopping',
                views: {
                     'clientInstanceView@clientInstance': {
                        templateUrl: basePath + 'list/shopping.list.tpl.html',
                        controller: 'shoppingListCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                 resolve:{
				   model: 'shoppingModel'
					},
                 ncyBreadcrumb: {
                   label: 'shopping'
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
