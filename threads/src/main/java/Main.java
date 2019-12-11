import java.util.concurrent.*;

public class Main {
    public static BlockingQueue<Ship> ships = new ArrayBlockingQueue<>(16);

    public static void main(String[] args) throws InterruptedException {
        ships.add(new Ship("Ship1", 700, 2000));
        ships.add(new Ship("Ship2", 0, 1500));
        ships.add(new Ship("Ship3", 900, 1000));
        ships.add(new Ship("Ship4", 600, 1300));
        ships.add(new Ship("Ship5", 1200, 2500));

        Dock[] docks = { new Dock(), new Dock() };

        Port port = new Port("Port", 1000, 5000, docks);
        port.start();
        for (Dock dock : docks) {
            dock.getCurrentShipInTheDock().start();
        }
        port.join();
        System.out.println("That's all for today. Good job :)");
    }
}
