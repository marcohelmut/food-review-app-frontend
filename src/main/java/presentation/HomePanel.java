/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package presentation;

import dto.FoodResponseDto;
import dto.StallResponseDto;
import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import service.Controller;

/**
 *
 * @author Marco
 */
public class HomePanel extends javax.swing.JPanel {
    
    Controller controller = new Controller();

    /**
     * Creates new form HomePanel
     */
    public HomePanel() {
        initComponents();

        FoodResponseDto topCleanliness = new FoodResponseDto();
        
        try {
            totalFoodsLabel.setText(String.valueOf(controller.getTotalFoodCount()));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load total food count: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "Food count loading interrupted.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        
        try {
            totalStallsLabel.setText(String.valueOf(controller.getTotalStallCount()));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load total stall count: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "Stall count loading interrupted.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        
        try {
            totalReviewsLabel.setText(String.valueOf(controller.getTotalReviewCount()));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load total review count: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "review count loading interrupted.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        
        displayTopPriceRank();
        displayTopTasteRank();
        displayTopCleanlinessRank();
    }
    
    public void displayTopPriceRank() {
        FoodResponseDto topPrice = new FoodResponseDto();
        
        try {
            topPrice = controller.getPriceRanking().getFirst();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load total food count: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "Food count loading interrupted.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        
        topPriceName.setText(topPrice.getName());
        
        //convert the file path into an ImageIcon
        ImageIcon photo = new ImageIcon(topPrice.getFoodPhotoFilePath());

        //scale down to preview JLabel
        Image scaledImage = photo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

        //display the image on the preview JLabel
        topPricePhoto.setIcon(new ImageIcon(scaledImage));
    }
    
    public void displayTopTasteRank() {
        FoodResponseDto topTaste = new FoodResponseDto();
        
        try {
            topTaste = controller.getTasteRanking().getFirst();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load total food count: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "Food count loading interrupted.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        
        topTasteName.setText(topTaste.getName());
        
        //convert the file path into an ImageIcon
        ImageIcon photo = new ImageIcon(topTaste.getFoodPhotoFilePath());

        //scale down to preview JLabel
        Image scaledImage = photo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

        //display the image on the preview JLabel
        topTastePhoto.setIcon(new ImageIcon(scaledImage));
    }
    
    public void displayTopCleanlinessRank() {
        StallResponseDto topStall = new StallResponseDto();
        
        try {
            long stallId = controller.getTasteRanking().getFirst().getStallId();
            topStall = controller.getStallById(stallId);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load total food count: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "Food count loading interrupted.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        
        topCleanlinessName.setText(topStall.getName());
        
        //convert the file path into an ImageIcon
        ImageIcon photo = new ImageIcon(topStall.getPhotoFilePath());

        //scale down to preview JLabel
        Image scaledImage = photo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

        //display the image on the preview JLabel
        topCleanlinessPhoto.setIcon(new ImageIcon(scaledImage));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        topPriceName = new javax.swing.JLabel();
        topTasteName = new javax.swing.JLabel();
        topCleanlinessName = new javax.swing.JLabel();
        totalFoodsLabel = new javax.swing.JLabel();
        topPricePhoto = new javax.swing.JLabel();
        topTastePhoto = new javax.swing.JLabel();
        topCleanlinessPhoto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        totalReviewsLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        totalStallsLabel = new javax.swing.JLabel();

        jLabel1.setText("Home");

        jLabel2.setText("Total Foods Available:");

        jLabel3.setText("Most popular Foods:");

        topPriceName.setText("Food 1 (most sulit)");

        topTasteName.setText("food 2 (tastiest)");

        topCleanlinessName.setText("food 3 (highest cleanliness score)");

        totalFoodsLabel.setText("total foods");

        jLabel4.setText("Stall with highest cleanliness score:");

        jLabel5.setText("Total Reviews:");

        totalReviewsLabel.setText("total reviews");

        jLabel6.setText("Total Stalls:");

        totalStallsLabel.setText("total stalls");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(topPricePhoto)
                                        .addGap(144, 144, 144))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalStallsLabel)
                                        .addGap(56, 56, 56)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(topPriceName)
                                        .addGap(57, 57, 57)
                                        .addComponent(topTastePhoto)
                                        .addGap(40, 40, 40)
                                        .addComponent(topTasteName)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(topCleanlinessPhoto)
                                        .addGap(18, 18, 18)
                                        .addComponent(topCleanlinessName))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 51, Short.MAX_VALUE)
                                        .addComponent(jLabel2)
                                        .addGap(32, 32, 32)
                                        .addComponent(totalFoodsLabel)
                                        .addGap(81, 81, 81)
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalReviewsLabel)
                                        .addGap(52, 52, 52)))))
                        .addGap(47, 47, 47))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(88, 88, 88))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(totalFoodsLabel)
                    .addComponent(jLabel5)
                    .addComponent(totalReviewsLabel)
                    .addComponent(jLabel6)
                    .addComponent(totalStallsLabel))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(topPriceName)
                    .addComponent(topTasteName)
                    .addComponent(topCleanlinessName)
                    .addComponent(topPricePhoto)
                    .addComponent(topTastePhoto)
                    .addComponent(topCleanlinessPhoto))
                .addContainerGap(247, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel topCleanlinessName;
    private javax.swing.JLabel topCleanlinessPhoto;
    private javax.swing.JLabel topPriceName;
    private javax.swing.JLabel topPricePhoto;
    private javax.swing.JLabel topTasteName;
    private javax.swing.JLabel topTastePhoto;
    private javax.swing.JLabel totalFoodsLabel;
    private javax.swing.JLabel totalReviewsLabel;
    private javax.swing.JLabel totalStallsLabel;
    // End of variables declaration//GEN-END:variables
}
