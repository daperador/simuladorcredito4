'use strict';

// Declare app level module which depends on filters, and services
var module=angular.module('simuladorCredito', [
  'ngRoute',
  'simuladorCredito.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/menu', {templateUrl: 'partials/menuPartial.html', controller: 'menuCtrl'});
  $routeProvider.otherwise({redirectTo: '/menu'}); 
}]);
