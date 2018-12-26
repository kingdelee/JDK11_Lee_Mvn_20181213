package classloader;

public class AppletClassLoader {

    public AppletClassLoader() {
        System.out.println("constructed");
    }

    public static void main(String[] args) {
        System.out.println("recognized as sun.applet.classloader.AppletClassLoader in jdk," +
                " and there isn't any main method");
    }
}
