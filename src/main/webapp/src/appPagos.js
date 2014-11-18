'use strict';

// Declare app level module which depends on filters, and services
var module=angular.module('simuladorCredito', [
  'ngRoute',
  'simuladorCredito.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/planPagosPartial.html', controller: 'planPagosCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
