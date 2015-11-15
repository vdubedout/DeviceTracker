package eu.dubedout.devicecounter.helper;

public class StringHelper {
    public static boolean isEmpty(String string) {
        if (string != null && string.length() != 0) {
            return false;
        }
        return true;
    }
}
