package sbz.app.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sbz.app.model.Korisnik;
import sbz.app.model.ProfilKupca;

public interface ProfilKupcaRepository extends JpaRepository<ProfilKupca, Integer> {
	
}
