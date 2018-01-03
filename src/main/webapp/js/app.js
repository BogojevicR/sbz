var app= angular.module('app',['ui.router','app.services','app.controllers']);

app.config(function($stateProvider,$urlRouterProvider) {
	$stateProvider
	.state('index', {
		url:'',
		template:'<div ng-init="userRoleCheck()"> </div>',
		controller:'appController'
	}).state('kupac', {
		url:'/kupac',
		templateUrl:'/kupac.html',
		controller:'appController'
	})
	.state('profil',{
		url:'/profil',
		templateUrl:'/profil.html',
		controller:'appController'
	})
	.state('menadzer',{
		url:'/menadzer',
		templateUrl:'/menadzer.html',
		controller:'appController'	
	})
	.state('azuriraj_kategoriju_kupca',{
		url:'/azuriraj_kategoriju_kupca',
		templateUrl:'/azuriraj_kategoriju_kupca.html',
		controller:'appController'	
	}).state('azuriraj_kategoriju_artikla',{
		url:'/azuriraj_kategoriju_artikla',
		templateUrl:'/azuriraj_kategoriju_artikla.html',
		controller:'appController'	
	}).state('azuriraj_dogadjaj',{
		url:'/azuriraj_dogadjaj',
		templateUrl:'/azuriraj_dogadjaj.html',
		controller:'appController'	
	});
	$urlRouterProvider.otherwise('/');
	
	
})