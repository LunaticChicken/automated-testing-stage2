public class Port extends Thread {
    private int currentAmountOfContainers, maxAmountOfContainers;
    private Dock[] docks;
    public Port(String string, int currentAmountOfContainers, int maxAmountOfContainers, Dock[] docks) {
        super(string);
        this.currentAmountOfContainers = currentAmountOfContainers;
        this.maxAmountOfContainers = maxAmountOfContainers;
        this.docks = docks;
        for (Dock dock : docks) {
            dock.setPort(this);
        }
    }

    public int getCurrentAmountOfContainers() {
        return currentAmountOfContainers;
    }

    public void setCurrentAmountOfContainers(int currentAmountOfContainers) {
        this.currentAmountOfContainers = currentAmountOfContainers;
    }

    public int getMaxAmountOfContainers() {
        return maxAmountOfContainers;
    }

    private boolean atLeastOneShipIsNotReady(Dock[] docks) {
        for (Dock dock : docks) {
            if (!dock.getCurrentShipInTheDock().isReady()) return true;
        }
        return false;
    }

    @Override
    public void run() {
        while (atLeastOneShipIsNotReady(docks)) {
            for (Dock dock : docks) dock.check(currentAmountOfContainers, maxAmountOfContainers);
            try {
                Thread.sleep(500); //5000
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
