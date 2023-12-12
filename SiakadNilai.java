import java.util.Scanner;

public class SiakadNilai {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int jumlahSiswa;
        String username, password;

        // Menyimpan siswa dan nilai
        Siswa[] siswaArray = null;

        // Loop menu
        while (true) {
            System.out.println("- MENU SIAKAD NILAI -");
            System.out.println("1. Login");
            System.out.println("2. Lihat Transkrip");
            System.out.println("3. Keluar");
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

                        if (jenisPengguna.equals("siswa")) {
                            // Siswa hanya bisa melihat transkrip
                            tampilkanTranskripSiswa();
                        } else {
                            // Guru bisa memasukkan nilai
                            System.out.print("Masukkan jumlah siswa: ");
                            jumlahSiswa = input.nextInt();

                            if (jumlahSiswa <= 0) {
                                System.out.println("Jumlah siswa harus lebih dari 0.");
                            } else {
                                siswaArray = new Siswa[jumlahSiswa];

                                // Memasukkan nilai dan menyimpan siswa
                                for (int i = 0; i < jumlahSiswa; i++) {
                                    System.out.print("Masukkan nama siswa ke-" + (i + 1) + ": ");
                                    String namaSiswa = input.next();
                                    System.out.print("Masukkan nilai siswa ke-" + (i + 1) + ": ");
                                    int nilaiSiswa = input.nextInt();

                                    siswaArray[i] = new Siswa(namaSiswa, nilaiSiswa);
                                }

                                System.out.println("Data siswa dan nilai telah disimpan.");
                            }
                        }
                    } else {
                        System.out.println("Login gagal. Username atau password salah.");
                    }
                    break;

                case 2:
                    // Fitur Lihat Transkrip untuk guru
                    if (siswaArray != null) {
                        tampilkanTranskripGuru(siswaArray);
                    } else {
                        System.out.println("Belum ada data siswa dan nilai. Silakan masukkan data terlebih dahulu.");
                    }
                    break;

                case 3:
                    System.out.println("Terima kasih. Program selesai.");
                    System.exit(0); // Keluar dari program
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }

    // Fungsi untuk menampilkan transkrip berdasarkan rata-rata nilai
    public static void tampilkanTranskripGuru(Siswa[] siswaArray) {
        System.out.println("Transkrip Guru: ");
        for (Siswa siswa : siswaArray) {
            System.out.println("Nama: " + siswa.nama + ", Nilai: " + siswa.nilai + ", Transkrip: " + siswa.transkrip);
        }
    }

    // Fungsi untuk menampilkan transkrip siswa
    public static void tampilkanTranskripSiswa() {
        System.out.println("Transkrip Siswa: Siswa tidak memiliki nilai saat ini.");
    }

    // Fungsi untuk menghitung rata-rata nilai
    public static double hitungRataRata(int totalNilai, int jumlahSiswa) {
        return (double) totalNilai / jumlahSiswa;
    }

    // Fungsi untuk mendapatkan jenis pengguna berdasarkan username
    static String getJenisPengguna(String username) {
        if (username.endsWith("siswa")) {
            return "siswa";
        } else if (username.endsWith("guru")) {
            return "guru";
        } else {
            return null;
        }
    }

    // Fungsi untuk memvalidasi login
    static boolean isValidLogin(String jenisPengguna, String password) {
        return password.equals(jenisPengguna + "123");
    }
}

class Siswa {
    String nama;
    int nilai;
    String transkrip;

    // Konstruktor untuk menginisialisasi objek Siswa
    public Siswa(String nama, int nilai) {
        this.nama = nama;
        this.nilai = nilai;
        hitungTranskrip(); // Hitung transkrip setiap kali objek Siswa dibuat
    }

    // Metode untuk menghitung transkrip berdasarkan nilai
    private void hitungTranskrip() {
        if (nilai >= 80) {
            transkrip = "A";
        } else if (nilai >= 70) {
            transkrip = "B";
        } else if (nilai >= 60) {
            transkrip = "C";
        } else {
            transkrip = "D";
        }
    }

    // Metode untuk mendapatkan transkrip
    public String getTranskrip() {
        return transkrip;
    }
}