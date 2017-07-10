/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.controller;

import java.security.MessageDigest;
import java.text.Normalizer;
import java.util.regex.Pattern;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ray Sparrow
 */
public class UserController {

    public String encryption(String str) {
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

    public Cookie getCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookie = request.getCookies();
        Cookie c = null;
        if (cookie != null) {
            for (Cookie cc : cookie) {
                if (cc.getName().equals("username")) {
                    c = cc;
                }
            }
        }
        return c;
    }

    public void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookie = request.getCookies();
        Cookie c = null;
        if (cookie != null) {
            for (Cookie cc : cookie) {
                if (cc.getName().equals("username")) {
                    c = cc;
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
    }

    public String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
