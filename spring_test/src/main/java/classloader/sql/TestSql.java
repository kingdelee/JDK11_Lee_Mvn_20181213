package classloader.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSql {

    public static void main(String[] args) throws SQLException {
        // 加载Class到AppClassLoader（系统类加载器），然后注册驱动类
// Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://localhost:3306/testdb";
// 通过java库获取数据库连接

        ClassLoader classLoader = DriverManager.class.getClassLoader();
        System.out.println(classLoader.getName());
        System.out.println(TestSql.class.getClassLoader().getName());

        StringBuilder sb = new StringBuilder();

        System.out.println(sb.getClass().getClassLoader());

        System.out.println(Thread.currentThread().getContextClassLoader().getName());

        Connection conn = java.sql.DriverManager.getConnection(url, "name", "password");

    }

}
