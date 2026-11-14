package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import com.unpri.charactermanager.model.CharacterType;

/**
 * Pusat tema tampilan aplikasi: semua warna, font, dan border ada di sini.
 * Semua anggotanya static, jadi dipakai langsung tanpa membuat object,
 * contoh: AppTheme.WARNA_LATAR
 */
public class AppTheme {

    // ===== WARNA UMUM =====
    public static final Color WARNA_LATAR = new Color(250, 250, 250);   // latar jendela
    public static final Color WARNA_TEKS = new Color(30, 30, 30);       // warna teks
    public static final Color WARNA_GARIS = new Color(150, 150, 150);   // garis tepi kartu

    // ===== WARNA KARTU PER ROLE =====
    public static final Color WARNA_WARRIOR = new Color(235, 200, 200);
    public static final Color WARNA_MAGE = new Color(200, 210, 235);
    public static final Color WARNA_ARCHER = new Color(205, 230, 205);
    public static final Color WARNA_HEALER = new Color(240, 230, 190);

    // ===== FONT =====
    public static final Font FONT_JUDUL = new Font("SansSerif", Font.BOLD, 18);
    public static final Font FONT_KARTU = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font FONT_STATISTIK = new Font("SansSerif", Font.BOLD, 14);

    // ===== BORDER =====
    public static final Border BORDER_PANEL = BorderFactory.createEmptyBorder(12, 12, 12, 12);
    public static final Border BORDER_KARTU = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(WARNA_GARIS),
            BorderFactory.createEmptyBorder(8, 12, 8, 12));

    // ===== KONSTANTA SPRINT #4 (level tinggi dan pemula) =====
    public static final int BATAS_LEVEL_TINGGI = 5;    // level >= nilai ini dihitung sebagai level tinggi
    public static final int BATAS_LEVEL_PEMULA = 1;    // level <= nilai ini dianggap pemula
    public static final Color WARNA_EMAS = new Color(184, 134, 11);
    public static final Font FONT_KARTU_TEBAL = new Font("SansSerif", Font.BOLD, 14);

    // ===== [TP12 Sprint #5] NAMA TAMBAHAN UNTUK MAINWINDOW v0.5 =====
    // Harus berada DI BAWAH konstanta yang ditunjuknya (kalau di atas: error "illegal forward reference")
    public static final Color BG_MAIN      = WARNA_LATAR;
    public static final Color TEXT_MAIN    = WARNA_TEKS;
    public static final Color TEXT_LIGHT   = WARNA_TEKS;     // kartu pastel, jadi teks tetap gelap
    public static final Color GOLD         = WARNA_EMAS;
    public static final Color BG_STATUS    = Color.decode("#0d0d1a");   // latar bar status: lebih gelap
    public static final Color TEXT_MUTED   = Color.decode("#9d9dbf");   // teks sekunder
    public static final Font FONT_TITLE    = FONT_JUDUL;
    public static final Font FONT_BODY     = FONT_KARTU;
    public static final Font FONT_MONO     = new Font("Monospaced", Font.PLAIN, 13);
    public static final Border CARD_BORDER = BORDER_KARTU;

    // ===== METHOD: pilih warna kartu berdasarkan role =====
    public static Color colorFor(CharacterType type) {
        switch (type) {
            case WARRIOR:
                return WARNA_WARRIOR;
            case MAGE:
                return WARNA_MAGE;
            case ARCHER:
                return WARNA_ARCHER;
            case HEALER:
                return WARNA_HEALER;
            default:
                return WARNA_LATAR;   // cadangan kalau ada tipe baru
        }
    }

    // ===== [TP12 Sprint #5] METHOD: teks tooltip untuk tiap role =====
    public static String describe(CharacterType type) {
        switch (type) {
            case WARRIOR:
                return "WARRIOR: HP dan attack tinggi";
            case MAGE:
                return "MAGE: attack tinggi, HP rendah";
            case ARCHER:
                return "ARCHER: seimbang, serangan jarak jauh";
            case HEALER:
                return "HEALER: HP tinggi, attack rendah";
            default:
                return type.name();
        }
    }
}