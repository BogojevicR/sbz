package sbz.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sbz.app.model.KategorijaArtikla;
import sbz.app.model.KategorijaKupca;


public interface KategorijaArtiklaRepository extends JpaRepository<KategorijaArtikla, Integer> {
	public KategorijaArtikla findBySifra(String sifra);
}
