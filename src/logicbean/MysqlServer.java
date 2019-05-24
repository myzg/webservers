package logicbean;

import serverinterface.DataBaseServer;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;

public abstract class MysqlServer implements DataBaseServer {
    protected Connection connection;
    protected Statement statement;
    private ServletConfig config;
    protected HttpServletRequest request;

    protected MysqlServer(HttpServletRequest request, ServletConfig config) {
        this.config = config;
        this.request = request;
    }

    /**
     * 功能： 启动连接。
     * 返回： 一个connection对象 , 或者null。
     * 异常： 1.没有找到驱动类 2.数据库访问错误
     * */
    @Override
    public Connection getLink() {
       if(config != null) {
           try {
               Class.forName(config.getInitParameter("mysql_driver"));
               Properties props = new Properties();
               Enumeration<String> parameternames= config.getInitParameterNames();
               while (parameternames.hasMoreElements()) {
                   String oneparameter = null;
                   oneparameter = parameternames.nextElement();
                   if(oneparameter != null && !oneparameter.equals("mysql_driver") && !oneparameter.equals("url")) {
                       props.setProperty(oneparameter,config.getInitParameter(oneparameter));
                   }
               }
               connection = DriverManager.getConnection(config.getInitParameter("url"),props);
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           } catch (SQLException e) {
               e.printStackTrace();
           }
           if(connection != null) {
               return connection;
           }else {
               return null;
           }
       }else {
           return null;
       }
    }


    @Override
    public abstract Statement getStatement();

    @Override
    public abstract Object execute();

    /**
     * 功能： 关闭所有的资源
     * 返回： 没有返回值
     * 异常： 1.如果发生数据库访问错误
     * */
    @Override
    public void close() {
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sequence() {
        getLink();
        getStatement();
        execute();
        close();
    }

    @Override
    public void getServer(HttpServletRequest request, ServletConfig config) {
        Object result = null;
        getLink();
        getStatement();
        result = execute();
        request.setAttribute("result",result);
        close();
    }

}
