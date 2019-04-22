<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div ng-app="myApp">
		<div ng-controller="salaPcController">
			<input ng-keypress="$event.keyCode == 13 ? sendText() : ''" style="width:50%" ng-model="text">
			<button ng-click="sendText()">Invia</button> <br>
			<label ng-bind="risultato"></label> <br>
			<button ng-click="activeDisactiveSilente()">Silente</button>
			<label ng-bind="silente"></label>
			<br/><br/><br/>
			<button ng-click="resetGames()">Reset</button>
			<label ng-bind="resetted"></label>
			
			<label ng-show="error" ng-bind="'Errore: ' + error"></label>
		</div>
	</div>
	
	<script type="text/javascript" src="/resources/js/lib/angularjs-1.6.9.min.js"></script>
	<script>
	var app = angular.module("myApp",[]);
	
	app.controller('salaPcController',function($scope,salaPcService){
		
		$scope.sendText = function(){
			if($scope.text != undefined && $scope.text != ""){
				
				salaPcService.setText($scope.text).then(function(response){
					
				},function(response){
					
				})
			}
			
			salaPcService.getText().then(function(response){
				$scope.risultato = response.data.text;
			},function(response){
				$scope.risultato = response.data.text;
			})
			
		}
		
		salaPcService.getSilente().then(function(response){
			$scope.silente = response.data;
		},function(response){
			$scope.error = response.data;
		});
		
		salaPcService.getText().then(function(response){
			$scope.risultato = response.data.text;
		},function(response){
			$scope.error = response.data;
		});
		
		$scope.activeDisactiveSilente = function(){
			if($scope.silente){
				salaPcService.setSilente(false).then(function(){
					$scope.silente = response.data;
				},function(){
					$scope.error = response.data;
				});
			}else{
				salaPcService.setSilente(true).then(function(){
					$scope.silente = response.data;
				},function(){
					$scope.error = response.data;
				});
			}
			
			salaPcService.getSilente().then(function(response){
				$scope.silente = response.data;
			},function(response){
				$scope.error = response.data;
			});
		}
		

		
		$scope.resetGames = function(){
				salaPcService.resetGames().then(function(response){
					$scope.resetted = response.data.text;
				},function(response){
					$scope.error = response.data.text;
				});
		}
	});

	app.service('salaPcService',function($http){
		
		this.silente = false;
		
		this.setText = function(text){
			return $http({
				method: 'POST',
				url: '/escape/writeText',
				data: {
					text: text
				}
			});
		}
		
		this.getText = function(){
			return $http({
				method: 'GET',
				url: '/escape/getText'
			});
		}
		
		this.getSilente = function(){
			return $http({
				method: 'GET',
				url: '/escape/getSilente',
			});
		}

		this.setSilente = function(silente){
			return $http({
				method: 'POST',
				url: '/escape/setSilente',
				params: {
					silente: silente
				}
			});
		}
		
		this.resetGames = function(){
			return $http({
				method: 'POST',
				url: '/escape/resetGames'
			});
		}
		
		
	});

	
	</script>
</body>
</html>