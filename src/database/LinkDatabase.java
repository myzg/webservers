package database;

import enumeration.DataBaseEnum;
import enumeration.ExceptionEnum;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Properties;

public class LinkDatabase {
    // 数据库连接
    public static Logger log = Logger.getLogger(LinkDatabase.class);

    /**
     *   功能： 构造函数
     * */

    public LinkDatabase() {
    }

    /**
     *  功能： 1.初始化数据库连接
     *  异常： 1.没有找到加载器类名。 2.发生数据库访问错误。 3.数据库连接失败，没有成功赋值连接。
     * */

    public PreparedStatement init(String generalsqlstatement) {
        Connection connection = null;
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
            log.error(ExceptionEnum.PARAMETER_ERROR.getException_message());
            e.printStackTrace();
        } catch (SQLException e) {
            log.error(ExceptionEnum.VISIT_DATABASE_EXCEPTION.getException_message());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(connection == null) {
                try {
                    throw new Exception(ExceptionEnum.DATABASE_UNLINK.getException_message());
                } catch (Exception e) {
                    log.error(ExceptionEnum.DATABASE_UNLINK.getException_message());
                    e.printStackTrace();
                }
            }
        }
        return getPreparedStatement(connection, generalsqlstatement);
    }

    /**
     *  功能： 1.得到预备处理的 statement  （PreparedStatement）
     *  异常： 1.方法参数异常 2.数据库连接已经断开且不再活跃，此时再次尝试连接产生异常 3.如果发生数据库访问错误，或者在关闭的连接上调用此方法
     *  返回： 1.返回带正确值的PreparedStatement 2.返回空值
     * */

    private PreparedStatement getPreparedStatement(Connection connection, String generalsqlstatement) {
        PreparedStatement preparedStatement = null;
        if(generalsqlstatement != null && connection != null) {
            try {
                if(connection.isValid(3000)) {
                    preparedStatement = connection.prepareStatement(generalsqlstatement);
                }else {
                    throw new SQLException(ExceptionEnum.DATABASE_UNLINK.getException_message());
                }
            } catch (SQLException e) {
                log.error(ExceptionEnum.DATABASE_UNLINK.getException_message());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                throw new Exception(ExceptionEnum.PARAMETER_ERROR.getException_message());
            } catch (Exception e) {
                log.error(ExceptionEnum.PARAMETER_ERROR.getException_message());
                e.printStackTrace();
            }
        }
        return preparedStatement;
    }

    /**
     *  功能：  1.关闭与数据库的连接。
     *  异常：  1.数据库连接早已关闭，重复调用，发生异常。 2.数据库连接关闭异常。
     *  返回：  1.无返回值。
     * */

    public void close(Connection connection) {
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

    /**
     *  功能： 1.设置预处理语句中的站位符为String的地方
     *  异常： 1.参数异常，不符合规则。2.如果 Index 不对应于 SQL 语句中的参数标记下标；如果发生数据库访问错误，或者在关闭的 PreparedStatement 上调用此方法
     *  返回： 1. null。
     * */

    public void setString(PreparedStatement preparedStatement, int index, String parameter) {
        if(preparedStatement != null && parameter != null && index >=1) {
            try {
                preparedStatement.setString(index,parameter);
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                throw new Exception(ExceptionEnum.PARAMETER_ERROR.getException_message());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  功能： 1.设置预处理语句中的占位符为 Double 的地方
     *  异常： 1.参数异常，不符合规则。2.如果 Index 不对应于 SQL 语句中的参数标记下标；如果发生数据库访问错误，或者在关闭的 PreparedStatement 上调用此方法
     *  返回： 1.null
     * */

    public void setDouble(PreparedStatement preparedStatement, int index, double parameter) {
        if(preparedStatement != null && index >=1 && parameter >= 0) {
            try {
                preparedStatement.setDouble(index,parameter);
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                throw new Exception(ExceptionEnum.PARAMETER_ERROR.getException_message());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  功能： 1.设置预处理语句中的占位符为 int 的地方
     *  异常： 1.参数异常，不符合规则。2.如果 Index 不对应于 SQL 语句中的参数标记下标；如果发生数据库访问错误，或者在关闭的 PreparedStatement 上调用此方法
     *  返回： 1.null
     * */

    public void setInt(PreparedStatement preparedStatement, int index, int parameter) {
        if(preparedStatement != null && index >=1 && parameter >= 0) {
            try {
                preparedStatement.setInt(index,parameter);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            try {
                throw new Exception(ExceptionEnum.PARAMETER_ERROR.getException_message());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
