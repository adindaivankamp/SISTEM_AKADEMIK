import java.util.Scanner;

class Mahasiswa {
    String nama;
    String nim;
    String programStudi;

    Mahasiswa(String nama, String nim, String programStudi) {
        this.nama = nama;
        this.nim = nim;
        this.programStudi = programStudi;
    }
}

class PendaftaranMaster {
    Mahasiswa[] daftarMahasiswa;
    int jumlahMahasiswa;

    PendaftaranMaster(int kapasitas) {
        daftarMahasiswa = new Mahasiswa[kapasitas];
        jumlahMahasiswa = 0;
    }

    void tambahMahasiswa(String nama, String nim, String programStudi) {
        if (jumlahMahasiswa < daftarMahasiswa.length) {
            Mahasiswa mahasiswaBaru = new Mahasiswa(nama, nim, programStudi);
            daftarMahasiswa[jumlahMahasiswa] = mahasiswaBaru;
            jumlahMahasiswa++;
            System.out.println("Pendaftaran berhasil untuk " + nama);
        } else {
            System.out.println("Kuota penuh, pendaftaran ditutup!");
        }
    }

    void tampilkanDaftarMahasiswa() {
        System.out.println("Daftar Mahasiswa yang Mendaftar ke Program Master:");
        for (int i = 0; i < jumlahMahasiswa; i++) {
            System.out.println("Nama: " + daftarMahasiswa[i].nama +
                    ", NIM: " + daftarMahasiswa[i].nim +
                    ", Program Studi: " + daftarMahasiswa[i].programStudi);
        }
    }
}

public class SistemAkademik1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selamat datang di Aplikasi Pendaftaran Master");

        // Inisialisasi objek pendaftaran dengan kapasitas 50 mahasiswa
        PendaftaranMaster pendaftaranMaster = new PendaftaranMaster(50);

        // Contoh pendaftaran mahasiswa
        pendaftaranMaster.tambahMahasiswa("John Doe", "12345", "Ilmu Komputer");
        pendaftaranMaster.tambahMahasiswa("Jane Doe", "67890", "Manajemen Bisnis");

        // Menampilkan daftar mahasiswa yang mendaftar
        pendaftaranMaster.tampilkanDaftarMahasiswa();
    }
}

