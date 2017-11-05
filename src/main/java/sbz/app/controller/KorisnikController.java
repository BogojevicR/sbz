package sbz.app.controller;
import sbz.app.model.User;
import sbz.app.repository.UserRepository;

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
public class UserController {
	@Autowired
	UserRepository rep;
	
	@RequestMapping("/all")
	@ResponseBody
	public List<User> getAll(){
		
		return rep.findAll();
		
	}
	
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public boolean create(@RequestBody final User user){
		rep.save(user);
		return true;
		
	}
	

}
