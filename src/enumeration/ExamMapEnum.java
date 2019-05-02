package enumeration;

public enum ExamMapEnum {
    EXAM_DESCRIPTION("exam_description","varchar(30)"),
    EXAM_SQL_ID("exam_sql_id","varchar(20)"),
    EXAM_ID("exam_id","int");

    private String field_name;
    private String field_type;

    private ExamMapEnum(String field_name, String field_type) {
        this.field_name = field_name;
        this.field_type = field_type;
    }

    public String getField_name() {
        return this.field_name;
    }

    public String getField_type() {
        return this.field_type;
    }
}
