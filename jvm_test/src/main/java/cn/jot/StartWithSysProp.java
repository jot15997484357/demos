package cn.jot;

public class StartWithSysProp {
    public static void main(String[] args) {
        String sys = System.getProperty("sys");
        if (sys == null || sys.equals("")) {
            System.out.println("none of it");
        } else {
            System.out.println(sys);
        }
    }
}
