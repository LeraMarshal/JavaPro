package bankaccount;

/*
    Имеется счет BankAccount, на котором хранится определенная сумма денег int sum.
    Реализовать потокобезопасный метод пополнения счета - deposit (int amount),
    снятия со счета - withdraw (int amount) .
*/
public class BankAccount {
    private volatile int sum = 0; // Чтобы запретить кэширование

    public BankAccount(int sum) {
        this.sum = sum;
    }

    public synchronized void deposit(int amount) {
        sum = sum + amount;
    }

    public synchronized void withdraw(int amount) {
        sum = sum - amount;
    }

    public int getSum() {
        return sum;
    }
}
