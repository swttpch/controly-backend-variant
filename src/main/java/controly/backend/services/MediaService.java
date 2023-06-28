package controly.backend.services;


import controly.backend.dto.MediaFileResponse;
import controly.backend.entities.MediaDataEntity;
import controly.backend.exceptions.MediaNotFould;
import controly.backend.mappers.MediaMapper;
import controly.backend.repositories.ImageDataRepository;
import controly.backend.utils.ImageUtil;
import jakarta.transaction.Transactional;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class MediaService {
    @Autowired
    private ImageDataRepository imageDataRepository;
    @Autowired
    private MediaMapper mediaMapper;

    public byte[] getImage(String name) throws SQLException {
        MediaDataEntity dbImage = imageDataRepository.findByName(name).orElseThrow(MediaNotFould::new);
        byte[] image = ImageUtil.decompressImage(dbImage.getMediaData());
        return image;
    }

    @Transactional
    public MediaDataEntity getInfoByImageByName(String name) throws SQLException {
        MediaDataEntity dbImage = imageDataRepository.findByName(name).orElseThrow(MediaNotFould::new);
        return MediaDataEntity.builder()
                .name(dbImage.getName())
                .type(dbImage.getType())
                .mediaData(ImageUtil.decompressImage(dbImage.getMediaData())).build();
    }
    @Transactional
    public MediaDataEntity uploadMedia(String mediaName, MultipartFile file) throws IOException, SQLException {
        MediaDataEntity image = new MediaDataEntity();
        image.setMediaData(ImageUtil.compressImage(file.getBytes()));
        image.setType(file.getContentType());
        image.setName(mediaName+"."+ FilenameUtils.getExtension(file.getOriginalFilename()));
        image.setUrl("/upload/"+image.getName());
        MediaDataEntity img = imageDataRepository.save(image);
        return (img);
    }

    @Transactional
    public MediaDataEntity uploadMedia(MultipartFile file) throws IOException, SQLException {
        MediaDataEntity image = new MediaDataEntity();
        image.setMediaData(ImageUtil.compressImage(file.getBytes()));
        image.setType(file.getContentType());
        image.setName(file.getOriginalFilename());
        image.setUrl("/upload/"+image.getName());
        MediaDataEntity img = imageDataRepository.save(image);
        return (img);
    }

    public MediaFileResponse getMediaFileResponse(MediaDataEntity mediaData){
        MediaFileResponse mediaFileResponse = new MediaFileResponse();
        mediaMapper.getResponseDtoFromMedia(mediaData, mediaFileResponse);
        return mediaFileResponse;
    }
}
