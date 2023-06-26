package controly.backend.utils;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtil {

    public static Blob compressImage(byte[] data) throws SQLException {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return new SerialBlob(outputStream.toByteArray());
    }

    public static Blob decompressImage(Blob data) throws SQLException {
        Inflater inflater = new Inflater();
        int blobLength = (int) data.length();
        inflater.setInput(data.getBytes(1, blobLength));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream((int) data.length());
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return new SerialBlob(outputStream.toByteArray());
    }
}
