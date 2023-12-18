import java.util.Scanner;

public class kodeprogram7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int jumlahSiswa;
        String username, password;

        // Menyimpan data siswa dan mata kuliah
        Siswa[] siswaArray = null;

        // Loop menu
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

                        if (jenisPengguna.equals("siswa")) {
                            if (siswaArray != null) {
                                tampilkanTranskripSiswa(siswaArray);
                            } else {
                                System.out.println("Belum ada data siswa dan nilai. Silakan masukkan data terlebih dahulu.");
                            }
                        } else {
                            System.out.print("Masukkan jumlah siswa: ");
                            jumlahSiswa = input.nextInt();

                            if (jumlahSiswa <= 0) {
                                System.out.println("Jumlah siswa harus lebih dari 0.");
                            } else {
                                siswaArray = new Siswa[jumlahSiswa];

                                for (int i = 0; i < jumlahSiswa; i++) {
                                    System.out.print("Masukkan nama siswa ke-" + (i + 1) + ": ");
                                    String namaSiswa = input.next();

                                    System.out.print("Masukkan jumlah mata kuliah yang diambil oleh siswa ke-" + (i + 1) + ": ");
                                    int jumlahMataKuliah = input.nextInt();

                                    MataKuliah[] mataKuliahArray = new MataKuliah[jumlahMataKuliah];

                                    for (int j = 0; j < jumlahMataKuliah; j++) {
                                        System.out.print("Masukkan nama mata kuliah ke-" + (j + 1) + ": ");
                                        String namaMataKuliah = input.next();
                                        System.out.print("Masukkan sks mata kuliah ke-" + (j + 1) + ": ");
                                        int sksMataKuliah = input.nextInt();
                                        System.out.print("Masukkan nilai mata kuliah ke-" + (j + 1) + ": ");
                                        int nilaiMataKuliah = input.nextInt();

                                        mataKuliahArray[j] = new MataKuliah(namaMataKuliah, sksMataKuliah, nilaiMataKuliah);
                                    }

                                    siswaArray[i] = new Siswa(namaSiswa, mataKuliahArray);
                                }

                                System.out.println("Data siswa dan nilai telah disimpan.");
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

    public static void tampilkanTranskripSiswa(Siswa[] siswaArray) {
        System.out.println("Transkrip Siswa: ");
        for (Siswa siswa : siswaArray) {
            System.out.println("Nama: " + siswa.nama);
            System.out.println("Mata Kuliah yang Diambil:");
            for (MataKuliah mataKuliah : siswa.mataKuliahArray) {
                System.out.println("  - " + mataKuliah.namaMataKuliah + ", SKS: " + mataKuliah.sksMataKuliah + ", Nilai: " + mataKuliah.nilaiMataKuliah);
            }
            System.out.println("Rata-rata Nilai: " + siswa.hitungRataRata());
            System.out.println();
        }
    }

    // ... (Metode lainnya tetap sama)

}

class Siswa {
    String nama;
    MataKuliah[] mataKuliahArray;

    // Konstruktor untuk menginisialisasi objek Siswa
    public Siswa(String nama, MataKuliah[] mataKuliahArray) {
        this.nama = nama;
        this.mataKuliahArray = mataKuliahArray;
    }

    // Metode untuk menghitung rata-rata nilai mata kuliah
    public double hitungRataRata() {
        int totalNilai = 0;
        for (MataKuliah mataKuliah : mataKuliahArray) {
            totalNilai += mataKuliah.nilaiMataKuliah;
        }
        return (double) totalNilai / mataKuliahArray.length;
    }
}

class MataKuliah {
    String namaMataKuliah;
    int sksMataKuliah;
    int nilaiMataKuliah;

    // Konstruktor untuk menginisialisasi objek MataKuliah
    public MataKuliah(String namaMataKuliah, int sksMataKuliah, int nilaiMataKuliah) {
        this.namaMataKuliah = namaMataKuliah;
        this.sksMataKuliah = sksMataKuliah;
        this.nilaiMataKuliah = nilaiMataKuliah;
