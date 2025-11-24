package model;

/**
 * Kelas Admin mewakili akun admin dan mewarisi atribut dasar dari Akun.
 * Digunakan untuk operasi khusus admin dalam sistem.
 */


public class Admin extends Akun {

    public Admin(String id, String username, String password) {
        super(id, username, password);
    }
}

