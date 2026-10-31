## TP10 - Tema Tampilan Character Manager (AppTheme)

## Screenshot sebelum dan sesudah
Sebelum (tampilan polos tanpa tema):

![MainWindow v0.3](screenshot/OUTPUT-TP8.png)

Sesudah (v0.4, memakai AppTheme, dengan 7 karakter dan Rata-rata Level 4,9):

![MainWindow v0.4](screenshot/Output-tp10-v04.png)

## Mengapa warna disimpan di AppTheme, bukan di Character
Character berada di package model dan tugasnya hanya menyimpan data karakter, sedangkan warna adalah urusan tampilan yang seharusnya berada di package view. Jika warna ditaruh di Character, class model ikut bergantung pada library tampilan (java.awt) dan harus diubah setiap kali desain berganti. Dengan AppTheme, seluruh warna, font, dan border berada di satu file, sehingga mengganti tema cukup mengubah satu tempat dan MainWindow tidak perlu disentuh. Pemisahan ini juga terbukti lewat git diff, yaitu package model tidak berubah sama sekali dibandingkan commit TP 8.

## Dua properti tambahan
1. Tooltip (setToolTipText) berisi mana dan status hidup karakter. Alasannya, kartu sudah memuat nama, tipe, level, HP, dan ATK, sehingga detail tambahan ditaruh di tooltip agar kartu tidak terlalu padat dan tetap mudah dibaca.
2. Karakter level 1 dinonaktifkan dengan setEnabled(false) sehingga teksnya tampil abu-abu. Alasannya, karakter level 1 adalah pemula yang belum layak dipakai, dan tampilan abu-abu menandainya tanpa perlu menambah teks baru. Batasnya memakai konstanta bernama BATAS_LEVEL_PEMULA di AppTheme.

## Mini-Challenge
Karakter dengan level 5 ke atas tampil dengan teks emas dan tebal. Batas level disimpan di konstanta bernama BATAS_LEVEL_TINGGI di AppTheme, sehingga angka 5 tidak tertulis langsung di MainWindow dan mudah diubah di satu tempat.

## Bagian D
Google Docs: [Klik di sini](https://docs.google.com/document/d/1LQiuIeGG7x2rfd6hcrVvuJs4YSeTAgvtn638HeK3M8Y/edit?usp=sharing)