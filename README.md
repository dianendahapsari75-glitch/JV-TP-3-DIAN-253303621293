## Bagian C - Fitur Analitik findStrongest()


## Method yang dirancang
Method findStrongest() ditambahkan di class CharacterManager. Method ini mencari karakter dengan nilai attack tertinggi dari semua karakter yang tersimpan, lalu mengembalikannya sebagai satu objek Character. Jika belum ada karakter, method mengembalikan null. Method ini belum dicontohkan di modul, karena getAverageLevel() hanya menghasilkan satu angka rata-rata dan findByType() hanya menyaring berdasarkan tipe.

## Alasan memilih method ini
Di class Character, nilai attack dihitung dari level (10 + level * 5), sehingga karakter dengan level tinggi otomatis punya attack tinggi. Karena itu pertanyaan "siapa karakter terkuat" adalah analisis yang wajar untuk sebuah manager. findStrongest() membandingkan data antar karakter, bukan sekadar menghitung atau menyaring. Logikanya ditaruh di CharacterManager, bukan di Main, karena manager yang memegang daftar karakter. Main cukup memanggil method tanpa perlu tahu cara pencariannya.

## Penerapan Encapsulation
1. List characterList bersifat private, sehingga tidak bisa diakses atau diganti dari luar class. findStrongest() hanya membaca list dan tidak mengubah isinya.
2. Method tidak mengembalikan ArrayList asli, sejalan dengan getAllCharacters() yang juga mengembalikan salinan list.
3. Method juga tidak mengembalikan objek Character asli yang ada di dalam list. Karena Character punya setter publik seperti setLevel() dan setId(), jika objek aslinya dikembalikan maka kode di luar bisa mengubah data di dalam manager. Karena itu method membuat salinan dengan constructor kedua new Character(id, name, type, level, hitPoints, attackPower). Constructor ini hanya menyalin enam atribut utama tersebut, sedangkan atribut mana tidak ikut disalin dan bernilai 0 pada salinan. Hal ini tidak memengaruhi tujuan method, yaitu menentukan karakter terkuat berdasarkan attack.

## Penanganan kasus khusus
a. Jika list kosong, method mengembalikan null agar tidak terjadi error saat mengambil characterList.get(0).
b. Jika ada dua karakter dengan attack yang sama, karakter yang lebih dulu ditambahkan yang terpilih, karena perbandingan memakai tanda > bukan >=.

## Javadoc
Method dilengkapi Javadoc yang menjelaskan fungsinya, bahwa hasilnya berupa salinan, dan nilai kembaliannya (@return).

## Pengujian
Di Main, findStrongest() dipanggil setelah Athena dihapus dan Gandalf serta Freya ditambahkan. Hasilnya Gandalf (level 6, attack 40), yang lebih kuat dari Arjuna (level 5, attack 35), Freya (level 4, attack 30), dan Merlin (level 3, attack 25). Level salinan kemudian diubah menjadi 99, tetapi level Gandalf di dalam manager tetap 6, sehingga terbukti objek asli tidak ikut berubah. Pada CharacterManager yang kosong, method mengembalikan null.

## Bagian D
Google Docs: [Klik di sini](https://docs.google.com/document/d/1QXR0eMXxgXzKj5QscEK41Qgux_qik8Ii55qplsQObeM/edit?usp=sharing)