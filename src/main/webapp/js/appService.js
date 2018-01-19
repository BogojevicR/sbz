var services = angular.module('app.services',['ngResource']);

services.service('appService', ['$http', '$rootScope', 
	function($http, $rootScope) {
	
	this.createKorisnik = function(korisnik){
		return $http.post("http://localhost:8080/user/create",korisnik)
	}
	
	this.login = function(username,lozinka){
		return $http.get("http://localhost:8080/user/login/"+username+"/"+lozinka)
	}
	
	this.getKategorijeKupca = function(){
		return $http.get("http://localhost:8080/menadzer/all/katKupca")
	}
	
	this.getKatKupcaBySifra= function(sifra){
		return $http.get("http://localhost:8080/menadzer/katKupca/"+sifra)
	}
	
	this.editPragBySifra=function(sifra,donja,gornja,procenat){
		return $http.get("http://localhost:8080/menadzer/prag/edit/"+sifra+"/"+donja+"/"+gornja+"/"+procenat)
	}
	
	this.dodajPragKategoriji=function(sifraKat,donja,gornja,procenat){
		return $http.post("http://localhost:8080/menadzer/prag/create/"+sifraKat+"/"+donja+"/"+gornja+"/"+procenat)
	}
	
	this.deletePrag=function(sifraKat,sifra){
		return $http.get("http://localhost:8080/menadzer/prag/delete/"+sifraKat+"/"+sifra)
	}
	
	this.getKategorijeArtikla = function(){
		return $http.get("http://localhost:8080/menadzer/all/katArt")
	}
	
	
	this.createKategorijaArtikla = function(kategorija){
		return $http.post("http://localhost:8080/menadzer/katArt/create",kategorija)
	}
	
	this.getKatArtBySifra=function(sifra){
		return $http.get("http://localhost:8080/menadzer/katArt/"+sifra);
	}
	
	this.izmeniKategoriju=function(sifra,naziv,procenat,nadkategorija){
		return $http.get("http://localhost:8080/menadzer/katArt/edit/"+sifra+"/"+"/"+naziv+"/"+procenat+"/"+nadkategorija)
	}
	
	this.getAllArtikal=function(){
		return $http.get("http://localhost:8080/menadzer/all/artikal")
	}
	
	this.getArtikalBySifra=function(sifra){
		return $http.get("http://localhost:8080/menadzer/artikal/"+sifra)
	}
	
	this.createArtikal=function(artikal){
		return $http.post("http://localhost:8080/menadzer/artikal/create",artikal)
	}
	
	this.createDogadjaj=function(dogadjaj){
		return $http.post("http://localhost:8080/menadzer/dogadjaj/create",dogadjaj)
	}
	
	this.getAkcijskeDogadjaje=function(){
		return $http.get("http://localhost:8080/menadzer/all/dogadjaj")
	}
	
	this.getDogadjajBySifra=function(sifra){
		return $http.get("http://localhost:8080/menadzer/dogadjaj/"+sifra)
	}
	
	this.izmeniDogadjaj=function(dogadjaj){
		return $http.post("http://localhost:8080/menadzer/dogadjaj/izmeni",dogadjaj)
	}
	
	this.kreirajRacun=function(racun){
		return $http.post("http://localhost:8080/user/racun/create",racun)
	}
	
	this.getRacunBySifra=function(sifra){
		return $http.get("http://localhost:8080/menadzer/racun/"+sifra)
	}
	
	this.getRacune=function(){
		return $http.get("http://localhost:8080/menadzer/all/racun")
	}
	
	this.otkaziRacun=function(sifra){
		return $http.get("http://localhost:8080/menadzer/racun/otkazi/"+sifra) 
	}
	this.obradiRacun=function(sifra){
		return $http.get("http://localhost:8080/menadzer/racun/obradi/"+sifra) 
	}
	this.poruciArtikal=function(sifra){
		return $http.get("http://localhost:8080/menadzer/artikal/poruci/"+sifra) 
	}

	
}])