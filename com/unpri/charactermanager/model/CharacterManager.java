package com.unpri.charactermanager.model;

import java.util.ArrayList;

/**
 * Mengelola koleksi Character: tambah, cari, hapus, dan statistik dasar.
 * Menerapkan Composition: CharacterManager "has-a" ArrayList<Character>.
 */
public class CharacterManager {

    private ArrayList<Character> characterList;

    public CharacterManager() {
        this.characterList = new ArrayList<>();   // list kosong saat manager pertama dibuat
    }

    /**
     * Menambahkan karakter baru ke dalam list.
     * Menolak jika sudah ada karakter dengan nama yang sama (cek via equals()).
     * @return true jika berhasil ditambahkan, false jika ditolak karena duplikat
     */
    public boolean addCharacter(Character character) {
        if (findByName(character.getName()) != null) {   // cek dulu apakah nama sudah ada
            System.out.println("Gagal: karakter dengan nama '" + character.getName() + "' sudah ada!");
            return false;
        }
        characterList.add(character);
        System.out.println("Karakter '" + character.getName() + "' berhasil ditambahkan.");
        return true;
    }

    /**
     * Mencari karakter berdasarkan nama.
     * @return object Character jika ditemukan, null jika tidak ada
     */
    public Character findByName(String name) {
        for (Character c : characterList) {         // iterasi setiap elemen di list
            if (c.getName().equals(name)) {          // bandingkan ISI nama, bukan ==
                return c;                             // langsung kembalikan referensi yang ditemukan
            }
        }
        return null;                                  // tidak ditemukan setelah seluruh list diperiksa
    }

    /**
     * Menghapus karakter dari list berdasarkan nama.
     * @return true jika berhasil dihapus, false jika tidak ditemukan
     */
    public boolean removeCharacter(String name) {
        Character target = findByName(name);
        if (target == null) {
            System.out.println(" Gagal hapus: karakter '" + name + "' tidak ditemukan.");
            return false;
        }
        characterList.remove(target);                 // ArrayList.remove(Object) memakai equals() di balik layar
        System.out.println("Karakter '" + name + "' berhasil dihapus.");
        return true;
    }

    public int getTotalCharacters() {
        return characterList.size();
    }

    /**
     * Mengembalikan SALINAN list, bukan referensi asli —
     * agar pemanggil tidak bisa mengubah data internal manager secara langsung.
     */
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characterList);        // defensive copy — lihat Halaman 7 untuk eksperimen
    }

        // [BARU - TP5 Bagian B] menghitung rata-rata level semua karakter
    public double getAverageLevel() {
        if (characterList.isEmpty()) {            // list kosong: kembalikan 0 supaya tidak ada pembagian dengan nol
            return 0;
        }
        int total = 0;
        for (Character c : characterList) {       // jumlahkan level semua karakter
            total += c.getLevel();
        }
        return (double) total / characterList.size();   // (double) agar hasilnya desimal, bukan dibulatkan
    }

    // [BARU - TP5 Bagian B] mencari semua karakter dengan tipe tertentu
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> hasil = new ArrayList<>();   // list baru untuk menampung hasil
        for (Character c : characterList) {
            if (c.getType() == type) {                    // enum dibandingkan dengan == (aman dan standar)
                hasil.add(c);
            }
        }
        return hasil;   
        // kalau tidak ada yang cocok, list kosong (bukan null)
    }
        /**
     * Mencari karakter dengan attackPower tertinggi.
     * Yang dikembalikan adalah SALINAN, bukan object asli di dalam list,
     * sehingga perubahan di luar tidak memengaruhi data manager.
     * Jika attackPower sama, karakter yang lebih dulu ditambahkan yang dipilih.
     * @return salinan Character terkuat, atau null jika list kosong
     */
    public Character findStrongest() {
        if (characterList.isEmpty()) {               // list kosong: tidak ada yang bisa dibandingkan
            return null;
        }
        Character terkuat = characterList.get(0);    // anggap karakter pertama yang terkuat
        for (Character c : characterList) {
            if (c.getAttackPower() > terkuat.getAttackPower()) {   // pakai > (bukan >=) agar yang lebih dulu menang saat seri
                terkuat = c;
            }
        }
        // buat salinan lewat constructor kedua supaya object asli tidak bocor keluar
        Character salinan = new Character(terkuat.getId(), terkuat.getName(), terkuat.getType(),
                                          terkuat.getLevel(), terkuat.getHitPoints(), terkuat.getAttackPower());
        salinan.setMana(terkuat.getMana());          // constructor kedua tidak mengisi mana, jadi disalin manual
        return salinan;
    }

}   // kurung kurawal penutup class CharacterManager
