package com.unpri.charactermanager.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.ArrayList;
import com.unpri.charactermanager.model.Character;

/**
 * Jendela utama aplikasi Character Manager.
 * Menampilkan daftar karakter yang diterima dari luar (bukan dibuat sendiri).
 */
public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private ArrayList<Character> characterList;   // data yang akan ditampilkan

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager v0.3");    // judul title bar
        this.characterList = characterList;  // simpan data yang diterima dari Main.java

        initComponents();

        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // [DIUBAH - TP8] isi method diganti: sekarang menampilkan data karakter
    private void initComponents() {
        mainPanel = new JPanel();

        JLabel titleLabel = new JLabel("=== Daftar Karakter (" + characterList.size() + ") ===");
        mainPanel.add(titleLabel);

        // Loop setiap Character, buat SATU JLabel baru untuk masing-masing
        for (Character c : characterList) {
            String info = c.getName() + " | " + c.getType() +
                          " | Lv." + c.getLevel() +
                          " | HP:" + c.getHitPoints() +
                          " | ATK:" + c.getAttackPower();
            JLabel characterLabel = new JLabel(info);
            mainPanel.add(characterLabel);   // setiap karakter mendapat JLabel sendiri
        }
                // [BARU - TP8 Bagian B] hitung rata-rata level dari data yang diterima
        int totalLevel = 0;
        for (Character c : characterList) {
            totalLevel = totalLevel + c.getLevel();          // jumlahkan level semua karakter
        }
        double rataRata = 0;                                  // default 0 kalau list kosong (hindari bagi dengan nol)
        if (characterList.size() > 0) {
            rataRata = (double) totalLevel / characterList.size();   // (double) agar hasilnya desimal
        }
        JLabel statistikLabel = new JLabel("Rata-rata Level: " + String.format("%.1f", rataRata));
        mainPanel.add(statistikLabel);                        // ditambahkan SETELAH loop, jadi muncul di bawah daftar

        add(mainPanel);
    }
    

}