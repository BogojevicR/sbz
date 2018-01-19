package sbz.app.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sbz.app.model.AkcijskiDogadjaj;
import sbz.app.model.Artikal;
import sbz.app.model.KategorijaArtikla;
import sbz.app.model.KategorijaKupca;
import sbz.app.model.Korisnik;
import sbz.app.model.PragPotrosnje;
import sbz.app.model.Racun;
import sbz.app.model.StavkaRacuna;
import sbz.app.repository.AkcijskiDogadjajRepository;
import sbz.app.repository.ArtikalRepository;
import sbz.app.repository.KategorijaArtiklaRepository;
import sbz.app.repository.KategorijaKupcaRepository;
import sbz.app.repository.KorisnikRepository;
import sbz.app.repository.PragPotrosnjeRepository;
import sbz.app.repository.RacunRepository;

@RestController
@RequestMapping("/menadzer")
public class MenadzerController {
	@Autowired
	KorisnikRepository rep;
	@Autowired
	KategorijaKupcaRepository katkrep;
	
	@Autowired
	PragPotrosnjeRepository pragrep;
	
	@Autowired
	KategorijaArtiklaRepository katarep;
	
	@Autowired
	ArtikalRepository artrep;
	
	@Autowired
	AkcijskiDogadjajRepository dogrep;
	
	@Autowired
	RacunRepository racunrep;
	
	@RequestMapping("/all/katKupca")
	@ResponseBody
	public List<KategorijaKupca> getAllKatKupca(){
		
		return katkrep.findAll();
		
	}
	
	@RequestMapping("/")
	@ResponseBody
	public List<KategorijaKupca> getKatKupca(){
		
		return katkrep.findAll();
		
	}
	
	@RequestMapping(value="/katKupca/{sifra}", method=RequestMethod.GET)
	@ResponseBody
	public KategorijaKupca getKatKupcaBySifra(@PathVariable String sifra) throws NullPointerException {
		KategorijaKupca kategorija=katkrep.findBySifra(sifra);
		
		return kategorija;
		
	}
	
	@RequestMapping(value="/prag/edit/{sifra}/{donja}/{gornja}/{procenat}", method=RequestMethod.GET)
	@ResponseBody
	public PragPotrosnje editPragBySifra(@PathVariable String sifra,@PathVariable int donja,@PathVariable int gornja,@PathVariable double procenat  ) throws NullPointerException {
		PragPotrosnje prag=pragrep.findBySifra(sifra);
		prag.setGornja_granica(gornja);
		prag.setDonja_granica(donja);
		prag.setProcenat(procenat);
		pragrep.save(prag);
		
		return prag;
		
	}
	
	@RequestMapping(value="/prag/create/{sifra}/{donja}/{gornja}/{procenat}", method=RequestMethod.POST)
	public boolean createPrag(@PathVariable String sifra,@PathVariable int donja,@PathVariable int gornja,@PathVariable String procenat) throws JsonParseException, JsonMappingException, IOException, JsonMappingException, IOException{
		KategorijaKupca kategorija=katkrep.findBySifra(sifra);
		if(kategorija==null){
			return false;
		}
			
		double procenat2=Double.parseDouble(procenat);
		PragPotrosnje prag=new PragPotrosnje(UUID.randomUUID().toString().replaceAll("-", ""),donja,gornja,procenat2);
		kategorija.addPragPotrosnje(prag);
		pragrep.save(prag);
		katkrep.save(kategorija);
		return true;
		
		
	}
	
	@RequestMapping(value="/prag/delete/{sifraKat}/{sifra}", method=RequestMethod.GET)
	@ResponseBody
	public boolean deletePragBySifra(@PathVariable String sifraKat,@PathVariable String sifra  ) throws NullPointerException {
		KategorijaKupca kategorija=katkrep.findBySifra(sifraKat);
		if(kategorija==null){
			return false;
		}
		PragPotrosnje pp=pragrep.findBySifra(sifra);
		if(pp==null){
			return false;
		}
		kategorija.deletePragPotrosnje(pp);
		katkrep.save(kategorija);
		pragrep.delete(pp);
		return true;
	}
	
	@RequestMapping("/all/katArt")
	@ResponseBody
	public List<KategorijaArtikla> getAllKatArtikla(){
		
		return katarep.findAll();
		
	}
	
	@RequestMapping(value="/katArt/{sifra}", method=RequestMethod.GET)
	@ResponseBody
	public KategorijaArtikla getkatArtBySifra(@PathVariable String sifra) throws NullPointerException {
		KategorijaArtikla kategorija=katarep.findBySifra(sifra);
		return kategorija;
		
	}
	
	
	@RequestMapping(value="/katArt/create", method=RequestMethod.POST)
	public boolean create(@RequestBody final String userString) throws JsonParseException, JsonMappingException, IOException, JsonMappingException, IOException{
			ObjectMapper mapper = new ObjectMapper();
			KategorijaArtikla ka = mapper.readValue(userString, KategorijaArtikla.class);
			KategorijaArtikla nk= katarep.findBySifra(ka.getSifra());
			ka.setNadkategorija(nk);
			ka.setSifra(UUID.randomUUID().toString().replaceAll("-", ""));
			if(katarep.findBySifra(ka.getSifra())!=null){
				return false;
			}else{
				katarep.save(ka);
				return true;
			}
	}
	
	
	@RequestMapping(value="/katArt/edit/{sifra}/{naziv}/{procenat}/{nadkategorija}", method=RequestMethod.GET)
	@ResponseBody
	public boolean editKategorijaArtikla(@PathVariable String sifra,@PathVariable String naziv,@PathVariable float procenat,@PathVariable String nadkategorija  ) throws NullPointerException {
		
		KategorijaArtikla kat=katarep.findBySifra(sifra);
		KategorijaArtikla nadk=katarep.findBySifra(nadkategorija);
		kat.setNaziv(naziv);
		kat.setMaksimalni_popust(procenat);
		kat.setNadkategorija(nadk);
		
		katarep.save(kat);
		return false;
	
		
	}
	
	@RequestMapping("/all/artikal")
	@ResponseBody
	public List<Artikal> getAllArtikal(){
		
		return artrep.findAll();
		
	}
	
	@RequestMapping(value="/artikal/{sifra}", method=RequestMethod.GET)
	@ResponseBody
	public Artikal getArtikalBySifra(@PathVariable String sifra) throws NullPointerException {
		Artikal artikal=artrep.findBySifra(sifra);
		return artikal;
		
	}
	
	
	
	
	@RequestMapping(value="/artikal/create", method=RequestMethod.POST)
	public boolean createArtiakl(@RequestBody final String userString) throws JsonParseException, JsonMappingException, IOException, JsonMappingException, IOException{
			ObjectMapper mapper = new ObjectMapper();
			Artikal art = mapper.readValue(userString, Artikal.class);
			KategorijaArtikla nk= katarep.findBySifra(art.getSifra());
			art.setKategorija(nk);
			art.setSifra(UUID.randomUUID().toString().replaceAll("-", ""));
			art.podesi(art);
			if(artrep.findBySifra(art.getSifra())!=null){
				return false;
			}else{
				artrep.save(art);
				return true;
			}
	}
	
	
	@RequestMapping(value="/dogadjaj/create", method=RequestMethod.POST)
	public boolean createDogadjaj(@RequestBody final String userString) throws JsonParseException, JsonMappingException, IOException, JsonMappingException, IOException{
			ObjectMapper mapper = new ObjectMapper();
			AkcijskiDogadjaj dog = mapper.readValue(userString, AkcijskiDogadjaj.class);
			
			System.out.println(dog.toString());
			
			dog.setSifra(UUID.randomUUID().toString().replaceAll("-", ""));
			
			if(dogrep.findBySifra(dog.getSifra())!=null){
				return false;
			}else{
				dogrep.save(dog);
				return true;
			}
	}
	
	@RequestMapping("/all/dogadjaj")
	@ResponseBody
	public  List<AkcijskiDogadjaj> getAllDogadjaj(){
		
		return dogrep.findAll();
		
	}
	
	@RequestMapping(value="/dogadjaj/{sifra}", method=RequestMethod.GET)
	@ResponseBody
	public AkcijskiDogadjaj getDogadjajBySifra(@PathVariable String sifra) throws NullPointerException {
		AkcijskiDogadjaj dogadjaj=dogrep.findBySifra(sifra);
		return dogadjaj;
		
	}
	
	
	@RequestMapping(value="/dogadjaj/izmeni", method=RequestMethod.POST)
	public boolean izmeniDogadjaj(@RequestBody final String userString) throws JsonParseException, JsonMappingException, IOException, JsonMappingException, IOException{
			ObjectMapper mapper = new ObjectMapper();
			AkcijskiDogadjaj dog = mapper.readValue(userString, AkcijskiDogadjaj.class);
			
			System.out.println(dog.toString());

			if(dogrep.findBySifra(dog.getSifra())==null){
				return false;
			}else{
				dogrep.save(dog);
				return true;
			}
	}
	
	@RequestMapping(value="/racun/{sifra}", method=RequestMethod.GET)
	@ResponseBody
	public Racun getRacunBySifra(@PathVariable String sifra) throws NullPointerException {
		Racun r=racunrep.findBySifra(sifra);
		return r;
		
	}
	
	@RequestMapping("/all/racun")
	@ResponseBody
	public  List<Racun> getRacune(){
		
		return racunrep.findAll();
		
	}
	
	@RequestMapping(value="/racun/otkazi/{sifra}", method=RequestMethod.GET)
	@ResponseBody
	public Racun otkaziRacun(@PathVariable String sifra) throws NullPointerException {
		Racun r=racunrep.findBySifra(sifra);
		r.setStanje(Racun.StanjeRacuna.OTKAZANO);
		racunrep.save(r);
		return r;
		
	}
	
	@RequestMapping(value="/racun/obradi/{sifra}", method=RequestMethod.GET)
	@ResponseBody
	public boolean obradiRacun(@PathVariable String sifra) throws NullPointerException {
		Racun racun=racunrep.findBySifra(sifra);
		boolean realizacija=true;
		for(StavkaRacuna s:racun.getListaStavki()){
			if(s.getKolicina()>s.getArtikal().getBrojnoStanje()){
				System.out.print("Usao u realizaciju");
				Artikal a=artrep.findBySifra(s.getArtikal().getSifra());
				a.setTreba_zaliha(true);
				realizacija=false;
				artrep.save(a);
			}
		}	
		if(realizacija==true){
			for(StavkaRacuna s:racun.getListaStavki()){
				Artikal a=artrep.findBySifra(s.getArtikal().getSifra());
				a.setBrojnoStanje(a.getBrojnoStanje()-s.getKolicina());
				if(a.getBrojnoStanje()<a.getMinimalno_stanje()){
					a.setTreba_zaliha(true);
				}
				artrep.save(a);				
			}
		}else{
			return false;
		}
		System.out.print("NIJE DOSAO OVDE");
		racun.setStanje(Racun.StanjeRacuna.REALIZOVANO);
		Korisnik k=rep.findByUsername(racun.getKupac().getUsername());
		k.getProfil_kupca().setNagradni_bodovi(k.getProfil_kupca().getNagradni_bodovi()-racun.getBrojPotrosenihBodova()+racun.getBrojOstvarenihBodova());
		rep.save(k);
		
		
		racunrep.save(racun);
		return true;
		
	}
	
	
	
}
