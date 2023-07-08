package controly.backend.repositories;

import controly.backend.entities.PasswordRecoveryEntity;
import controly.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);

  Optional<UserEntity> findByIdGithub(Long id);

  Optional<UserEntity> findByNickname(String nickname);

  @Query("SELECT u.passwordRecoveryEntity FROM UserEntity u WHERE u.passwordRecoveryEntity.token = :token")
  Optional<PasswordRecoveryEntity> findPasswordRecoveryByPasswordRecoveryEntityToken(@Param("token") String token);
  @Query("SELECT u FROM UserEntity u WHERE u.passwordRecoveryEntity.token = :token")
  Optional<UserEntity> findByPasswordRecoveryEntityToken(@Param("token") String token);
}
