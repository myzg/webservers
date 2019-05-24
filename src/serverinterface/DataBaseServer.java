package serverinterface;

import java.sql.Connection;
import java.sql.Statement;

public interface DataBaseServer extends Server{
    public Connection getLink();
    public Statement getStatement();
    public Object execute();
    public void close();
    public void sequence();
}
