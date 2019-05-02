package test;


import database.LinkDatabase;
import enumeration.DataBaseEnum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test1 {

    public static void main(String [] args) {
        LinkDatabase ba = new LinkDatabase();
        PreparedStatement statement = ba.getPreparedStatement("select * from exam_2");
        try {
            System.out.println(statement.toString());
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                System.out.println(set.getString("stu_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
