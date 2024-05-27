import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<ship> tunnelQueue = new LinkedBlockingQueue<>(5);
        Semaphore tunnelSemaphore = new Semaphore(5);

        shipGenerator generator = new shipGenerator( tunnelQueue);
        Dock breadDock = new Dock(ship.Type.Bread, tunnelQueue, tunnelSemaphore);
        Dock bananaDock = new Dock(ship.Type.Banana, tunnelQueue, tunnelSemaphore);
        Dock clothes = new Dock(ship.Type.Clothes, tunnelQueue, tunnelSemaphore);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.execute(generator);
        executor.execute(breadDock);
        executor.execute(bananaDock);
        executor.execute(clothes);

        try {
            Thread.sleep(60000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }finally {
            executor.shutdown();
        }
    }
}
