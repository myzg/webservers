package tools;

import database.LinkDatabase;
import enumeration.ExamMapEnum;
import enumeration.ExceptionEnum;
import enumeration.SQLStatementEnum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExamMap {
    //  数据库链接
    private LinkDatabase database_link = null;
    //  考试映射
    private HashMap<String,String> exam_map = null;
    //  考试次序号
    private ArrayList<Integer> exam_id = null;

    /**
     *  功能： 1.构造函数
     *  异常： 1.为 init 方法抛出
     *  返回： 1.无返回值
     * */

    public ExamMap(String sqlstatement) {
        exam_map = new HashMap<String,String>();
        exam_id = new ArrayList<Integer>();
        database_link = new LinkDatabase();
        init(sqlstatement);
    }

    /**
     *  功能： 1.为容器添加值
     *  异常： 1.参数异常 2.如果发生数据库访问错误，在关闭的 PreparedStatement 上调用此方法，或者 SQL 语句没有返回 ResultSet 对象
     *  返回： 1.空
     * */

    private void init(String sqlstatement) {
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        preparedStatement = database_link.getPreparedStatement(SQLStatementEnum.EXAMMAP_RESEARCH.getSqlstatement());
        if(preparedStatement != null && database_link != null && sqlstatement != null && exam_map != null && exam_id != null) {
            try {
                result = preparedStatement.executeQuery();
                while(result.next()) {
                    exam_map.put(result.getString(ExamMapEnum.EXAM_DESCRIPTION.getField_name()), result.getString(ExamMapEnum.EXAM_SQL_ID.getField_name()));
                    exam_id.add(result.getInt(ExamMapEnum.EXAM_ID.getField_name()));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(database_link != null) {
                    database_link.close();
                }
            }
        }else {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(database_link != null) {
                database_link.close();
            }
            try {
                throw new Exception(ExceptionEnum.PARAMETER_ERROR.getException_message());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 功能： 1.返回映射关系（考试实际名和数据库表名的映射关系）
     * 异常： 1.映射关系为空（由于构造函数初始化参数，此时再为null,则出现异常）
     * 返回： 1.带正确值的应用 2.NULL;
     * */

    public HashMap<String,String> getExam_map() {
        if(exam_map != null) {
            return this.exam_map;
        }else {
            try {
                throw new Exception(ExceptionEnum.INIT_EXCEPTION.getException_message());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 功能： 1.返回考试的次序序列（最小为1，且可能为空<即，此时数据库中并没有相关的次序序列>）
     * 异常： 1.次序序列为空（由于构造函数初始化参数，此时再为null,则出现异常）
     * 返回： 1.带正确值的应用 2.NULL;
     * */

    public ArrayList<Integer> getExam_id() {
        if(exam_id != null) {
            return this.exam_id;
        }else {
            try {
                throw new Exception(ExceptionEnum.INIT_EXCEPTION.getException_message());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
