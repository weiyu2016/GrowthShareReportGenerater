/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfilechooserdemo.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import javax.swing.JFileChooser;

class MyCustomFilter extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File file) {
        // Allow only directories, or files with ".txt" extension
        return file.isDirectory() || file.getAbsolutePath().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        // This description will be displayed in the dialog,
        // hard-coded = ugly, should be done via I18N
        return "Text documents (*.txt)";
    }
}

public class GrowthShareGeneraterForm extends javax.swing.JFrame {

    /**
     * Creates new form JFileChooserDemo
     */
    public GrowthShareGeneraterForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        textarea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        generateReport = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();

        fileChooser.setFileFilter(new MyCustomFilter());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Growth Share Generator");

        textarea.setEditable(false);
        textarea.setColumns(20);
        textarea.setRows(5);
        textarea.setText("Please read the following instructions about how to generate Growth Share Report!!!\n\n1. Click \"File -> Open\" to load the Growth Share source file\n\n2. Click \"File -> Open\" to load the 1TIN file\n\n3. Click \"File -> Open\" to load the 2TIN file\n\n4. If all loaded successfully, click \"File -> Generate Report\" to generate the report\n\n\n");
        jScrollPane1.setViewportView(textarea);

        jMenu1.setText("File");

        Open.setText("Open");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenActionPerformed(evt);
            }
        });
        jMenu1.add(Open);

        generateReport.setText("Generate Report");
        generateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReportActionPerformed(evt);
            }
        });
        jMenu1.add(generateReport);

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jMenu1.add(Exit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                String s;
                String [] inputs;
                BigDecimal newValue1, newValue2, newValue3;
                if(loadTimes % 3 == 0) {
                    candidates.clear();
                    while ((s = br.readLine()) != null) {
                        inputs = s.split("\t");
                        if (candidates.containsKey(inputs[1])) {
                            newValue1 = candidates.get(inputs[1]);
                            newValue2 = new BigDecimal(inputs[2].replace(",", ""));
                            newValue3 = newValue1.add(newValue2);
                            candidates.put(inputs[1], newValue3);
                        } else
                            candidates.put(inputs[1], new BigDecimal(inputs[2].replace(",", "")));
                    }
                    
                    textarea.setText(initalString + "Source file was loaded!\r\n\r\n");
                } else if(loadTimes % 3 == 1) {
                    allUS.clear();
                    while ((s = br.readLine()) != null) {
                        inputs = s.split("\t");
                        if (!allUS.containsKey(inputs[2])) {
                            allUS.put(inputs[2], s);
                        }
                    }                    
                    textarea.append("1TIN file was loaded!\r\n\r\n");                    
                } else if(loadTimes % 3 == 2) {
                    allCA.clear();
                    while ((s = br.readLine()) != null) {
                        inputs = s.split("\t");
                        if (!allCA.containsKey(inputs[2])) {
                            allCA.put(inputs[2], s);
                        }
                    }                    
                    textarea.append("2TIN file was loaded!\r\n\r\nNow please click \"File -> Generate Report\" to generate the report\r\n\r\n");
                }
                
                loadTimes++;
            } catch(Exception ex) {
                textarea.append("problem accessing file " + file.getAbsolutePath() + "\r\n\r\n");
            }
        } else {
            textarea.setText("File access cancelled by user. Please close the program and restart!\r\n\r\n");            
        }
    }//GEN-LAST:event_OpenActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void generateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportActionPerformed
        System.out.println("laodTimes is: " + loadTimes);
        if(loadTimes / 3 != 1) {
            textarea.setText(initalString + warningString);
            loadTimes = 0;
        } else {
            int index;
            String tmp, keyNoDash;
            OutputStream out, out2, out3;
            try {
                File dir = new File(outputFolder);
                if(!dir.exists()) {
                    dir.mkdir();
                }
                out = new FileOutputStream(new File(outputFolder + File.separator + fileName));
                out2 = new FileOutputStream(new File(outputFolder + File.separator + fileName2));
                out3 = new FileOutputStream(new File(outputFolder + File.separator + notFound));

                for (String key : candidates.keySet()) {
                    keyNoDash = key.replaceAll("-", "");
                    if (allUS.containsKey(keyNoDash)) {
//                    System.out.println("Found in US!!!");
                        tmp = "";
                        for (index = 0; index < 9; index++) {
                            tmp += allUS.get(keyNoDash).split("\t")[index] + "\t";
                        }
//                    System.out.println(tmp + candidates.get(key) + "\tKWW Growth Share Payment");
                        tmp += candidates.get(key) + "\tKWW Growth Share Payment\r\n";
                        out.write(tmp.getBytes());
                    } else if (allCA.containsKey(keyNoDash)) {
//                    System.out.println("Found in CA!!!");
                        tmp = "";
                        for (index = 0; index < 10; index++) {
                            tmp += allCA.get(keyNoDash).split("\t")[index] + "\t";
                        }
//                    System.out.println(tmp + candidates.get(key) + "\tKWW Growth Share Payment");
                        tmp += candidates.get(key) + "\tKWW Growth Share Payment\r\n";
                        out2.write(tmp.getBytes());
                    } else {
                        tmp = keyNoDash + "\r\n";
                        out3.write(tmp.getBytes());
                    }
                }
            } catch (Exception ex) {
                textarea.append("Failed to generate report! Please close the program and try again later!");
            }
            
            loadTimes = 0;
            textarea.setText(sucessString);
        }        
    }//GEN-LAST:event_generateReportActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GrowthShareGeneraterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GrowthShareGeneraterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GrowthShareGeneraterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GrowthShareGeneraterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GrowthShareGeneraterForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem Open;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JMenuItem generateReport;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textarea;
    // End of variables declaration//GEN-END:variables
    private int loadTimes;
    private final String initalString = "Please read the following instructions about how to generate Growth Share Report!!!\n\n1. Click \"File -> Open\" to load the Growth Share source file\n\n2. Click \"File -> Open\" to load the 1TIN file\n\n3. Click \"File -> Open\" to load the 2TIN file\n\n4. If all loaded successfully, click \"File -> Generate Report\" to generate the report\n\n\n";
    private final String warningString = "All needed files must be loaded first! Please load from the beginning again!\n\n";
    private final String sucessString = "Report was generated at C:\\GrowthShareReports. You can close the program now.\n\n";
    private HashMap<String, BigDecimal> candidates = new HashMap<>();
    private HashMap<String, String> allUS = new HashMap<>();
    private HashMap<String, String> allCA = new HashMap<>();
    private final String fileName = "Growth Share - US.txt";
    private final String fileName2 = "Growth Share - CA.txt";
    private final String notFound = "Not Found.txt";
    private final String outputFolder = "C:\\GrowthShareReports\\";
}
