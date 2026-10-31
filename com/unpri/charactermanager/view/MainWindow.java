package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import com.unpri.charactermanager.model.Character;

/**
 * Jendela utama aplikasi Character Manager v0.4.
 * Tampilan (warna, font, border) semuanya diambil dari AppTheme.
 */
public class MainWindow extends JFrame {

    private JPanel mainPanel;                      // container utama
    private ArrayList<Character> characterList;    // data dari Main.java

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager v0.4");           // [DIUBAH - TP13] versi naik ke v0.4
        this.characterList = characterList;

        initComponents();

        pack();                                    // [DIUBAH - TP13] ukuran jendela dihitung otomatis dari isinya
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);               // taruh di tengah layar (setelah pack)
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout(0, 10));
        mainPanel.setBackground(AppTheme.WARNA_LATAR);
        mainPanel.setBorder(AppTheme.BORDER_PANEL);

        // ----- judul di bagian atas -----
        JLabel judul = new JLabel("=== Daftar Karakter (" + characterList.size() + ") ===",
                                  SwingConstants.CENTER);
        judul.setFont(AppTheme.FONT_JUDUL);
        judul.setForeground(AppTheme.WARNA_TEKS);
        mainPanel.add(judul, BorderLayout.NORTH);

        // ----- kartu karakter di tengah: 1 kolom, baris menyesuaikan jumlah data -----
        JPanel panelKartu = new JPanel(new GridLayout(0, 1, 0, 6));
        panelKartu.setBackground(AppTheme.WARNA_LATAR);

        for (Character c : characterList) {
            String info = c.getName() + " | " + c.getType() +
                          " | Lv." + c.getLevel() +
                          " | HP:" + c.getHitPoints() +
                          " | ATK:" + c.getAttackPower();
            JLabel kartu = new JLabel(info);
            kartu.setOpaque(true);                                  // WAJIB: JLabel transparan, tanpa ini warna latar tidak tampil
            kartu.setBackground(AppTheme.colorFor(c.getType()));    // warna sesuai role
            kartu.setBorder(AppTheme.BORDER_KARTU);

            // [DIUBAH - TP13 Bagian B] Mini-Challenge: level tinggi tampil emas dan tebal
            if (c.getLevel() >= AppTheme.BATAS_LEVEL_TINGGI) {      // batas dari konstanta bernama, bukan angka 5 langsung
                kartu.setForeground(AppTheme.WARNA_EMAS);
                kartu.setFont(AppTheme.FONT_KARTU_TEBAL);
            } else {
                kartu.setForeground(AppTheme.WARNA_TEKS);
                kartu.setFont(AppTheme.FONT_KARTU);
            }

            // [BARU - TP13 Bagian B] properti tambahan 1: tooltip berisi detail yang tidak ada di kartu
            kartu.setToolTipText("Mana: " + c.getMana() + " | Status: " + (c.isAlive() ? "hidup" : "gugur"));

            // [BARU - TP13 Bagian B] properti tambahan 2: karakter pemula dinonaktifkan
            if (c.getLevel() <= AppTheme.BATAS_LEVEL_PEMULA) {
                kartu.setEnabled(false);                            // teks otomatis tampil abu-abu
            }

            panelKartu.add(kartu);
        }
        mainPanel.add(panelKartu, BorderLayout.CENTER);             // kartu ditaruh di tengah

        // ----- statistik rata-rata level di bagian bawah -----
        int totalLevel = 0;
        for (Character c : characterList) {
            totalLevel = totalLevel + c.getLevel();
        }
        double rataRata = 0;                                        // default 0 kalau list kosong
        if (characterList.size() > 0) {
            rataRata = (double) totalLevel / characterList.size();
        }
        JLabel statistik = new JLabel("Rata-rata Level: " + String.format("%.1f", rataRata),
                                      SwingConstants.CENTER);
        statistik.setFont(AppTheme.FONT_STATISTIK);
        statistik.setForeground(AppTheme.WARNA_TEKS);
        mainPanel.add(statistik, BorderLayout.SOUTH);

        add(mainPanel);                                             // tempatkan panel ke jendela
    }

}