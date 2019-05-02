package enumeration;

public enum ExceptionEnum {
    DATABASE_UNLINK("数据库连接已断开");


    private String exception_message;

    private ExceptionEnum(String exception_message) {
        this.exception_message = exception_message;
    }

    public String getException_message() {
        return this.exception_message;
    }
}
