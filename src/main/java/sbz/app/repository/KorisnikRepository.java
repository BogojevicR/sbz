package sbz.app.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sbz.app.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
	//List<User> findByLastName(String last_name);
}
