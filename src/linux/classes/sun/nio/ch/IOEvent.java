package sun.nio.ch;

public class IOEvent {

    public static Class<?> eventClass() {
        return EPollPort.class;
    }

}