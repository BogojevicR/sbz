package sbz.app.service;
import sbz.app.Application;
import sbz.app.model.AkcijskiDogadjaj;
import sbz.app.model.Korisnik;
import sbz.app.model.PopustNaRacun;
import sbz.app.model.PopustNaStavku;
import sbz.app.model.Racun;
import sbz.app.model.StavkaRacuna;
import sbz.app.repository.AkcijskiDogadjajRepository;
import sbz.app.repository.KategorijaKupcaRepository;
import sbz.app.repository.KorisnikRepository;
import sbz.app.repository.PopustNaRacunRepository;
import sbz.app.repository.PopustNaStavkuRepository;
import sbz.app.repository.ProfilKupcaRepository;
import sbz.app.repository.RacunRepository;
import sbz.app.repository.StavkaRacunaRepository;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import javax.persistence.EnumType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.sonatype.inject.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

@RestController
@RequestMapping("/user")
public class KorisnikController {
	@Autowired
	KorisnikRepository rep;
	@Autowired
	ProfilKupcaRepository profilrep;
	@Autowired
	RacunRepository racunrep;
	@Autowired
	StavkaRacunaRepository stavkaracrep;
	@Autowired
	PopustNaStavkuRepository popustnastavkurep;
	@Autowired
	PopustNaRacunRepository popustnaracunrep;
	
	@Autowired
	KategorijaKupcaRepository katkuprep;
	
	@Autowired
	AkcijskiDogadjajRepository adrep;
	
	@RequestMapping("/all")
	@ResponseBody
	public List<Korisnik> getAll(){
		
		return rep.findAll();
		
	}
	
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public boolean create(@RequestBody final String userString) throws JsonParseException, JsonMappingException, IOException, JsonMappingException, IOException{
			ObjectMapper mapper = new ObjectMapper();
			Korisnik user = mapper.readValue(userString, Korisnik.class);
			if(rep.findByUsername(user.getUsername())!=null){
				return false;
			}else{
			user.getProfil_kupca().setKategorija(katkuprep.findBySifra("obicni"));
			user.podesiprofil(user);
			if(user.getUloga()==Korisnik.Role.KUPAC)
				profilrep.save(user.getProfil_kupca());

			rep.save(user);
			return true;
			
		}
	}
	
	
	
	
	@RequestMapping(value="/login/{username}/{lozinka}", method=RequestMethod.GET)
	@ResponseBody
	public Korisnik login(@PathVariable String username, @PathVariable String lozinka) throws NullPointerException {
		Korisnik user=rep.findByUsername(username);
		
		try {
			if(user.getUsername().equals(username) && user.getPassword().equals(lozinka)){
				return user;
			}
		} catch (NullPointerException npe) {
		    // It's fine if findUser throws a NPE
		}
		return null;
		
	}
	
	@RequestMapping(value="/getHistory/{username}", method=RequestMethod.GET)
	public List<Racun> getShoppingHistory(@PathVariable  String username) throws JsonParseException, JsonMappingException, IOException, JsonMappingException, IOException{
		List<Racun> history=new ArrayList<Racun>();
		Korisnik k=rep.findByUsername(username);
		for(String sifra:k.getProfil_kupca().getIstorija_kupovina()){
			history.add(racunrep.findBySifra(sifra));
		}
		
		return history;
	
	}
	
	
	@RequestMapping(value="/racun/create", method=RequestMethod.POST)
	public Racun kreirajRacun(@RequestBody final String userString) throws JsonParseException, JsonMappingException, IOException, JsonMappingException, IOException{
			
			ObjectMapper mapper = new ObjectMapper();
			System.out.println(userString);
			Racun racun = mapper.readValue(userString, Racun.class);
			racun.podesiRacun(racun);
			
		/*	for(StavkaRacuna sr: racun.getListaStavki()){
				stavkaracrep.save(sr);
				
			}
			racunrep.save(racun); 
			
			*/
			//Prolazi korz pravila
			
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("stavka-rules");
			kSession.insert(racun);
			for(int i=0; i<racun.getListaStavki().size(); i++) {
				
				kSession.insert(racun.getListaStavki().get(i));
			}
			
			List<AkcijskiDogadjaj> dogadjaji = adrep.findAll();
			
			for(int i=0; i<dogadjaji.size(); i++){
				kSession.insert(dogadjaji.get(i));
			}

			
			
			System.out.println("Sistem za popuste je poceo obradu!");
			System.out.println(kSession.fireAllRules());
			System.out.println("Sistem je zavrsio obradu! ");
			Korisnik k=racun.getKupac();
			
			racun.getKupac().getProfil_kupca().getIstorija_kupovina().add(racun.getSifra());
			
			for(int i=0; i<racun.getListaStavki().size(); i++) {
				
				System.out.println(racun.getListaStavki().get(i).getArtikal().getNaziv()+" :"+racun.getListaStavki().get(i).getListaPopusta());
				for(PopustNaStavku ps:racun.getListaStavki().get(i).getListaPopusta()){
					popustnastavkurep.save(ps);
				}
				stavkaracrep.save(racun.getListaStavki().get(i));
			
			}
			
			for(PopustNaRacun pr:racun.getListaPopusta()){
				popustnaracunrep.save(pr);
			}
			racun.getKupac().getProfil_kupca().setNagradni_bodovi(racun.getKupac().getProfil_kupca().getNagradni_bodovi()-racun.getBrojPotrosenihBodova());
			profilrep.save(racun.getKupac().getProfil_kupca());
			rep.save(racun.getKupac());
			
			racunrep.save(racun);
			return racun;
	}
	
	
	
	
}
