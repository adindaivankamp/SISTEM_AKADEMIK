import java.util.Scanner;

public class SiakadNilaii {
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
            System.out.println("2. Presensi Mahasiswa");
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
                    // Fitur Presensi Mahasiswa
                    if (mahasiswaArray != null) {
                        presensiMahasiswa(mahasiswaArray);
                    } else {
                        System.out.println("Belum ada data mahasiswa dan nilai. Silakan masukkan data terlebih dahulu.");
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

        // Konstruktor untuk menginisialisasi objek Mahasiswa
        public Mahasiswa(String nama, int nilai) {
            this.nama = nama;
            this.nilai = nilai;
            hitungTranskrip();
        }

        // Metode untuk menghitung transkrip berdasarkan nilai dan presensi
        public void hitungTranskrip() {
            if (nilai >= 80 && presensi) {
                transkrip = "A";
            } else if (nilai >= 70 && presensi) {
                transkrip = "B";
            } else if (nilai >= 60 && presensi) {
                transkrip = "C";
            } else {
                transkrip = "D";
            }
        }

        // Setter untuk mengatur nilai presensi
        public void setPresensi(boolean presensi) {
            this.presensi = presensi;
            hitungTranskrip(); // Hitung ulang transkrip setelah mengatur presensi
        }

        public String getTranskrip() {
            return transkrip;
        }

        public String getNama() {
            return nama;
        }
    }
}
