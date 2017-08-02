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
    var mod = ng.module('brandModule', ['ngCrud', 'ui.router']);

    mod.constant('brandModel', {
        name: 'brand',
        displayName: 'Marca',
        url: 'brands',
        fields: {
            name: {
                
                displayName: 'Nombre',
                type:  'String',
                required: true 
            }        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/brand/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('brand', {
                url: '/brands?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'brand.tpl.html',
                        controller: 'brandCtrl'
                    }
                },
                resolve: {
                    model: 'brandModel',
                    brands: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('brandList', {
                url: '/list',
                parent: 'brand',
                views: {
                     brandView: {
                        templateUrl: basePath + 'list/brand.list.tpl.html',
                        controller: 'brandListCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                 resolve:{
				   model: 'brandModel'
					},
                 ncyBreadcrumb: {
                   label: 'brand'
                    }
            });
            $sp.state('brandNew', {
                url: '/new',
                parent: 'brand',
                views: {
                    brandView: {
                        templateUrl: basePath + 'new/brand.new.tpl.html',
                        controller: 'brandNewCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                  resolve:{
						model: 'brandModel'
					},
                  ncyBreadcrumb: {
                        parent :'brandList', 
                        label: 'new'
                   }
            });
            $sp.state('brandInstance', {
                url: '/{brandId:int}',
                abstract: true,
                parent: 'brand',
                views: {
                    brandView: {
                        template: '<div ui-view="brandInstanceView"></div>'
                    }
                },
                resolve: {
                    brand: ['brands', '$stateParams', function (brands, $params) {
                            return brands.get($params.brandId);
                        }]
                }
            });
            $sp.state('brandDetail', {
                url: '/details',
                parent: 'brandInstance',
                views: {
                    brandInstanceView: {
                        templateUrl: baseInstancePath + 'detail/brand.detail.tpl.html',
                        controller: 'brandDetailCtrl'
                    }
                },
                  resolve:{
						model: 'brandModel'
					},
                  ncyBreadcrumb: {
                        parent :'brandList', 
                        label: 'details'
                    }
            });
            $sp.state('brandEdit', {
                url: '/edit',
                sticky: true,
                parent: 'brandInstance',
                views: {
                    brandInstanceView: {
                        templateUrl: baseInstancePath + 'edit/brand.edit.tpl.html',
                        controller: 'brandEditCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                  resolve:{
						model: 'brandModel'
					},
                  ncyBreadcrumb: {
                        parent :'brandDetail', 
                        label: 'edit'
                    }
            });
            $sp.state('brandDelete', {
                url: '/delete',
                parent: 'brandInstance',
                views: {
                    brandInstanceView: {
                        templateUrl: baseInstancePath + 'delete/brand.delete.tpl.html',
                        controller: 'brandDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                  resolve:{
				      model: 'brandModel'
					}
            });
            $sp.state('brandBicycle', {
                url: '/bicycle',
                parent: 'brandDetail',
                abstract: true,
                views: {
                    brandChieldView: {
                        template: '<div ui-view="brandBicycleView"></div>'
                    }
                },
                resolve: {
                     
                    bicycle: ['brand', function (brand) {
                            return brand.getList('bicycle');
                        }],
                    model: 'bicycleModel'
                }
            });
            $sp.state('brandBicycleList', {
                url: '/list',
                parent: 'brandBicycle',
                views: {
                    brandBicycleView: {
                        templateUrl: baseInstancePath + 'bicycle/list/brand.bicycle.list.tpl.html',
                        controller: 'brandBicycleListCtrl'
                    }
                }
            });
            $sp.state('brandBicycleEdit', {
                url: '/edit',
                parent: 'brandBicycle',
                views: {
                    brandBicycleView: {
                        templateUrl: baseInstancePath + 'bicycle/edit/brand.bicycle.edit.tpl.html',
                        controller: 'brandBicycleEditCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    model: 'bicycleModel',
                    pool: ['Restangular', 'model', function (r, model) {
                            return r.all(model.url).getList();
                        }]
                }
            });
	}]);
})(window.angular);
