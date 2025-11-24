package model;

import java.io.Serializable;

/**
 * Kelas yang merepresentasikan akun pengguna dalam sistem.
 * Menyimpan informasi kredensial dan data profil dasar serta role admin.
 * Mengimplementasikan {@link Serializable} agar dapat disimpan dalam bentuk file.
 */
public class UserAccount implements Serializable {

    /** Username atau identitas unik pengguna untuk login */
    private String username;

    /** Password pengguna (sebaiknya disimpan dalam bentuk terenkripsi di implementasi nyata) */
    private String password;

    /** Alamat email pengguna */
    private String email;

    /** Nomor telepon pengguna */
    private String phone;

    /** Menunjukkan apakah pengguna merupakan admin */
    private boolean isAdmin;
    
    /**
     * Membuat objek UserAccount baru dengan data yang diberikan.
     *
     * @param username nama pengguna untuk login
     * @param password kata sandi pengguna
     * @param email alamat email pengguna
     * @param phone nomor telepon pengguna
     * @param isAdmin status role admin
     */
    public UserAccount(String username, String password, String email, String phone, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.isAdmin = isAdmin;
    }

    /**
     * Mengambil username pengguna.
     *
     * @return username pengguna
     */
    public String getUsername() {
        return username;
    }

    /**
     * Mengubah username pengguna.
     *
     * @param username username baru
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Mengambil password pengguna.
     *
     * @return password pengguna
     */
    public String getPassword() {
        return password;
    }

    /**
     * Mengubah password pengguna.
     *
     * @param password password baru
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Mengambil email pengguna.
     *
     * @return email pengguna
     */
    public String getEmail() {
        return email;
    }

    /**
     * Mengubah email pengguna.
     *
     * @param email email baru
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Mengambil nomor telepon pengguna.
     *
     * @return nomor telepon
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Mengubah nomor telepon pengguna.
     *
     * @param phone nomor telepon baru
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Mengecek apakah pengguna memiliki hak akses admin.
     *
     * @return true jika admin, false jika bukan
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Mengatur role admin pengguna.
     *
     * @param admin nilai boolean untuk status admin baru
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * Menghasilkan representasi teks dari akun pengguna,
     * dipisahkan koma untuk keperluan penyimpanan data sederhana.
     *
     * @return string berisi username,password,email,phone,isAdmin
     */
    @Override
    public String toString() {
        return username + "," + password + "," + email + "," + phone + "," + isAdmin;
    }

    /**
     * Membuat objek UserAccount dari string yang diperoleh dari method toString().
     *
     * @param line string berisi data akun yang dipisahkan koma
     * @return objek UserAccount jika format valid, null jika data tidak sesuai
     */
    public static UserAccount fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 5) {
            return new UserAccount(parts[0], parts[1], parts[2], parts[3], Boolean.parseBoolean(parts[4]));
        }
        return null;
    }
}

