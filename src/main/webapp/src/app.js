'use strict';

// Declare app level module which depends on filters, and services
var module=angular.module('simuladorCredito', [
  'ngRoute',
  'simuladorCredito.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/inicio', {templateUrl: 'partials/inicio.html', controller: 'inicioCtrl'});
  $routeProvider.otherwise({redirectTo: '/inicio'}); 
}]);
