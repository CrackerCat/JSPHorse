package org.sec.util;


import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    public static String readFile(String filename) throws IOException {
        InputStream r = new FileInputStream(filename);
        return doReadFile(r);
    }

    public static String readFile(InputStream is) throws IOException {
        return doReadFile(is);
    }

    private static String doReadFile(InputStream is) throws IOException {
        ByteArrayOutputStream byteData = new ByteArrayOutputStream();
        byte[] temp = new byte[1024];
        byte[] context;
        int i;
        while ((i = is.read(temp)) > 0) {
            byteData.write(temp, 0, i);
        }
        context = byteData.toByteArray();
        String str = new String(context, StandardCharsets.UTF_8);
        is.close();
        byteData.close();
        return str;
    }

    public static void writeFile(String filename, String output) throws IOException {
        File file = new File(filename);
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file),
                        StandardCharsets.UTF_8));
        bw.write(output);
        bw.close();
    }

    public static String getFileExt(String filename) {
        String[] splits = filename.split("\\.");
        return splits[splits.length - 1];
    }
}
