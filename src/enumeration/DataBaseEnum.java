package enumeration;

public enum DataBaseEnum {
    DRIVER("com.mysql.jdbc.Driver","Driver"),
    URl("jdbc:mysql://cdb-9qo2boau.cd.tencentcdb.com:10043/websubject","url"),
    USER("root","user"),
    PASSWORD("zg138904","password"),
    USEUNICODE("true","useUnicode"),
    CHARACTERENCODING("UTF-8","characterEncoding"),
    ALLOWMUTIQUERIES("true","allowMultiQueries");

    private String values;
    private String name;

    private DataBaseEnum(String values, String name) {
        this.values = values;
        this.name = name;
    }

    public String getValues() {
        return this.values;
    }

    public String getName() {
        return this.name;
    }
}
