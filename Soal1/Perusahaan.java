package Soal1;

//reminder:
//untuk mengubah gaji dan posisi masukin id lalu ketik jabatan atau gaji untuk perubahannya

//import library swing ui dan array list biar dinamis
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

//menggunakan jframe dari swing
public class Perusahaan extends JFrame {
    //atribut
    private ArrayList<Karyawan> daftarKaryawan = new ArrayList<>();
    private JTextArea areaTampilan;
    private JTextField fieldId, fieldNama, fieldPosisi, fieldGaji;

    //constructor
    public Perusahaan() {
        setTitle("Sistem Manajemen Karyawan");
        setSize(700, 550);
        setLocationRelativeTo(null); // Center window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // tampilan menggunakan swing UI menggunakan gridbag layout
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBorder(BorderFactory.createTitledBorder("Data Karyawan"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //label dan field untuk mengisi inputan
        JLabel lblId = new JLabel("ID:");
        fieldId = new JTextField(15);

        JLabel lblNama = new JLabel("Nama:");
        fieldNama = new JTextField(15);

        JLabel lblPosisi = new JLabel("Posisi:");
        fieldPosisi = new JTextField(15);

        JLabel lblGaji = new JLabel("Gaji:");
        fieldGaji = new JTextField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        panelInput.add(lblId, gbc);
        gbc.gridx = 1;
        panelInput.add(fieldId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelInput.add(lblNama, gbc);
        gbc.gridx = 1;
        panelInput.add(fieldNama, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelInput.add(lblPosisi, gbc);
        gbc.gridx = 1;
        panelInput.add(fieldPosisi, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelInput.add(lblGaji, gbc);
        gbc.gridx = 1;
        panelInput.add(fieldGaji, gbc);

        // Panel Tombol
        JPanel panelButton = new JPanel(new GridLayout(2, 2, 10, 10));
        panelButton.setBorder(BorderFactory.createTitledBorder("Aksi"));

        JButton btnTambah = new JButton("Tambah");
        JButton btnHapus = new JButton("Hapus");
        JButton btnUbahPosisi = new JButton("Ubah Posisi");
        JButton btnUbahGaji = new JButton("Ubah Gaji");

        panelButton.add(btnTambah);
        panelButton.add(btnHapus);
        panelButton.add(btnUbahPosisi);
        panelButton.add(btnUbahGaji);

        // Panel  input dan tombol jadi satu
        JPanel panelAtas = new JPanel(new BorderLayout(10, 10));
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelButton, BorderLayout.EAST);

        areaTampilan = new JTextArea();
        areaTampilan.setEditable(false);
        areaTampilan.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(areaTampilan);

        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Event Button untuk action
        btnTambah.addActionListener(e -> tambahKaryawan());
        btnHapus.addActionListener(e -> hapusKaryawan());
        btnUbahPosisi.addActionListener(e -> ubahPosisi());
        btnUbahGaji.addActionListener(e -> ubahGaji());

        tampilkanKaryawan();
    }

    private void tambahKaryawan() {
        try {
            String id = fieldId.getText().trim();
            String nama = fieldNama.getText().trim();
            String posisi = fieldPosisi.getText().trim();
            double gaji = Double.parseDouble(fieldGaji.getText().trim());

            if (id.isEmpty() || nama.isEmpty() || posisi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
                return;
            }

            //gaji tidak boleh negatif
            if (gaji < 0) {
                JOptionPane.showMessageDialog(this, "Gaji tidak boleh negatif.");
                return;
            }

            //validasi id tidak duplikat
            if (cariKaryawan(id) == null) {
                daftarKaryawan.add(new Karyawan(id, nama, posisi, gaji));
                JOptionPane.showMessageDialog(this, "Karyawan berhasil ditambahkan.");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "ID sudah ada!");
            }
            tampilkanKaryawan();
        }
        //jika gaji berupa huruf
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Gaji harus berupa angka!");
        }
    }

    //menghapus karyawan berdasarkan id
    private void hapusKaryawan() {
        String id = fieldId.getText().trim();
        Karyawan karyawan = cariKaryawan(id);
        if (karyawan != null) {
            daftarKaryawan.remove(karyawan);
            JOptionPane.showMessageDialog(this, "Karyawan berhasil dihapus.");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Karyawan tidak ditemukan.");
        }
        tampilkanKaryawan();
    }

    //ubah posisi karyawan berdasarkan id
    private void ubahPosisi() {
        String id = fieldId.getText().trim();
        String posisiBaru = fieldPosisi.getText().trim();
        Karyawan karyawan = cariKaryawan(id);
        if (karyawan != null) {
            karyawan.setPosisi(posisiBaru);
            JOptionPane.showMessageDialog(this, "Posisi berhasil diubah.");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Karyawan tidak ditemukan.");
        }
        tampilkanKaryawan();
    }

    //ubah gaji dari id karyawan
    private void ubahGaji() {
        try {
            String id = fieldId.getText().trim();
            double gajiBaru = Double.parseDouble(fieldGaji.getText().trim());
            if (gajiBaru < 0) {
                JOptionPane.showMessageDialog(this, "Gaji tidak boleh negatif.");
                return;
            }

            Karyawan karyawan = cariKaryawan(id);
            if (karyawan != null) {
                karyawan.setGaji(gajiBaru);
                JOptionPane.showMessageDialog(this, "Gaji berhasil diubah.");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Karyawan tidak ditemukan.");
            }
            tampilkanKaryawan();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Gaji harus berupa angka!");
        }
    }

    private Karyawan cariKaryawan(String id) {
        for (Karyawan k : daftarKaryawan) {
            if (k.getId().equalsIgnoreCase(id)) {
                return k;
            }
        }
        return null;
    }

    //menampilkan karyawan
    private void tampilkanKaryawan() {
        areaTampilan.setText("");
        if (daftarKaryawan.isEmpty()) {
            areaTampilan.setText("Belum ada karyawan.");
            return;
        }
        for (Karyawan karyawan : daftarKaryawan) {
            areaTampilan.append(karyawan.toString() + "\n");
        }
    }

    private void clearFields() {
        fieldId.setText("");
        fieldNama.setText("");
        fieldPosisi.setText("");
        fieldGaji.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Perusahaan().setVisible(true);
        });
    }
}
