package test;


import datamodel.StudentModel;
import sun.awt.windows.ThemeReader;


public class test1 extends test2{

    public void dosometing(){
        System.out.println("我是子类，我的方法被调用");
    }
    public static void main(String [] args) {
        test2 tt = new test1();
        tt.dosometing();
        tt.doget();
    }
}
class test2 {
    public void dosometing(){
        System.out.println("我是父类，我的方法被调用");
    }
    public void doget(){System.out.println("我是父类的非重写方法，我被调用");}
}