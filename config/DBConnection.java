package config;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConnection {

    private static Connection con;
    private static volatile boolean driverLoaded = false;

    private static final String DB_HOST = envOrDefault("DB_HOST", "localhost");
    private static final String DB_PORT = envOrDefault("DB_PORT", "3306");
    private static final String DB_NAME = envOrDefault("DB_NAME", "BookstoreManagement");
    private static final String DB_USER = envOrDefault("DB_USER", "root");
    private static final String DB_PASSWORD = envOrDefault("DB_PASSWORD", "Anshul@123#*");

    private static String envOrDefault(String key, String fallback) {
        String value = System.getenv(key);
        return (value == null || value.trim().isEmpty()) ? fallback : value;
    }

    public static synchronized Connection getConnection() {
        try {
            if (con != null && !con.isClosed()) {
                return con;
            }

            ensureDriverLoaded();
            initializeDatabaseAndTables();

            String dbUrl = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME
                    + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            con = DriverManager.getConnection(dbUrl, DB_USER, DB_PASSWORD);
            return con;
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Unable to connect to MySQL. Verify DB_HOST/DB_PORT/DB_NAME/DB_USER/DB_PASSWORD.",
                    e
            );
        }
    }

    private static void ensureDriverLoaded() {
        if (driverLoaded) {
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            driverLoaded = true;
        } catch (ClassNotFoundException e) {
            if (tryLoadDriverFromLocalJar()) {
                driverLoaded = true;
                return;
            }
            throw new IllegalStateException(
                    "MySQL JDBC driver not found. Put mysql-connector-j jar in lib/ or run with classpath.",
                    e
            );
        }
    }

    private static boolean tryLoadDriverFromLocalJar() {
        try {
            File libDir = new File("lib");
            if (!libDir.exists() || !libDir.isDirectory()) {
                return false;
            }

            File[] jars = libDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith("mysql-connector-j-") && name.endsWith(".jar");
                }
            });

            if (jars == null || jars.length == 0) {
                return false;
            }

            URL jarUrl = jars[0].toURI().toURL();
            URLClassLoader loader = new URLClassLoader(new URL[]{jarUrl}, DBConnection.class.getClassLoader());
            Class<?> driverClass = Class.forName("com.mysql.cj.jdbc.Driver", true, loader);
            Driver rawDriver = (Driver) driverClass.getDeclaredConstructor().newInstance();
            DriverManager.registerDriver(new DriverShim(rawDriver));
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private static void initializeDatabaseAndTables() {
        String rootUrl = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT
                + "/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        try (Connection rootCon = DriverManager.getConnection(rootUrl, DB_USER, DB_PASSWORD);
             Statement st = rootCon.createStatement()) {

            st.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            st.executeUpdate("USE " + DB_NAME);

            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS users ("
                            + "user_id INT PRIMARY KEY AUTO_INCREMENT,"
                            + "username VARCHAR(100) NOT NULL UNIQUE,"
                            + "password VARCHAR(255) NOT NULL,"
                            + "role VARCHAR(20) NOT NULL DEFAULT 'STAFF'"
                            + ")"
            );

            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS customer ("
                            + "customer_id VARCHAR(50) PRIMARY KEY,"
                            + "name VARCHAR(150) NOT NULL,"
                            + "phone VARCHAR(20) NOT NULL"
                            + ")"
            );

            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS book ("
                            + "book_id VARCHAR(50) PRIMARY KEY,"
                            + "title VARCHAR(255) NOT NULL,"
                            + "price DECIMAL(10,2) NOT NULL,"
                            + "stock INT NOT NULL DEFAULT 0"
                            + ")"
            );

            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS sales ("
                            + "sale_id INT PRIMARY KEY AUTO_INCREMENT,"
                            + "customer_id VARCHAR(50) NOT NULL,"
                            + "sale_date DATE NOT NULL,"
                            + "CONSTRAINT fk_sales_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id) "
                            + "ON UPDATE CASCADE ON DELETE RESTRICT"
                            + ")"
            );

            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS sales_details ("
                            + "sale_id INT NOT NULL,"
                            + "book_id VARCHAR(50) NOT NULL,"
                            + "quantity INT NOT NULL,"
                            + "PRIMARY KEY (sale_id, book_id),"
                            + "CONSTRAINT fk_sales_details_sale FOREIGN KEY (sale_id) REFERENCES sales(sale_id) "
                            + "ON UPDATE CASCADE ON DELETE CASCADE,"
                            + "CONSTRAINT fk_sales_details_book FOREIGN KEY (book_id) REFERENCES book(book_id) "
                            + "ON UPDATE CASCADE ON DELETE RESTRICT"
                            + ")"
            );
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Failed to initialize database/tables in MySQL.",
                    e
            );
        }
    }

    private static final class DriverShim implements Driver {
        private final Driver driver;

        private DriverShim(Driver driver) {
            this.driver = driver;
        }

        @Override
        public Connection connect(String url, Properties info) throws SQLException {
            return driver.connect(url, info);
        }

        @Override
        public boolean acceptsURL(String url) throws SQLException {
            return driver.acceptsURL(url);
        }

        @Override
        public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
            return driver.getPropertyInfo(url, info);
        }

        @Override
        public int getMajorVersion() {
            return driver.getMajorVersion();
        }

        @Override
        public int getMinorVersion() {
            return driver.getMinorVersion();
        }

        @Override
        public boolean jdbcCompliant() {
            return driver.jdbcCompliant();
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return driver.getParentLogger();
        }
    }
}
