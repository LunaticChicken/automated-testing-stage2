public class DockThread extends Thread {
    private Port port;
    private Ship currentShipInTheDock;

    public DockThread() {
        if (Main.ships.peek() == null) return;
        currentShipInTheDock = Main.ships.poll();
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public Ship getCurrentShipInTheDock() {
        return currentShipInTheDock;
    }

    public void setCurrentShipInTheDock(Ship currentShipInTheDock) {
        this.currentShipInTheDock = currentShipInTheDock;
    }

    @Override
    public void run() {
        while (currentShipInTheDock != null) {
            System.out.printf("NEW SHIP IN THE DOCK. %s(%d/%d)\n", currentShipInTheDock.getName(),
                    currentShipInTheDock.getCurrentAmountOfContainers(), currentShipInTheDock.getMaxAmountOfContainers());
            if (currentShipInTheDock.isNeedsLoading()) {
                while (currentShipInTheDock.getCurrentAmountOfContainers() < currentShipInTheDock.getMaxAmountOfContainers()) {
                    port.unload(100);
                    currentShipInTheDock.load(100);
                    System.out.printf("%s was loaded with 100 containers (%d/%d). Port has %d/%d\n",
                            currentShipInTheDock.getName(),
                            currentShipInTheDock.getCurrentAmountOfContainers(),
                            currentShipInTheDock.getMaxAmountOfContainers(),
                            port.getCurrentAmountOfContainers(),
                            port.getMaxAmountOfContainers()
                    );
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                while (currentShipInTheDock.getCurrentAmountOfContainers() > 0) {
                    currentShipInTheDock.unload(100);
                    port.load(100);
                    System.out.printf("%s was unloaded with 100 containers (%d/%d). Port has %d/%d\n",
                            currentShipInTheDock.getName(),
                            currentShipInTheDock.getCurrentAmountOfContainers(),
                            currentShipInTheDock.getMaxAmountOfContainers(),
                            port.getCurrentAmountOfContainers(),
                            port.getMaxAmountOfContainers()
                    );
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Good! " + currentShipInTheDock.getName() + " is leaving the port");
            currentShipInTheDock = Main.ships.poll();
        }
    }
}