package controly.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="tbTopic")
@Data
@Builder
public class TopicEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTopic", updatable = false, unique = true, nullable = false)
    private Long idTopic;
    private String name;
    private String about;
    private String slug;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
    @ManyToMany(mappedBy = "followedTopics")
    private Set<UserEntity> follows;
    @ManyToMany(mappedBy = "topics")
    private Set<PostEntity> posts;
}