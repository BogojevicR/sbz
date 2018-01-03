package sbz.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import sbz.app.model.Artikal;
import sbz.app.model.Korisnik;


public interface ArtikalRepository extends JpaRepository<Artikal, Integer> {
	public Artikal findBySifra(String sifra);
}