package org.example.readFiles;

import java.io.*;

public class SqlFileReader {
    public static String sqlReaderFile(String sqlQuery){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(sqlQuery))) {
            if(bufferedReader == null){
                System.out.println("Not found SQLfileQuery");
                return null;
            }
            StringBuffer sb = new StringBuffer();
            bufferedReader.lines().forEach(sb::append);
            return  sb.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
