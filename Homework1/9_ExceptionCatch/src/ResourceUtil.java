public class ResourceUtil {
    public static void closeQuietly(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                GlobalExceptionHandler.handleException(e);
            }
        }
    }
}
