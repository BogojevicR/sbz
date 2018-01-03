package sbz.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sbz.app.model.StavkaRacuna;


public interface StavkaRacunaRepository extends JpaRepository<StavkaRacuna, Integer> {
	
}