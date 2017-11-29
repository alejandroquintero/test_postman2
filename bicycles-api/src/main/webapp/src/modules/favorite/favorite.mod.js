/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('favoriteModule', ['ngCrud', 'ui.router']);

    mod.constant('favoriteModel', {
        name: 'favorite',
        displayName: 'WorkShop',
		url: 'favorites',
        fields: {
            id: {
                displayName: 'Id',
                type: 'Long',
                required: true
            },
            idBicycle: {
                displayName: 'idBicycle',
                type: 'Long',
                required: true
            },
            idClient: {
                displayName: 'idClient',
                type: 'Long',
                required: true
            }           }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/favorite/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('favorite', {
                url: '/favorites?username',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'favorite.tpl.html',
                        controller: 'favoriteCtrl'
                    }
                },
                resolve: {
                    model: 'favoriteModel',
                    favorites: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('favoriteList', {
                url: '/list',
                parent: 'favorite',
                views: {
                    favoriteView: {
                        templateUrl: basePath + 'list/favorite.list.tpl.html',
                        controller: 'favoriteListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('favoriteNew', {
                url: '/new',
                parent: 'favorite',
                views: {
                    favoriteView: {
                        templateUrl: basePath + 'new/favorite.new.tpl.html',
                        controller: 'favoriteNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('favoriteInstance', {
                url: '/{favoriteId:int}',
                abstract: true,
                parent: 'favorite',
                views: {
                    favoriteView: {
                        template: '<div ui-view="favoriteInstanceView"></div>'
                    }
                },
                resolve: {
                    favorite: ['favorites', '$stateParams', function (favorites, $params) {
                            return favorites.get($params.favoriteId);
                        }]
                }
            });
            $sp.state('favoriteDelete', {
                url: '/delete',
                parent: 'favoriteInstance',
                views: {
                    favoriteInstanceView: {
                        templateUrl: baseInstancePath + 'delete/favorite.delete.tpl.html',
                        controller: 'favoriteDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);