package database;

import enumeration.DataBaseEnum;
import enumeration.ExceptionEnum;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public class LinkDatabase {
    private Connection connection;

    public LinkDatabase() {
        init();
    }

    private void init() {
        try {
            Class.forName(DataBaseEnum.DRIVER.getValues());
            Properties props = new Properties();
            for(DataBaseEnum temp : DataBaseEnum.values()) {
                if(!temp.equals(DataBaseEnum.DRIVER) && !temp.equals(DataBaseEnum.URl)) {
                    props.setProperty(temp.getName(),temp.getValues());
                }
            }
            connection = DriverManager.getConnection(DataBaseEnum.URl.getValues(),props);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPreparedStatement(String generalsqlstatement) throws Exception {
        PreparedStatement preparedStatement = null;
        if(generalsqlstatement != null) {
            try {
                if(connection.isValid(3000)) {
                    preparedStatement = connection.prepareStatement(generalsqlstatement);
                }else {
                    throw new SQLException(ExceptionEnum.DATABASE_UNLINK.getException_message());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            throw new Exception("");
        }
        return preparedStatement;
    }
}
