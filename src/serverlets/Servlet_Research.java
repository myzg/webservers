package serverlets;

import database.LinkDatabase;
import datamodel.StudentModel;
import enumeration.ExamMapEnum;
import enumeration.StudentMessageEnum;
import logicbean.Research;
import org.apache.log4j.Logger;
import serverinterface.StructLanguage;
import tools.AddParameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "Servlet_Research")
public class Servlet_Research extends HttpServlet {
    public static Logger log = Logger.getLogger(Servlet_Research.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tag = -1;
        String sqlstatement = null;
        StructLanguage factory = new Research();
        sqlstatement = factory.getStatement(request);
        tag = Integer.parseInt(request.getParameter("tag").trim());
        if(sqlstatement != null && tag != -1) {
            LinkDatabase database = new LinkDatabase();
            PreparedStatement excute = database.init(sqlstatement);
            if(tag == 1 && excute != null) {
                AddParameter.add(request,excute,database);
                disposeExamMap(excute,request);
                if(request.getParameter("needtranspond").equals("yes")) {
                    /**
                     * 通过请求转发处理
                     * */
                }
            }else if(tag == 2 && excute != null) {
                AddParameter.add(request,excute,database);
                disposeStudentMessage(excute,request);
            }else if(tag == 3 && excute != null) {

            }else if(tag == 4 && excute != null) {

            }else if(tag == 5 && excute != null) {

            }
        }else {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private void disposeExamMap(PreparedStatement excute, HttpServletRequest request) {
        HashMap<String,String> name_map = null;
        ResultSet result = null;
        name_map = new HashMap<String,String>();
        try {
            result = excute.executeQuery();
            while(result.next()) {
                name_map.put(result.getString(ExamMapEnum.EXAM_DESCRIPTION.getField_name()),result.getString(ExamMapEnum.EXAM_SQL_ID.getField_name()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("exammap",name_map);
    }

    private void disposeStudentMessage(PreparedStatement excute, HttpServletRequest request) {
        ResultSet result = null;
        try {
            result = excute.executeQuery();
            StudentModel student = null;
            while (result.next()) {
                student = new StudentModel();
                student.setStu_id(result.getInt(StudentMessageEnum.ID.getFiled_name()));
                student.setGrade_number(result.getInt(StudentMessageEnum.GRADE.getFiled_name()));
                student.setClass_number(result.getInt(StudentMessageEnum.CLASS.getFiled_name()));
                student.setStu_name(result.getString(StudentMessageEnum.NAME.getFiled_name()));
            }
            request.setAttribute("studentmessage",student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
