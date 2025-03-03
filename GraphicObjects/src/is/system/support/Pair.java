package is.system.support;

public class Pair<T,U> {

    private final T Key;
    private final U Value;

    public Pair(T Key, U Value) {
        this.Key = Key;
        this.Value = Value;
    }

    public Pair() {
        this.Key = null;
        this.Value = null;
    }

    public T getKey() {
        return Key;
    }

    public U getValue() {
        return Value;
    }

    public boolean notEmpty(){
        return Key != null;
    }
}
