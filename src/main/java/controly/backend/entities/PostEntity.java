package controly.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "tbPost")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbPost")
@SecondaryTable(name= "tbDoubtsAnswer", pkJoinColumns = @PrimaryKeyJoinColumn(name = "idPost"))
@Data
@Builder
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPost", updatable = false, unique = true, nullable = false)
    private Long idPost;
    private String content;
    @ManyToOne @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private UserEntity owner;
    @ManyToMany(mappedBy = "likedPosts")
    private Set<UserEntity> likes;
    private String slug;
    private String title;
    @ManyToMany
    @JoinTable(
        name = "tb_post_topics",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<TopicEntity> topics;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPost")
    private List<CommentEntity> comments;

    @Embedded
    private DoubtsAnswerEntity doubtsAnswerEntity;

    @Column(name = "createdIn")
    private Date createdIn;
    @Column(name = "updatedIn")
    private Date updatedIn;
}
