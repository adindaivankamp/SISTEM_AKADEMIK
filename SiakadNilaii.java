import java.util.Scanner;

<<<<<<< HEAD
public class SiakadNilaii {
=======
public class SiakadNilaii{
>>>>>>> 96aa9e40f37ca7073a706998c6968ac7ffde0bfe
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int jumlahMahasiswa;
        String username, password;

<<<<<<< HEAD
        // Menyimpan nilai mahasiswa
        Mahasiswa[] mahasiswaArray = null;

        // Fitur menu
        while (true) {
            System.out.println("||| MENU SIAKAD NILAI |||");
            System.out.println("1. Login");
            System.out.println("2. Presensi Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu (1/2/3): ");
=======
        // Array untuk menyimpan data mahasiswa
        Mahasiswa[] arrayMahasiswa = null;

        // Loop menu
        while (true) {
            System.out.println("||| SISTEM AKADEMIK |||");
            System.out.println("1. Masuk");
            System.out.println("2. Keluar");
            System.out.print("Pilih menu (1/2): ");
>>>>>>> 96aa9e40f37ca7073a706998c6968ac7ffde0bfe
            int pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
<<<<<<< HEAD
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
=======
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
>>>>>>> 96aa9e40f37ca7073a706998c6968ac7ffde0bfe
                            }
                        } else {
                            System.out.print("Masukkan jumlah mahasiswa: ");
                            jumlahMahasiswa = input.nextInt();

                            if (jumlahMahasiswa <= 0) {
                                System.out.println("Jumlah mahasiswa harus lebih dari 0.");
                            } else {
<<<<<<< HEAD
                                mahasiswaArray = new Mahasiswa[jumlahMahasiswa];
=======
                                arrayMahasiswa = new Mahasiswa[jumlahMahasiswa];
>>>>>>> 96aa9e40f37ca7073a706998c6968ac7ffde0bfe

                                for (int i = 0; i < jumlahMahasiswa; i++) {
                                    System.out.print("Masukkan nama mahasiswa ke-" + (i + 1) + ": ");
                                    String namaMahasiswa = input.next();
                                    System.out.print("Masukkan nilai mahasiswa ke-" + (i + 1) + ": ");
                                    int nilaiMahasiswa = input.nextInt();

<<<<<<< HEAD
                                    mahasiswaArray[i] = new Mahasiswa(namaMahasiswa, nilaiMahasiswa);
=======
                                    arrayMahasiswa[i] = new Mahasiswa(namaMahasiswa, nilaiMahasiswa);
>>>>>>> 96aa9e40f37ca7073a706998c6968ac7ffde0bfe
                                }

                                System.out.println("Data mahasiswa dan nilai telah disimpan.");
                            }
                        }
                    } else {
<<<<<<< HEAD
                        System.out.println("Login gagal. Username atau password salah.");
=======
                        System.out.println("Masuk gagal. Nama pengguna atau kata sandi salah.");
>>>>>>> 96aa9e40f37ca7073a706998c6968ac7ffde0bfe
                    }
                    break;

                case 2:
<<<<<<< HEAD
                    // Fitur Presensi Mahasiswa
                    if (mahasiswaArray != null) {
                        presensiMahasiswa(mahasiswaArray);
                    } else {
                        System.out.println("Belum ada data mahasiswa dan nilai. Silakan masukkan data terlebih dahulu.");
                    }
                    break;

                case 3:
=======
>>>>>>> 96aa9e40f37ca7073a706998c6968ac7ffde0bfe
                    System.out.println("Terima kasih. Program selesai.");
                    System.exit(0); // Keluar dari program
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }

<<<<<<< HEAD
    public static void presensiMahasiswa(Mahasiswa[] mahasiswaArray) {
        System.out.print("Masukkan nomor mahasiswa yang akan diambil presensinya: ");
        Scanner input = new Scanner(System.in);
        int nomorMahasiswa = input.nextInt();

        if (nomorMahasiswa >= 1 && nomorMahasiswa <= mahasiswaArray.length) {
            Mahasiswa mahasiswa = mahasiswaArray[nomorMahasiswa - 1];
            System.out.print("Apakah mahasiswa " + mahasiswa.getNama() + " hadir? (true/false): ");
            boolean presensi = input.nextBoolean();
            mahasiswa.setPresensi(presensi);

            System.out.println("Presensi mahasiswa " + mahasiswa.getNama() + " berhasil diambil.");
        } else {
            System.out.println("Nomor mahasiswa tidak valid.");
        }
    }

    // ... (Bagian lain dari program tetap tidak berubah)
    
    static class Mahasiswa {
        String nama;
        int nilai;
        boolean presensi;
        String transkrip;
=======
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
>>>>>>> 96aa9e40f37ca7073a706998c6968ac7ffde0bfe

        // Konstruktor untuk menginisialisasi objek Mahasiswa
        public Mahasiswa(String nama, int nilai) {
            this.nama = nama;
            this.nilai = nilai;
            hitungTranskrip();
        }

<<<<<<< HEAD
        // Metode untuk menghitung transkrip berdasarkan nilai dan presensi
        public void hitungTranskrip() {
            if (nilai >= 80 && presensi) {
                transkrip = "A";
            } else if (nilai >= 70 && presensi) {
                transkrip = "B";
            } else if (nilai >= 60 && presensi) {
=======
        // Metode untuk menghitung transkrip berdasarkan nilai
        public void hitungTranskrip() {
            if (nilai >= 80) {
                transkrip = "A";
            } else if (nilai >= 70) {
                transkrip = "B";
            } else if (nilai >= 60) {
>>>>>>> 96aa9e40f37ca7073a706998c6968ac7ffde0bfe
                transkrip = "C";
            } else {
                transkrip = "D";
            }
        }

<<<<<<< HEAD
        // Setter untuk mengatur nilai presensi
        public void setPresensi(boolean presensi) {
            this.presensi = presensi;
            hitungTranskrip(); // Hitung ulang transkrip setelah mengatur presensi
        }

=======
>>>>>>> 96aa9e40f37ca7073a706998c6968ac7ffde0bfe
        public String getTranskrip() {
            return transkrip;
        }

        public String getNama() {
            return nama;
        }
<<<<<<< HEAD
=======

        public boolean isHadir() {
            return hadir;
        }

        public void setKehadiran(boolean hadir) {
            this.hadir = hadir;
        }
>>>>>>> 96aa9e40f37ca7073a706998c6968ac7ffde0bfe
    }
}
