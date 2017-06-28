/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.function;

import java.security.MessageDigest;

/**
 *
 * @author Ray Sparrow
 */
public class FuncUser {
    public  String encryption(String str) {
        byte[] defaultBytes = str.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            str = hexString + "";
        } catch (Exception e) {
            System.out.println(e);
        }
        return str;
    }
}
