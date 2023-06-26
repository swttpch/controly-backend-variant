package controly.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="tbUser")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "idUser")
  private Long idUser;
  private String name;
  @Column(unique = true)
  private String nickname;
  @Column(nullable = false)
  private String password;
  @Column(unique = true, nullable = false)
  private String email;
  private Boolean isActive;
  private Long idGithub;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date disabledIn;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date createdAt;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date updatedAt;
  @OneToOne
  private MediaDataEntity profilePicture;

  @ManyToMany
  @JoinTable(
      name = "tb_post_like",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "post_id"))
  private Set<PostEntity> likedPosts;

  @ManyToMany
  @JoinTable(
      name = "tb_comment_like",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "comment_id"))
  private Set<CommentEntity> likedComments;

  @ManyToMany
  @JoinTable(
      name = "tb_topic_follow",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "topic_id"))
  private Set<TopicEntity> followedTopics;

}