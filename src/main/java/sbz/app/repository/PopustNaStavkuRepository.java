package sbz.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sbz.app.model.PopustNaStavku;


public interface PopustNaStavkuRepository extends JpaRepository<PopustNaStavku, Integer> {
	
}
