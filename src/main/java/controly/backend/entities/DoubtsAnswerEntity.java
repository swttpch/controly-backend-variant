package controly.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DoubtsAnswerEntity {

    @OneToOne()
    @JoinColumn(name = "idAnswer", referencedColumnName = "idComment", table = "tbDoubtsAnswer")
    private CommentEntity answer;
    @Column(name = "isSolved",  table = "tbDoubtsAnswer")
    private boolean isSolved;
    @Column(name = "solvedIn", table = "tbDoubtsAnswer")
    private Date solvedIn;
}
