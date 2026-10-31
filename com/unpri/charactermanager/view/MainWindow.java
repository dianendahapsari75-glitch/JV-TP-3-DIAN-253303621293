package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import com.unpri.charactermanager.model.Character;

public class MainWindow extends JFrame {

    private JPanel mainPanel;                      // container utama
    private ArrayList<Character> characterList;    // data dari Main.java (Model)

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager v0.4");           // versi naik: sprint #4
        this.characterList = characterList;

        initComponents();                          // 1. susun semua komponen dulu

        pack();                                    // 2. ukuran jendela mengikuti isi
        setMinimumSize(new Dimension(420, 300));   // 3. cegah jendela terlalu kecil
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);               // 4. pusatkan SETELAH pack()
    }

    private void initComponents() {
        // GridLayout(0, 1, 0, 8): berapa pun baris, 1 kolom, jarak vertikal 8 px.
        // (Layout dibahas penuh di Pertemuan 5; di sini dipakai agar kartu tersusun rapi.)
        mainPanel = new JPanel(new GridLayout(0, 1, 0, 8));
        mainPanel.setBackground(AppTheme.BG_MAIN);                           // warna latar panel
        mainPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16)); // padding luar

        mainPanel.add(createTitleLabel());          // judul di atas

        for (Character c : characterList) {          // satu kartu per karakter
            mainPanel.add(createCharacterLabel(c));
        }

        add(mainPanel);                             // pasang panel ke JFrame
    }

    /** Membuat judul layar. */
    private JLabel createTitleLabel() {
        JLabel lbl = new JLabel("Daftar Karakter (" + characterList.size() + ")",
                                SwingConstants.CENTER);   // teks di tengah label
        lbl.setFont(AppTheme.FONT_TITLE);                 // font judul dari tema
        lbl.setForeground(AppTheme.TEXT_MAIN);            // warna teks judul
        return lbl;
    }

    /** Membuat satu kartu karakter (JLabel berwarna sesuai role). */
    private JLabel createCharacterLabel(Character c) {
        String info = c.getName() + " | " + c.getType()
                    + " | Lv." + c.getLevel()
                    + " | HP:" + c.getHitPoints()
                    + " | ATK:" + c.getAttackPower();     // hanya MEMBACA lewat getter

        JLabel lbl = new JLabel(info);
        lbl.setFont(AppTheme.FONT_BODY);                          // font isi
        lbl.setForeground(AppTheme.TEXT_LIGHT);                   // teks putih
        lbl.setBackground(AppTheme.colorFor(c.getType()));        // warna dari role
        lbl.setOpaque(true);                                      // WAJIB agar background tampak
        lbl.setBorder(AppTheme.CARD_BORDER);                      // padding kartu
        lbl.setToolTipText(AppTheme.describe(c.getType()));       // bantuan saat hover
        lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // kursor tangan
        return lbl;
    }
}