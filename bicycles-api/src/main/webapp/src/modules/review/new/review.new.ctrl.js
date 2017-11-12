(function (ng) {

    var mod = ng.module("reviewModule");

    mod.controller("reviewNewCtrl", ["$scope", "$state", "reviews","model",
        function ($scope, $state, reviews, model) {
            $scope.model = model;
            $scope.currentRecord = {};
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.reviewForm.$valid) {
                            reviews.post($scope.currentRecord).then(function (rc) {
                                $state.go('reviewDetail', {reviewId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('reviewList');
                    }
                }
            };
        }]);
})(window.angular);
