public class Widget {
    private int quantity;
    private double cost;

    public Widget(int quantity, double cost) {
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getQuantity() {return this.quantity;}
    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
    public double getCost() {return this.cost;}
    public void setCost(double newCost) {
        this.cost = newCost;
    }

    public String toString() {
        return getQuantity() + "widgets @ " + getCost();
    }
}
