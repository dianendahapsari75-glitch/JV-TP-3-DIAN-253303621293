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
    // jarak kosong di sekeliling panel utama
    public static final Border BORDER_PANEL = BorderFactory.createEmptyBorder(12, 12, 12, 12);
    // garis tipis + jarak dalam untuk setiap kartu
    public static final Border BORDER_KARTU = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(WARNA_GARIS),
            BorderFactory.createEmptyBorder(8, 12, 8, 12));

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
}