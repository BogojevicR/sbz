package sbz.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import sbz.app.model.KategorijaKupca;
import sbz.app.model.PragPotrosnje;


public interface PragPotrosnjeRepository extends JpaRepository<PragPotrosnje, Integer> {
	public PragPotrosnje findBySifra(String sifra);
}