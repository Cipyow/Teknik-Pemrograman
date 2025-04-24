public class Transaction<T, U> {
    private T id;
    private U amount;

    public Transaction(T id, U amount) {
        this.id = id;
        this.amount = amount;
    }

    public T getId() {
        return id;
    }

    public U getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", amount= $" + amount + '}';
    }
}
