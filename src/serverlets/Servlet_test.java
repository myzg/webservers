package serverlets;


import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Servlet_test")
public class Servlet_test extends HttpServlet {
    private static Logger logger = Logger.getLogger(Servlet_test.class.getName());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("我不知道我是谁");
        logger.error("我是 error");
        logger.info("我是 info");
        logger.warn("我是 warn");
        System.out.println("卧槽这次怎么成功了你妈");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
