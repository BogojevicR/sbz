package sbz.app.service;
import sbz.app.model.Korisnik;
import sbz.app.model.Racun;
import sbz.app.repository.KorisnikRepository;
import sbz.app.repository.ProfilKupcaRepository;

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
	
	
	
	
}
