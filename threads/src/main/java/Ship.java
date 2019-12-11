public class Ship extends Thread {
    private int currentAmountOfContainers, maxAmountOfContainers;
    private boolean needsLoading, isReady = false;
    private Dock currentDock;
    public Ship(String string, int currentAmountOfContainers, int maxAmountOfContainers) {
        super(string);
        this.currentAmountOfContainers = currentAmountOfContainers;
        this.maxAmountOfContainers = maxAmountOfContainers;
        needsLoading = currentAmountOfContainers < maxAmountOfContainers/2;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setCurrentDock(Dock currentDock) {
        this.currentDock = currentDock;
    }

    public int getMaxAmountOfContainers() {
        return maxAmountOfContainers;
    }

    public int getCurrentAmountOfContainers() {
        return currentAmountOfContainers;
    }

    public void setCurrentAmountOfContainers(int currentAmountOfContainers) {
        this.currentAmountOfContainers = currentAmountOfContainers;
    }

    @Override
    public void run() {
        try {
            if (needsLoading) {
                System.out.println("NEW SHIP IN THE DOCK. " + this.getName() + " has started loading");
                while (currentAmountOfContainers <= maxAmountOfContainers-100) currentDock.loadContainersOnShip();
                System.out.println("SUCCESS! " + this.getName() + " was loaded");
            } else {
                System.out.println("NEW SHIP IN THE DOCK. " + this.getName() + " has started unloading");
                while (currentAmountOfContainers >= 100) currentDock.unloadContainersFromShip();
                System.out.println("SUCCESS! " + this.getName() + " was unloaded");
            }
            isReady = true;
            currentDock.nextShip();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}