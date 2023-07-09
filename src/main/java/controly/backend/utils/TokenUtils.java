package controly.backend.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenUtils {
  final private static SecureRandom secureRandom = new SecureRandom();
  final private static Base64.Encoder base64Encoder = Base64.getUrlEncoder();
  public static String generateNewToken() {
    byte[] randomBytes = new byte[24];
    secureRandom.nextBytes(randomBytes);
    return base64Encoder.encodeToString(randomBytes);
  }
}
