package org.sec.util;

import com.github.javaparser.ast.body.MethodDeclaration;

public class PrintUtil {
    public static void print(MethodDeclaration method) {
        String code = method.getBody().isPresent() ? method.getBody().get().toString() : null;
        if (code != null) {
            String newCode = code.substring(1, code.length() - 2);
            newCode = compactCode(newCode);
            newCode = UnicodeUtil.encodeString(newCode);
            System.out.println("Code:");
            System.out.println(newCode);
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
