(function (ng) {

    var mod = ng.module("reviewModule");

    mod.controller("reviewDetailCtrl", ['$scope', "$state", "review", "model",
        function ($scope, $state, review, model) {
            $scope.model = model;
            $scope.currentRecord = review;
            $scope.buttons = ['none'];
            $scope.actions = {
                create: {
                    displayName: 'Create',
                    icon: 'plus',
                    fn: function () {
                        $state.go('reviewNew');
                    }
                },
                edit: {
                    displayName: 'Edit',
                    icon: 'edit',
                    fn: function () {
                        $state.go('reviewEdit');
                    }
                },
                delete: {
                    displayName: 'Delete',
                    icon: 'minus',
                    fn: function () {
                        $state.go('reviewDelete');
                    }
                },
                refresh: {
                    displayName: 'Refresh',
                    icon: 'refresh',
                    fn: function () {
                        $state.reload();
                    }
                },
                list: {
                    displayName: 'List',
                    icon: 'th-list',
                    fn: function () {
                        $state.go('reviewList');
                    }
                }
            };
        }]);
})(window.angular);
