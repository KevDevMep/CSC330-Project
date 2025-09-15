/**
 * Interface to check if a user has access
 */
public interface UserAccessible {
    /**
     * @param user check the user access
     * @return true if user has access else false
     */
    public boolean hasAccess(String user);
}