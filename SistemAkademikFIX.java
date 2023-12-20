import java.util.Random;
import java.util.Scanner;

public class SistemAkademikFIX {

    static final String ADMIN_USERNAME = "admin";
    static final String ADMIN_PASSWORD = "12345";
    static final int MAX_MAHASISWA = 100; 
    static final int MAX_DOSEN = 10;
    
    static String[] mahasiswaNama = new String[MAX_MAHASISWA];
    static String[] mahasiswaPassword = new String[MAX_MAHASISWA];
    static int[] mahasiswaNIM = new int[MAX_MAHASISWA];
    static int[][] nilaiMahasiswa = new int[MAX_MAHASISWA][4];
    static boolean[][] kehadiranMahasiswa = new boolean[MAX_MAHASISWA][4];

    static String[] dosenNama = new String[MAX_DOSEN];
    static String[] dosenPassword = new String[MAX_DOSEN];
    static int[][] nilaiDosen = new int[MAX_DOSEN][4];

    static int jumlahMahasiswa = 0;
    static int jumlahDosen = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("||| MENU SISTEM AKADEMIK |||");
            System.out.println("1. Admin");
            System.out.println("2. Dosen");
            System.out.println("3. Mahasiswa");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1/2/3/4): ");
            int pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    loginAdmin(input);
                    break;
                case 2:
                    loginDosen(input);
                    break;
                case 3:
                    loginMahasiswa(input);
                    break;
                case 4:
                    System.out.println("Terima kasih. Program selesai.");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }

    private static void loginAdmin(Scanner input) {
        System.out.print("Masukkan username (admin): ");
        String username = input.next();
        System.out.print("Masukkan password (12345): ");
        String password = input.next();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            menuAdmin(input);
        } else {
            System.out.println("Login gagal. Username atau password salah.");
        }
    }

    private static void menuAdmin(Scanner input) {
        while (true) {
            System.out.println("||| MENU ADMIN |||");
            System.out.println("1. Tambah Data Mahasiswa");
            System.out.println("2. Tambah Data Dosen");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.print("Pilih menu (1/2/3): ");
            int adminPilihan = input.nextInt();

            switch (adminPilihan) {
                case 1:
                    tambahDataMahasiswa(input);
                    break;
                case 2:
                    tambahDataDosen(input);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }

    private static void tambahDataMahasiswa(Scanner input) {
        Random random = new Random();

        System.out.print("Masukkan nama mahasiswa: ");
        String nama = input.next();

        // Membuat NIM secara acak (6 digit)
        int nim = 100000 + random.nextInt(900000);

        System.out.print("Masukkan password mahasiswa: ");
        String password = input.next();

        mahasiswaNama[jumlahMahasiswa] = nama;
        mahasiswaPassword[jumlahMahasiswa] = password;
        mahasiswaNIM[jumlahMahasiswa] = nim;

        jumlahMahasiswa++;

        System.out.println("Mahasiswa berhasil ditambahkan:");
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
        System.out.println("Password: " + password);
    }


    private static void tambahDataDosen(Scanner input) {
        if (jumlahDosen < MAX_DOSEN) {
            System.out.print("Masukkan nama dosen: ");
            dosenNama[jumlahDosen] = input.next();
            System.out.print("Masukkan password dosen: ");
            dosenPassword[jumlahDosen] = input.next();
            System.out.println("Data dosen berhasil ditambahkan.");
            jumlahDosen++;
        } else {
            System.out.println("Kuota dosen sudah penuh.");
        }
    }

    private static void loginDosen(Scanner input) {
        System.out.print("Masukkan nama dosen: ");
        String namaDosen = input.next();
        System.out.print("Masukkan password dosen: ");
        String passwordDosen = input.next();

        int indexDosen = findDosen(namaDosen, passwordDosen);

        if (indexDosen != -1) {
            menuDosen(input, indexDosen);
        } else {
            System.out.println("Login gagal. Nama atau password dosen salah.");
        }
    }

    private static int findDosen(String namaDosen, String passwordDosen) {
        for (int i = 0; i < jumlahDosen; i++) {
            if (dosenNama[i].equals(namaDosen) && dosenPassword[i].equals(passwordDosen)) {
                return i;
            }
        }
        return -1; // Dosen tidak ditemukan
    }

    private static void menuDosen(Scanner input, int indexDosen) {
        while (true) {
            System.out.println("||| MENU DOSEN |||");
            System.out.println("1. Input Nilai Mahasiswa");
            System.out.println("2. Update Nilai Mahasiswa");
            System.out.println("3. Laporan Rata-rata Nilai per Mata Kuliah");
            System.out.println("4. Tampilkan Transkrip Rata-rata Mahasiswa");
            System.out.println("5. Input Kehadiran Mahasiswa");
            System.out.println("6. Laporan Kehadiran Mahasiswa");
            System.out.println("7. Kembali ke Menu Utama");
            System.out.print("Pilih menu (1/2/3/4/5/6/7): ");
            int dosenPilihan = input.nextInt();

            switch (dosenPilihan) {
                case 1:
                    inputNilaiMahasiswa(input, indexDosen);
                    break;
                case 2:
                    updateNilaiMahasiswa(input, indexDosen);
                    break;
                case 3:
                    laporanRataRataNilai(indexDosen);
                    break;
                case 4:
                    tampilkanTranskripRataRata(indexDosen);
                    break;
                case 5:
                    inputKehadiranMahasiswa(input, indexDosen);
                    break;
                case 6:
                    laporanKehadiranMahasiswa(indexDosen);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }

    private static void inputNilaiMahasiswa(Scanner input, int indexDosen) {
        System.out.print("Masukkan NIM mahasiswa: ");
        int nim = input.nextInt();
        int indexMahasiswa = findMahasiswaByNIM(nim);

        if (indexMahasiswa != -1) {
            for (int i = 0; i < nilaiDosen[indexDosen].length; i++) {
                System.out.print("Masukkan nilai untuk mata kuliah ke-" + (i + 1) + ": ");
                nilaiMahasiswa[indexMahasiswa][i] = input.nextInt();
            }
            System.out.println("Nilai mahasiswa berhasil diinput.");
        } else {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }

    private static void updateNilaiMahasiswa(Scanner input, int indexDosen) {
        System.out.print("Masukkan NIM mahasiswa: ");
        int nim = input.nextInt();
        int indexMahasiswa = findMahasiswaByNIM(nim);

        if (indexMahasiswa != -1) {
            for (int i = 0; i < nilaiDosen[indexDosen].length; i++) {
                System.out.print("Masukkan nilai baru untuk mata kuliah ke-" + (i + 1) + ": ");
                nilaiMahasiswa[indexMahasiswa][i] = input.nextInt();
            }
            System.out.println("Nilai mahasiswa berhasil diupdate.");
        } else {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }

    private static void laporanRataRataNilai(int indexDosen) {
        for (int i = 0; i < nilaiDosen[indexDosen].length; i++) {
            int totalNilai = 0;
            int jumlahMahasiswaDalamMataKuliah = 0;
    
            for (int j = 0; j < jumlahMahasiswa; j++) {
                if (nilaiMahasiswa[j][i] != 0) { 
                    totalNilai += nilaiMahasiswa[j][i];
                    jumlahMahasiswaDalamMataKuliah++;
                }
            }
    
            if (jumlahMahasiswaDalamMataKuliah > 0) {
                double rataRata = (double) totalNilai / jumlahMahasiswaDalamMataKuliah;
                System.out.println("Rata-rata nilai Mata Kuliah " + (i + 1) + ": " + rataRata);
            } else {
                System.out.println("Belum ada nilai untuk Mata Kuliah " + (i + 1));
            }
        }
    }
    private static void tampilkanTranskripRataRata(int indexDosen) {
        for (int i = 0; i < jumlahMahasiswa; i++) {
            if (nilaiMahasiswa[i][0] != 0 && nilaiMahasiswa[i][1] != 0 && nilaiMahasiswa[i][2] != 0 && nilaiMahasiswa[i][3] != 0) {
                double rataRata = (double) (nilaiMahasiswa[i][0] + nilaiMahasiswa[i][1] + nilaiMahasiswa[i][2] + nilaiMahasiswa[i][3]) / 4;
                System.out.println("Transkrip Mahasiswa " + mahasiswaNama[i] + ": " + getGrade(rataRata));
            } else {
                System.out.println("Belum ada nilai untuk Mahasiswa " + mahasiswaNama[i]);
            }
        }
    }

    private static void inputKehadiranMahasiswa(Scanner input, int indexDosen) {
        System.out.print("Masukkan NIM mahasiswa: ");
        int nim = input.nextInt();
        int indexMahasiswa = findMahasiswaByNIM(nim);

        if (indexMahasiswa != -1) {
            for (int i = 0; i < kehadiranMahasiswa[indexMahasiswa].length; i++) {
                System.out.print("Masukkan kehadiran untuk mata kuliah ke-" + (i + 1) + " (true/false): ");
                kehadiranMahasiswa[indexMahasiswa][i] = input.nextBoolean();
            }
            System.out.println("Kehadiran mahasiswa berhasil diinput.");
        } else {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }

    private static void laporanKehadiranMahasiswa(int indexDosen) {
        for (int i = 0; i < jumlahMahasiswa; i++) {
            System.out.println("Kehadiran Mahasiswa " + mahasiswaNama[i] + ":");
            for (int j = 0; j < kehadiranMahasiswa[i].length; j++) {
                System.out.println("Mata Kuliah " + (j + 1) + ": " + (kehadiranMahasiswa[i][j] ? "Hadir" : "Tidak Hadir"));
            }
        }
    }

    private static void loginMahasiswa(Scanner input) {
        System.out.print("Masukkan nama mahasiswa: ");
        String namaMahasiswa = input.next();
        System.out.print("Masukkan password mahasiswa: ");
        String passwordMahasiswa = input.next();

        int indexMahasiswa = findMahasiswa(namaMahasiswa, passwordMahasiswa);

        if (indexMahasiswa != -1) {
            menuMahasiswa(input, indexMahasiswa);
        } else {
            System.out.println("Login gagal. Nama atau password mahasiswa salah.");
        }
    }

    private static int findMahasiswa(String namaMahasiswa, String passwordMahasiswa) {
        for (int i = 0; i < jumlahMahasiswa; i++) {
            if (mahasiswaNama[i].equals(namaMahasiswa) && mahasiswaPassword[i].equals(passwordMahasiswa)) {
                return i;
            }
        }
        return -1; // Mahasiswa tidak ditemukan
    }

    private static void menuMahasiswa(Scanner input, int indexMahasiswa) {
        while (true) {
            System.out.println("||| MENU MAHASISWA |||");
            System.out.println("1. Pencarian Mahasiswa");
            System.out.println("2. Lihat Nilai dan Rata-rata");
            System.out.println("3. Lihat Kehadiran");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih menu (1/2/3/4): ");
            int mahasiswaPilihan = input.nextInt();

            switch (mahasiswaPilihan) {
                case 1:
                    // Implementasi fungsi pencarianMahasiswa
                    break;
                case 2:
                    lihatNilaiRataRata(indexMahasiswa);
                    break;
                case 3:
                    lihatKehadiran(indexMahasiswa);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }

    private static void lihatNilaiRataRata(int indexMahasiswa) {
        double rataRata = 0;
        int totalNilai = 0;

        for (int i = 0; i < nilaiMahasiswa[indexMahasiswa].length; i++) {
            totalNilai += nilaiMahasiswa[indexMahasiswa][i];
        }

        if (totalNilai > 0) {
            rataRata = (double) totalNilai / nilaiMahasiswa[indexMahasiswa].length;
        }

        System.out.println("Rata-rata Nilai: " + rataRata);
    }

    private static int findMahasiswaByNIM(int nim) {
        for (int i = 0; i < mahasiswaNIM.length; i++) {

            if (mahasiswaNIM[i] == nim) {
                return i;
            }
        }
        return -1; // Mahasiswa tidak ditemukan
    }

    private static void lihatKehadiran(int indexMahasiswa) {
        System.out.println("Kehadiran Mahasiswa " + mahasiswaNama[indexMahasiswa] + ":");
        for (int i = 0; i < kehadiranMahasiswa[indexMahasiswa].length; i++) {
            System.out.println("Mata Kuliah " + (i + 1) + ": " + (kehadiranMahasiswa[indexMahasiswa][i] ? "Hadir" : "Tidak Hadir"));
        }
    }

    private static String getGrade(double rataRata) {
        if (rataRata >= 80) {
            return "A";
        } else if (rataRata >= 70) {
            return "B";
        } else if (rataRata >= 60) {
            return "C";
        } else {
            return "D";
        }
    }

}
