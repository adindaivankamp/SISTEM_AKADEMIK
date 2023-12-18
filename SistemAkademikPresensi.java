import java.util.Scanner;

public class SistemAkademikPresensi {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Daftar mahasiswa dan status presensi
        String[] mahasiswa = {"Mahasiswa1", "Mahasiswa2", "Mahasiswa3", "Mahasiswa4", "Mahasiswa5"};
        boolean[] presensi = new boolean[mahasiswa.length];

        // Loop menu presensi
        while (true) {
            System.out.println("Menu Presensi ");
            System.out.println("1. Lihat Daftar Mahasiswa");
            System.out.println("2. Presensi Mahasiswa");
            System.out.println("3. Exit");

            System.out.print("Pilih menu (1/2/3): ");
            int pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    // Lihat daftar mahasiswa
                    System.out.println("Daftar Mahasiswa:");
                    for (int i = 0; i < mahasiswa.length; i++) {
                        System.out.println((i + 1) + ". " + mahasiswa[i] + " - Presensi: " + (presensi[i] ? "Hadir" : "Tidak Hadir"));
                    }
                    break;

                case 2:
                    // Presensi Mahasiswa
                    System.out.print("Masukkan nomor mahasiswa yang hadir (1-" + mahasiswa.length + "): ");
                    int nomorMahasiswa = input.nextInt();

                    if (nomorMahasiswa >= 1 && nomorMahasiswa <= mahasiswa.length) {
                        // Mengatur presensi mahasiswa yang dipilih
                        presensi[nomorMahasiswa - 1] = true;
                        System.out.println(mahasiswa[nomorMahasiswa - 1] + " berhasil diproses presensinya.");
                    } else {
                        System.out.println("Nomor mahasiswa tidak valid.");
                    }
                    break;

                case 3:
                    System.out.println("Terima kasih. Program selesai.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }
}
