package enumeration;

public enum SQLStatementEnum {
    EXAMMAP_RESEARCH("select * from exammap");

    private String sqlstatement;

    private SQLStatementEnum(String sqlstatement) {
        this.sqlstatement = sqlstatement;
    }

    public String getSqlstatement() {
        return this.sqlstatement;
    }
}
