import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Bank Transactions
        TransactionProcessor<BankTransaction> bankProcessor = new TransactionProcessor<>();
        bankProcessor.process(new BankTransaction("TXB-001", 1500.0));
        bankProcessor.process(new BankTransaction("TXB-002", 3200.0));

        // E-Commerce Transactions
        TransactionProcessor<ECommerceTransaction> ecommerceProcessor = new TransactionProcessor<>();
        ecommerceProcessor.process(new ECommerceTransaction(101, 250.0));
        ecommerceProcessor.process(new ECommerceTransaction(102, 499.99));

        System.out.println("Total Bank: $" + bankProcessor.calculateTotal());
        System.out.println("Total E-Commerce: $" + ecommerceProcessor.calculateTotal());

        // Print all transaction details
        List<Transaction<?, ?>> allTransactions = new ArrayList<>();
        allTransactions.add(new BankTransaction("TXB-003", 1200.0));
        allTransactions.add(new ECommerceTransaction(103, 189.75));

        System.out.println("\n--- All Transaction Details ---");
        TransactionProcessor.printTransactionDetails(allTransactions);
    }
}
