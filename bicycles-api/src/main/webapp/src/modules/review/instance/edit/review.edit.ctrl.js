(function (ng) {

    var mod = ng.module("reviewModule");

    mod.controller("reviewEditCtrl", ["$scope", "$state", "review","model",
        function ($scope, $state, review,model) {
            $scope.model = model;
            $scope.currentRecord = review;
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.reviewForm.$valid) {
                            $scope.currentRecord.put().then(function (rc) {
                                $state.go('reviewDetail', {reviewId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('reviewDetail');
                    }
                }
            };
        }]);
})(window.angular);
