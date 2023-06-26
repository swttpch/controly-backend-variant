package controly.backend.repositories;

import controly.backend.entities.MediaDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<MediaDataEntity, Long> {
    Optional<MediaDataEntity> findByName(String name);
}