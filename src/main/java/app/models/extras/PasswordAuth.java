package app.models.extras;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordAuth
{
    public static final String ID = "$31$";
    public static final int DEFAULT_COST = 16;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int SIZE = 128;
    private static final Pattern layout = Pattern.compile("\\$31\\$(\\d\\d?)\\$(.+)");
    private static final SecureRandom random = new SecureRandom();

    private static int iterations(int cost)
    {
        if ((cost < 0) || (cost > 30))
            throw new IllegalArgumentException("cost: " + cost);
        return 1 << cost;
    }

    public static String hash(char[] password)
    {
        int iterations = iterations(DEFAULT_COST);
        byte[] salt = new byte[SIZE / 8];
        random.nextBytes(salt);
        byte[] dk = pbkdf2(password, salt, iterations);
        byte[] hash = new byte[salt.length + dk.length];
        System.arraycopy(salt, 0, hash, 0, salt.length);
        System.arraycopy(dk, 0, hash, salt.length, dk.length);
        Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
        return ID + DEFAULT_COST + '$' + enc.encodeToString(hash);
    }

    public static boolean authenticate(char[] password, String token)
    {
        Matcher m = layout.matcher(token);
        if (!m.matches()) {
            System.out.println("Token no coincide con el patrón");
            return false;
        }

        int iterations = iterations(Integer.parseInt(m.group(1)));
        String hashStr = m.group(2);

        try {
            byte[] hash = Base64.getUrlDecoder().decode(hashStr);
            byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8);
            byte[] expectedHash = Arrays.copyOfRange(hash, SIZE / 8, hash.length);
            byte[] actualHash = pbkdf2(password, salt, iterations);

            return Arrays.equals(expectedHash, actualHash);

        } catch (IllegalArgumentException e) {
            System.out.println("Error decoding Base64: " + e.getMessage());
            return false;
        }
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations)
    {
        KeySpec spec = new PBEKeySpec(password, salt, iterations, SIZE);
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
            return f.generateSecret(spec).getEncoded();
        }
        catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Missing algorithm: " + ALGORITHM, ex);
        }
        catch (InvalidKeySpecException ex) {
            throw new IllegalStateException("Invalid SecretKeyFactory", ex);
        }
    }

    // Métodos sobrecargados para String
    public static String hash(String password) {
        return hash(password.toCharArray());
    }

    public static boolean authenticate(String password, String token) {
        return authenticate(password.toCharArray(), token);
    }
}
