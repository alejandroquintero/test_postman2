/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module("clientModule");

    mod.controller("clientDeleteCtrl", ["$state", "client", function ($state, client) {
            this.confirmDelete = function () {
                client.remove().then(function () {
                    $state.go('clientList', null, {reload: true});
                });
            };
        }]);
})(window.angular);
