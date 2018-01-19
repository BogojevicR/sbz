package sbz.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import sbz.app.model.PragPotrosnje;
import sbz.app.model.Racun;


public interface RacunRepository extends JpaRepository<Racun, Integer> {
	public Racun findBySifra(String sifra);
	
}