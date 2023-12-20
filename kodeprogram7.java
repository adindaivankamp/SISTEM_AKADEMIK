import java.util.Scanner;

public class kodeprogram7{
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

                    if (jenisPengguna != null && isValidLogin(jenisPengguna, password, mahasiswaArray)) {
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
                        }

                        // Setelah memasukkan data mahasiswa, tampilkan submenu sesuai jenis pengguna
                        if (jenisPengguna.equals("Mahasiswa")) {
                            if (mahasiswaArray != null) {
                                Mahasiswa.tampilkanTranskripMahasiswa(mahasiswaArray);
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

    public static void tampilkanSubMenuDosen(Scanner input, Mahasiswa[] mahasiswaArray) {
        // Dosen memiliki submenu sendiri
        while (true) {
            System.out.println("1. Lihat Transkrip Mahasiswa");
            System.out.println("2. Input Nilai Mahasiswa");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.print("Pilih submenu (1/2/3): ");
            int submenuPilihan = input.nextInt();

            switch (submenuPilihan) {
                case 1:
                    Mahasiswa.tampilkanTranskripDosen(mahasiswaArray);
                    break;
                case 2:
                    // Implementasi input nilai oleh Dosen
                    for (Mahasiswa mahasiswa : mahasiswaArray) {
                        System.out.print("Masukkan nilai untuk mahasiswa " + mahasiswa.getNama() + ": ");
                        int nilai = input.nextInt();
                        mahasiswa.setNilai(nilai);
                    }
                    break;
                case 3:
                    return; // Kembali ke Menu Utama
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
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

    public static boolean isValidLogin(String jenisPengguna, String password, Mahasiswa[] mahasiswaArray) {
        boolean valid = false;
        if (mahasiswaArray != null) {
            for (Mahasiswa mahasiswa : mahasiswaArray) {
                if (mahasiswa.getNama().equals(jenisPengguna) && mahasiswa.isFirstLogin()) {
                    valid = password.equals(jenisPengguna + "123");
                    if (valid) {
                        mahasiswa.setFirstLogin(false);
                    }
                    break;
                }
            }
        }
        return valid;
    }
}

class Mahasiswa {
    private String nama;
    private int nilai;
    private String Transkrip;
    private boolean firstLogin;

    public Mahasiswa() {
        firstLogin = true;
    }

    public static void tampilkanTranskripMahasiswa(Mahasiswa[] mahasiswaArray) {
        System.out.println("Transkrip Mahasiswa: ");
        if (mahasiswaArray != null) {
            for (Mahasiswa mahasiswa : mahasiswaArray) {
                if (!mahasiswa.isFirstLogin()) {
                    System.out.println("Nama: " + mahasiswa.getNama() +
                            ", Nilai: " + mahasiswa.getNilaiAsString() +
                            ", Transkrip: " + mahasiswa.getTranskrip());
                }
            }
        } else {
            System.out.println("Belum ada data mahasiswa. Silakan masukkan data terlebih dahulu.");
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

    public void hitungTranskrip() {
        if (getRataRata() >= 80) {
            Transkrip = "A";
        } else if (getRataRata() >= 70) {
            Transkrip = "B";
        } else if (getRataRata() >= 60) {
            Transkrip = "C";
        } else {
            Transkrip = "D";
        }
    }

    public String getTranskrip() {
        hitungTranskrip();
        return Transkrip;
    }

    public double getRataRata() {
        return nilai;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public String getNilaiAsString() {
        return Integer.toString(nilai);
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }
}
