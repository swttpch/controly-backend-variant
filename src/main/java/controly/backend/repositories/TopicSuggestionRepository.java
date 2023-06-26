package controly.backend.repositories;

import controly.backend.entities.TopicSuggestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicSuggestionRepository extends JpaRepository<TopicSuggestionEntity,Long> {
}