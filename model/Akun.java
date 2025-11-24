package model;

/**
 * Kelas Akun berfungsi sebagai kelas dasar untuk Admin dan Customer.
 * Menyimpan data umum seperti id, username, dan password,
 * serta menyediakan fungsi login sederhana.
 */

public class Akun {
    protected String id;
    protected String username;
    protected String password;

    public Akun(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public boolean login(String u, String p) {
        return username.equals(u) && password.equals(p);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
