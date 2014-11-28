'use strict';

/* Controllers */
var module = angular.module('simuladorCredito.controllers', []);

module.controller('menuCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.lineas=[];
    $scope.planes;
    $scope.linea;
    $scope.plan;
    $scope.administrador=1;
    $scope.ultimoKey=null;
    $scope.tamano = 50;
    $scope.desde = 0;
    
    $http.get('webresources/login/administrador/', {})
        .success(function (data, status, headers, config) {
            $scope.administrador=data;
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        }); 
        
    $scope.crudLineas=function(){
        $scope.cargarLineas();
        $('#dlgLineas').modal();
    };
    
    
    $scope.cargarLineas=function(){
        $http.get('webresources/linea/administrador/'+$scope.administrador, {})
        .success(function (data, status, headers, config) {
            $scope.lineas=data;
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });        
    };
    
    $scope.editarLinea=function(lineaId){
        $http.get('webresources/linea/linea/'+lineaId, {})
        .success(function (data, status, headers, config) {
            $scope.linea=data;
            $scope.linea.actividad='actualizar';
            $('#dlgLinea').modal();
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });        
        
    };
    
    $scope.guardarLinea=function(){
        $scope.linea.administrador=$scope.administrador;
        if ($scope.linea.actividad == 'actualizar'){
            $http.put('webresources/linea/linea', $scope.linea)
            .success(function (data, status, headers, config) {
                $('#dlgLinea').modal('toggle');
                $scope.cargarLineas();
            }).error(function (data, status, headers, config) {
                alert('Error al guardar la Línea, por favor intente más tarde');
            });
        }else{
            $http.post('webresources/linea/linea', $scope.linea)
            .success(function (data, status, headers, config) {
                $('#dlgLinea').modal('toggle');
                $scope.cargarLineas();
            }).error(function (data, status, headers, config) {
                alert('Error al guardar la Línea, por favor intente más tarde');
            });
        }
        
    };
    
    $scope.nuevaLinea=function(){
        $scope.linea={id:null, nombre:'', tasa:0.0, actividad:'crear', administrador: $scope.administrador};
        $('#dlgLinea').modal();
    };
    
    $scope.eliminarLinea=function(lineaId){
        $http.delete('webresources/linea/linea/'+lineaId, {})
        .success(function (data, status, headers, config) {
            $scope.cargarLineas();
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });        
    };
    
    $scope.cargarPlanes=function(primero){
        if (primero){
            $scope.desde=0;
        }else{
            $scope.desde=$scope.desde+$scope.tamano;
        }
        $http.get('webresources/planPago/planesPago/'+$scope.desde+'/'+$scope.tamano, {})
        .success(function (data, status, headers, config) {
            $scope.planes=data;
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });
    };
    
    $scope.primeraPagina=function(){
        $scope.cargarPlanes(true);
    };
    
    $scope.siguiente=function(){
        $scope.cargarPlanes(false);
    };
    
    $scope.listarPlanes=function(){
        $scope.cargarPlanes(true);
        $('#dlgPlanes').modal();
    };
    
    $scope.detallePlan=function(planId){
        $http.get('webresources/planPago/planPago/'+planId, {})
        .success(function (data, status, headers, config) {
            $scope.plan=data;
            $('#dlgPlan').modal();
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });        
    };
    
    
    
}]);

