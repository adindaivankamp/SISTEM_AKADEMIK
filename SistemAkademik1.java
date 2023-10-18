import java.util.Scanner;
public class SistemAkademik1 {
    public static void main (String[]args){
        Scanner sc = new Scanner(System.in);
        int NilaiTugas, NilaiKuis, NilaiUTS, NilaiUAS;
        double RataRata;

        System.out.println("Masukkan nilai tugas");
        NilaiTugas = sc.nextInt();
        System.out.println("Masukkan nilai kuis");
        NilaiKuis = sc.nextInt();
        System.out.println("Masukkan nilai UTS");
        NilaiUTS = sc.nextInt();
        System.out.println("Masukkan nilai UAS");
        NilaiUAS = sc.nextInt();

        RataRata = (NilaiTugas + NilaiKuis + NilaiUTS + NilaiUAS) / 4;

        System.out.println("Rata - rata = " + RataRata);
        
    }
}
