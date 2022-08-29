package g56133.atl.Mentoring.config;

import g56133.mentoring.repository.RepositoryException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

/**
 *
 * @author Leong Paeg-Hing
 */
public class ConfigManager {
    
    private ConfigManager() {
        this.prop = new Properties();
        String path = getClass().getClassLoader().getResource(FILE).getFile();
        path = URLDecoder.decode(path); // Pour regler les problemes d'espace dans le chemin
        this.url = path;
    }
    
    private static final String FILE = "./config/config.properties";
    
    private final Properties prop;
    
    private final String url;
    
    public void load() throws RepositoryException{
        try (InputStream input = new FileInputStream(url)) {
            prop.load(input);
        } catch (IOException ex) {
            throw new RepositoryException("Chargement configuration impossible " +
                    ex.getMessage());
        }
    }
    
    public String getProperties(String name) {
        return prop.getProperty(name);
    }
    
    public static ConfigManager getInstance() {
        return ConfigManagerHolder.INSTANCE;
    }
    
    private static class ConfigManagerHolder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }
}
