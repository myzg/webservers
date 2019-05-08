package logicbean;

import serverinterface.StructLanguage;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class Research implements StructLanguage {

    @Override
    public String getStatement(HttpServletRequest request) {
        ServletContext context = null;
        String sqlstatement = null;
        int tag = -1;
        tag = Integer.parseInt(request.getParameter("tag").trim());
        context = request.getServletContext();
        if(tag != -1 && context != null) {
            switch (tag) {
                case 1: //此处为考试映射关系查询
                    sqlstatement = getMessage(context,context.getInitParameter("research_exam"));
                    break;
                case 2: //此处为学生信息查询
                    sqlstatement = getMessage(context,context.getInitParameter("research_student_message"));
                    break;
                case 3: //此处为单个学生成绩查询
                    sqlstatement = getMessage(context,context.getInitParameter("research_single_performance"));
                    break;
                case 4: //此处为班级查询
                    sqlstatement = getMessage(context,context.getInitParameter("research_class_performance"));
                    break;
                case 5: //此处为按考试查询所有的信息
                    sqlstatement = getMessage(context,context.getInitParameter("research_all_performance"));
                    break;
            }
        }
        return sqlstatement;
    }

    /**
     *  功能：查询数据库中存在的考试。如果参数出现错误，返回null;
     * */

    private String getMessage(ServletContext context, String param) {
        String sqlstatement = null;
        sqlstatement = context.getInitParameter(param);
        if(sqlstatement != null) {
            return sqlstatement;
        }else {
            return null;
        }
    }



}
