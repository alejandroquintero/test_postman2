(function (ng) {

    var mod = ng.module("reviewModule");

    mod.controller("reviewDeleteCtrl", ["$state", "review","model","$scope", function ($state, review,model,$scope) {
           $scope.model = model;
             this.confirmDelete = function () {
                review.remove().then(function () {
                    $state.go('reviewList', null, {reload: true});
                });
            };
        }]);
})(window.angular);
