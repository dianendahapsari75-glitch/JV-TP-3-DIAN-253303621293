package com.unpri.charactermanager.model;

/**
 * Merepresentasikan satu karakter dalam aplikasi Character Manager.
 * Menerapkan prinsip Encapsulation: semua atribut private,
 * akses hanya melalui getter/setter.
 */
public class Character {

    // ===== ATRIBUT (private — tidak bisa diakses langsung dari luar class) =====
    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;   // [BARU - TP3 Bagian B] atribut mana, private sesuai Encapsulation

    // ===== CONSTRUCTOR 1: untuk membuat karakter BARU =====
    // Dipakai saat user membuat karakter dari form (belum punya id dari database)
    public Character(String name, CharacterType type, int level) {
        this.name = name;              // "this" merujuk ke atribut milik object ini
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();      // HP dihitung otomatis dari level
        this.attackPower = calculateBaseAttack(); // Attack dihitung otomatis dari level
    }

    // ===== CONSTRUCTOR 2: untuk MEMUAT karakter dari database =====
    // Dipakai saat data sudah ada (id dan statistik sudah final dari DB)
    public Character(int id, String name, CharacterType type, int level, int hitPoints, int attackPower) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
    }

    // ===== HELPER PRIVATE: hanya dipakai internal class ini =====
    private int calculateBaseHP() {
        return 100 + (level * 10);   // contoh: level 5 → HP = 150
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);     // contoh: level 5 → Attack = 35
    }

    // ===== GETTER: cara AMAN membaca nilai atribut private dari luar =====
    public int getId() { return id; }
    public String getName() { return name; }
    public CharacterType getType() { return type; }
    public int getLevel() { return level; }
    public int getHitPoints() { return hitPoints; }
    public int getAttackPower() { return attackPower; }

    // ===== SETTER: cara AMAN mengubah atribut, dengan VALIDASI =====
    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int level) {
        if (level < 1) {                       // validasi: level tidak boleh kurang dari 1
            throw new IllegalArgumentException("Level minimal adalah 1");
        }
        this.level = level;
        this.hitPoints = calculateBaseHP();      // statistik dihitung ULANG setiap level berubah
        this.attackPower = calculateBaseAttack();
    }
        // [BARU - TP3] setter untuk atribut yang sebelumnya belum punya setter
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {   // validasi: nama tidak boleh kosong
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        this.name = name;
    }

    public void setType(CharacterType type) {
        if (type == null) {                            // validasi: jenis karakter harus diisi
            throw new IllegalArgumentException("Jenis karakter tidak boleh null");
        }
        this.type = type;
    }

    public void setHitPoints(int hitPoints) {
        if (hitPoints < 0) {                           // validasi: HP tidak boleh negatif
            throw new IllegalArgumentException("HP tidak boleh negatif");
        }
        this.hitPoints = hitPoints;
    }

    public void setAttackPower(int attackPower) {
        if (attackPower < 0) {                         // validasi: attack tidak boleh negatif
            throw new IllegalArgumentException("Attack tidak boleh negatif");
        }
        this.attackPower = attackPower;
    }
        // [BARU - TP3 Bagian B] getter mana
    public int getMana() { return mana; }

    // [BARU - TP3 Bagian B] setter mana dengan validasi
    public void setMana(int mana) {
        if (mana < 0) {                                // validasi: mana tidak boleh negatif
            throw new IllegalArgumentException("Mana tidak boleh negatif");
        }
        this.mana = mana;
    }

    // [BARU - TP3 Bagian B] cek apakah karakter masih hidup
    public boolean isAlive() {
        return hitPoints > 0;                          // true kalau HP lebih dari 0
    }

    // ===== toString(): representasi teks object, berguna untuk debugging =====
    @Override
    public String toString() {
        return "Character{id=" + id +
               ", name='" + name + '\'' +
               ", type=" + type +
               ", level=" + level +
               ", hp=" + hitPoints +
               ", atk=" + attackPower +
               ", mana=" + mana + '}';     // [DIUBAH - TP3 Bagian B] tambah mana di output
    
    }   // kurung kurawal penutup CLASS Character — pastikan jumlah buka/tutup seimbang!
    // ===== equals(): membandingkan ISI DATA, bukan alamat memori =====
    // Dua Character dianggap "sama" jika NAMA-nya identik (case-sensitive)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                    // optimasi: jika memang object yang sama persis
        if (!(obj instanceof Character)) return false;    // jika bukan Character, pasti tidak sama
        Character other = (Character) obj;                // casting aman karena sudah dicek instanceof
        return this.name.equals(other.name);               // bandingkan isi atribut name (String punya equals sendiri)
    }


}