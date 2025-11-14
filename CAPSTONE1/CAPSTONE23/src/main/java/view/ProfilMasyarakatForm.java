package view;

import javax.swing.*;
import java.awt.*;
import dao.AkunPenggunaDAO;
import dao.AkunPenggunaDAOImpl;
import model.AkunPengguna;

public class ProfilMasyarakatForm extends JFrame {

    private final JTextField txtNama = new JTextField();
    private final JPasswordField txtPassword = new JPasswordField();
    private final JTextField txtAlamat = new JTextField();
    private final JTextField txtKontak = new JTextField();
    private final JTextField txtJenisKelamin = new JTextField();
    private final JTextField txtTanggalDaftar = new JTextField();

    private final JButton btnSimpan = new JButton("SIMPAN");
    private final JButton btnKembali = new JButton("KEMBALI");

    private final AkunPengguna akun;

    public ProfilMasyarakatForm(AkunPengguna akun) {
        super("Profil Masyarakat");
        this.akun = akun;
        initUI();
        bindData();
    }

    private void initUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 520);
        setLocationRelativeTo(null);
        setResizable(false);

         //🌿 Background gradasi lembut (hijau muda → krem pastel)
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(214, 255, 229),
                    getWidth(), getHeight(), new Color(255, 250, 240)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(new GridBagLayout());
        add(background);

         //📋 Panel utama (card)
        JPanel card = new JPanel(new GridBagLayout());
        card.setPreferredSize(new Dimension(520, 400));
        card.setBackground(new Color(240, 255, 245, 240));
        card.setOpaque(true);

         //🟢 Border dengan efek bayangan lembut
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 128, 110), 1, true),
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(new Color(0, 0, 0, 30), 10, true)  //bayangan lembut
            )
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 🏷️ Judul
        JLabel lblTitle = new JLabel("Profil Masyarakat");
        lblTitle.setFont(new Font("Berlin Sans FB", Font.BOLD, 26));
        lblTitle.setForeground(new Color(0, 102, 102));

        JLabel lblDesc = new JLabel("Perbarui informasi pribadi Anda di bawah ini:");
        lblDesc.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblDesc.setForeground(new Color(60, 80, 80));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        card.add(lblTitle, gbc);
        gbc.gridy++;
        card.add(lblDesc, gbc);
        gbc.gridwidth = 1;

        // 🧩 Input fields
        addField(card, gbc, "Nama", txtNama);
        addField(card, gbc, "Password", txtPassword);
        addField(card, gbc, "Alamat", txtAlamat);
        addField(card, gbc, "Kontak", txtKontak);

        txtJenisKelamin.setEditable(false);
        txtTanggalDaftar.setEditable(false);
        addField(card, gbc, "Jenis Kelamin", txtJenisKelamin);
        addField(card, gbc, "Tanggal Daftar", txtTanggalDaftar);

        // 🎛 Tombol panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        buttonPanel.setOpaque(false);

        // 🟩 Tombol SIMPAN
        btnSimpan.setBackground(new Color(0, 102, 102));
        btnSimpan.setFont(new Font("Berlin Sans FB", Font.BOLD, 13));
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFocusPainted(false);
        btnSimpan.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        btnSimpan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSimpan.addActionListener(e -> simpanProfil());
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSimpan.setBackground(new Color(0, 128, 128));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSimpan.setBackground(new Color(0, 102, 102));
            }
        });

        // 💗 Tombol KEMBALI
        btnKembali.setBackground(new Color(255, 204, 252));
        btnKembali.setFont(new Font("Berlin Sans FB", Font.BOLD, 13));
        btnKembali.setForeground(new Color(0, 102, 102));
        btnKembali.setFocusPainted(false);
        btnKembali.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        btnKembali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnKembali.addActionListener(e -> {
            new PelaporanMasyarakatForm(akun).setVisible(true);
            dispose();
        });
        btnKembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKembali.setBackground(new Color(255, 180, 240));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKembali.setBackground(new Color(255, 204, 252));
            }
        });

        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnKembali);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        card.add(buttonPanel, gbc);

        background.add(card);
    }

    // 🔹 Menambahkan field form
    private void addField(JPanel card, GridBagConstraints gbc, String label, JComponent input) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        lbl.setForeground(new Color(0, 102, 102));

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0.3;
        card.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;

        input.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        input.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 128, 110), 1, true),
            BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
        card.add(input, gbc);
    }

     //🔹 Bind data dari database
    private void bindData() {
        if (akun != null) {
            txtNama.setText(akun.getUsername());
            txtPassword.setText(akun.getPassword());
            txtAlamat.setText(akun.getAlamat());
            txtKontak.setText(akun.getKontak());
            txtJenisKelamin.setText("Laki-laki");  //sementara (ambil dari DB nanti)
            txtTanggalDaftar.setText("2025-11-04"); // sementara (ambil dari DB nanti)
        }
    }

    // 🔹 Simpan data profil
    private void simpanProfil() {
        akun.setUsername(txtNama.getText());
        akun.setPassword(new String(txtPassword.getPassword()));
        akun.setAlamat(txtAlamat.getText());
        akun.setKontak(txtKontak.getText());

        AkunPenggunaDAO dao = new AkunPenggunaDAOImpl();
        if (dao.updateAkun(akun)) {
            JOptionPane.showMessageDialog(this, "✅ Profil berhasil diperbarui!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Gagal memperbarui profil!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
