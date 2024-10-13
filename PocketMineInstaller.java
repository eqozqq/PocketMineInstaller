import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class PocketMineInstaller extends JFrame {

    private static JFrame mainFrame;
    private JPanel contentPanel;
    private JTextField searchField;
    private Map<String, VersionInfo> versions;
    private JPanel versionsPanel;
    private boolean cancelDownload;

    public PocketMineInstaller() {
        super("PocketMine Installer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterVersions(searchField.getText());
            }
        });

        contentPanel.add(searchField);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        versionsPanel = new JPanel();
        versionsPanel.setLayout(new BoxLayout(versionsPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(versionsPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPanel.add(scrollPane);

        populateVersions();
        filterVersions("");
        add(contentPanel);
    }

    private void populateVersions() {
        versions = new LinkedHashMap<>();

        versions.put("PocketMine-MP_Installer_0.2", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_0.2.exe", ""));
        versions.put("PocketMine-MP_Installer_0.3", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_0.3.exe", ""));
        versions.put("PocketMine-MP_Installer_0.4", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_0.4.exe", ""));    

        versions.put("1.0", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.0/PocketMine-MP-Alpha_1.0.zip"));
        versions.put("1.0 test", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.0/PocketMine-MP-Alpha_1.0_test.zip"));
        versions.put("1.0.1", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.0/PocketMine-MP-Alpha_1.0.1.zip"));
        versions.put("1.0.2", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.0/PocketMine-MP-Alpha_1.0.2.zip"));
        versions.put("1.0.3", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.0/PocketMine-MP-Alpha_1.0.3.zip"));
        versions.put("1.0.4", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.0/PocketMine-MP-Alpha_1.0.4.zip"));
        versions.put("1.0.5", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.0/PocketMine-MP-Alpha_1.0.5.zip"));
        versions.put("1.0.6", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.0/PocketMine-MP-Alpha_1.0.6.zip"));
        versions.put("1.0.7", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_0.5.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.0/PocketMine-MP-Alpha_1.0.7.zip"));
        versions.put("1.0.8", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_0.6.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.0/PocketMine-MP-Alpha_1.0.8.zip"));

        versions.put("1.1", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.1/PocketMine-MP-Alpha_1.1.zip"));
        versions.put("1.1.1", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_0.7.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.1/PocketMine-MP-Alpha_1.1.1.zip"));
        versions.put("1.2", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_0.8.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.2/PocketMine-MP-Alpha_1.2.zip"));
        versions.put("1.2.1", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_0.9.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.2/PocketMine-MP-Alpha_1.2.1.zip"));
        versions.put("1.2.2", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_1.0.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.2/PocketMine-MP-Alpha_1.2.2.zip"));

        versions.put("1.3", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.3.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.3/PocketMine-MP-Alpha_1.3.zip"));
        versions.put("1.3.1", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.3.1.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.3/PocketMine-MP-Alpha_1.3.1.zip"));
        versions.put("1.3.2", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.3.2.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.3/PocketMine-MP-Alpha_1.3.2.zip"));
        versions.put("1.3.3", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.3.3.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.3/PocketMine-MP-Alpha_1.3.3.zip"));
        versions.put("1.3.4", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.3.4.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.3/PocketMine-MP-Alpha_1.3.4.zip"));
        versions.put("1.3.5", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.3.5.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.3/PocketMine-MP-Alpha_1.3.5.zip"));
        versions.put("1.3.6", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.3.6.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.3/PocketMine-MP-Alpha_1.3.6.zip"));
        versions.put("1.3.7", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.3.7.exe", "https://github.com/PocketMine/PocketMine-MP/archive/refs/tags/Alpha_1.3.7.zip"));
        versions.put("1.3.8", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.3.8.exe", "https://github.com/PocketMine/PocketMine-MP/archive/refs/tags/Alpha_1.3.8.zip"));
        versions.put("1.3.9", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.3.9.exe", "https://github.com/PocketMine/PocketMine-MP/archive/refs/tags/Alpha_1.3.9.zip"));
        versions.put("1.3.10", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.3.10.exe", "https://github.com/PocketMine/PocketMine-MP/archive/refs/tags/Alpha_1.3.10.zip"));
        versions.put("1.3.11", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.3.11.exe", "https://github.com/PocketMine/PocketMine-MP/archive/refs/tags/Alpha_1.3.11.zip"));
        versions.put("1.3.12", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.3.12.exe", "https://github.com/PocketMine/PocketMine-MP/archive/refs/tags/Alpha_1.3.12.zip"));

        versions.put("1.4", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_1.4_x86.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/PocketMine-MP-1.4.zip"));
        versions.put("1.4.1", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_1.4.1_x86.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/PocketMine-MP-1.4.1.zip"));
        versions.put("1.4.1dev-936", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-1.4.1dev-936.zip"));
        versions.put("1.4-916", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-1.4-916.zip"));
        versions.put("Alpha_1.4dev-228", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-228.zip"));
        versions.put("Alpha_1.4dev-277", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-277.zip"));
        versions.put("Alpha_1.4dev-413", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-413.zip"));
        versions.put("Alpha_1.4dev-449", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-449.zip"));
        versions.put("Alpha_1.4dev-478", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-478.zip"));
        versions.put("Alpha_1.4dev-491", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-491.zip"));
        versions.put("Alpha_1.4dev-576", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-576.zip"));
        versions.put("Alpha_1.4dev-599", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-599.zip"));
        versions.put("Alpha_1.4dev-659", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-659.zip"));
        versions.put("Alpha_1.4dev-665", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-665.zip"));
        versions.put("Alpha_1.4dev-707", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-707.zip"));
        versions.put("Alpha_1.4dev-822", new VersionInfo("https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/installers/PocketMine-MP_Installer_Alpha_1.4dev-822_x86.exe", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-822.zip"));
        versions.put("Alpha_1.4dev-834", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-834.zip"));
        versions.put("Alpha_1.4dev-842", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-842.zip"));
        versions.put("Alpha_1.4dev-847", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-847.zip"));
        versions.put("Alpha_1.4dev-855", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-855.zip"));
        versions.put("Alpha_1.4dev-900", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-Alpha_1.4dev-900.zip"));
        versions.put("api-1.11.0", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-api-1.11.0.zip"));
        versions.put("api-1.12.0", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.4/dev-builds/PocketMine-MP-api-1.12.0.zip"));

        versions.put("1.5dev", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.5dev/PocketMine-MP-1.5dev.zip"));

        versions.put("1.6.1dev-87", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.6dev/PocketMine-MP-1.6.1dev-87.zip"));

        versions.put("1.7dev-27", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-1.7dev-27.zip"));
        versions.put("1.7dev-83", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-1.7dev-83.zip"));
        versions.put("1.7dev-318", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-1.7dev-318.zip"));
        versions.put("1.7dev-501", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-1.7dev-501.zip"));
        versions.put("1.7dev-516", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-1.7dev-516.zip"));
        versions.put("1.7dev-677", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-1.7dev-677.zip"));
        versions.put("1.7dev-698", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-1.7dev-698.zip"));
        versions.put("1.7dev-703", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-1.7dev-703.zip"));
        versions.put("1.7dev-717", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-1.7dev-717.zip"));
        versions.put("1.7dev-743", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-1.7dev-743.zip"));
        versions.put("1.7dev-937", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-1.7dev-937.zip"));
        versions.put("1.7dev-999", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-1.7dev-999.zip"));
        versions.put("1.7dev-1001", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-1.7dev-1001.zip"));
        versions.put("api-3.0.0-ALPHA8", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-api-3.0.0-ALPHA8.zip"));
        versions.put("api-3.0.0-ALPHA9", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-api-3.0.0-ALPHA9.zip"));
        versions.put("api-3.0.0-ALPHA10", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-api-3.0.0-ALPHA10.zip"));
        versions.put("api-3.0.0-ALPHA11", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-api-3.0.0-ALPHA11.zip"));
        versions.put("api-3.0.0-ALPHA12", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/1.7dev/PocketMine-MP-api-3.0.0-ALPHA12.zip"));

        versions.put("before-fixed-wrong-paths(1.3.13)", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/pre-releases/PocketMine-MP-before-fixed-wrong-paths(1.3.13).zip"));
        versions.put("pocketmine-mp-rebrand(pre-release)", new VersionInfo("", "https://legacyminecraftpe.github.io/LegacyCoreVersions/assets/versions/pre-releases/PocketMine-MP-pocketmine-mp-rebrand(pre-realese).zip"));
    }

    private void filterVersions(String query) {
        versionsPanel.removeAll();

        for (Map.Entry<String, VersionInfo> entry : versions.entrySet()) {
            if (entry.getKey().toLowerCase().contains(query.toLowerCase())) {
                JPanel versionPanel = new JPanel(new BorderLayout());
                versionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
                versionPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

                JLabel versionLabel = new JLabel(entry.getKey());
                versionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                versionLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                versionPanel.add(versionLabel, BorderLayout.WEST);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

                if (!entry.getValue().installerUrl.isEmpty()) {
                    JButton installerButton = new JButton("Installer");
                    installerButton.setFont(new Font("Arial", Font.PLAIN, 16));
                    installerButton.setPreferredSize(new Dimension(120, 40));
                    installerButton.addActionListener(e -> downloadAndInstall(entry.getValue().installerUrl, "PocketMine_" + entry.getKey() + "_Setup.exe", true));
                    buttonPanel.add(installerButton);
                }

                if (!entry.getValue().sourceUrl.isEmpty()) {
                    JButton sourceButton = new JButton("Source");
                    sourceButton.setFont(new Font("Arial", Font.PLAIN, 16));
                    sourceButton.setPreferredSize(new Dimension(120, 40));
                    sourceButton.addActionListener(e -> downloadAndInstall(entry.getValue().sourceUrl, "PocketMine_" + entry.getKey() + "_Source.zip", false));
                    buttonPanel.add(sourceButton);
                }

                versionPanel.add(buttonPanel, BorderLayout.EAST);
                versionsPanel.add(versionPanel);
                versionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        versionsPanel.revalidate();
        versionsPanel.repaint();
    }

    private void downloadAndInstall(String fileURL, String fileName, boolean isInstaller) {
        JDialog progressDialog = new JDialog(this, "Downloading", true);
        progressDialog.setLayout(new BorderLayout());

        JLabel progressLabel = new JLabel("Starting download...");
        progressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        progressLabel.setHorizontalAlignment(JLabel.CENTER);
        progressLabel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(400, 30));
        progressBar.setBorder(new EmptyBorder(20, 20, 20, 20));

        progressDialog.add(progressLabel, BorderLayout.NORTH);
        progressDialog.add(progressBar, BorderLayout.CENTER);
        progressDialog.pack();
        progressDialog.setLocationRelativeTo(this);

        cancelDownload = false;
        progressDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancelDownload = true;
            }
        });

        new Thread(() -> {
            try {
                URL url = new URL(fileURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int fileSize = connection.getContentLength();
                InputStream in = connection.getInputStream();

                File downloadDir = new File("downloaded");
                if (!downloadDir.exists()) {
                    downloadDir.mkdir();
                }

                File outputFile = new File(downloadDir, fileName);
                FileOutputStream out = new FileOutputStream(outputFile);

                byte[] buffer = new byte[4096];
                int bytesRead;
                int downloaded = 0;

                while ((bytesRead = in.read(buffer)) != -1) {
                    if (cancelDownload) {
                        in.close();
                        out.close();
                        outputFile.delete();
                        SwingUtilities.invokeLater(progressDialog::dispose);
                        return;
                    }
                    downloaded += bytesRead;
                    out.write(buffer, 0, bytesRead);
                    final int percentComplete = (int) ((downloaded / (float) fileSize) * 100);
                    SwingUtilities.invokeLater(() -> {
                        progressLabel.setText("Downloading... " + percentComplete + "%");
                        progressBar.setValue(percentComplete);
                    });
                }

                in.close();
                out.close();
                SwingUtilities.invokeLater(progressDialog::dispose);

                if (isInstaller) {
                    SwingUtilities.invokeLater(() -> {
                        int choice = JOptionPane.showConfirmDialog(
                            this,
                            "Download completed. Do you want to install PocketMine now?",
                            "Installation",
                            JOptionPane.YES_NO_OPTION
                        );
                        if (choice == JOptionPane.YES_OPTION) {
                            try {
                                Runtime.getRuntime().exec(outputFile.getAbsolutePath());
                            } catch (IOException e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Failed to start installer: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                } else {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(this, "Download completed: " + fileName, "Success", JOptionPane.INFORMATION_MESSAGE);
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    progressDialog.dispose();
                    JOptionPane.showMessageDialog(this, "Download failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();

        progressDialog.setVisible(true);
    }

    private static class VersionInfo {
        String installerUrl;
        String sourceUrl;

        VersionInfo(String installerUrl, String sourceUrl) {
            this.installerUrl = installerUrl;
            this.sourceUrl = sourceUrl;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            mainFrame = new PocketMineInstaller();
            mainFrame.setVisible(true);
        });
    }
}