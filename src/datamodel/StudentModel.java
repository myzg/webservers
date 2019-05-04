package datamodel;

import java.util.HashMap;

public class StudentModel {
    private String stu_name;
    private int stu_id;
    private int grade_number;
    private int class_number;
    private HashMap<String,Double> performance;

    public StudentModel() {
        performance = new HashMap<String,Double>();
    }

    public HashMap<String, Double> getPerformance() {
        return this.performance;
    }

    public void setPerformance(HashMap<String, Double> performance) {
        this.performance = performance;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getGrade_number() {
        return grade_number;
    }

    public void setGrade_number(int grade_number) {
        this.grade_number = grade_number;
    }

    public int getClass_number() {
        return class_number;
    }

    public void setClass_number(int class_number) {
        this.class_number = class_number;
    }
}
