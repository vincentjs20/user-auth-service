package flin.financial.demo.repository;

import flin.financial.demo.model.MUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<MUser, UUID> {

    Optional<MUser> findByUsername(String username);

}
