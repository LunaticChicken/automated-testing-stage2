public class Dock {
    private Port port;
    private Ship currentShipInTheDock;

    public Dock() {
        if (Main.ships.peek() == null) return;
        currentShipInTheDock = Main.ships.poll();
        currentShipInTheDock.setCurrentDock(this);
    }

    public void nextShip() {
        if (Main.ships.peek() == null) return;
        currentShipInTheDock = Main.ships.poll();
        currentShipInTheDock.setCurrentDock(this);
        currentShipInTheDock.start();
    }

    public Ship getCurrentShipInTheDock() {
        return currentShipInTheDock;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public synchronized void checkIfPortIsFullOrEmpty(int currentAmountOfContainers, int maxAmountOfContainers) {
        if (currentAmountOfContainers > maxAmountOfContainers-100) {
            port.setCurrentAmountOfContainers(currentAmountOfContainers-1000);
            System.out.print("1000 containers were exported. ");
            System.out.println("Port has " + port.getCurrentAmountOfContainers()+"/"+maxAmountOfContainers + " containers");
        }
        if (currentAmountOfContainers < 100) {
            port.setCurrentAmountOfContainers(currentAmountOfContainers+1000);
            System.out.println("1000 containers were imported. ");
            System.out.println("Port has " + port.getCurrentAmountOfContainers()+"/"+maxAmountOfContainers + " containers");
        }
        notifyAll();
    }

    public synchronized void loadContainersOnShip() throws InterruptedException {
        while (port.getCurrentAmountOfContainers() < 100) {
            System.out.println("WARNING! Port is empty. Waiting for importing... ");
            wait();
        }
        synchronized (port) {
            currentShipInTheDock.setCurrentAmountOfContainers(currentShipInTheDock.getCurrentAmountOfContainers()+100);
            port.setCurrentAmountOfContainers(port.getCurrentAmountOfContainers()-100);
            System.out.printf("%s has %s/%s containers. Port has %s/%s containers\n", currentShipInTheDock.getName(),
                    currentShipInTheDock.getCurrentAmountOfContainers(), currentShipInTheDock.getMaxAmountOfContainers(),
                    port.getCurrentAmountOfContainers(), port.getMaxAmountOfContainers());
        }
        Thread.sleep(10);
    }

    public synchronized void unloadContainersFromShip() throws InterruptedException {
        while (port.getCurrentAmountOfContainers() > port.getMaxAmountOfContainers()-100) {
            System.out.println("WARNING! Port is full. Waiting for exporting... ");
            wait();
        }
        synchronized (port) {
            currentShipInTheDock.setCurrentAmountOfContainers(currentShipInTheDock.getCurrentAmountOfContainers()-100);
            port.setCurrentAmountOfContainers(port.getCurrentAmountOfContainers()+100);
            System.out.printf("%s has %s/%s containers. Port has %s/%s containers\n", currentShipInTheDock.getName(),
                    currentShipInTheDock.getCurrentAmountOfContainers(), currentShipInTheDock.getMaxAmountOfContainers(),
                    port.getCurrentAmountOfContainers(), port.getMaxAmountOfContainers());
        }
        Thread.sleep(10);
    }
}