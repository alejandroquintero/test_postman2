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
    var mod = ng.module('bicycleModule', ['ngCrud', 'ui.router']);

    mod.constant('bicycleModel', {
        name: 'bicycle',
        displayName: 'Bicicletas',
        url: 'bicycles',
        fields: {
            status: {

                displayName: 'Estado',
                type: 'String',
                required: true
            },
            description: {

                displayName: 'Descripcion',
                type: 'String',
                required: true
            },
            name: {

                displayName: 'Nombre',
                type: 'String',
                required: true
            },
            stock: {

                displayName: 'Stock',
                type: 'Long',
                required: true
            },
            color: {

                displayName: 'Color',
                type: 'String',
                required: true
            },
            brand: {
                displayName: 'Marca',
                type: 'Reference',
                model: 'brandModel',
                options: [],
                required: true
            },
            price: {
                displayName: 'Price',
                type: 'Double',
                required: true
            },
            category: {
                displayName: 'Categoria',
                type: 'Reference',
                model: 'categoryModel',
                options: [],
                required: true
            },
            creationDate: {
                displayName: 'Fecha de Creación',
                type: 'Date',
                model: 'categoryModel',
                options: [],
                required: false
            }
        }
    });

    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/bicycle/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('bicycle', {
                url: '/bicycles?page&limit',
                abstract: true,

                views: {
                    mainView: {
                        templateUrl: basePath + 'bicycle.tpl.html',
                        controller: 'bicycleCtrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({
                                brand: r.all('brands').getList()
                                , category: r.all('categorys').getList()
                            });
                        }],
                    model: 'bicycleModel',
                    bicycles: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('bicycleNew', {
                url: '/new',
                parent: 'bicycle',
                views: {
                    bicycleView: {
                        templateUrl: basePath + 'new/bicycle.new.tpl.html',
                        controller: 'bicycleNewCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    model: 'bicycleModel'
                },
                ncyBreadcrumb: {
                    parent: 'bicycleList',
                    label: 'new'
                }
            });
            $sp.state('bicycleInstance', {
                url: '/{bicycleId:int}',
                abstract: true,
                parent: 'bicycle',
                views: {
                    bicycleView: {
                        template: '<div ui-view="bicycleInstanceView"></div>'
                    }
                },
                resolve: {
                    bicycle: ['bicycles', '$stateParams', function (bicycles, $params) {
                            return bicycles.get($params.bicycleId);
                        }]
                }
            });
            $sp.state('bicycleDetail', {
                url: '/details',
                parent: 'bicycleInstance',
                views: {
                    bicycleInstanceView: {
                        templateUrl: baseInstancePath + 'detail/bicycle.detail.tpl.html',
                        controller: 'bicycleDetailCtrl'
                    }
                },
                resolve: {
                    model: 'bicycleModel'
                },
                ncyBreadcrumb: {
                    parent: 'bicycleList',
                    label: 'details'
                }
            });
            $sp.state('bicycleEdit', {
                url: '/edit',
                sticky: true,
                parent: 'bicycleInstance',
                views: {
                    bicycleInstanceView: {
                        templateUrl: baseInstancePath + 'edit/bicycle.edit.tpl.html',
                        controller: 'bicycleEditCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    model: 'bicycleModel'
                },
                ncyBreadcrumb: {
                    parent: 'bicycleDetail',
                    label: 'edit'
                }
            });
            $sp.state('bicycleDelete', {
                url: '/delete',
                parent: 'bicycleInstance',
                views: {
                    bicycleInstanceView: {
                        templateUrl: baseInstancePath + 'delete/bicycle.delete.tpl.html',
                        controller: 'bicycleDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    model: 'bicycleModel'
                }
            });
            $sp.state('bicycleList', {
                url: '/list/:description',
                views: {
                    mainView: {
                        templateUrl: basePath + 'list/bicycle.list.tpl.html',
                        controller: 'bicycleListCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({
                                brand: r.all('brands').getList()
                                , category: r.all('categorys').getList()
                            });
                        }],
                    model: 'bicycleModel',
                    bicycles: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
        }]);
})(window.angular);
