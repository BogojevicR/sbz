package sbz.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sbz.app.model.KategorijaKupca;
import sbz.app.model.Korisnik;


public interface KategorijaKupcaRepository extends JpaRepository<KategorijaKupca, Integer> {
	
	public KategorijaKupca findBySifra(String sifra);
	
}
