package com.aojiaoo.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtils extends org.apache.commons.io.FileUtils {
    private FileUtils() {
    }

    /**
     * 拼接路径
     *
     * @param path1 路径1
     * @param path2 路径2
     * @return 拼接后的路径
     */
    public static String spliceFilePath(String path1, String path2) {
        path1 = StringUtils.trimToEmpty(path1);
        path2 = StringUtils.trimToEmpty(path2);

        path1 = path1.replaceAll("\\\\", "/");
        path2 = path2.replaceAll("\\\\", "/");

        if (!path1.endsWith("/")) {
            path1 = path1 + "/";
        }

        if (path2.startsWith("/")) {
            path2 = path2.substring(1);
        }
        return path1 + path2;
    }


    public static boolean createPath(String filePath, boolean isFolder) {

        if (StringUtils.isBlank(filePath)) {
            return false;
        }
        filePath = StringUtils.trimToEmpty(filePath);
        File file = new File(filePath);
        if (file.exists()) {
            return true;//文件存在 不需要创建
        }

        if (isFolder) {
            //文件夹 直接创建
            return file.mkdirs();
        }

        if (!file.getParentFile().exists()) {
            //所在文件夹不存在创建文件夹
            file.getParentFile().mkdirs();
        }

        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static String spliceFilePath(String... pathArr) {
        if (pathArr.length < 2) {
            if (pathArr.length == 1) {
                return pathArr[0];
            } else {
                return "";
            }
        }

        String splicedFilePath = pathArr[0];

        for (int i = 1; i < pathArr.length; i++) {
            splicedFilePath = spliceFilePath(splicedFilePath, pathArr[i]);
        }
        return splicedFilePath;
    }


    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }


    /**
     * 得到该Resource的绝对路径  如果不符合要求(startsWith("classpath:")) 则原样返回
     *
     * @param classpathResource classpath 写法的路径 如 classpath:xxx.xml
     * @return 该Resource的绝对路径 如果不符合要求(startsWith("classpath:")) 则原样返回
     */
    public static String getFilePathByClasspathOrSelf(String classpathResource) {
        if (StringUtils.startsWith(classpathResource, ResourceUtil.CLASSPATH_URL_PREFIX)) {
            String filePath = classpathResource;
            try {
                filePath = ResourceUtil.getURL(classpathResource).getPath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return filePath;
        } else {
            return classpathResource;
        }
    }

    public static void main(String[] args) {

        System.out.println(createPath("C:\\project\\kissPlan\\admin\\src\\main\\java\\com\\aojiaoo\\modules\\kissPlan\\entity\\Article.java", false));
    }
}
