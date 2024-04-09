package anmao.mc.translate.translate;

import anmao.mc.translate.config.Config;
import anmao.mc.translate.config.Configs;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.sql.*;

public class DB {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String file = Configs.ConfigDir + "translate.db";
    private static final String url;
    private static Connection dbLink = null;
    static {
        url = "jdbc:sqlite:D:/AM/AMDev/minecraft/forge/1.20.1/Translate/run/config/Translate/translate.db";//"jdbc:sqlite:"+file;
        System.out.println("sql :: "+url);
        link();
        create();
    }

    public static void create() {
        try {
            Statement statement = dbLink.createStatement();
            String createDbQuery = "CREATE TABLE IF NOT EXISTS translate (source TEXT PRIMARY KEY, translate TEXT)";
            statement.executeUpdate(createDbQuery);
            LOGGER.debug("Database created successfully.");
        }catch (SQLException e){
            LOGGER.error(e.getMessage());
        }

    }
    public static void link(){
        try {
            // 创建SQLite DataSource
            //Class.forName("org.sqlite.JDBC");
            //DriverManager.registerDriver(new JDBC());
            dbLink = DriverManager.getConnection(url);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
    public static String get(String source){
        String translate = source;
        try {
            String sql = "SELECT * FROM translate WHERE source = ?";
            PreparedStatement statement = dbLink.prepareStatement(sql);
            statement.setString(1, source);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                translate = resultSet.getString("translate");
                if (!translate.isEmpty()){
                    //System.out.println("Translate: " + translate);
                    return translate;
                }
            }
            add(source);
        }catch (SQLException e){
            LOGGER.error(e.getMessage());
        }
        return translate;
    }
    public static void add(String source){
        add(source,getTranslate(source));
    }

    private static String getTranslate(String source) {
        if (Config.I.isOnlineTranslate()) {
            return Network.getOnlineTranslate(source);
        }
        return source;
    }

    public static void add(String source , String translate){
        String insertQuery = "INSERT INTO employees (name, age, salary) SELECT ?, ?, ? WHERE NOT EXISTS (SELECT 1 FROM employees WHERE name = ?)";
        try (PreparedStatement preparedStatement = dbLink.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, source);
            preparedStatement.setString(2, translate);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
