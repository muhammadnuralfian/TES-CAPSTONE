package view;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import model.Laporan;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Dialog untuk mengedit laporan dengan tampilan sesuai sistem utama.
 * @author alfian
 */
public class EditLaporanDialog extends JDialog {
    private final JTextField txtLokasi = new JTextField(20);
    private final JTextField txtJenisMasalah = new JTextField(20);
    private final JTextField txtDeskripsi = new JTextField(20);
    private final JDateChooser dateChooser = new JDateChooser();
    private final JButton btnSimpan = new JButton("Simpan");
    private final JButton btnBatal = new JButton("Batal");
    private boolean isSaved = false;

    public EditLaporanDialog(JFrame parent, Laporan laporan) {
        super(parent, "Edit Laporan", true);
        setSize(500, 380);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null); // selalu muncul di tengah layar

        // 🔹 Warna dan Font utama
        Color toska = new Color(0, 102, 102);
        Font labelFont = new Font("Berlin Sans FB", Font.PLAIN, 16);

        // 🔹 Background gambar seperti form utama
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/images/3.png")));
        background.setBounds(0, 0, 500, 380);

        // 🔹 Judul
        JLabel lblJudul = new JLabel("Edit Data Laporan");
        lblJudul.setFont(new Font("Berlin Sans FB", Font.BOLD, 24));
        lblJudul.setForeground(toska);
        lblJudul.setBounds(150, 20, 250, 30);
        add(lblJudul);

        // 🔹 Label dan Field
        JLabel lblTanggal = new JLabel("Tanggal Laporan:");
        lblTanggal.setFont(labelFont);
        lblTanggal.setForeground(toska);
        lblTanggal.setBounds(60, 80, 150, 25);
        add(lblTanggal);
        dateChooser.setBounds(220, 80, 200, 25);
        dateChooser.setDate(laporan.getTanggalLaporan());
        add(dateChooser);

        JLabel lblLokasi = new JLabel("Lokasi Laporan:");
        lblLokasi.setFont(labelFont);
        lblLokasi.setForeground(toska);
        lblLokasi.setBounds(60, 120, 150, 25);
        add(lblLokasi);
        txtLokasi.setBounds(220, 120, 200, 25);
        txtLokasi.setText(laporan.getLokasiLaporan());
        add(txtLokasi);

        JLabel lblJenis = new JLabel("Jenis Masalah:");
        lblJenis.setFont(labelFont);
        lblJenis.setForeground(toska);
        lblJenis.setBounds(60, 160, 150, 25);
        add(lblJenis);
        txtJenisMasalah.setBounds(220, 160, 200, 25);
        txtJenisMasalah.setText(laporan.getJenisMasalah());
        add(txtJenisMasalah);

        JLabel lblDeskripsi = new JLabel("Deskripsi:");
        lblDeskripsi.setFont(labelFont);
        lblDeskripsi.setForeground(toska);
        lblDeskripsi.setBounds(60, 200, 150, 25);
        add(lblDeskripsi);
        txtDeskripsi.setBounds(220, 200, 200, 25);
        txtDeskripsi.setText(laporan.getDeskripsi());
        add(txtDeskripsi);

        // 🔹 Tombol SIMPAN
        btnSimpan.setFont(new Font("Berlin Sans FB", Font.BOLD, 14));
        btnSimpan.setBackground(toska);
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFocusPainted(false);
        btnSimpan.setBorder(BorderFactory.createBevelBorder(1));
        btnSimpan.setBounds(180, 270, 100, 35);
        btnSimpan.addActionListener((ActionEvent e) -> simpanData(laporan));
        add(btnSimpan);

        // 🔹 Tombol BATAL
        btnBatal.setFont(new Font("Berlin Sans FB", Font.BOLD, 14));
        btnBatal.setBackground(new Color(255, 204, 252));
        btnBatal.setForeground(toska);
        btnBatal.setFocusPainted(false);
        btnBatal.setBorder(BorderFactory.createBevelBorder(1));
        btnBatal.setBounds(300, 270, 100, 35);
        btnBatal.addActionListener(e -> dispose());
        add(btnBatal);

        // 🔹 Tambahkan background paling bawah
        add(background);
    }

    private void simpanData(Laporan laporan) {
        if (dateChooser.getDate() == null || 
            txtLokasi.getText().trim().isEmpty() || 
            txtJenisMasalah.getText().trim().isEmpty() || 
            txtDeskripsi.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, 
                "Semua field wajib diisi!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        laporan.setTanggalLaporan(dateChooser.getDate());
        laporan.setLokasiLaporan(txtLokasi.getText().trim());
        laporan.setJenisMasalah(txtJenisMasalah.getText().trim());
        laporan.setDeskripsi(txtDeskripsi.getText().trim());
        isSaved = true;

        JOptionPane.showMessageDialog(this, 
            "✅ Perubahan berhasil disimpan!", 
            "Sukses", 
            JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    public boolean isSaved() {
        return isSaved;
    }
}
