import java.util.Scanner;
public class SistemAkademik2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int jumlahSiswa, nilai, totalNilai = 0;
        double rataRata;

        System.out.print("Masukkan jumlah siswa: ");
        jumlahSiswa = input.nextInt();

        if (jumlahSiswa <= 0) {
            System.out.println("Jumlah siswa harus lebih dari 0."); } 
        else {
            int i = 0; {
                
   System.out.print("Masukkan total nilai seluruh siswa : ");
        totalNilai = input.nextInt();
}

        rataRata = (double) totalNilai / jumlahSiswa;

        System.out.println("Rata-rata nilai: " + rataRata);
        }
    }
}
