package sbz.app.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sbz.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	//List<User> findByLastName(String last_name);
}
