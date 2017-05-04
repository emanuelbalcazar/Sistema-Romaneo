package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase encargada de obtener los valores configurados en un archivo de
 * properties. Util si se desea agrupar la configuracion fuera de una clase
 * Java.
 *
 * @author emanuel
 */
public class Configuration {

    private Properties properties;
    private final String CONFIG_FILE_NAME = "config.properties";
    
    // Constantes estaticas utilizadas para acceder a los valores en el archivo properties.
    public static final String SERVER_PUBLISH = "Server.publish";
    public static final String SERVER_TEST = "Server.test";

    
    private Configuration() {
        this.properties = new Properties();
        readPropertiesFile();
    }
    
    /**
     * Lee el archivo de properties y lo inicializa en el atributo privado de la clase.
     */
    private void readPropertiesFile() {
        try {
            try (FileInputStream in = new FileInputStream(CONFIG_FILE_NAME)) {
                properties.load(in);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }
    
    /**
     * Utilizado para la implementacion del patron de diseño Singleton.
     * 
     * @return una unica instancia de la clase.
     */
    public static Configuration getInstance() {
        return ConfigurationHolder.INSTANCE;
    }
    
    private static class ConfigurationHolder {
        private static final Configuration INSTANCE = new Configuration();
    }
}
