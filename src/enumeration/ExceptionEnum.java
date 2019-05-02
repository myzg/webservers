package enumeration;

public enum ExceptionEnum {
    DATABASE_UNLINK("数据库连接处于断开状态"),
    PARAMETER_ERROR("参数错误");

    private String exception_message;

    private ExceptionEnum(String exception_message) {
        this.exception_message = exception_message;
    }

    public String getException_message() {
        return this.exception_message;
    }
}
