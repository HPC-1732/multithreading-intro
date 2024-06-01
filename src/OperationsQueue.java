import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;;
public class OperationsQueue {

    private final List<Integer> operations = new ArrayList<>();
    
    private final ReentrantLock lock = new ReentrantLock();

    public void addSimulation(int totalSimulation) {

        // Add 50 random numbers in the operations list. The number will be range from -100 to 100. It cannot be zero.
        for (int i = 0; i < totalSimulation; i++) {
            lock.lock();
                try{
                    int random = (int) (Math.random() * 200) - 100;
                    if (random != 0) {
                        operations.add(random);
                        System.out.println(i + ". New operation added: " + random);
                    }
                // add small delay to simulate the time taken for a new customer to arrive
                try {
                    Thread.sleep((int) (Math.random() * 80));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally{
                lock.unlock();
            }
        }
        lock.lock();
        try{
            operations.add(-9999);
        }finally {lock.unlock();}
    }

    public void add(int amount) {
        operations.add(amount);
    }

    public int getNextItem() {
        // add a small delay to simulate the time taken to get the next operation.
        while(operations.isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return operations.remove(0);
    }
}
