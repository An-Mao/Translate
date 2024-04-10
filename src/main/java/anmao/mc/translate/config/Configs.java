package anmao.mc.translate.config;

import anmao.mc.amlib.system._File;

public class Configs {
    public static final String ConfigDir = _File.getFileFullPathWithRun("config/Translate/");
    public static final String ConfigDir_JS = ConfigDir +"JavaScript/";
    public static final String ConfigDir_Lang = ConfigDir +"Lang/";
    static {
        _File.checkAndCreateDir(ConfigDir);
        _File.checkAndCreateDir(ConfigDir_JS);
        _File.checkAndCreateDir(ConfigDir_Lang);
    }
}
