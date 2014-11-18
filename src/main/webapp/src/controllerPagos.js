'use strict';

/* Controllers */
var module = angular.module('simuladorCredito.controllers', []);

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

module.controller('planPagosCtrl', ['$scope', '$http', function ($scope, $http) {
        $scope.generando=false;
        $scope.datosFormulario={};
        $scope.plan;
        $http.get('webresources/linea/administrador/'+getParameterByName('id'), {})
        .success(function (data, status, headers, config) {
            $scope.lineas=data;
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });
        
        $scope.registrar = function(){
            $scope.datosFormulario.fechaNacimiento=$('#fechaNacimiento').data().datepicker.viewDate.getTime();
            $http.post('webresources/planPago',$scope.datosFormulario,{})
            .success(function (data, status, headers, config) {
                $scope.plan=data;
                $scope.generando=true;
            }).error(function (data, status, headers, config) {
                alert('Error al guardar la información, por favor intente más tarde');
            }); 
        };
        
        $scope.limpiar = function(){
            $scope.datosFormulario={};
            $scope.generando=false;
        };
        
    $scope.detallePlan=function(){
        $http.get('webresources/planPago/planPago/'+$scope.plan.id, {})
        .success(function (data, status, headers, config) {
            $scope.plan=data;
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });        
    };

}]);