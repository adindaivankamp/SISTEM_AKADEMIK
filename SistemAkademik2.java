
import java.util.Scanner;

public class SistemAkademik2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int jumlahSiswa, totalNilai = 0;
        double rataRata;

        String username, password;

        // Declare nilai array
        int[][] nilai = null;

        // Loop menu
        while (true) {
            System.out.println("Menu Siakad ");
            System.out.println("1. Login");
            System.out.println("2. Daftar Nilai");
            System.out.println("3. Exit");

            System.out.print("Pilih menu (1/2/3): ");
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
                            // Initialize nilai array
                            nilai = new int[jumlahSiswa][];

                            // Input nilai seluruh siswa
                            for (int i = 0; i < jumlahSiswa; i++) {
                                System.out.print("Masukkan jumlah nilai siswa ke-" + (i + 1) + ": ");
                                int jumlahNilai = input.nextInt();

                                // Initialize array for each student
                                nilai[i] = new int[jumlahNilai];

                                // Input nilai for each student
                                for (int j = 0; j < jumlahNilai; j++) {
                                    System.out.print("Masukkan nilai ke-" + (j + 1) + ": ");
                                    nilai[i][j] = input.nextInt();
                                    totalNilai += nilai[i][j];
                                }
                            }

                            rataRata = (double) totalNilai / jumlahSiswa;
                            System.out.println("Total nilai: " + totalNilai);
                            System.out.println("Rata-rata nilai: " + rataRata);
                        }
                    } else {
                        System.out.println("Login gagal. Username atau password salah.");
                    }
                    break;

                case 2:
                    // Daftar Nilai
                    System.out.println("Daftar Nilai:");
                    if (nilai != null) {
                        for (int i = 0; i < nilai.length; i++) {
                            for (int j = 0; j < nilai[i].length; j++) {
                                System.out.println("Siswa " + (i + 1) + ", Nilai " + (j + 1) + ": " + nilai[i][j]);
                            }
                        }
                    } else {
                        System.out.println("Belum ada nilai yang dimasukkan.");
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

    static String getJenisPengguna(String username) {
        if (username.endsWith("siswa")) {
            return "siswa";
        } else if (username.endsWith("guru")) {
            return "guru";
        } else {
            return null;
        }
    }

    static boolean isValidLogin(String jenisPengguna, String password) {
        return password.equals(jenisPengguna + "123");
    }
}

