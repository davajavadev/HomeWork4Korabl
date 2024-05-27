public class ship {
    enum Type{Bread, Banana, Clothes  }
    private Type type;
    private int capacity;

    public ship(Type type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }
    public Type getType() {
        return type;
    }
    public int getCapacity() {
        return capacity;
    }
}
