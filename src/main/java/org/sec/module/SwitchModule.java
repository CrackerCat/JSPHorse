package org.sec.module;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.SwitchEntry;

import java.util.List;
import java.util.Random;

public class SwitchModule {
    public static void changeSwitch(MethodDeclaration method,String target) {
        String[] a = target.split("\\|");
        List<SwitchEntry> entryList = method.findAll(SwitchEntry.class);
        for (int i=0;i<entryList.size();i++) {
            if(entryList.get(i).getLabels().get(0) instanceof IntegerLiteralExpr){
                IntegerLiteralExpr expr = (IntegerLiteralExpr) entryList.get(i).getLabels().get(0);
                expr.setValue(a[i]);
            }
        }
    }

    public static String shuffle(MethodDeclaration method) {
        Random rand = new Random();
        String result = null;
        rand.setSeed(System.currentTimeMillis());
        List<ArrayInitializerExpr> arrayExpr = method.findAll(ArrayInitializerExpr.class);
        for (ArrayInitializerExpr expr : arrayExpr) {
            Node target = expr.getChildNodes().get(0);
            if (target instanceof StringLiteralExpr) {
                String value = ((StringLiteralExpr) target).getValue();
                if (value.contains("|")) {
                    String[] a = value.split("\\|");
                    int length = a.length;
                    for (int i = length; i > 0; i--) {
                        int randInd = rand.nextInt(i);
                        String temp = a[randInd];
                        a[randInd] = a[i - 1];
                        a[i - 1] = temp;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (String s : a) {
                        sb.append(s).append("|");
                    }
                    String finalStr = sb.toString();
                    finalStr = finalStr.substring(0, finalStr.length() - 1);
                    ((StringLiteralExpr) target).setValue(finalStr);
                    result = finalStr;
                }
            }
        }
        return result;
    }
}
