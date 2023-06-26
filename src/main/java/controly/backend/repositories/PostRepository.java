package controly.backend.repositories;

import controly.backend.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
//    List<PostEntity> findByOwnerIdUser(Long idUser);
//
//    List<PostEntity> findByTopicIdTopic(Long idTopic);
}
