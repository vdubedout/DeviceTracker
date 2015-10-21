package eu.dubedout.devicecounter.helper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Contains a map of the different instances.
 */
public class ServiceRegistry {
    private static Map<Class, Object> registry = new ConcurrentHashMap<>();

    private ServiceRegistry() {
    }

    /**
     * Get an instance
     * @param clazz class to lookup
     * @param <T> instance
     * @return a existing instance
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> clazz) {
        return (T) registry.get(clazz);
    }

    /**
     * Create the registry
     * @param map map
     */
    public static void create(Map<Class, Object> map) {
        registry.putAll(map);
    }

    /**
     * Update
     * @param item new instance
     */
    public static void update(Object item) {
        if (item == null) {
            return;
        }
        registry.put(item.getClass(), item);
    }

    /**
     * Utility method for testing that clear the cache
     */
    public static void clear(){
        registry.clear();
    }
}
