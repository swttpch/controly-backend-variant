package controly.backend.repositories;

import controly.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);

  Optional<UserEntity> findByIdGithub(Long id);

  Optional<UserEntity> findByNickname(String nickname);
}
