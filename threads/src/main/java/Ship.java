public class Ship extends Thread {
    private int currentAmountOfContainers, maxAmountOfContainers;
    private boolean needsLoading;
    private Dock currentDock;

    public Ship(String string, int currentAmountOfContainers, int maxAmountOfContainers) {
        super(string);
        this.currentAmountOfContainers = currentAmountOfContainers;
        this.maxAmountOfContainers = maxAmountOfContainers;
        needsLoading = currentAmountOfContainers < maxAmountOfContainers/2;
    }

    public void setCurrentDock(Dock currentDock) {
        this.currentDock = currentDock;
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

    @Override
    public void run() {
        try {
            if (needsLoading) {
                System.out.printf("NEW SHIP IN THE DOCK. %s(%s/%s) has started loading\n",
                        this.getName(), currentAmountOfContainers, maxAmountOfContainers);
                while (currentAmountOfContainers <= maxAmountOfContainers-100) currentDock.loadContainersOnShip();
                System.out.println("SUCCESS! " + this.getName() + " was loaded");
            } else {
                System.out.printf("NEW SHIP IN THE DOCK. %s(%s/%s) has started unloading\n",
                        this.getName(), currentAmountOfContainers, maxAmountOfContainers);
                while (currentAmountOfContainers >= 100) currentDock.unloadContainersFromShip();
                System.out.println("SUCCESS! " + this.getName() + " was unloaded");
            }
            currentDock.nextShip();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}