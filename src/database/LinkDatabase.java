package database;

import enumeration.DataBaseEnum;
import enumeration.ExceptionEnum;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public class LinkDatabase {
    private Connection connection = null;

    /**
     *   功能： 构造函数
     * */

    public LinkDatabase() {
        init();
    }

    /**
     *  功能： 1.初始化数据库连接
     *  异常： 1.没有找到加载器类名。 2.发生数据库访问错误。 3.数据库连接失败，没有成功赋值连接。
     * */

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
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(connection == null) {
                try {
                    throw new Exception(ExceptionEnum.DATABASE_UNLINK.getException_message());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  功能： 1.得到预备处理的 statement  （PreparedStatement）
     *  异常： 1.方法参数异常 2.数据库连接已经断开且不再活跃，此时再次尝试连接产生异常
     *  返回： 1.返回带正确值的PreparedStatement 2.返回空值
     * */

    public PreparedStatement getPreparedStatement(String generalsqlstatement) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                throw new Exception(ExceptionEnum.PARAMETER_ERROR.getException_message());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return preparedStatement;
    }

    /**
     *  功能：  关闭与数据库的连接。
     *  异常：  1.数据库连接早已关闭，重复调用，发生异常。 2.数据库连接关闭异常。
     *  返回：  无返回值。
     * */

    public void close() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                throw new SQLException(ExceptionEnum.DATABASE_UNLINK.getException_message());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
