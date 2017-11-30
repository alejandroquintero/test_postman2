/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("favoriteModule");

    mod.controller("favoriteDeleteCtrl", ["$scope", "$state", "favorites", "$cookies",
        function ($scope, $state, favorites, $cookies) {
            bicycleId = parseInt($cookies.get("bicycleFavorite"));
            $cookies.remove("bicycleFavorite");
            username = $cookies.get("username");
            
            favorites.remove({params: {bicycleId: bicycleId, username: username}}).then(function (rc) {
                $state.go('bicycleList', {});
            });
        }]);
})(window.angular);

