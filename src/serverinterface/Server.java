package serverinterface;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

public interface Server {
    public void getServer(HttpServletRequest request, ServletConfig config);
}
