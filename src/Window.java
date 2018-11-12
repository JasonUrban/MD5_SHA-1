import javax.swing.*;
import java.awt.*;

class Window extends JFrame {
    private JTextArea sourceText;
    private JTextArea outputText;
    private JRadioButton md5;

    Window(String name) {
        super(name);
        setSize(1150, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final MyPanel panel = new MyPanel(new GridLayout(0, 2, 30, 20));
        JButton encryptButton = new JButton("Encrypt");
        JButton clearButton = new JButton("Clear fields");
        JLabel sourceLabel = new JLabel("Input text for encryption here:");
        sourceLabel.setForeground(Color.WHITE);
        JLabel outLabel = new JLabel("Your encrypted data:");
        outLabel.setForeground(Color.WHITE);
        md5 = new JRadioButton("MD5");
        JRadioButton sha1 = new JRadioButton("SHA-1");
        sourceText = new JTextArea();
        sourceText.setRows(10);
        outputText = new JTextArea();
        outputText.setRows(10);
        JScrollPane sourceTextScrollPane = new JScrollPane(sourceText);
        JScrollPane outputTextScrollPane = new JScrollPane(outputText);
        encryptButton.setContentAreaFilled(false);
        clearButton.setContentAreaFilled(false);
        encryptButton.setOpaque(true);
        clearButton.setOpaque(true);
        md5.setOpaque(false);
        sha1.setOpaque(false);
        md5.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(md5);
        group.add(sha1);
        md5.setMaximumSize(new Dimension(500, 50));
        sha1.setMaximumSize(new Dimension(500, 50));
        sourceLabel.setMaximumSize(new Dimension(500, 50));
        outLabel.setMaximumSize(new Dimension(500, 50));
        sourceTextScrollPane.setPreferredSize(new Dimension(500, 100));
        outputTextScrollPane.setPreferredSize(new Dimension(500, 100));
        encryptButton.setMaximumSize(new Dimension(500, 50));
        clearButton.setMaximumSize(new Dimension(500, 50));
        panel.add(md5);
        panel.add(new JLabel());
        panel.add(sha1);
        panel.add(new JLabel());
        panel.add(sourceLabel);
        panel.add(outLabel);
        panel.add(sourceTextScrollPane);
        panel.add(outputTextScrollPane);
        panel.add(encryptButton);
        panel.add(clearButton);
        encryptButton.setBackground(new Color(100, 200, 225));
        clearButton.setBackground(new Color(225, 100, 100));
        outputText.setLineWrap(true);
        sourceText.setLineWrap(true);
        encryptButton.setVisible(true);
        encryptButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sourceText.setFont(new Font("System Regular", Font.PLAIN, 16));
        outputText.setFont(new Font("System Regular", Font.PLAIN, 16));
        outputText.setWrapStyleWord(true);
        sourceText.setWrapStyleWord(true);
        outputText.setEditable(true);
        getContentPane().add(panel, BorderLayout.NORTH);
        clearButton.addActionListener(e1 -> {
            sourceText.setText("");
            outputText.setText("");
        });
        encryptButton.addActionListener(e -> translate());
    }

    public class MyPanel extends JPanel {
        MyPanel(LayoutManager layout) {
            super(layout);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            int w = getWidth();
            int h = getHeight();
            Color shadeColor = new Color(155, 146, 255), primaryLeft = new Color(144, 23, 255), primaryRight = new Color(0, 200, 100);
            int rC = shadeColor.getRed();
            int gC = shadeColor.getGreen();
            int bC = shadeColor.getBlue();
            GradientPaint primary = new GradientPaint(
                    0f, 0f, primaryLeft, w, 0f, primaryRight);
            GradientPaint shade = new GradientPaint(
                    0f, 0f, new Color(rC, gC, bC, 0),
                    0f, h, shadeColor);
            g2d.setPaint(primary);
            g2d.fillRect(0, 0, w, h);
            g2d.setPaint(shade);
            g2d.fillRect(0, 0, w, h);
        }
    }

    private void translate() {
        if (this.md5.isSelected()) {
            outputText.setText(MD5.toHexString(MD5.computeMD5(sourceText.getText().getBytes())));
        } else {
            outputText.setText(SHA1.encodeHex(sourceText.getText()));
        }
    }
}
