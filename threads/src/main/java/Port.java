public class Port {
    private int currentAmountOfContainers, maxAmountOfContainers;

    public Port(String name, int currentAmountOfContainers, int maxAmountOfContainers, DockThread[] docks) {
        this.currentAmountOfContainers = currentAmountOfContainers;
        this.maxAmountOfContainers = maxAmountOfContainers;
        for (DockThread dock : docks) {
            dock.setPort(this);
        }
    }

    public int getCurrentAmountOfContainers() {
        return currentAmountOfContainers;
    }

    public synchronized void load(int amountOfContainers) {
        while (currentAmountOfContainers >= maxAmountOfContainers) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentAmountOfContainers += amountOfContainers;
        notifyAll();
    }

    public synchronized void unload(int amountOfContainers) {
        while (currentAmountOfContainers <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentAmountOfContainers -= amountOfContainers;
        notifyAll();
    }

    public int getMaxAmountOfContainers() {
        return maxAmountOfContainers;
    }
}
