package com.example.chatapp.utils;

import lombok.Synchronized;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiFile {

    public static String path="";
    public static AtomicInteger count = new AtomicInteger(0);

    public static boolean dirExist = false;
    public static String fileStore(byte[] data,String filename) throws Exception{//返回路径,filename包括后缀名
        count.incrementAndGet();
        if(!dirExist) {
            synchronized (MultiFile.class) {
                if(!dirExist) {
                    String tmp0 = "../FileStorage";
                    new File(tmp0).mkdir();
                }
            }
        }
        String tmp="../FileStorage/"+String.valueOf(count)+"_"+filename;
        fileStore(tmp,data);
        return new File(tmp).getAbsolutePath();
    }
    public static void fileStore(String path,byte[] data) throws IOException {
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path));
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
    }

    public static boolean fileGet(String path, HttpServletResponse response) {
        try {
            String filename = path.split("/")[path.split("/").length-1];
            response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
            OutputStream output = response.getOutputStream();
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(path));
            byte[] tmpData = new byte[512];
            while(inputStream.read(tmpData) != -1) {
                output.write(tmpData);
        }
            output.flush();
            output.close();
            inputStream.close();
            return  true;
        }
        catch (Exception e){
          e.printStackTrace();
          return false;
        }
    }

}
