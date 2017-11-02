(function (ng) {

    var mod = ng.module('authModule');

    mod.provider('authService', function () {

        //Default
        var values = {
            apiUrl: 'api/users/',
            loginState: 'login',
            logoutRedirectState: 'login',
            registerState: 'register',
            forgotPassState: 'forgot',
            successState: 'home',
            forbiddenState: 'forbidden',
            perfilState: 'clientEditUser',
            shoppingList:'shoppingListTo',
            loginURL: 'login',
            registerURL: 'register',
            logoutURL: 'logout',
            forgotPassURL: 'forgot',
            meURL: 'me',
            clientUrl: 'api/clients/',
            shoppingUrl: 'api/shopping/',
            itemShopping: 'api/itemShopping'
        };

        //Default Roles
        var roles = {cliente:{} };
        
         
        this.setValues = function (newValues) {
            values = ng.extend(values, newValues);
        };

        this.getValues = function () {
            return values;
        };

        this.getRoles = function(){
            return roles;
        };

        this.setRoles = function(newRoles){
            roles = newRoles;
        };

        this.$get = ['$state', '$http','$rootScope','$cookies','Restangular', function ($state, $http, $rootScope,$cookies,Restangular) {
            return {
                getRoles: function(){
                    return roles;
                },
                login: function (user) {
                    return $http.post(values.apiUrl+values.loginURL, user).then(function (response) {
                        $rootScope.$broadcast('logged-in', response.data);        
                        $cookies.put("id_token",response.headers("id_token"));
                        $cookies.put("username",user.userName);
                        var permissions = JSON.stringify(response.data.permissions);
                        $cookies.put("permissions",permissions);
                        var roles = JSON.stringify(response.data.roles);
                        $cookies.put("roles",roles);
                        $state.go(values.successState);
                      
                    });
                },
                getConf: function () {
                    return values;
                },
                logout: function () {
                    
                    return $http.get(values.apiUrl+values.logoutURL).then(function () {
                        $rootScope.$broadcast('logged-out');
                        $cookies.remove("id_token");
                        $cookies.remove("username");
                        $cookies.remove("permissions");
                        $cookies.remove("roles");
                        $state.go(values.logoutRedirectState);
                    });
                },
                register: function (user) {
                    
                        return $http.post(values.apiUrl + values.registerURL, user).then(function (data) {
                            var newClient = {'address': "",
                                'email': user.email,
                                'firstName': user.givenName + " " + user.middleName,
                                'lastName': user.surName,
                                'login': user.userName,
                                'phone': "",
                                'idAuth0': data.data._id
                            };
                            $http.post(values.clientUrl, newClient).then(function (data1) {
                                $state.go(values.loginState);
                            });
                        });
                },
                forgotPass: function (user) {
                    return $http.post(values.apiUrl+values.forgotPassURL, user).then(function (data) {
                        $state.go(values.loginState);
                    });
                },
                registration: function () {
                    $state.go(values.registerState);
                },
                goToLogin: function () {
                    $state.go(values.loginState);
                },
                goToForgotPass: function(){
                    $state.go(values.forgotPassState);
                },
                goToBack: function () {
                    $state.go(values.loginState);
                },
                goToSuccess: function () {
                    
                    $state.go(values.successState);
                },
                goToForbidden: function(){
                    $state.go(values.forbiddenState);
                },
                goToPerfil: function(username){
                    $state.go(values.perfilState, {clientUser:username});
                },
                goToShoppingList: function (username) {
                    $http.get(values.clientUrl + username).then(function (data) {
                        $state.go(values.shoppingList, {clientId: data.data.id});
                    });
                },
                getCarShopping: function (username) {
                    var request = new XMLHttpRequest();
                    request.open('GET', values.clientUrl + username, false);  // `false` makes the request synchronous
                    request.send(null);

                    if (request.status === 200) {
                        return $http.get(values.shoppingUrl + 'count?clientId=' + JSON.parse(request.responseText).id);
                    }
                },
                checkout: function (username) {
                    var request = new XMLHttpRequest();
                    request.open('GET', values.clientUrl + username, false);  // `false` makes the request synchronous
                    request.send(null);

                    if (request.status === 200) {
                        return $http.get(values.shoppingUrl + 'checkout?clientId=' + JSON.parse(request.responseText).id);
                    }
                },
                userAuthenticated: function(){
                    $http.get(values.apiUrl + values.meURL).then(function(response){
                       var permissions = JSON.stringify(response.data.permissions);
                        $cookies.put("permissions",permissions);
                        var roles = JSON.stringify(response.data.roles);
                        $cookies.put("roles",roles);
                   });
                    return $http.get(values.apiUrl + values.meURL);
                }, 
                delete: function (idAuth0) {
                        return $http.post('api/' + idAuth0).then(function (data) {
                            //susseful
                        });
                }
            };
        }];
    });
})(window.angular);

