/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module("clientModule");

    mod.controller("clientDeleteCtrl", ["$state", "client", 'authService', 
        function ($state, client, authSvc) {
            this.confirmDelete = function () {
                authSvc.delete(client.idAuth0).then(function (data) {
                    client.remove().then(function () {
                        $state.go('clientList', null, {reload: true});
                    });
                }, function (data) {
                    //self.showError("Error al eliminar");
                });
            };
        }]);
})(window.angular);
