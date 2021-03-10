
public interface Subject {
    void addObserver(User user);
    void removeObserver(User c);
    void notifyAllObservers(String notification);
}
