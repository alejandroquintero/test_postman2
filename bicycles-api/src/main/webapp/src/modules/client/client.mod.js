/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('clientModule', ['ngCrud', 'ui.router']);

    mod.constant('clientModel', {
        name: 'client',
        displayName: 'Client',
		url: 'clients',
        fields: {
            lastName: {
                displayName: 'Last Name',
                type: 'String',
                required: true
            },
            firstName: {
                displayName: 'First Name',
                type: 'String',
                required: true
            },
            login: {
                displayName: 'login',
                type: 'String',
                required: true
            },
            phone: {
                displayName: 'Phone',
                type: 'String',
                required: true
            },
            address: {
                displayName: 'Address',
                type: 'String',
                required: true
            },
            email: {
                displayName: 'Email',
                type: 'String',
                required: true
            },
            idAuth0: {
                displayName: 'Email',
                type: 'String',
                required: true
            }
        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/client/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('client', {
                url: '/clients?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'client.tpl.html',
                        controller: 'clientCtrl'
                    }
                },
                resolve: {
                    model: 'clientModel',
                    clients: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('clientList', {
                url: '/list',
                parent: 'client',
                views: {
                    clientView: {
                        templateUrl: basePath + 'list/client.list.tpl.html',
                        controller: 'clientListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('clientNew', {
                url: '/new',
                parent: 'client',
                views: {
                    clientView: {
                        templateUrl: basePath + 'new/client.new.tpl.html',
                        controller: 'clientNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('clientInstance', {
                url: '/{clientId:int}',
                abstract: true,
                parent: 'client',
                views: {
                    clientView: {
                        template: '<div ui-view="clientInstanceView"></div>'
                    }
                },
                resolve: {
                    client: ['clients', '$stateParams', function (clients, $params) {
                            return clients.get($params.clientId);
                        }]
                }
            });
            $sp.state('clientDetail', {
                url: '/details',
                parent: 'clientInstance',
                views: {
                    clientInstanceView: {
                        templateUrl: baseInstancePath + 'detail/client.detail.tpl.html',
                        controller: 'clientDetailCtrl'
                    }
                }
            });
            $sp.state('clientEdit', {
                url: '/edit',
                sticky: true,
                parent: 'clientInstance',
                views: {
                    clientInstanceView: {
                        templateUrl: baseInstancePath + 'edit/client.edit.tpl.html',
                        controller: 'clientEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('clientDelete', {
                url: '/delete',
                parent: 'clientInstance',
                views: {
                    clientInstanceView: {
                        templateUrl: baseInstancePath + 'delete/client.delete.tpl.html',
                        controller: 'clientDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('clientInstanceUser', {
                url: '/{clientUser:string}',
                abstract: true,
                parent: 'client',
                views: {
                    clientView: {
                        template: '<div ui-view="clientInstanceView"></div>'
                    }
                },
                resolve: {
                    client: ['clients', '$stateParams', function (clients, $params) {
                            return clients.get($params.clientUser);
                        }]
                }
            });
            $sp.state('clientEditUser', {
                url: '/edit',
                parent: 'clientInstanceUser',
                views: {
                    clientInstanceView: {
                        templateUrl: baseInstancePath + 'edit/client.edit.tpl.html',
                        controller: 'clientEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);

