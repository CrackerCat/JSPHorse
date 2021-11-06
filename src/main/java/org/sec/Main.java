package org.sec;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.sec.module.IdentifyModule;
import org.sec.module.StringModule;
import org.sec.module.SwitchModule;
import org.sec.module.XORModule;
import org.sec.util.FileUtil;
import org.sec.util.WriteUtil;

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
        CompilationUnit unit = StaticJavaParser.parse(code);
        MethodDeclaration method = unit.findFirst(MethodDeclaration.class).isPresent() ?
                unit.findFirst(MethodDeclaration.class).get() : null;
        if (method == null) {
            return;
        }

        String newValue = SwitchModule.shuffle(method);
        SwitchModule.changeSwitch(method, newValue);
        int offset = StringModule.encodeString(method);
        StringModule.changeRef(method, offset);
        IdentifyModule.doIdentify(method);
        XORModule.doXOR(method);
        WriteUtil.write(method, password);
    }
}

