package sbz.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import sbz.app.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
	
	public Korisnik findByUsername(String username);
}


