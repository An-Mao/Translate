package anmao.mc.translate.config;

import anmao.mc.amlib.system._File;

public class Configs {
    public static final String ConfigDir = _File.getFileFullPathWithRun("config/Translate/");
    static {
        _File.checkAndCreateDir(ConfigDir);
    }
}
