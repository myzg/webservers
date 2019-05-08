package enumeration;

public enum SQLStatementEnum {
    EXAMMAP_RESEARCH("");

    private String sqlstatement;

    private SQLStatementEnum(String sqlstatement) {
        this.sqlstatement = sqlstatement;
    }

    public String getSqlstatement() {
        return this.sqlstatement;
    }
}
