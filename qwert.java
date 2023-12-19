import java.util.Scanner;

public class qwert {
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

                        System.out.print("Masukkan jumlah mahasiswa: ");
                        jumlahMahasiswa = input.nextInt();

                        // Inisialisasi array mahasiswa berdasarkan jumlah siswa yang dimasukkan
                        mahasiswaArray = new Mahasiswa[jumlahMahasiswa];

                        // Loop untuk memasukkan data mahasiswa
                        for (int i = 0; i < jumlahMahasiswa; i++) {
                            // Buat objek Mahasiswa
                            mahasiswaArray[i] = new Mahasiswa();

                            System.out.print("Masukkan nama mahasiswa ke-" + (i + 1) + ": ");
                            mahasiswaArray[i].setNama(input.next());

                            // Loop untuk memasukkan nilai dan mata kuliah
                            System.out.print("Masukkan jumlah mata kuliah: ");
                            int jumlahMataKuliah = input.nextInt();

                            mahasiswaArray[i].setNilai(new int[jumlahMataKuliah]);
                            mahasiswaArray[i].setMataKuliah(new String[jumlahMataKuliah]);

                            for (int j = 0; j < jumlahMataKuliah; j++) {
                                System.out.print("Masukkan mata kuliah ke-" + (j + 1) + ": ");
                                mahasiswaArray[i].getMataKuliah()[j] = input.next();

                                System.out.print("Masukkan nilai untuk mata kuliah " +
                                        mahasiswaArray[i].getMataKuliah()[j] + ": ");
                                mahasiswaArray[i].getNilai()[j] = input.nextInt();
                            }
                        }

                        // Setelah memasukkan data mahasiswa, tampilkan submenu sesuai jenis pengguna
                        if (jenisPengguna.equals("Mahasiswa")) {
                            if (mahasiswaArray != null) {
                                tampilkanTranskripMahasiswa(mahasiswaArray);
                            } else {
                                System.out.println("Belum ada data mahasiswa dan nilai. " +
                                        "Silakan masukkan data terlebih dahulu.");
                            }
                        } else if (jenisPengguna.equals("Dosen")) {
                            // Tampilkan submenu langsung setelah login sebagai Dosen
                            tampilkanSubMenuDosen(input, mahasiswaArray);
                        } else {
                            System.out.println("Login gagal. Username atau password salah.");
                        }
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

    public static void tampilkanSubMenuDosen(Scanner input, Mahasiswa[] mahasiswaArray) {
        // Dosen memiliki submenu sendiri
        while (true) {
            System.out.println("1. Lihat Transkrip Mahasiswa");
            System.out.println("2. Update Nilai Mahasiswa");
            System.out.println("3. Input Nilai Mahasiswa");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih submenu (1/2/3/4): ");
            int submenuPilihan = input.nextInt();

            switch (submenuPilihan) {
                case 1:
                    tampilkanTranskripDosen(mahasiswaArray);
                    break;
                case 2:
                    // Implementasi update nilai oleh Dosen
                    break;
                case 3:
                    // Implementasi input nilai oleh Dosen
                    break;
                case 4:
                    return; // Kembali ke Menu Utama
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }

    public static void tampilkanTranskripDosen(Mahasiswa[] mahasiswaArray) {
        System.out.println("Transkrip Dosen: ");
        if (mahasiswaArray != null) {
            for (Mahasiswa mahasiswa : mahasiswaArray) {
                System.out.println("Nama: " + mahasiswa.getNama() +
                        ", Nilai: " + mahasiswa.getRataRata() +
                        ", Transkrip: " + mahasiswa.getTranskrip());
            }
        } else {
            System.out.println("Belum ada data mahasiswa. Silakan masukkan data terlebih dahulu.");
        }
    }

    public static void tampilkanTranskripMahasiswa(Mahasiswa[] mahasiswaArray) {
        System.out.println("Transkrip Mahasiswa: ");
        if (mahasiswaArray != null) {
            for (Mahasiswa mahasiswa : mahasiswaArray) {
                System.out.println("Nama: " + mahasiswa.getNama() +
                        ", Nilai: " + mahasiswa.getNilaiAsString() +
                        ", Transkrip: " + mahasiswa.getTranskrip());
            }
        } else {
            System.out.println("Belum ada data mahasiswa. Silakan masukkan data terlebih dahulu.");
        }
    }

    public static String getJenisPengguna(String username) {
        if (username.endsWith("Mahasiswa")) {
            return "Mahasiswa";
        } else if (username.endsWith("Dosen")) {
            return "Dosen";
        } else {
            return null;
        }
    }

    public static boolean isValidLogin(String jenisPengguna, String password) {
        return password.equals(jenisPengguna + "123");
    }

    static class Mahasiswa {
        private String nama;
        private int[] nilai;
        private String[] mataKuliah;

        public double getRataRata() {
            if (nilai.length == 0) {
                return 0.0;
            }

            int totalNilai = 0;
            for (int n : nilai) {
                totalNilai += n;
            }

            return (double) totalNilai / nilai.length;
        }

        public String getTranskrip() {
            // Implementasi logika transkrip di sini
            // Anda dapat mengembalikan representasi string transkrip berdasarkan kebutuhan Anda
            return "Transkrip Mahasiswa";
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public int[] getNilai() {
            return nilai;
        }

        public void setNilai(int[] nilai) {
            this.nilai = nilai;
        }

        public String[] getMataKuliah() {
            return mataKuliah;
        }

        public void setMataKuliah(String[] mataKuliah) {
            this.mataKuliah = mataKuliah;
        }

        public String getNilaiAsString() {
            StringBuilder nilaiAsString = new StringBuilder("[");
            for (int i = 0; i < nilai.length; i++) {
                nilaiAsString.append(mataKuliah[i]).append(":").append(nilai[i]);
                if (i < nilai.length - 1) {
                    nilaiAsString.append(", ");
                }
            }
            nilaiAsString.append("]");
            return nilaiAsString.toString();
        }
    }
}