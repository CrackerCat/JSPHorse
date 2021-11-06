package org.sec.util;

/**
 * 需要写入JSP的揭解密方法
 */
public class Dec {
    public static String dec(String str, int offset) {
        try {
            byte[] code = java.util.Base64.getDecoder().decode(str.getBytes("utf-8"));
            str = new String(code);
            char c;
            StringBuilder str1 = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                c = str.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    c = (char) (((c - 'a') - offset + 26) % 26 + 'a');
                } else if (c >= 'A' && c <= 'Z') {
                    c = (char) (((c - 'A') - offset + 26) % 26 + 'A');
                } else if (c >= '0' && c <= '9') {
                    c = (char) (((c - '0') - offset + 10) % 10 + '0');
                } else {
                    str1 = new StringBuilder(str);
                    break;
                }
                str1.append(c);
            }
            return str1.toString();
        } catch (Exception ignored) {
            return "";
        }
    }
}
