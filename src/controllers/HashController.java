package controllers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public abstract class HashController {

    static public String encode(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(text.getBytes(StandardCharsets.UTF_8));

            String hashedPass = toHex(new String(encodedhash));
            return hashedPass;
        } catch (Exception e) {
            return null;
        }
    }

    static public boolean verify(String hash, String text) {
        return encode(text).equals(hash);
    }

    static public String toHex(String text) {
        StringBuilder hexString = new StringBuilder();
        for (char c : text.toCharArray()) {
            hexString.append(Integer.toHexString((int) c));
        }

        return hexString.toString();

    }

}
