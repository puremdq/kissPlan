package com.aojiaoo.utils;

import java.io.File;
import java.nio.charset.Charset;

public class CommandUtil {

    /**
     * @param cmd           要运行的命令
     * @param workspacePath 工作空间路基
     * @return
     */
    public static String exec(String cmd, String workspacePath, Charset charset) {
        if (cmd == null || cmd.length() < 1) {
            return "fail! command can not be empty";
        }
        if (charset == null) {
            return "fail! the charset can not be null";
        }
        if (workspacePath != null && workspacePath.length() > 0 && !(new File(workspacePath)).exists()) {
            return "fail! you point out a workspacePath [" + workspacePath + "]. but it's does't exists";
        }

        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        try {
            Process p;
            if (workspacePath == null || workspacePath.length() < 1) {
                p = Runtime.getRuntime().exec(cmd, null);//调用控制台执行shell
            } else {
                p = Runtime.getRuntime().exec(cmd, null, new File(workspacePath));//调用控制台执行shell
            }

            for (int n; (n = p.getInputStream().read(b)) != -1; ) {
                out.append(new String(b, 0, n, charset));
            }
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "fail! command exec fail because [" + e.getMessage() + "]";
        }

    }


    public static String exec(String cmd, String workspacePath) {
        return exec(cmd, workspacePath, getOsDefaultCharset());
    }

    public static String exec(String cmd) {
        return exec(cmd, null, getOsDefaultCharset());
    }

    public static Charset getOsDefaultCharset() {
        if (System.getProperties().getProperty("os.name").toLowerCase().contains("windows")) {
            return Charset.forName("GBK");
        } else {
            return Charset.forName("UTF-8");
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(exec("ipconfig"));
    }

}
