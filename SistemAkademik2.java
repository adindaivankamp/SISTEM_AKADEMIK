import java.util.Scanner;

public class SistemAkademik2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int jumlahMahasiswa;
        String username, password;

        // Array untuk menyimpan data mahasiswa
        Mahasiswa[] arrayMahasiswa = null;

        // Loop menu
        while (true) {
            System.out.println("||| SISTEM AKADEMIK |||");
            System.out.println("1. Masuk");
            System.out.println("2. Keluar");
            System.out.print("Pilih menu (1/2): ");
            int pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    // Fitur Masuk
                    System.out.print("Masukkan nama pengguna: ");
                    username = input.next();
                    System.out.print("Masukkan kata sandi: ");
                    password = input.next();

                    String jenisPengguna = dapatkanJenisPengguna(username);

                    if (jenisPengguna != null && isValidLogin(jenisPengguna, password)) {
                        System.out.println("Masuk berhasil sebagai " + jenisPengguna + ".");

                        if (jenisPengguna.equals("Mahasiswa")) {
                            if (arrayMahasiswa != null) {
                                tampilkanTranskripMahasiswa(arrayMahasiswa);
                                tandaiKehadiran(arrayMahasiswa);
                            } else {
                                System.out.println("Tidak ada data mahasiswa dan nilai. Silakan masukkan data terlebih dahulu.");
                            }
                        } else {
                            System.out.print("Masukkan jumlah mahasiswa: ");
                            jumlahMahasiswa = input.nextInt();

                            if (jumlahMahasiswa <= 0) {
                                System.out.println("Jumlah mahasiswa harus lebih dari 0.");
                            } else {
                                arrayMahasiswa = new Mahasiswa[jumlahMahasiswa];

                                for (int i = 0; i < jumlahMahasiswa; i++) {
                                    System.out.print("Masukkan nama mahasiswa ke-" + (i + 1) + ": ");
                                    String namaMahasiswa = input.next();
                                    System.out.print("Masukkan nilai mahasiswa ke-" + (i + 1) + ": ");
                                    int nilaiMahasiswa = input.nextInt();

                                    arrayMahasiswa[i] = new Mahasiswa(namaMahasiswa, nilaiMahasiswa);
                                }

                                System.out.println("Data mahasiswa dan nilai telah disimpan.");
                            }
                        }
                    } else {
                        System.out.println("Masuk gagal. Nama pengguna atau kata sandi salah.");
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

    public static void tampilkanTranskripDosen(Mahasiswa[] arrayMahasiswa) {
        System.out.println("Transkrip Dosen: ");
        for (Mahasiswa mahasiswa : arrayMahasiswa) {
            System.out.println("Nama: " + mahasiswa.getNama() + ", Nilai: " + mahasiswa.nilai + ", Transkrip: " + mahasiswa.transkrip);
        }
    }

    public static double hitungRataRata(int totalNilai, int jumlahMahasiswa) {
        return (double) totalNilai / jumlahMahasiswa;
    }

    static String dapatkanJenisPengguna(String username) {
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

    public static void tampilkanTranskripMahasiswa(Mahasiswa[] arrayMahasiswa) {
        System.out.println("Transkrip Mahasiswa: ");
        for (Mahasiswa mahasiswa : arrayMahasiswa) {
            System.out.println("Nama: " + mahasiswa.getNama() + ", Nilai: " + mahasiswa.nilai + ", Transkrip: " + mahasiswa.transkrip);
        }
    }

    public static void tandaiKehadiran(Mahasiswa[] arrayMahasiswa) {
        Scanner input = new Scanner(System.in);
        System.out.println("Tandai Kehadiran: ");
        for (int i = 0; i < arrayMahasiswa.length; i++) {
            System.out.print("Apakah " + arrayMahasiswa[i].getNama() + " hadir? (ya/tidak): ");
            String statusKehadiran = input.next().toLowerCase();
            arrayMahasiswa[i].setKehadiran(statusKehadiran.equals("ya"));
        }
    }

    static class Mahasiswa {
        String nama;
        int nilai;
        String transkrip;
        boolean hadir;

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

        public boolean isHadir() {
            return hadir;
        }

        public void setKehadiran(boolean hadir) {
            this.hadir = hadir;
        }
    }
}