(function (ng) {
    var mod = ng.module('reviewModule', ['ngCrud', 'ui.router']);

    mod.constant('reviewModel', {
        name: 'review',
        displayName: 'Comentarios',
        url: 'review',
        fields: {
            star: {
                displayName: 'Star',
                type: 'Long',
                required: true
            },
            commentary: {
                displayName: 'Commentary',
                type: 'String',
                required: true
            }}
    });

    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/review/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('review', {
                url: '/review?page&limit',
                abstract: true,
                parent: 'bicycleDetail',
                views: {
                    bicycleChieldView: {
                        templateUrl: basePath + 'review.tpl.html',
                        controller: 'reviewCtrl'
                    }
                },
                resolve: {
                    model: 'reviewModel',
                    reviews: ['bicycle', '$stateParams', 'model', function (bicycle, $params, model) {
                            return bicycle.getList(model.url, $params);
                        }]}
            });
            $sp.state('reviewList', {
                url: '/list',
                parent: 'review',
                views: {
                    'bicycleInstanceView@bicycleInstance': {
                        templateUrl: basePath + 'list/review.list.tpl.html',
                        controller: 'reviewListCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    model: 'reviewModel'
                },
                ncyBreadcrumb: {
                    label: 'review'
                }
            });
            $sp.state('reviewNew', {
                url: '/new',
                parent: 'review',
                views: {
                    'bicycleInstanceView@bicycleInstance': {
                        templateUrl: basePath + 'new/review.new.tpl.html',
                        controller: 'reviewNewCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    model: 'reviewModel'
                },
                ncyBreadcrumb: {
                    parent: 'reviewList',
                    label: 'new'
                }
            });
            $sp.state('reviewInstance', {
                url: '/{reviewId:int}',
                abstract: true,
                parent: 'review',
                views: {
                    'bicycleInstanceView@bicycleInstance': {
                        template: '<div ui-view="reviewInstanceView"></div>'
                    }
                },
                resolve: {
                    review: ['reviews', '$stateParams', function (reviews, $params) {
                            return reviews.get($params.reviewId);
                        }]
                }
            });
            $sp.state('reviewDetail', {
                url: '/details',
                parent: 'reviewInstance',
                views: {
                    reviewInstanceView: {
                        templateUrl: baseInstancePath + 'detail/review.detail.tpl.html',
                        controller: 'reviewDetailCtrl'
                    }
                },
                resolve: {
                    model: 'reviewModel'
                },
                ncyBreadcrumb: {
                    parent: 'reviewList',
                    label: 'details'
                }
            });
            $sp.state('reviewEdit', {
                url: '/edit',
                sticky: true,
                parent: 'reviewInstance',
                views: {
                    reviewInstanceView: {
                        templateUrl: baseInstancePath + 'edit/review.edit.tpl.html',
                        controller: 'reviewEditCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    model: 'reviewModel'
                },
                ncyBreadcrumb: {
                    parent: 'reviewDetail',
                    label: 'edit'
                }
            });
            $sp.state('reviewDelete', {
                url: '/delete',
                parent: 'reviewInstance',
                views: {
                    reviewInstanceView: {
                        templateUrl: baseInstancePath + 'delete/review.delete.tpl.html',
                        controller: 'reviewDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    model: 'reviewModel'
                }
            });
        }]);
})(window.angular);
