package org.sec.util;


import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.ExecutionException;

public class EncodeUtil {
    public static void main(String[] args) {
        String a = encryption("amF2YS5sYW5nLlJ1bnRpbWU=",5);
        String b = dec("Nnw4fDl8NXwzfDB8Mnw3fDExfDEwfDR8MQ==",(1309614 ^ 1309613));
        System.out.println(a);
        System.out.println(b);
    }
    public static String encryption(String str,int offset) {
        char c ;
        StringBuilder str1 = new StringBuilder();
        for(int i = 0;i<str.length();i++) {
            c = str.charAt(i);
            if(c >= 'a'&& c<='z') {
                c = (char)(((c-'a')+offset)%26+'a');
            }else if(c >= 'A'&& c<='Z') {
                c = (char)(((c-'A')+offset)%26+'A');
            }else if(c >= '0'&& c<='9') {
                c = (char)(((c-'0')+offset)%10+'0');
            }else {
                str1 = new StringBuilder(str);
                break;
            }
            str1.append(c);
        }
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(str1.toString().getBytes(StandardCharsets.UTF_8));
    }
    public static String dec(String str,int offset) {
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
        }catch (Exception ignored){
            return "";
        }
    }
}
