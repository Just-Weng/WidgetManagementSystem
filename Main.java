import java.util.*;
import java.io.*;

/**
 * Main program reads the input file through a Scanner object then using a File Writer object, writes to an output txt file
 * The program parses each input file line into instruction sets of Receive (R) or Sell (S) and diverges into separate instructions
 * for the receive instructions, the program checks waitlist and if an item in the waitlist exists, removes from the quantity the waitlist item quantity and fulfills the order, before adding back into the inventory queue
 * for the sell instructions, the program checks the inventory, and backlogs the item into the waitlist if there is remaining quantity that is yet to be fulfilled, it also includes a check for if you are selling for $0
 */
public class Main {

    public static void main(String[] args) {

        try {
            int quantity, initialQuantity;
            double toSellPrice = 0, recentCost = 0, initialCost = 0;
            QueueArray waitlist = new QueueArray();
            StackArray inventory = new StackArray();
            Scanner input = new Scanner(new File("Proj _1_Transactions.txt"));
            FileWriter fw = new FileWriter("Output.txt");

            while (input.hasNextLine()) {
                double runningTotal = 0;
                String[] instructions = input.nextLine().split(" ");
                quantity = Integer.parseInt(instructions[1]);
                initialQuantity = quantity;

                if (instructions[0].equalsIgnoreCase("R")) {
                    double cost = Double.parseDouble(instructions[2]);
                    Widget in = new Widget(quantity, cost);

                    while (!waitlist.isEmpty() && quantity > 0) {
                        Widget backlog = waitlist.dequeue();
                        int toFulfill = Math.min(quantity, backlog.getQuantity());
                        double fulfillPrice = recentCost * 1.25;
                        double total = toFulfill * fulfillPrice;

                        fw.write("Filled backorder: " + toFulfill + " widgets @ $" + String.format("%.2f", fulfillPrice) +
                                " Price: $" + String.format("%.2f", total) + "\n");

                        quantity -= toFulfill;
                        backlog.setQuantity(backlog.getQuantity() - toFulfill);

                        if (backlog.getQuantity() > 0)
                            waitlist.enqueue(backlog);

                        in.setQuantity(quantity);
                    }

                    if (quantity > 0) {
                        inventory.push(in);
                        fw.write("Received " + quantity + " widgets @ $" + String.format("%.2f", cost) +
                                " Cost: $" + String.format("%.2f", quantity * cost) + "\n");
                    }

                    recentCost = cost;

                } else if (instructions[0].equalsIgnoreCase("S")) {

                    while (!inventory.isEmpty() && quantity > 0) {
                        Widget out = inventory.peek();
                        int inventoryQuantity = out.getQuantity();

                        if (initialCost == 0) {
                            initialCost = recentCost;
                            toSellPrice = initialCost * 1.25;
                        }

                        int sellAmt = Math.min(quantity, inventoryQuantity);

                        runningTotal += sellAmt * toSellPrice;
                        quantity -= sellAmt;

                        if (inventoryQuantity <= sellAmt)
                            inventory.pop();
                        else
                            out.setQuantity(inventoryQuantity - sellAmt);
                    }

                    initialCost = 0;

                    if (initialQuantity - quantity > 0) {
                        fw.write("Sold " + (initialQuantity - quantity) + " widgets @ $" + String.format("%.2f", toSellPrice) +
                                " Price: $" + String.format("%.2f", runningTotal) + "\n");
                    }

                    if (quantity > 0) {
                        waitlist.enqueue(new Widget(quantity, toSellPrice));
                        fw.write("Backorder: " + quantity + " widgets @ $" + String.format("%.2f", toSellPrice) + "\n");
                    }

                    if (toSellPrice == 0)
                        fw.write("Cant sell for free, will go bankrupt\n");
                }
            }

            input.close();
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
