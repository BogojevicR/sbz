var app = angular.module('app.controllers',[]);


app.controller('appController', ['$http','$window','$location','$rootScope','$scope','appService', 
	function($http,$window,$location,$scope,$rootScope, appService) {
	


	$scope.init= function() {
			$scope.CheckUser();
			$scope.getKategorijeKupca();
			$scope.getKategorijeArtikla();
			$scope.getAllArtikal();
			$scope.getAkcijskeDogadjaje();
			if(localStorage.getItem("username")==null){
				localStorage.setItem("username", null);
				localStorage.setItem("lozinka", null);
				localStorage.setItem("uloga",null)
			}
			if(localStorage.getItem("username")!= "null"){
				
				if(localStorage.getItem("uloga")=="KUPAC"){
					$("#profileBtn").show();
				}else{
					$("#profileBtn").hide();
				}
				
				$("#logoutBtn").show();
				$("#loginBtn").hide();
				$("#regBtn").hide();
			}else{
				$("#logoutBtn").hide();
				$("#loginBtn").show();
				$("#regBtn").show();
				$("#profileBtn").hide();
			}
	}
	$scope.roles = ["KUPAC", "MENADZER", "PRODAVAC"];
	$scope.artikal={
			sifra:"",
			popust:""
	}
	$scope.changedRole= function (role){
		if(role=="KUPAC"){
			$("#adresa").show();
		}else{
			$("#adresa").hide();
		}
	}
	
	$scope.createKorisnik = function(username,lozinka,ime,prezime,datePicker,uloga,adresa){
		console.log("USAO U createKorisnik")
		
		KorisnikJSON={
			username:username,
			ime:ime,
			prezime:prezime,
			password:lozinka,
			uloga:uloga,
			datum_registrovanja:"2012-04-23T18:25:43.511Z",
			profil_kupca:{
				sifra:"sifra",
				adresa:adresa,
				nagradni_bodovi:0,
				kategorija:null,
				istorija_kupovina:null
			}	
		}
		var string = JSON.stringify(KorisnikJSON);		
		appService.createKorisnik(string).then(function(response){
			if(response.data==false){
				alert("Username je zauzet!")
			}else{
				document.getElementById("regclose").click();
				alert("Registracija uspesna!")
			}
		})
	}
	
	
	$scope.login = function(username,lozinka){
		appService.login(username,lozinka).then(function(response){
			if(response.data!=""){
				console.log(response.data.datum_registrovanja);
				$rootScope.korisnik=response.data;
				localStorage.setItem("username", username);
				localStorage.setItem("lozinka", lozinka);
				localStorage.setItem("uloga",response.data.uloga)
				if(localStorage.getItem("uloga")=="MENADZER"){
					$window.location.href = "/#/menadzer"
					window.location.reload();
					$scope.$apply;
				}else{
					$window.location.href = ""
					$scope.$apply;
				}
				
				
			}else{
				alert("Korisnik ne postoji!")
			}
			
		})		
	}
	$scope.logout = function(username,lozinka){
		
				$rootScope.korisnik=null;
				localStorage.setItem("username", null);
				localStorage.setItem("lozinka", null);
				localStorage.setItem("uloga", null);
				$window.location.href = ""
				$scope.$apply;
		}	

	$scope.CheckUser = function(){
		var uname=localStorage.getItem("username");
		var pass=localStorage.getItem("lozinka");
		
		appService.login(uname,pass).then(function(response){
			if(response.data!=null){
				$rootScope.korisnik=response.data;
				var date=new Date($rootScope.korisnik.datum_registrovanja);
				$rootScope.korisnik.datum_registrovanja=date.toLocaleString();
			}else{
				$rootScope.korisnik=null;
			}
		})
	}
	
	$scope.getKategorijeKupca = function(){
		appService.getKategorijeKupca().then(function(response){
			$rootScope.kategorije_kupca=response.data;
		})	
	}
	
	$scope.azurirajKategoriju_kupca= function(ime_kategorije_kupca){
		appService.getKatKupcaBySifra(ime_kategorije_kupca).then(function(response){
			$scope.kategorija=response.data;
		})	
		$location.path('/azuriraj_kategoriju_kupca')
	}
	
	$scope.azurirajPrag=function(sifra,donja,gornja,procenat){
		appService.editPragBySifra(sifra,donja,gornja,procenat).then(function(response){
			$location.path('/menadzer')
		})
		
	}
	
	$scope.dodajPrag=function(sifraKat,donja,gornja,procenat){
		appService.dodajPragKategoriji(sifraKat,donja,gornja,procenat).then(function(response){
			if(response.data==true){
				$location.path('/menadzer')
			}else{
				
				alert("Dogodila se greska, pokusajte ponovo!")
			}
			
		})
	}
	
	$scope.obrisiPrag=function(sifraKat,sifra){
		appService.deletePrag(sifraKat,sifra).then(function(response){
			if(response.data==true){
				$location.path('/menadzer')
			}else{
				
				alert("Dogodila se greska, pokusajte ponovo!")
			}
		})
	}
	
	$scope.getKategorijeArtikla = function(){
		appService.getKategorijeArtikla().then(function(response){
			$scope.kategorije_artikla=response.data;
			$scope.lista_kategorija=response.data;
			
		})	
	}
	
	
	$scope.dodajKategorijuArtikla = function(naziv,kategorija,popust){
		var sifraa=null;
		if(kategorija!=undefined){
			sifraa=kategorija.sifra;
		}
		KategorijaJSON={
				
		
				sifra: sifraa,
				naziv: naziv,
				nadkategorija: null,
				maksimalni_popust: popust	
		}
		
		
		
		var string = JSON.stringify(KategorijaJSON);		
		appService.createKategorijaArtikla(string).then(function(response){
			if(response.data==false){
				alert("Greska Pokusajte ponovo!")
			}else{
				document.getElementById("btnclose").click();
				window.location.reload();
			}
		})
		
		}
	
	$scope.azurirajKategoriju_artikla= function(sifra){
		appService.getKatArtBySifra(sifra).then(function(response){
			$scope.artkategorija=response.data;
		})	
		$location.path('/azuriraj_kategoriju_artikla')
	}
	
	$scope.izmeniKategoriju=function(sifra,naziv,procenat,nadkategorija){
		appService.izmeniKategoriju(sifra,naziv,procenat,nadkategorija).then(function(response){
			$location.path('/menadzer')
		})
	}
	
	$scope.getAllArtikal = function(){
		$scope.lista_artikala=[];
		$scope.sortedlista_artikala=[];
		appService.getAllArtikal().then(function(response){
			
			angular.forEach(response.data,function(value,index){
	            if(value.status_zapisa=="AKTIVAN"){
	            	
	            	$scope.sortedlista_artikala.push(value)	     
	            }
	        })
			
			$scope.lista_artikala=response.data;
			
			angular.forEach($scope.lista_artikala,function(value,index){
				value.datum_kreiranja=$scope.convertToString(value.datum_kreiranja);

	        })
	        
	        angular.forEach($scope.sortedlista_artikala,function(value,index){
				value.popust=0;

	        })
	        

		})	
	}
	
	$scope.getArtikalBySifra=function(sifra){
		appService.getArtikalBySifra(sifra).then(function(response){
			$location.path('/azuriraj_artikal')
		})
	}
	
	$scope.dodajArtikal=function(nazivA,cenaA,kateg,stanjeA,minimumA){
		ArtikalJSON={
				sifra: kateg.sifra,
				naziv: nazivA,
				kategorija: null,
				cena: cenaA,
				brojnoStanje: stanjeA,
				datum_kreiranja: "2012-04-23T18:25:43.511Z",
				treba_zaliha: false,
				status_zapisa: null,
				minimalno_stanje: minimumA
		}
		
		var string = JSON.stringify(ArtikalJSON);		
		appService.createArtikal(string).then(function(response){
			if(response.data==false){
				alert("Greska Pokusajte ponovo!")
			}else{
				document.getElementById("artikalclose").click();
				window.location.reload();
			}
		})
		
	}
	
	$scope.getAkcijskeDogadjaje = function(){
		appService.getAkcijskeDogadjaje().then(function(response){
			$scope.lista_dogadjaja=response.data;
			angular.forEach($scope.lista_dogadjaja,function(value,index){
				value.pocetak=$scope.convertToString(value.pocetak);

	        })
	        
	        angular.forEach($scope.lista_dogadjaja,function(value,index){
				value.zavrsetak=$scope.convertToString(value.zavrsetak);

	        })
	        
			$scope.podesiAkcije();
			
		})	
	}
	
	
	
	$scope.dodajDogadjaj=function(nazivDog,multipleSelect,procenatDog,pocetakDog,krajDog){
		DogadjajJSON={
				sifra: nazivDog,
				naziv: nazivDog,
				pocetak: pocetakDog,
				zavrsetak: krajDog,
				popust: procenatDog,
				listaKategorija: multipleSelect,
		}
		
		
		angular.forEach(DogadjajJSON.listaKategorija,function(value,index){
			delete value.$$hashKey;
        })
		
		
		var string = JSON.stringify(DogadjajJSON);		
		appService.createDogadjaj(string).then(function(response){
			if(response.data==false){
				alert("Greska Pokusajte ponovo!")
			}else{
				document.getElementById("dogadjajclose").click();
				window.location.reload();
			}
		})
		
	}
	
	$scope.azurirajDogadjaj= function(sifra){
		appService.getDogadjajBySifra(sifra).then(function(response){
			$scope.akcijski_dogadjaj=response.data;
		})	
		$location.path('/azuriraj_dogadjaj')
	}
	
	
	$scope.izmeniDogadjaj=function(sifra,naziv,pocetak,zavrsetak,popust,listaKategorija){
		angular.forEach(listaKategorija,function(value,index){
			delete value.$$hashKey;
        })
       
        DogadjajJSON={
				sifra: sifra,
				naziv: naziv,
				pocetak: pocetak,
				zavrsetak: zavrsetak,
				popust: popust,
				listaKategorija: listaKategorija,
		}
		
		
		
		var string = JSON.stringify(DogadjajJSON);		
		appService.izmeniDogadjaj(string).then(function(response){
			if(response.data==false){
				alert("Greska Pokusajte ponovo!")
			}else{
			
				$location.path('/menadzer')
				window.location.reload();
			}
		})
	}
	
	
	
	
	
	
	$scope.sort=function(sortsifra,sortnaziv,sortkat,sortod,sortdo){
		if(sortsifra==undefined){
			sortsifra=""
		}
		if(sortkat==undefined){
			sortkat=""
		}
		if(sortnaziv==undefined){
			sortnaziv=""
		}
		if(sortkat==undefined){
			sortkat=""
		}
		if(sortod==undefined || sortdo==undefined){
			sortod=0;
			sortdo=9999999999999;
		}
		
		$scope.sortedlista_artikala=[];

		angular.forEach($scope.lista_artikala,function(value,index){
            if(value.sifra.toLowerCase().indexOf(sortsifra.toLowerCase())!== -1 && value.naziv.toLowerCase().indexOf(sortnaziv.toLowerCase())!== -1 && value.kategorija.naziv.toLowerCase().indexOf(sortkat.toLowerCase())!== -1 && (value.cena>sortod && value.cena<sortdo)){
            	$scope.sortedlista_artikala.push(value);
            }
        })
	}
	
	$scope.dodajukorpu=function(sifra,brojArtikla){
		
	}
	
	
	$scope.userRoleCheck=function(){
		if(localStorage.getItem("username")==null){
			$location.path('')
		}else{
			if(localStorage.getItem("uloga")=="KUPAC"){
				
				$location.path('/kupac')
			}
			if(localStorage.getItem("uloga")=="MENADZER"){
				$location.path('/menadzer')
			}if(localStorage.getItem("uloga")=="PRODAVAC"){
				$location.path('/menadzer')
			}
		}
	}
	
	
	$scope.convertToString=function(date){
		
	//	var milliseconds = date.getTime();
		var date=new Date(date);
		date=date.toLocaleString();
		return date;
	}
	
	$scope.setDate=function(){

		var today = new Date();
		var dd = today.getDate();
		var tt = today.getDate()+1;
		var mm = today.getMonth()+1; //January is 0!
		var yyyy = today.getFullYear();
		 if(dd<10){
		        dd='0'+dd
		    } 
		    if(mm<10){
		        mm='0'+mm
		    } 

		today = yyyy+'-'+mm+'-'+dd;
		document.getElementById("datefield").setAttribute("min", today);	
		document.getElementById("datefield1").setAttribute("min", today);
	}
	
	$scope.setDogadjajDate=function(pocetak){
		$scope.akcijski_dogadjaj.pocetak=new Date(pocetak);	
	}
	$scope.setDogadjajDateZ=function(zavrsetak){
		$scope.akcijski_dogadjaj.zavrsetak=new Date(zavrsetak);
	}
	
	$scope.podesiAkcije=function(){
		$scope.akcijepoKategoriji=[];
		
		angular.forEach($scope.lista_dogadjaja,function(dogadjaj,index){
	        
			angular.forEach(dogadjaj.listaKategorija,function(kategorija,id){
				KatPop={
						sifra:kategorija.sifra,
						naziv:kategorija.naziv,
						popust:dogadjaj.popust,
						pocetak:dogadjaj.pocetak,
						zavrsetak:dogadjaj.zavrsetak
				}
				$scope.akcijepoKategoriji.push(KatPop);
			})
	    })
	}
	
	
	$scope.initPopust=function(sifra){
		var today = new Date().toLocaleString();
	
		angular.forEach($scope.sortedlista_artikala,function(artikal,index){
			angular.forEach($scope.akcijepoKategoriji,function(popust,index){
				if(popust.sifra==artikal.kategorija.sifra){
					if($scope.betweenDate(popust.pocetak,today,popust.zavrsetak))
					artikal.popust=popust.popust;
				}
	        })
        })
	}
	
	
	$scope.betweenDate=function(date1,date2,date3){
		var date1=date1.split(",");
		var date3=date3.split(",");
		var d1=new Date(date1[0]);
		var d2=new Date(date2);
		var d3=new Date(date3[0]);
		
		if(d1<=d2 && d3>=d2){
			return true;
		}else{
			return false;
		}
		
	}
	
	

	
}
])





