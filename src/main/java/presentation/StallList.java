/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

/**
 *
 * @author Marco
 */
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf; // or FlatLightLaf

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class StallList {

    public static void main(String[] args) {
        // 1. Install FlatLaf Look and Feel
        FlatDarkLaf.setup();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FlatLaf Image List");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(450, 550);
            frame.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBorder(new EmptyBorder(16, 16, 16, 16));

            // 2. Populate Model with Food Stall items
            DefaultListModel<StallItem> model = new DefaultListModel<>();

            // Option A: Using remote/local PNG images (scaled to thumbnail size)
            model.addElement(new StallItem("Burger Haven", "Gourmet burgers & fries",
                    createScaledImageIcon("https://picsum.photos/id/1080/100/100")));
            model.addElement(new StallItem("Pizza Corner", "Wood-fired stone pizzas",
                    createScaledImageIcon("https://picsum.photos/id/1062/100/100")));

            // 3. Create JList and apply FlatLaf styling properties
            JList<StallItem> stallList = new JList<>(model);
            stallList.setCellRenderer(new StallListCellRenderer());
            stallList.setFixedCellHeight(72); // Height per row
            stallList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Modern FlatLaf rounded selection corners & custom gaps
            stallList.putClientProperty(FlatClientProperties.STYLE, "" +
                    "selectionArc: 12;" +
                    "cellMargins: 4,8,4,8;");

            // 4. Wrap in a clean FlatLaf ScrollPane
            JScrollPane scrollPane = new JScrollPane(stallList);
            scrollPane.putClientProperty(FlatClientProperties.STYLE, "" +
                    "border: 0,0,0,0;");

            mainPanel.add(scrollPane, BorderLayout.CENTER);
            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }

    // --- Data Model Object ---
    public record StallItem(String name, String description, Icon photo) {}

    // --- Custom FlatLaf Cell Renderer ---
    public static class StallListCellRenderer extends JPanel implements ListCellRenderer<StallItem> {
        private final JLabel photoLabel = new JLabel();
        private final JLabel nameLabel = new JLabel();
        private final JLabel descLabel = new JLabel();

        public StallListCellRenderer() {
            setLayout(new BorderLayout(14, 0));
            setBorder(new EmptyBorder(8, 12, 8, 12));

            // Photo / Avatar container styling
            photoLabel.setPreferredSize(new Dimension(50, 50));
            photoLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // Rounded clip for the image border box using FlatLaf ARC property
            photoLabel.putClientProperty(FlatClientProperties.STYLE, "" +
                    "arc: 12;");

            // Text Panel
            JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 2));
            textPanel.setOpaque(false);

            // Apply modern font weights using FlatLaf inline typography styles
            nameLabel.putClientProperty(FlatClientProperties.STYLE, "font: bold +1");
            descLabel.putClientProperty(FlatClientProperties.STYLE, "[light]font: -1; foreground: $Label.disabledForeground");

            textPanel.add(nameLabel);
            textPanel.add(descLabel);

            add(photoLabel, BorderLayout.WEST);
            add(textPanel, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends StallItem> list, StallItem item, int index, boolean isSelected, boolean cellHasFocus) {
            nameLabel.setText(item.name());
            descLabel.setText(item.description());

            // Assign Image
            if (item.photo() != null) {
                photoLabel.setIcon(item.photo());
            } else {
                photoLabel.setIcon(null); // Fallback image/placeholder
            }

            // Colors handled naturally by FlatLaf list states
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                nameLabel.setForeground(list.getSelectionForeground());
                descLabel.setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                nameLabel.setForeground(list.getForeground());
                descLabel.setForeground(UIManager.getColor("Label.disabledForeground"));
            }

            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(isSelected); // FlatLaf handles selection background highlighting

            return this;
        }
    }

    // Helper method to load and smoothly scale PNG/JPG icons
    private static ImageIcon createScaledImageIcon(String urlPath) {
        try {
            ImageIcon originalIcon = new ImageIcon(new URL(urlPath));
            Image scaledImg = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        } catch (Exception e) {
            return null; // Return default or null image on failure
        }
    }
}
