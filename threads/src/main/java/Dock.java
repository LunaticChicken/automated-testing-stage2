public class Dock {
    private Port port;
    private Ship currentShipInTheDock;

    public Dock() throws InterruptedException {
        currentShipInTheDock = Main.ships.take();
        currentShipInTheDock.setCurrentDock(this);
    }

    public void nextShip() throws InterruptedException {
        currentShipInTheDock = Main.ships.take();
        currentShipInTheDock.setCurrentDock(this);
        currentShipInTheDock.start();
    }

    public Ship getCurrentShipInTheDock() {
        return currentShipInTheDock;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public synchronized void check(int currentAmountOfContainers, int maxAmountOfContainers) {
        if (currentAmountOfContainers > maxAmountOfContainers-100) {
            port.setCurrentAmountOfContainers(currentAmountOfContainers-500);
            System.out.print("500 containers were exported");
        }
        if (currentAmountOfContainers < 100) {
            port.setCurrentAmountOfContainers(currentAmountOfContainers+500);
            System.out.println("500 containers were imported");
        }
        System.out.println("Port has " + port.getCurrentAmountOfContainers()+"/"+maxAmountOfContainers + " containers");
        notifyAll();
    }

    public synchronized void loadContainersOnShip() throws InterruptedException {
        while (port.getCurrentAmountOfContainers() < 100) {
            System.out.println("WARNING! Port is empty. Waiting for importing... ");
            wait();
        }
        currentShipInTheDock.setCurrentAmountOfContainers(currentShipInTheDock.getCurrentAmountOfContainers()+100);
        port.setCurrentAmountOfContainers(port.getCurrentAmountOfContainers()-100);
        System.out.print(currentShipInTheDock.getName() + " has " +currentShipInTheDock.getCurrentAmountOfContainers()
                + "/" + currentShipInTheDock.getMaxAmountOfContainers() + " containers. ");
        System.out.println("Port has " +
                port.getCurrentAmountOfContainers()+"/"+port.getMaxAmountOfContainers() + " containers");
        Thread.sleep(2);//1000
    }

    public synchronized void unloadContainersFromShip() throws InterruptedException {
        while (port.getCurrentAmountOfContainers() > port.getMaxAmountOfContainers()-100) {
            System.out.println("WARNING! Port is full. Waiting for exporting... ");
            wait();
        }
        currentShipInTheDock.setCurrentAmountOfContainers(currentShipInTheDock.getCurrentAmountOfContainers()-100);
        port.setCurrentAmountOfContainers(port.getCurrentAmountOfContainers()+100);
        System.out.print(currentShipInTheDock.getName() + " has " +currentShipInTheDock.getCurrentAmountOfContainers()
                + "/" + currentShipInTheDock.getMaxAmountOfContainers() + " containers. ");
        System.out.println("Port has " +
                port.getCurrentAmountOfContainers()+"/"+port.getMaxAmountOfContainers() + " containers");
        Thread.sleep(5); //1000
    }
}
