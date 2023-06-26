package controly.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="tbTopicSuggestion")
@Data
@Builder
public class TopicSuggestionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "idTopic", updatable = false, unique = true, nullable = false)
  private Long idTopic;
  private String name;
  private String about;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date createdAt;
  @OneToOne
  private MediaDataEntity topicPicture;
  @ManyToOne @JoinColumn(name = "idUser", referencedColumnName = "idUser")
  private UserEntity owner;
}
