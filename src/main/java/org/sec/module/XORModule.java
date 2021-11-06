package org.sec.module;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;

import java.util.List;
import java.util.Random;

public class XORModule {
    public static void doXOR(MethodDeclaration method){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        List<IntegerLiteralExpr> integers = method.findAll(IntegerLiteralExpr.class);
        for (IntegerLiteralExpr i : integers) {
            int value = Integer.parseInt(i.getValue());
            int key = random.nextInt(1000000) + 1000000;
            int cipherNum = value ^ key;
            EnclosedExpr enclosedExpr = new EnclosedExpr();
            BinaryExpr binaryExpr = new BinaryExpr();
            binaryExpr.setLeft(new IntegerLiteralExpr(String.valueOf(cipherNum)));
            binaryExpr.setRight(new IntegerLiteralExpr(String.valueOf(key)));
            binaryExpr.setOperator(BinaryExpr.Operator.XOR);
            enclosedExpr.setInner(binaryExpr);
            i.replace(enclosedExpr);
        }
    }
}
