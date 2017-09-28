/*
 The MIT License (MIT)
 
 Copyright (c) 2015 Los Andes University
 
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
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
            bicycle: {
                displayName: 'Categoria',
                type: 'Reference',
                model: 'bicycleModel',
                options: [],
                required:  true 
            },
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

                views: {
                    mainView: {
                        templateUrl: basePath + 'shopping.tpl.html',
                        controller: 'shoppingCtrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({
                                client: r.all('client').getList()
                                , bicycle: r.all('bicycle').getList()
                            });
                        }],
                    model: 'shoppingModel',
                    shopping: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('shoppingList', {
                url: '/list',
                parent: 'shopping',
                views: {
                    bicycleView: {
                        templateUrl: basePath + 'list/shopping.list.tpl.html',
                        controller: 'shoppingListCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
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
                    bicycleView: {
                        templateUrl: basePath + 'new/shopping.new.tpl.html',
                        controller: 'shoppingNewCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    model: 'shoppingModel'
                },
                ncyBreadcrumb: {
                    parent: 'shoppingList',
                    label: 'new'
                }
            });
            $sp.state('shoppingInstance', {
                url: '/{shoppingId:int}',
                abstract: true,
                parent: 'shopping',
                views: {
                    bicycleView: {
                        template: '<div ui-view="shoppingInstanceView"></div>'
                    }
                },
                resolve: {
                    bicycle: ['shopping', '$stateParams', function (shopping, $params) {
                            return shopping.get($params.shoppingId);
                        }]
                }
            });
            $sp.state('shoppingDetail', {
                url: '/details',
                parent: 'shoppingInstance',
                views: {
                    bicycleInstanceView: {
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
