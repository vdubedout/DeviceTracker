package eu.dubedout.devicecounter.architecture.serviceregistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Contains a map of the different instances.
 */
public class ServiceRegistryImpl {
    private Map<Class, Object> registry = new ConcurrentHashMap<>();

    public ServiceRegistryImpl() {
    }

    /**
     * Get an instance
     * @param clazz class to lookup
     * @param <T> instance
     * @return a existing instance
     */
    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> clazz) {
        return (T) registry.get(clazz);
    }

    /**
     * Create the registry
     * @param map map
     */
    public void create(Map<Class, Object> map) {
        registry.putAll(map);
    }

    /**
     * Update
     * @param item new instance
     */
    public void update(Object item) {
        if (item == null) {
            return;
        }
        registry.put(item.getClass(), item);
    }

    /**
     * Utility method for testing that clear the cache
     */
    public void clear(){
        registry.clear();
    }
}
