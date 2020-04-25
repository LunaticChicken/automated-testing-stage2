public class Ship {
    private String name;
    private int currentAmountOfContainers, maxAmountOfContainers;
    private boolean needsLoading;

    public Ship(String name, int currentAmountOfContainers, int maxAmountOfContainers) {
        this.name = name;
        this.currentAmountOfContainers = currentAmountOfContainers;
        this.maxAmountOfContainers = maxAmountOfContainers;
        needsLoading = currentAmountOfContainers < maxAmountOfContainers/2;
    }

    public void load(int amountOfContainers) {
        currentAmountOfContainers += amountOfContainers;
    }

    public void unload(int amountOfContainers) {
        currentAmountOfContainers -= amountOfContainers;
    }

    public String getName() {
        return name;
    }

    public int getCurrentAmountOfContainers() {
        return currentAmountOfContainers;
    }

    public int getMaxAmountOfContainers() {
        return maxAmountOfContainers;
    }

    public boolean isNeedsLoading() {
        return needsLoading;
    }
}