package com.example.util;

import java.util.UUID;


public class FileNameUtil {

    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static String getFileName(String fileOriginName){
        return UUID.randomUUID().toString().replace("-", "") + getSuffix(fileOriginName);
    }
}
