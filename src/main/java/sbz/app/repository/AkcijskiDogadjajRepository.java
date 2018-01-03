package sbz.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import sbz.app.model.AkcijskiDogadjaj;
import sbz.app.model.Artikal;


public interface AkcijskiDogadjajRepository extends JpaRepository<AkcijskiDogadjaj, Integer> {
	public AkcijskiDogadjaj findBySifra(String sifra);
}