package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private static final String VERSI = "v0.5";     // [BARU - Bagian B] satu sumber untuk judul jendela dan label versi
    private ArrayList<Character> characterList;     // data dari Main.java (Model)

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager " + VERSI);        // [DIUBAH - Bagian B] judul memakai konstanta versi
        this.characterList = characterList;

        initComponents();                           // 1. susun semua area

        pack();                                     // 2. ukuran awal = preferred size isi
        setMinimumSize(new Dimension(560, 380));    // 3. batas terkecil yang masih rapi
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);                // 4. pusatkan SETELAH pack()
    }

    private void initComponents() {
        getContentPane().setBackground(AppTheme.BG_MAIN);  // warna di balik semua panel
        setLayout(new BorderLayout());                     // kerangka 4 wilayah

        add(createNorthPanel(),  BorderLayout.NORTH);      // [DIUBAH - Bagian B] header + panel statistik
        add(createListArea(),    BorderLayout.CENTER);     // daftar berscroll: satu-satunya yang tumbuh
        add(createLegendPanel(), BorderLayout.EAST);       // legenda role
        add(createStatusPanel(), BorderLayout.SOUTH);      // bar status
    }

    /** NORTH: judul di kiri, jumlah karakter di kanan. */
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());     // panel bersarang dengan layout sendiri
        header.setBackground(AppTheme.BG_MAIN);
        header.setBorder(BorderFactory.createEmptyBorder(16, 16, 8, 16)); // atas, kiri, bawah, kanan

        JLabel title = new JLabel("Character Manager");
        title.setFont(AppTheme.FONT_TITLE);
        title.setForeground(AppTheme.TEXT_MAIN);

        JLabel count = new JLabel(characterList.size() + " karakter");
        count.setFont(AppTheme.FONT_BODY);
        count.setForeground(AppTheme.GOLD);

        header.add(title, BorderLayout.WEST);               // rata kiri
        header.add(count, BorderLayout.EAST);               // rata kanan
        return header;
    }

    /** [BARU - Bagian B] NORTH: header di atas, panel statistik di bawahnya.
     *  Dibungkus satu panel karena wilayah NORTH hanya bisa berisi satu komponen. */
    private JPanel createNorthPanel() {
        JPanel utara = new JPanel(new BorderLayout());
        utara.setBackground(AppTheme.BG_MAIN);
        utara.add(createHeaderPanel(), BorderLayout.NORTH);
        utara.add(createStatistikPanel(), BorderLayout.CENTER);
        return utara;
    }

    /** [BARU - Bagian B] Panel statistik 2x2: empat sel berukuran sama, dihitung dari data. */
    private JPanel createStatistikPanel() {
        // hitung statistik dengan perulangan dasar
        int total = characterList.size();
        int jumlahLevel = 0;
        int levelTertinggi = 0;
        String namaTertinggi = "-";
        int jumlahLevelTinggi = 0;
        for (Character c : characterList) {
            jumlahLevel = jumlahLevel + c.getLevel();
            if (c.getLevel() > levelTertinggi) {             // cari level terbesar beserta namanya
                levelTertinggi = c.getLevel();
                namaTertinggi = c.getName();
            }
            if (c.getLevel() >= AppTheme.BATAS_LEVEL_TINGGI) {   // hitung karakter level tinggi
                jumlahLevelTinggi = jumlahLevelTinggi + 1;
            }
        }
        double rataRata = 0;                                 // default 0 kalau list kosong (hindari bagi dengan nol)
        if (total > 0) {
            rataRata = (double) jumlahLevel / total;
        }

        JPanel statistik = new JPanel(new GridLayout(2, 2, 8, 8));   // 2 baris x 2 kolom, jarak 8 px
        statistik.setBackground(AppTheme.BG_MAIN);
        statistik.setBorder(BorderFactory.createEmptyBorder(0, 16, 8, 16));

        statistik.add(createStatLabel("Total karakter", String.valueOf(total)));
        statistik.add(createStatLabel("Rata-rata level", String.format("%.1f", rataRata)));
        statistik.add(createStatLabel("Level tertinggi", levelTertinggi + " (" + namaTertinggi + ")"));
        statistik.add(createStatLabel("Level tinggi", String.valueOf(jumlahLevelTinggi)));
        return statistik;
    }

    /** [BARU - Bagian B] Satu sel statistik. */
    private JLabel createStatLabel(String judul, String nilai) {
        JLabel lbl = new JLabel(judul + ": " + nilai);
        lbl.setOpaque(true);                                 // wajib agar background tampak
        lbl.setBackground(AppTheme.BG_STATUS);
        lbl.setForeground(AppTheme.TEXT_MUTED);
        lbl.setFont(AppTheme.FONT_MONO);
        lbl.setBorder(AppTheme.CARD_BORDER);
        return lbl;
    }

    /** CENTER: kartu karakter di dalam JScrollPane. */
    private JScrollPane createListArea() {
        JPanel cards = new JPanel(new GridLayout(0, 1, 0, 8)); // satu kolom, jarak 8 px
        cards.setBackground(AppTheme.BG_MAIN);
        for (Character c : characterList) {
            cards.add(createCharacterLabel(c));              // kartu ditambah ke PANEL, bukan ke scroll pane
        }

        // holder: menaruh cards di NORTH agar kartu mempertahankan tinggi alaminya
        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(AppTheme.BG_MAIN);
        holder.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 8));
        holder.add(cards, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);        // bungkus HOLDER
        scroll.setBorder(null);                              // buang border bawaan
        scroll.getViewport().setBackground(AppTheme.BG_MAIN);
        scroll.setPreferredSize(new Dimension(460, 280));    // tanpa ini, banyak karakter = jendela raksasa
        return scroll;
    }

    /** Satu kartu karakter. */
    private JLabel createCharacterLabel(Character c) {
        String info = c.getName() + " | " + c.getType()
                    + " | Lv." + c.getLevel()
                    + " | HP:" + c.getHitPoints()
                    + " | ATK:" + c.getAttackPower();

        JLabel lbl = new JLabel(info);
        lbl.setFont(AppTheme.FONT_BODY);
        lbl.setForeground(AppTheme.TEXT_LIGHT);
        lbl.setBackground(AppTheme.colorFor(c.getType()));
        lbl.setOpaque(true);                                 // wajib agar background tampak
        lbl.setBorder(AppTheme.CARD_BORDER);
        lbl.setToolTipText(AppTheme.describe(c.getType()));
        lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return lbl;
    }

    /** EAST: legenda warna per role, ditumpuk vertikal dengan BoxLayout. */
    private JPanel createLegendPanel() {
        JPanel legend = new JPanel();
        legend.setLayout(new BoxLayout(legend, BoxLayout.Y_AXIS)); // target = panel ini sendiri, bukan this
        legend.setBackground(AppTheme.BG_MAIN);
        legend.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 16));

        JLabel head = new JLabel("Legenda Role");
        head.setFont(AppTheme.FONT_BODY.deriveFont(java.awt.Font.BOLD)); // tebalkan tanpa tahu nama font
        head.setForeground(AppTheme.TEXT_MAIN);
        legend.add(head);
        legend.add(Box.createVerticalStrut(8));             // spasi tetap 8 px

        for (CharacterType type : CharacterType.values()) {  // satu chip per role, diturunkan dari enum
            JLabel chip = new JLabel(type.name());
            chip.setOpaque(true);
            chip.setBackground(AppTheme.colorFor(type));
            chip.setForeground(AppTheme.TEXT_LIGHT);
            chip.setFont(AppTheme.FONT_MONO);
            chip.setBorder(AppTheme.CARD_BORDER);
            chip.setToolTipText(AppTheme.describe(type));
            legend.add(chip);
            legend.add(Box.createVerticalStrut(6));
        }
        legend.add(Box.createVerticalGlue());               // dorong semua ke atas
        return legend;
    }

    /** [DIUBAH - Bagian B] SOUTH: bar status, teks di kiri dan versi di kanan. */
    private JPanel createStatusPanel() {
        JPanel status = new JPanel(new BorderLayout());     // dua kutub kiri dan kanan
        status.setBackground(AppTheme.BG_STATUS);
        status.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));

        JLabel lbl = new JLabel("Total: " + characterList.size() + " karakter  |  Siap");
        lbl.setFont(AppTheme.FONT_MONO);
        lbl.setForeground(AppTheme.TEXT_MUTED);

        JLabel versi = new JLabel(VERSI);                   // label versi, tema sama dengan teks status
        versi.setFont(AppTheme.FONT_MONO);
        versi.setForeground(AppTheme.TEXT_MUTED);

        status.add(lbl, BorderLayout.WEST);                 // rata kiri
        status.add(versi, BorderLayout.EAST);               // rata kanan
        return status;
    }

}