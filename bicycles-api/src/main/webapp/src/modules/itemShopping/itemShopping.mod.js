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
    var mod = ng.module('itemShoppingModule', ['ngCrud', 'ui.router']);

    mod.constant('itemShoppingModel', {
        name: 'itemShopping',
        displayName: 'Item Compra',
        url: 'itemShoppings',
        fields: {
            quantity: {
                displayName: 'Quantity',
                type: 'Long',
                required: true
            },
<<<<<<< HEAD
            bicycle: {
                displayName: 'bicycle',
                type: 'Reference',
                model: 'bicycleModel',
                options: [],
=======
            bicycleId: {
                displayName: 'bicycleId',
                type: 'Long',
                required: true
            },
            clientId: {
                displayName: 'clientId',
                type: 'String',
>>>>>>> 64e91547ebb942e28806759b5dfb9c7ea6300d44
                required: true
            }
        }
    });

    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/itemShopping/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('itemShopping', {
                url: '/itemShoppings?page&limit',
                abstract: true,

                views: {
                    mainView: {
                        templateUrl: basePath + 'itemShopping.tpl.html',
                        controller: 'itemShoppingCtrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({
                                brand: r.all('bicycles').getList()
                            });
                        }],
                    model: 'itemShoppingModel',
                    itemShoppings: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('itemShoppingNew', {
                url: '/new',
                parent: 'itemShopping',
                views: {
                    itemShoppingView: {
                        templateUrl: basePath + 'new/itemShopping.new.tpl.html',
                        controller: 'itemShoppingNewCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    model: 'itemShoppingModel'
                },
                ncyBreadcrumb: {
                    parent: 'itemShoppingList',
                    label: 'new'
                }
            });
            $sp.state('itemShoppingInstance', {
                url: '/{itemShoppingId:int}',
                abstract: true,
                parent: 'itemShopping',
                views: {
                    itemShoppingView: {
                        template: '<div ui-view="itemShoppingInstanceView"></div>'
                    }
                },
                resolve: {
                    itemShopping: ['itemShoppings', '$stateParams', function (itemShoppings, $params) {
                            return itemShoppings.get($params.itemShoppingId);
                        }]
                }
            });
            $sp.state('itemShoppingDetail', {
                url: '/itemShoppings',
                parent: 'itemShoppingInstance',
                views: {
                    itemShoppingInstanceView: {
                        templateUrl: baseInstancePath + 'detail/itemShopping.detail.tpl.html',
                        controller: 'itemShoppingDetailCtrl'
                    }
                },
                resolve: {
                    model: 'itemShoppingModel'
                },
                ncyBreadcrumb: {
                    parent: 'itemShoppingList',
                    label: 'details'
                }
            });
            $sp.state('itemShoppingEdit', {
                url: '/edit',
                sticky: true,
                parent: 'itemShoppingInstance',
                views: {
                    itemShoppingInstanceView: {
                        templateUrl: baseInstancePath + 'edit/itemShopping.edit.tpl.html',
                        controller: 'itemShoppingEditCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    model: 'itemShoppingModel'
                },
                ncyBreadcrumb: {
                    parent: 'itemShoppingDetail',
                    label: 'edit'
                }
            });
            $sp.state('itemShoppingDelete', {
                url: '/delete',
                parent: 'itemShoppingInstance',
                views: {
                    itemShoppingInstanceView: {
                        templateUrl: baseInstancePath + 'delete/itemShopping.delete.tpl.html',
                        controller: 'itemShoppingDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    model: 'itemShoppingModel'
                }
            });
            $sp.state('itemShoppingList', {
                url: '/list',
                views: {
                    mainView: {
                        templateUrl: basePath + 'list/itemShopping.list.tpl.html',
                        controller: 'itemShoppingListCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({
                                bicycle: r.all('bicycles').getList()
                            });
                        }],
                    model: 'itemShoppingModel',
                    itemShoppings: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
        }]);
})(window.angular);
