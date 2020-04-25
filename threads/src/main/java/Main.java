import java.util.concurrent.*;

public class Main {
    public static BlockingQueue<Ship> ships = new ArrayBlockingQueue<>(16);

    public static void main(String[] args) throws InterruptedException {
        ships.add(new Ship("Ship1", 2000, 2000));
        ships.add(new Ship("Ship2", 1500, 1500));
        ships.add(new Ship("Ship3", 400, 1000));
        ships.add(new Ship("Ship4", 800, 1300));
        ships.add(new Ship("Ship5", 1200, 2500));

        DockThread[] docks = { new DockThread(), new DockThread() };
        Port port = new Port("Port", 3000, 5000, docks);
        System.out.printf("NEW PORT (%d/%d)\n", port.getCurrentAmountOfContainers(), port.getMaxAmountOfContainers());
        for (DockThread dock : docks) {
            dock.start();
        }

        Thread daemonThreadThatLooksAfterAmountOfContainersInThePort = new Thread(() -> {
            while (true) {
                if (port.getCurrentAmountOfContainers() <= 0) {
                    port.load(1000);
                    System.out.println("WARNING! Port is empty. Waiting for loading...\n" +
                            "Done! 1000 containers were imported in the port.");
                } else if (port.getCurrentAmountOfContainers() >= port.getMaxAmountOfContainers()) {
                    port.unload(1000);
                    System.out.println("WARNING! Port is full. Waiting for unloading... \n" +
                            "Done! 1000 containers were exported from the port.");
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        daemonThreadThatLooksAfterAmountOfContainersInThePort.setDaemon(true);
        daemonThreadThatLooksAfterAmountOfContainersInThePort.start();
        for (DockThread dock : docks) {
            dock.join();
        }
        System.out.println("That's all for today. Good job :)");
    }
}