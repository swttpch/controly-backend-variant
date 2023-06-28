package controly.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.Type;

import java.sql.Blob;

@Entity
@Table(name = "tbMediaData")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idMedia", updatable = false, unique = true, nullable = false)
    private Long idMedia;

    private String name;

    private String type;

    private String url;

    @Lob
    @Column(name = "mediadata", length = 1000)
    private byte[] mediaData;
}
