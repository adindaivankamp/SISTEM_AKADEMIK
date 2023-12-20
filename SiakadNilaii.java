import java.util.Scanner;

public class Bismillah {
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

                        // Setelah berhasil login tampilkan submenu sesuai jenis pengguna
                        if (jenisPengguna.equals("Mahasiswa"))
                            tampilkanSubMenuMahasiswa(input, mahasiswaArray);
                        else if (jenisPengguna.equals("Dosen"))
                            tampilkanSubMenuDosen(input, mahasiswaArray);
                        else
                            System.out.println("Login gagal. Username atau password salah.");
                    }
                    break;

                case 2:
                    System.out.println("Terima kasih. Program selesai.");
                    System.exit(0); // Keluar dari program
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                    break;
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

    public static boolean isValidLogin(String jenisPengguna, String password) {
        return password.equals(jenisPengguna + "123");
    }

    public static void tampilkanSubMenuDosen(Scanner input, Mahasiswa[] mahasiswaArray) {
        while (true) {
            System.out.println("1. Input Nilai dan Mahasiswa");
            System.out.println("2. Update Nilai Mahasiswa");
            System.out.println("3. Lihat Transkrip Nilai dan Rata-rata Mahasiswa");
            System.out.println("4. Pencarian");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih submenu (1/2/3/4/5): ");
            int submenuPilihan = input.nextInt();

            switch (submenuPilihan) {
                case 1:
                    inputMahasiswa(input, mahasiswaArray);
                    break;
                case 2:
                    updateNilaiMahasiswa(input, mahasiswaArray);
                    break;
                case 3:
                    tampilkanTranskripDosen(mahasiswaArray);
                    break;
                case 4:
                    cariMahasiswa(mahasiswaArray);
                    break;
                case 5:
                    return; // kembali ke menu utama
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                    break;
            }
        }
    }

    public static void inputMahasiswa(Scanner input, Mahasiswa[] mahasiswaArray) {
        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlahMahasiswa = input.nextInt();

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
    }

    public static void updateNilaiMahasiswa(Scanner input, Mahasiswa[] mahasiswaArray) {
        System.out.print("Masukkan nama mahasiswa yang akan diupdate nilai: ");
        String targetNama = input.next();

        Mahasiswa targetMahasiswa = null;
        for (Mahasiswa mahasiswa : mahasiswaArray) {
            if (mahasiswa.getNama().equals(targetNama)) {
                targetMahasiswa = mahasiswa;
                break;
            }
        }

        if (targetMahasiswa != null) {
            System.out.println("Data Mahasiswa yang akan diupdate:");
            System.out.println("Nama: " + targetMahasiswa.getNama());
            System.out.println("Nilai: " + targetMahasiswa.getNilaiAsString());

            System.out.print("Masukkan mata kuliah yang akan diupdate nilai: ");
            String targetMataKuliah = input.next();

            for (int i = 0; i < targetMahasiswa.getMataKuliah().length; i++) {
                if (targetMahasiswa.getMataKuliah()[i].equals(targetMataKuliah)) {
                    System.out.print("Masukkan nilai baru untuk mata kuliah " + targetMataKuliah + ": ");
                    targetMahasiswa.getNilai()[i] = input.nextInt();
                    System.out.println("Nilai berhasil diupdate.");
                    return;
                }
            }

            System.out.println("Mata kuliah tidak ditemukan.");
        } else {
            System.out.println("Mahasiswa tidak ditemukan.");
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

    public static void cariMahasiswa(Mahasiswa[] arrayMahasiswa) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama mahasiswa yang ingin dicari: ");
        String namaCari = input.next().toLowerCase();

        boolean ditemukan = false;

        for (Mahasiswa mahasiswa : arrayMahasiswa) {
            if (mahasiswa.getNama().toLowerCase().contains(namaCari)) {
                System.out.println("Mahasiswa ditemukan:");
                System.out.println("Nama: " + mahasiswa.getNama() + ", Nilai: " + mahasiswa.getRataRata() + ", Transkrip: " + mahasiswa.getTranskrip());
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Mahasiswa dengan nama '" + namaCari + "' tidak ditemukan.");
        }
    }
}

class Mahasiswa {
    private String nama;
    private int[] nilai;
    private String[] mataKuliah;

    // ... (tambahkan konstruktor, getter, setter, dan metode lainnya sesuai kebutuhan)

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

    public void hitungTranskrip() {
        // implementasi logika perhitungan transkrip berdasarkan nilai
    }

    public String getTranskrip() {
        // implementasi mendapatkan transkrip berdasarkan nilai
        return "";
    }

    // ... (tambahkan metode getter, setter, dan lainnya sesuai kebutuhan)
}