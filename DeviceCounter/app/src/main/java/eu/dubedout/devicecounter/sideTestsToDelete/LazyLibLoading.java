package eu.dubedout.devicecounter.sideTestsToDelete;

import android.os.Handler;

import java.util.HashMap;

/***
 *
 */
public class LazyLibLoading {
    public interface LazyGetter<T> {
        T get();
    }

    public interface ResponseHandler<T> {
        public void onSuccess(T responseObject);
        public void onFailure(Throwable error);
    }

    private final static HashMap<Class, LazyGetter> instanciationHash = new HashMap<>();
    private final static HashMap<Class, Object> registryMap = new HashMap<>();
    private final static Handler UIHandler = new Handler();

    private LazyLibLoading() {
    }

    public static void registerLazyCreation(Class clazz, LazyGetter lazyGetter) {
        instanciationHash.put(clazz, lazyGetter);
    }

    /***
     * Create object if not existing and return the instance
     * @param clazz class to lookup
     * @param <T> instance
     * @return instance
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> clazz) {
        if (registryMap.containsKey(clazz)) {
            return (T) registryMap.get(clazz);
        } else if (instanciationHash.containsKey(clazz)) {
            Object o = instanciationHash.get(clazz).get(); //creates the object, this stays in the UIThread
            registryMap.put(clazz, o);
            return (T) o;
        } else {
            // not added to load
            return null;
        }
    }

    /***
     * Create object if not existing and return the instance through the callback
     *
     * @param clazz class to lookup
     * @param callback execute action when processing is done
     * @param <T> instance
     */
    @SuppressWarnings("unchecked")
    public static <T> void getInstance(final Class<T> clazz, final ResponseHandler<T> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (registryMap.containsKey(clazz)) {
                    callback.onSuccess((T) registryMap.get(clazz));
                } else if (instanciationHash.containsKey(clazz)) {
                    Object o = instanciationHash.get(clazz).get(); //creates the object, this stays in the UIThread
                    registryMap.put(clazz, o);
                    callback.onSuccess((T) o);
                } else {
                    callback.onFailure(new Throwable("Class have not been registered, register it with registerLazyCreation"));
                }
            }
        }).start();
    }

    /**
     * Utility method for testing that clear the cache
     */
    public static void clear(){
        instanciationHash.clear();
        registryMap.clear();
    }

}
