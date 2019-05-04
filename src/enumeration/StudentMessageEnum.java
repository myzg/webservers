package enumeration;
/**
 *
 *  学生信息字段设置；
 * */
public enum StudentMessageEnum {
    NAME("","","");

    private String filed_name;
    private String filed_type;
    private String filed_limit;

    private StudentMessageEnum(String filed_name, String filed_type, String filed_limit) {
        this.filed_name = filed_name;
        this.filed_type = filed_type;
        this.filed_limit = filed_limit;
    }

    public String getFiled_name() {
        return this.filed_name;
    }

    public String getFiled_type() {
        return this.filed_type;
    }

    public String getFiled_limit() {
        return this.filed_limit;
    }

}
