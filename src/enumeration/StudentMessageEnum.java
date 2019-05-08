package enumeration;
/**
 *
 *  学生信息字段设置；
 * */
public enum StudentMessageEnum {
    NAME("stu_name"),
    ID("stu_id"),
    GRADE("graduate_number"),
    CLASS("class_number");

    private String filed_name;

    private StudentMessageEnum(String filed_name) {
        this.filed_name = filed_name;
    }

    public String getFiled_name() {
        return this.filed_name;
    }
}
