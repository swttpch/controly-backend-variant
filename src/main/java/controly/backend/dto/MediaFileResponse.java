package controly.backend.dto;

import lombok.Data;

@Data
public class MediaFileResponse {
  private Long idMedia;

  private String name;

  private String type;

  private String url;
}
