public class kodeprogram7 {

    public static void main(String[] args) {
        int nilai = 4, nilai2 = 3;
        System.out.println(pangkat(nilai, nilai2));

    }

    static int pangkat(int m, int n) {
        if (n == 1) {
            return m;
        } else {
            return m * pangkat(m, n - 1);
        }
    }
}
