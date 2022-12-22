import java.util.Scanner;
import java.util.Date;
import java.io.FileReader;

public class Main {

    /*
     * Tempat Menyimpan data2
     */
    public static void date() {
        Date datee = new Date();
        System.out.println(datee.toString());
    }

    public static String paket;
    public static String harga;
    public static String[] dataPelanggan = new String[20];
    public static String[] dataPaket = new String[20];
    public static String[] dataHarga = new String[20];
    public static String[] usernameAdmin = new String[] {
            "admin", "agung"
    };
    public static String[] passwordAdmin = new String[] {
            "admin", "1"
    };
    public static String data = "src/data.txt";
    public static java.util.Scanner scan = new Scanner(System.in);

    /*
     * Membuat Method untuk Menyimpan Inputan
     */
    public static String input(String info) {

        System.out.print(info + " : ");
        String data = scan.nextLine();
        return data;
    }

    public static void main(String[] args)throws Exception {
        viewLogin();
    }

    //////////////////////////////////////////

    /*
     * Method Untuk Login User,
     */
    public static void login(String user, String pass)throws Exception {
        Boolean status = false;
        Scanner inputFile = new Scanner(new FileReader(data));
        while(inputFile.hasNext() && !status){
            String line = inputFile.nextLine();
            String[] kolom = line.split(";");

            String datauser = kolom[0];
            String datapass = kolom[1];

            if(user.equals(datauser) && pass.equals(datapass)){
                status = true;
            }
        }
        if(status){
            System.out.println("Login Berhasil");
            viewaddDataPelanggan();
            viewshowDataPelanggan();
        }else{
            System.out.println("Login gagal");
            viewLogin();
        }
    }

    public static void viewLogin()throws Exception {
        System.out.println("Selamat Datang DI Aplikasi AutoInvoice Silahkan Login terlebih dahulu");
        var user = input("Silahkan Masukkan Username");
        var pass = input("Silahkan Masukkan Password");
        login(user,pass);
    }
    //////////////////////////////////////////////////

    public static void showDataPelanggan() {
        /*
         * Mengambil Data pelanggan berdasarkan Index perulangan lalu menyimpannya ke
         * sebuah variable untuk di tampilkan
         * Jika data dalam array !null
         */
        for (var i = 0; i < dataPelanggan.length; i++) {
            var pelanggan = dataPelanggan[i];
            var paket = dataPaket[i];
            var harga = dataHarga[i];
            var no = i + 1;

            if (pelanggan != null && paket != null && harga != null) {
                System.out.println(no + ". " + pelanggan + " " + paket + " " + harga);
            }

        }

    }

    public static void addDataPelanggan(String pelanggan, String paket, String harga) {
        var isFull = true;
        for (var i = 0; i < dataPelanggan.length; i++) {
            if (dataPelanggan[i] == null) {
                isFull = false;
                break;
            }
        }
        // jika penuh resize ukuran array 2x lipat
        if (isFull) {
            // menyimpan isi lama dari dataPelanggan
            var temp = dataPelanggan;
            var temp2 = dataPaket;
            var temp3 = dataHarga;
            dataPelanggan = new String[dataPelanggan.length * 2];
            dataPaket = new String[dataPaket.length * 2];
            dataHarga = new String[dataHarga.length * 2];

            // menambahkan isi temp ke dataPelanggan
            for (int i = 0; i < temp.length; i++) {
                dataPelanggan[i] = temp[i];
                dataPaket[i] = temp2[i];
                dataHarga[i] = temp3[i];
            }
        }
        // tambahkan ke posisi yang datanya null
        for (var i = 0; i < dataPelanggan.length; i++) {
            if (dataPelanggan[i] == null) {
                dataPelanggan[i] = pelanggan;
                dataPaket[i] = paket;
                dataHarga[i] = harga;
                break;
            }
        }

    }

    public static void inputAddDataPelanggan() {
        var isFull = true;

        var pelanggan = input("Masukkan nama Pelanggan");
        System.out.println("Silahkan pilih paket");
        System.out.println("1. 3Mb");
        System.out.println("2. 5Mb");
        System.out.println("3. 7Mb");
        var pilih = input("Masukkan Paket Pelanggan");

        if (pilih.equals("1")) {
            paket = "3Mb";
            harga = "100000";
        } else if (pilih.equals("2")) {
            paket = "5Mb";
            harga = "200000";
        } else if (pilih.equals("3")) {
            paket = "7Mb";
            harga = "300000";
        }else{
            System.out.println("Input Tidak di ketahui");
        }
        // var harga = input("Masukkan Tarif bulanan");

        for (var i = 0; i < dataPelanggan.length; i++) {
            if (dataPelanggan[i] == null) {
                isFull = false;
                break;
            }
        }
        // jika penuh resize ukuran array 2x lipat
        if (isFull) {
            // menyimpan isi lama dari dataPelanggan
            var temp = dataPelanggan;
            var temp2 = dataPaket;
            var temp3 = dataHarga;
            dataPelanggan = new String[dataPelanggan.length * 2];
            dataPaket = new String[dataPaket.length * 2];
            dataHarga = new String[dataHarga.length * 2];

            // menambahkan isi temp ke dataPelanggan
            for (int i = 0; i < temp.length; i++) {
                dataPelanggan[i] = temp[i];
                dataPaket[i] = temp2[i];
                dataHarga[i] = temp3[i];

            }
        }
        // tambahkan ke posisi yang datanya null
        for (var i = 0; i < dataPelanggan.length; i++) {
            if (dataPelanggan[i] == null) {
                dataPelanggan[i] = pelanggan;
                dataPaket[i] = paket;
                dataHarga[i] = "Rp." + harga;
                break;
            }
        }
    }

    public static boolean removeTodoList(Integer number) {

        if ((number - 1) >= dataPelanggan.length) {
            return false;
        } else if (dataPelanggan[number - 1] == null) {
            return false;
        } else {
            dataPelanggan[number - 1] = null;
            dataPaket[number - 1] = null;
            dataHarga[number - 1] = null;
            return true;
        }
    }

    public static void cetakInvoice(String number) {

        if(dataPelanggan[Integer.valueOf(number)-1]==null && dataPaket[Integer.valueOf(number)-1] == null && dataHarga[Integer.valueOf(number)-1] == null){
            System.out.println("\nData Kosong, Gagal Mencetak");
            viewshowDataPelanggan();
        }else{
            System.out.println("=============INVOICE WIJAYA NET===============");
            System.out.println("============= STRUK PEMBAYARAN ===============");
            System.out.println("Nomer ID 		  :" + (Integer.valueOf(number)));
            System.out.println("Nama     		  :" + dataPelanggan[(Integer.valueOf(number) - 1)]);
            System.out.println("Paket    		  :" + dataPaket[(Integer.valueOf(number) - 1)]);
            System.out.println("Total Penagihan   :" + dataHarga[(Integer.valueOf(number) - 1)]);
            System.out.println("Waktu Penagihan : ");
            date();
            System.out.println("Silahkan Kirimkan Jumlah tagihan ke");
            System.out.println("No.Rek : 12345678");
            System.out.println("=============    TERIMAKASIH   ===============");
        }
       

    }

    public static void viewcetakInvoice() {
        showDataPelanggan();
        var number = input("Masukkan ID pelanggan yang akan di cetak");
        if(Integer.valueOf(number)>dataPelanggan.length){
            System.out.println("No ID Tidak Terdaftar");
            viewcetakInvoice();
        }else{
            cetakInvoice(number);
        }

        var tanya = input("Sudah Melakukan pembayaran? (y/n)");
        if (tanya.equals("y")) {
            cetakKwitansi(number);
        } else {
            viewshowDataPelanggan();
        }
    }

    public static void cetakKwitansi(String number) {
        System.out.println("\n");
        System.out.println("============= STRUK KWITANSI ===============");
        System.out
                .println("Terimakasih Sudah Melakukan Pembayaran Sebesar " + dataHarga[(Integer.valueOf(number) - 1)]);
        System.out.println("Atas Nama : " + dataPelanggan[(Integer.valueOf(number) - 1)]);
        System.out.print("Dengan Nomer ID : " + (Integer.valueOf(number)));
        System.out.println("\nUntuk Pembayaran Bulanan Paket Wifi : " + dataPaket[(Integer.valueOf(number) - 1)]);
        System.out.println("=============    TERIMAKASIH   ===============");
        System.out.println(" ");
    }

    public static void viewshowDataPelanggan() {
        System.out.println("\nDAFTAR PELANGGAN\n");
        // viewaddDataPelanggan();
        showDataPelanggan();

        boolean isTrue = true;
        while (isTrue) {
            System.out.println("\n==========AutoInvoice============\n");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Hapus Pelanggan");
            System.out.println("3. Cetak Invoice");
            System.out.println("x. keluar");
            System.out.println("\n");
            var tanya = input("Silahkan Pilih..(x untuk batal");
            if (tanya.equals("1")) {
                viewinputAddDataPelanggan();
                showDataPelanggan();
            } else if (tanya.equals("2")) {
                viewremoveDatapelanggan();
                showDataPelanggan();
            } else if (tanya.equals("3")) {
                viewcetakInvoice();
            } else if (tanya.equals("x")) {
                isTrue = false;
            } else {
                System.out.println("\nPerintah tidak di ketahui");
            }
        }
    }

    public static void viewaddDataPelanggan() {
        addDataPelanggan("Agung", "3Mb", "Rp. " + "100000");
        addDataPelanggan("falen", "3Mb", "Rp. " + "100000");
        addDataPelanggan("Rifki", "3Mb", "Rp. " + "100000");
        addDataPelanggan("Nanas", "3Mb", "Rp. " + "100000");
        addDataPelanggan("Faiz", "3Mb", "Rp. " + "100000");
    }

    public static void viewinputAddDataPelanggan() {
        System.out.println("MENAMBAHKAN DATA PELANGGAN");
        inputAddDataPelanggan();
    }

    public static void viewremoveDatapelanggan() {
        System.out.println("MENGHAPUS DATA PELANGGAN");
        showDataPelanggan();
        var number = input("Masukkan ID pelanggan yang akan di hapus,(x untuk batal)");
        if(number.equals("x")){
            viewshowDataPelanggan();
        }else{
            boolean success = removeTodoList(Integer.valueOf(number));
            if (!success) {
                System.out.println("gagal menghapus");
            }
        }
        

    }
}
