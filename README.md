# SubmissionDicodingAplikasiAndroidIntermediate

*Submission Aplikasi Story App*
Fitur yang harus ada pada aplikasi:

1. Halaman Autentikasi
      - Menampilkan halaman login untuk masuk ke dalam aplikasi. Berikut input yang dibutuhkan.
        - Email (R.id.ed_login_email)
        - Password (R.id.ed_login_password)
      - Membuat halaman register untuk mendaftarkan diri dalam aplikasi. Berikut input yang dibutuhkan.
        - Nama (R.id.ed_register_name)
        - Email (R.id.ed_register_email)
        - Password (R.id.ed_register_password)
      - Untuk password wajib disembunyikan.
      - Membuat Custom View berupa EditText pada halaman login dan register dengan ketentuan sebagai berikut.
        - Jika jumlah password kurang dari 6 karakter, menampilkan error langsung pada EditText.
      - Menyimpan data sesi dan token di preferences. Data sesi digunakan untuk mengatur alur aplikasi dengan spesifikasi seperti berikut.
        - Jika sudah login langsung masuk ke halaman utama.
        - Jika belum maka akan masuk ke halaman login. 
      - Terdapat fitur untuk logout (R.id.action_logout) pada halaman utama dengan ketentuan sebagai berikut.
        - Ketika tombol logout ditekan, informasi token, dan sesi harus dihapus.

2. Daftar Cerita (List Story)
      - Menampilkan daftar cerita dari API yang disediakan. Berikut minimal informasi yang wajib Anda tampilkan.
        - Nama user (R.id.tv_item_name)
        - Foto  (R.id.iv_item_photo)
      - Muncul halaman detail ketika salah satu item cerita ditekan. Berikut  minimal informasi yang wajib Anda tampilkan.
        - Nama user (R.id.tv_detail_name)
        - Foto (R.id.iv_detail_photo)
        - Deskripsi (R.id.tv_detail_description)

3. Tambah Cerita
      - Membuat halaman untuk menambah cerita baru yang dapat diakses dari halaman daftar cerita. Berikut input minimal yang dibutuhkan.
        - File foto (bisa dari galeri dan kamera)
        - Deskripsi cerita (R.id.ed_add_description)
      - Ketentuan dalam menambahkan cerita baru:
        - Terdapat tombol (R.id.button_add) untuk upload data ke server. 
        - Setelah tombol tersebut diklik dan proses upload berhasil, maka akan kembali ke halaman daftar cerita. 
        - Data cerita terbaru harus muncul di paling atas.

4. Menampilkan Animasi
      - Membuat animasi pada aplikasi dengan menggunakan salah satu jenis animasi (menuliskan jenis dan lokasi animasi pada Student Note).
        - Property Animation
        - Motion Animation
        - Shared Element

Kerangka tampilan yang digunakan sebagai referensi:

<img src ="https://user-images.githubusercontent.com/99319439/185402402-0a6b3074-8f08-4704-bd9a-4f86377acdd9.jpeg" width="400" height="600"/>


*Submission Akhir Aplikasi Story App*
Fitur yang harus ada pada aplikasi:

1. Mempertahankan Fitur dari Submission Sebelumnya
      - Pastikan berbagai fitur yang ada dalam submission sebelumnya berjalan dengan baik.

2. Menampilkan Maps
      - Menampilkan satu halaman baru berisi peta yang menampilkan semua cerita yang memiliki lokasi dengan benar. 
        Bisa berupa marker maupun icon berupa gambar. Data story yang memiliki lokasi latitude dan longitude dapat diambil 
        melalui parameter location seperti berikut https://story-api.dicoding.dev/v1/stories?location=1.
        
3. Paging List
      - Menampilkan list story dengan menggunakan Paging 3 dengan benar.

Kerangka tampilan yang digunakan sebagai referensi:

<img src ="https://user-images.githubusercontent.com/99319439/185403178-a95d286c-8131-481e-9cfb-ae11ede18ec0.jpeg" width="400" height="400"/>
