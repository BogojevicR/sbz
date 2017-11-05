package sbz.app.controller;
import sbz.app.model.Korisnik;
import sbz.app.repository.KorisnikRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class KorisnikController {
	@Autowired
	KorisnikRepository rep;
	
	@RequestMapping("/all")
	@ResponseBody
	public List<Korisnik> getAll(){
		
		return rep.findAll();
		
	}
	
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public boolean create(@RequestBody final Korisnik user){
		rep.save(user);
		return true;
		
	}
	

}
