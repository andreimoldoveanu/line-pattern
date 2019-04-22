<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
		<style>
			@font-face {
				font-family: Potter;
				src: url(/resources/font/HARRYP__.TTF);
				format('truetype');
			}
			.button {
				background-color: #4CAF50;
				border: none;
				color: white;
				padding: 15px 25px;
				text-align: center;
				font-size: 16px;
				cursor: pointer;
			}
			
			.scritta{
				font-family: Potter;
    			font-size: 45px;
    			position: fixed;
			    margin-top: 100px;
			    margin-bottom: 100px;
			    margin-right: 150px;
			    margin-left: 80px;
			}
			
			.papiro{
				background: url("/resources/images/Papiro.jpg");
    			height: 100%; 	
				width: 100%;
   				}
   				
			.silente{
				height: 768px;
				width: 1366px;
				background: url("/resources/images/Silente.jpeg");
   				background-size:100% 100%;
			}
		</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div ng-app="myApp">
		<div ng-controller="salaEscapeController">
		
			<div class="papiro" ng-hide="silente">
				<label class="scritta" ng-bind="risultato"></label>
			</div>
			
			<div class="silente" ng-show="silente">
				
				
			</div>
			
		</div>
	</div>
	
	<script type="text/javascript" src="/resources/js/lib/angularjs-1.6.9.min.js"></script>
	<script>
	var app = angular.module("myApp",[]);
	
	app.controller('salaEscapeController',function($scope,salaEscapeService,$interval){
		
		$scope.silente = false;
		
		$interval( function(){
			salaEscapeService.getSilente().then(function(response){
				if(response.data){
					$scope.risultato = "";
					$scope.silente = true;
					//ng-show
					//aggiungere immagine di silente in background
				}else if(!response.data){
					$scope.silente = false;
				}
			},function(response){
				$scope.silente = true;
				console.log("errore: " + response.data);
			})
		},4000);

		$interval( function(){
			salaEscapeService.getText().then(function(response){
				$scope.risultato = response.data.text;
			},function(response){
				$scope.risultato = response.data.text;
			});
		},4000);
		
	});

	app.service('salaEscapeService',function($http){
		this.getText = function(){
			return $http({
				method: 'GET',
				url: '/escape/getText'
			});
		}
		
		this.getSilente = function(){
			return $http({
				method: 'GET',
				url: '/escape/getSilente'
			});
		}
	});

	
	</script>
</body>
</html>