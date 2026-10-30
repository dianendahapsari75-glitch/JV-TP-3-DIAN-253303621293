package com.unpri.charactermanager;   // [BARU - TP5] deklarasi package, sesuai letak file di folder charactermanager

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.model.CharacterManager;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Character Manager v0.2 — CRUD Test ===\n");

        CharacterManager manager = new CharacterManager();

        // 1. Tambah beberapa karakter
        manager.addCharacter(new Character("Arjuna", CharacterType.WARRIOR, 5));
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 3));
        manager.addCharacter(new Character("Athena", CharacterType.ARCHER, 4));

        // 2. Coba tambah DUPLIKAT nama — seharusnya DITOLAK
        System.out.println();
        manager.addCharacter(new Character("Arjuna", CharacterType.HEALER, 1));

        // 3. Tampilkan total karakter
        System.out.println("\nTotal karakter saat ini: " + manager.getTotalCharacters());

        // 4. Cari karakter
        System.out.println("\n--- Pencarian ---");
        Character hasil = manager.findByName("Merlin");
        System.out.println(hasil != null ? "Ditemukan: " + hasil : "Tidak ditemukan");

        Character tidakAda = manager.findByName("Zeus");
        System.out.println(tidakAda != null ? "Ditemukan: " + tidakAda : "Zeus tidak ditemukan (sesuai harapan)");

        // 5. Hapus karakter
        System.out.println("\n--- Penghapusan ---");
        manager.removeCharacter("Athena");
        System.out.println("Total setelah hapus: " + manager.getTotalCharacters());

        // 6. Tampilkan semua karakter yang tersisa
        System.out.println("\n--- Daftar Karakter Tersisa ---");
        for (Character c : manager.getAllCharacters()) {
            System.out.println(c);
        }

        // ===== EKSPERIMEN: Object Reference =====
        System.out.println("\n--- Eksperimen Reference ---");

        Character asli = manager.findByName("Arjuna");
        Character duplikatReferensi = asli;              // menyalin REFERENSI, bukan membuat object baru

        System.out.println("Level Arjuna sebelum: " + asli.getLevel());
        duplikatReferensi.setLevel(99);                  // mengubah LEWAT variabel lain

        System.out.println("Level Arjuna sesudah (lewat 'asli'): " + asli.getLevel());
        System.out.println("asli == duplikatReferensi? " + (asli == duplikatReferensi));

        // Bandingkan dengan getAllCharacters() yang mengembalikan SALINAN LIST:
        java.util.ArrayList<Character> salinanList = manager.getAllCharacters();
        salinanList.add(new Character("Hantu", CharacterType.MAGE, 1));   // tambah ke SALINAN list

        System.out.println("Total di manager asli: " + manager.getTotalCharacters());
        System.out.println("Total di salinan list : " + salinanList.size());

                // ===== [BARU - TP5 Bagian B] Uji getAverageLevel() =====
        System.out.println("\n--- Uji getAverageLevel() ---");
        CharacterManager managerKosong = new CharacterManager();      // manager baru tanpa karakter
        System.out.println("Rata-rata level (list kosong): " + managerKosong.getAverageLevel());
        System.out.println("Rata-rata level (manager asli): " + manager.getAverageLevel());

        // ===== [BARU - TP5 Bagian B] Uji findByType() =====
        System.out.println("\n--- Uji findByType() ---");
        manager.addCharacter(new Character("Lancelot", CharacterType.WARRIOR, 7));   // tambah 1 WARRIOR lagi untuk bahan uji

        java.util.ArrayList<Character> daftarWarrior = manager.findByType(CharacterType.WARRIOR);
        System.out.println("Jumlah WARRIOR: " + daftarWarrior.size());
        for (Character c : daftarWarrior) {
            System.out.println(c);
        }

        java.util.ArrayList<Character> daftarHealer = manager.findByType(CharacterType.HEALER);
        System.out.println("Jumlah HEALER: " + daftarHealer.size());      // tidak ada HEALER, hasilnya list kosong

                // ===== [BARU - TP5 Bagian C] Uji findStrongest() =====
        System.out.println("\n--- Uji findStrongest() ---");
        CharacterManager managerUji = new CharacterManager();    // manager baru agar datanya bersih
        managerUji.addCharacter(new Character("Arjuna", CharacterType.WARRIOR, 5));
        managerUji.addCharacter(new Character("Merlin", CharacterType.MAGE, 3));
        managerUji.addCharacter(new Character("Freya", CharacterType.ARCHER, 4));
        managerUji.addCharacter(new Character("Gandalf", CharacterType.MAGE, 6));

        Character terkuat = managerUji.findStrongest();
        System.out.println("Karakter terkuat: " + terkuat);

        terkuat.setLevel(99);                                    // ubah SALINAN, bukan object asli
        System.out.println("Level salinan setelah diubah: " + terkuat.getLevel());
        System.out.println("Level Gandalf di manager: " + managerUji.findByName("Gandalf").getLevel());

        System.out.println("Terkuat (list kosong): " + new CharacterManager().findStrongest());   // harus null

    }
    
}