package org.sec;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.sec.module.SwitchModule;
import org.sec.module.XORModule;
import org.sec.util.FileUtil;
import org.sec.util.PrintUtil;

import java.io.IOException;
import java.io.InputStream;


public class Main {
    public static void main(String[] args) throws IOException {
        String password = null;
        if (args.length == 1 && !args[0].equals("")) {
            password = args[0];
        } else {
            System.out.println("error input");
            return;
        }

        InputStream in = Main.class.getClassLoader().getResourceAsStream("base.java");
        String code = FileUtil.readFile(in);
        code = code.replace("4ra1n", password);
        CompilationUnit unit = StaticJavaParser.parse(code);
        MethodDeclaration method = unit.findFirst(MethodDeclaration.class).isPresent() ?
                unit.findFirst(MethodDeclaration.class).get() : null;
        if (method == null) {
            return;
        }

        String newValue = SwitchModule.shuffle(method);
        SwitchModule.changeSwitch(method,newValue);
        XORModule.doXOR(method);
        System.out.println(method.getBody().isPresent() ? method.getBody().get().toString() : "");
        PrintUtil.print(method);
    }
}

