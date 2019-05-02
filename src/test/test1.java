package test;


import database.LinkDatabase;
import enumeration.DataBaseEnum;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test1 {

    public static void main(String [] args) throws IOException {
        File file = new File("/Users/mac/Desktop/Context.xml");
        File url = new File("/Users/mac/Desktop/server.xml");
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        int i =0;
        byte [] bytes = new byte[1024];
        try {
            in = new BufferedInputStream(new FileInputStream(url));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            out  = new BufferedOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while((i = in.read(bytes,0,1024))!=-1) {
            out.write(bytes,0,1024);
            out.flush();
        }
        out.flush();

        in.close();
        out.close();
    }
}
