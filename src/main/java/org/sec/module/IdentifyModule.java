package org.sec.module;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.NameExpr;
import org.sec.util.RandomUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdentifyModule {
    public static void doIdentify(MethodDeclaration method){
        Map<String,String> vas = new HashMap<>();
        List<VariableDeclarator> vaList = method.findAll(VariableDeclarator.class);
        for(VariableDeclarator va:vaList){
            String newName = RandomUtil.getRandomString(20);
            vas.put(va.getNameAsString(), newName);
            va.setName(newName);
        }
        method.findAll(NameExpr.class).forEach(n->{
            if(vas.containsKey(n.getNameAsString())){
                n.setName(vas.get(n.getNameAsString()));
            }
        });
    }
}
