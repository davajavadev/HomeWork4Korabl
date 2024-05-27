import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Dock implements Runnable {
    private ship.Type type;
    private BlockingQueue<ship> tunnelQueue;
    private Semaphore tunnelSemaphore;

    public Dock(ship.Type type, BlockingQueue<ship> tunnelQueue, Semaphore tunnelSemaphore) {
        this.type = type;
        this.tunnelQueue = tunnelQueue;
        this.tunnelSemaphore = tunnelSemaphore;
    }
    public void run() {
        while (true) {
            try {
                ship ship = null;
                synchronized (tunnelQueue) {
                    for(ship s : tunnelQueue) {
                        if (s.getType() == type) {
                            ship = s;
                            tunnelQueue.remove(s);
                            break;
                        }
                    }
                }
                if (ship != null) {
                    tunnelSemaphore.acquire();
                    System.out.println(type + " dock processing ship with capacity  " + ship.getCapacity());
                    Thread.sleep(ship.getCapacity()*100);
                    tunnelSemaphore.release();
                }else {
                    Thread.sleep(500);
                }
            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
