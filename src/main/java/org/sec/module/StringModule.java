package org.sec.module;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.*;
import org.sec.util.EncodeUtil;

import java.util.List;
import java.util.Random;

public class StringModule {
    public static int encodeString(MethodDeclaration method) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int offset = random.nextInt(10);
        List<StringLiteralExpr> stringList = method.findAll(StringLiteralExpr.class);
        for (StringLiteralExpr s : stringList) {
            if (s.getParentNode().isPresent()) {
                if (s.getParentNode().get() instanceof ArrayInitializerExpr) {
                    s.setValue(EncodeUtil.encryption(s.getValue(), offset));
                }
            }
        }
        return offset;
    }

    public static void changeRef(MethodDeclaration method, int offset) {
        List<ArrayAccessExpr> arrayExpr = method.findAll(ArrayAccessExpr.class);
        for (ArrayAccessExpr expr : arrayExpr) {
            if (expr.getName().asNameExpr().getNameAsString().equals("globalArr")) {
                MethodCallExpr methodCallExpr = new MethodCallExpr();
                methodCallExpr.setName("dec");
                methodCallExpr.setScope(null);
                NodeList<Expression> nodeList = new NodeList<>();
                ArrayAccessExpr a = new ArrayAccessExpr();
                a.setName(expr.getName());
                a.setIndex(expr.getIndex());
                nodeList.add(a);
                IntegerLiteralExpr intValue = new IntegerLiteralExpr();
                intValue.setValue(String.valueOf(offset));
                nodeList.add(intValue);
                methodCallExpr.setArguments(nodeList);
                expr.replace(methodCallExpr);
            }
        }
    }
}
