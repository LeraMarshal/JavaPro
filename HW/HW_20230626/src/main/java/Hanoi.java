public class Hanoi {
    public static void main(String[] args) {
        hanoi(3, 1, 2, 3);
    }

    public static void hanoi(int n, int source, int destination, int helper) {
        if (n == 0) {
            return;
        }
        hanoi(n - 1, source, helper, destination);
        System.out.println(source + "->" + destination);
        hanoi(n - 1, helper, destination, source);
    }
}
