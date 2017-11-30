/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('favoriteModule', ['ngCrud', 'ui.router']);

    mod.constant('favoriteModel', {
        name: 'favorite',
        displayName: 'favorite',
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
                        controller: 'favoriteNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('favoriteDelete', {
                url: '/delete',
                parent: 'favorite',
                views: {
                    favoriteView: {
                        controller: 'favoriteDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('favoriteListUser', {
                url: '/list/:username',
                views: {
                    mainView: {
                        controller: 'favoriteListCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({});
                        }],
                    model: 'favoriteModel',
                    bicycles: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
	}]);
})(window.angular);