import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class shipGenerator implements Runnable {
    private BlockingQueue<ship> tunnelQueue;

    public shipGenerator(BlockingQueue<ship> tunnelQueue) {
        this.tunnelQueue = tunnelQueue;
    }
    public void run() {
        Random rand = new Random();
        ship.Type[] types = ship.Type.values();
        int[] capacities = {10,50,100};

        while (true){
            try {
                ship.Type type = types[rand.nextInt(types.length)];
                int capacity = capacities[rand.nextInt(capacities.length)];
                ship ship = new ship(type, capacity);
                tunnelQueue.put(ship);
                System.out.println("Generated ship " + type.name() + " with capacity " + capacity);
                Thread.sleep(1000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
