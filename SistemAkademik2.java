import java.util.Scanner;

public class SistemAkademik2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int jumlahSiswa, nilai, totalNilai = 0;
        double rataRata;

        String username, password;

        // Loop menu
        while (true) {
            System.out.println("Menu Siakad ");
            System.out.println("1. Login");
            System.out.println("2. Keluar");
            System.out.print("Pilih menu (1/2): ");
            int pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    // Fitur Login
                    System.out.print("Masukkan username: ");
                    username = input.next();
                    System.out.print("Masukkan password: ");
                    password = input.next();

                    String jenisPengguna = getJenisPengguna(username);

                    if (jenisPengguna != null && isValidLogin(jenisPengguna, password)) {
                        System.out.println("Login berhasil sebagai " + jenisPengguna + ".");

                        System.out.print("Masukkan jumlah siswa: ");
                        jumlahSiswa = input.nextInt();

                        if (jumlahSiswa <= 0) {
                            System.out.println("Jumlah siswa harus lebih dari 0.");
                        } else {
                            // Input nilai seluruh siswa
                            for (int i = 1; i <= jumlahSiswa; i++) {
                                System.out.print("Masukkan nilai siswa ke-" + i + ": ");
                                nilai = input.nextInt();
                                totalNilai += nilai;
                            }

                            rataRata = (double) totalNilai / jumlahSiswa;

                            System.out.println("Rata-rata nilai: " + rataRata);
                        }
                    } else {
                        System.out.println("Login gagal. Username atau password salah.");
                    }
                    break;
                case 2:
                    System.out.println("Terima kasih. Program selesai.");
                    System.exit(0); // Keluar dari program
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }

    private static String getJenisPengguna(String username) {
        if (username.endsWith("siswa")) {
            return "siswa";
        } else if (username.endsWith("guru")) {
            return "guru";
        } else {
            return null;
        }
    }

    private static boolean isValidLogin(String jenisPengguna, String password) {
        return password.equals(jenisPengguna + "123");
    }
}
