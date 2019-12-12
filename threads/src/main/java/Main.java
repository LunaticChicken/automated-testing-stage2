import java.util.concurrent.*;

public class Main {
    public static BlockingQueue<Ship> ships = new ArrayBlockingQueue<>(16);

    public static void main(String[] args) throws InterruptedException {
        ships.add(new Ship("Ship1", 2000, 2000));
        ships.add(new Ship("Ship2", 1500, 1500));
        ships.add(new Ship("Ship3", 400, 1000));
        ships.add(new Ship("Ship4", 800, 1300));
        ships.add(new Ship("Ship5", 1200, 2500));

        Dock[] docks = { new Dock(), new Dock() };

        Port port = new Port("Port", 3000, 5000, docks);
        port.setDaemon(true);
        port.start();
        for (Dock dock : docks) {
            dock.getCurrentShipInTheDock().start();
        }

        Thread.sleep(1000);
        System.out.println("That's all for today. Good job :)");
    }
}