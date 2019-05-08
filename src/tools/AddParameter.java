package tools;

import database.LinkDatabase;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;

public class AddParameter {
    public static boolean add(HttpServletRequest request, PreparedStatement preparedStatement, LinkDatabase database) {
        int tag = -1;
        boolean isoperation = false;
        tag = Integer.parseInt(request.getParameter("tag").trim());
        if(tag != -1){
            switch (tag) {
                /**
                 *  当值为1 的时候，为处理考试映射，不需要添加参数。
                 * */
                case 1:
                    isoperation = true;
                    break;
                /**
                 *  当值为 2 的时候，为处理学生信息查询，此处需要添加stu_id 。
                 * */
                case 2:
                    int id_parameter = -1;
                    id_parameter = Integer.parseInt(request.getParameter("stu_id").trim());
                    if(id_parameter != -1) {
                        database.setInt(preparedStatement,1,id_parameter);
                        isoperation = true;
                    }
                    break;
                /**
                 *  当值为 3 的时候，为处理 单人成绩查询，需要添加 1.考试的逻辑编号 2.学号。
                 * */
                case 3:
                    int id_parameter_3 = -1;
                    int exam_parameter = -1;
                    id_parameter = Integer.parseInt(request.getParameter("stu_id").trim());
                    exam_parameter = (Integer) request.getAttribute("exam_id");
                    if(id_parameter != -1 && exam_parameter != -1) {
                        database.setInt(preparedStatement,1,exam_parameter);
                        database.setInt(preparedStatement,1,id_parameter_3);
                        isoperation = true;
                    }
                    break;
                /**
                 *  当值为 4 的时候，为处理班级查询，需要添加 1.考试逻辑编号 2.毕业号 3.班级
                 * */
                case 4:
                    int exam_parameter_4 = -1;
                    int grade_parameter = -1;
                    int class_parameter = -1;
                    exam_parameter_4 = (Integer) request.getAttribute("exam_id");
                    grade_parameter = Integer.parseInt(request.getParameter("grade_number").trim());
                    class_parameter = Integer.parseInt(request.getParameter("class_number").trim());
                    if(exam_parameter_4 != -1 && grade_parameter != -1 && class_parameter != -1) {
                        database.setInt(preparedStatement,1,exam_parameter_4);
                        database.setInt(preparedStatement,2,grade_parameter);
                        database.setInt(preparedStatement,3,class_parameter);
                        isoperation = true;
                    }
                    break;
                /**
                 *   当值为 5 的时候，为查询对应考试中的所有信息。需要添加 1.考试的逻辑编号
                 * */
                case 5:
                    int exam_parameter_5 = -1;
                    exam_parameter_5 = (Integer) request.getAttribute("exam_id");
                    if(exam_parameter_5 != -1) {
                        database.setInt(preparedStatement,1,exam_parameter_5);
                        isoperation = true;
                    }
                    break;
            }
        }
        return isoperation;
    }
}
