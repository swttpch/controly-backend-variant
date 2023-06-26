package controly.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tbComment") @NoArgsConstructor @AllArgsConstructor @Data @Builder
public class CommentEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idComment", updatable = false, unique = true, nullable = false)
    private Long idComment;
    @Column(name = "content")
    private String content;
    @Column(name = "createdIn")
    private Date createdIn;
    @Column(name = "updatedIn") @Nullable
    private Date updatedIn;
    @ManyToOne
    @JoinColumn(name = "idPost", referencedColumnName = "idPost")
    private PostEntity post;

    @ManyToMany(mappedBy = "likedComments")
    private Set<UserEntity> likes;

    @ManyToOne @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private UserEntity owner;
}
