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

    @Override
    public void run() {
        while (true) {
            for (Dock dock : docks) dock.checkIfPortIsFullOrEmpty(currentAmountOfContainers, maxAmountOfContainers);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
