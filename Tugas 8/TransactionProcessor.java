import java.util.*;

public class TransactionProcessor<T extends Transaction<?, Double>> implements Processor<T> {
    private List<T> transactions = new ArrayList<>();

    @Override
    public void process(T item) {
        transactions.add(item);
        System.out.println("Processed: " + item);
    }

    public double calculateTotal() {
        double total = 0;
        for (T t : transactions) {
            total += t.getAmount();
        }
        return total;
    }

    public static void printTransactionDetails(List<? extends Transaction<?, ?>> list) {
        for (Transaction<?, ?> t : list) {
            System.out.println(t);
        }
    }
}
