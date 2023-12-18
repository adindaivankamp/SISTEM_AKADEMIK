import java.util.Scanner;

public class SistemAkademik3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int jumlahMahasiswa;
        String username, password;

        // Menyimpan nilai mahasiswa
        Mahasiswa[] mahasiswaArray = null;

        // Fitur menu
        while (true) {
            System.out.println("||| MENU SIAKAD NILAI |||");
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

                        if (jenisPengguna.equals("Mahasiswa")) {
                            if (mahasiswaArray != null) {
                                tampilkanTranskripMahasiswa(mahasiswaArray);
                            } else {
                                System.out.println("Belum ada data mahasiswa dan nilai. Silakan masukkan data terlebih dahulu.");
                            }
                        } else {
                            System.out.print("Masukkan jumlah mahasiswa: ");
                            jumlahMahasiswa = input.nextInt();

                            if (jumlahMahasiswa <= 0) {
                                System.out.println("Jumlah mahasiswa harus lebih dari 0.");
                            } else {
                                mahasiswaArray = new Mahasiswa[jumlahMahasiswa];

                                for (int i = 0; i < jumlahMahasiswa; i++) {
                                    System.out.print("Masukkan nama mahasiswa ke-" + (i + 1) + ": ");
                                    String namaMahasiswa = input.next();
                                    System.out.print("Masukkan nilai mahasiswa ke-" + (i + 1) + ": ");
                                    int nilaiMahasiswa = input.nextInt();

                                    mahasiswaArray[i] = new Mahasiswa(namaMahasiswa, nilaiMahasiswa);
                                }

                                System.out.println("Data mahasiswa dan nilai telah disimpan.");
                            }
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

    public static void tampilkanTranskripDosen(Mahasiswa[] mahasiswaArray) {
        System.out.println("Transkrip Dosen: ");
        for (Mahasiswa mahasiswa : mahasiswaArray) {
            System.out.println("Nama: " + mahasiswa.getNama() + ", Nilai: " + mahasiswa.nilai + ", Transkrip: " + mahasiswa.transkrip);
        }
    }

    public static double hitungRataRata(int totalNilai, int jumlahSiswa) {
        return (double) totalNilai / jumlahSiswa;
    }

    static String getJenisPengguna(String username) {
        if (username.endsWith("Mahasiswa")) {
            return "Mahasiswa";
        } else if (username.endsWith("Dosen")) {
            return "Dosen";
        } else {
            return null;
        }
    }

    static boolean isValidLogin(String jenisPengguna, String password) {
        return password.equals(jenisPengguna + "123");
    }

    public static void tampilkanTranskripMahasiswa(Mahasiswa[] mahasiswaArray) {
        System.out.println("Transkrip Mahasiswa: ");
        for (Mahasiswa mahasiswa : mahasiswaArray) {
            System.out.println("Nama: " + mahasiswa.getNama() + ", Nilai: " + mahasiswa.nilai + ", Transkrip: " + mahasiswa.transkrip);
        }
    }


    
    static class Mahasiswa {
        String nama;
        int nilai;
        String transkrip;

        // Konstruktor untuk menginisialisasi objek Mahasiswa
        public Mahasiswa(String nama, int nilai) {
            this.nama = nama;
            this.nilai = nilai;
            hitungTranskrip();
        }

        // Metode untuk menghitung transkrip berdasarkan nilai
        public void hitungTranskrip() {
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

        public String getTranskrip() {
            return transkrip;
        }

        public String getNama() {
            return nama;
        }
    }
}