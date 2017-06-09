package jfilechooserdemo.resources;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class GrowthShareGeneraterForm extends JFrame
{
  private JMenuItem Exit;
  private JButton caFileLoader;
  private JLabel caFileStatus;
  private JButton exceptionLookupFileLoader;
  private JLabel exceptionLookupFileStatus;
  private JFileChooser fileChooser;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JLabel jLabel7;
  private JMenu jMenu1;
  private JMenuBar jMenuBar1;
  private JTextField outputPath;
  private JLabel outputPathStatus;
  private JButton reportGenerator;
  private JLabel reportStatus;
  private JButton sourceFileLoader;
  private JLabel sourceFileStatus;
  private JButton usFileLoader;
  private JLabel usFileStatus;
  private final HashMap<String, BigDecimal> candidates = new HashMap();
  private final HashMap<String, String> allUS = new HashMap();
  private final HashMap<String, String> allCA = new HashMap();
  private final String fileName = "Growth Share - US.txt";
  private final String fileName2 = "Growth Share - CA.txt";
  private final String notFound = "Not Found.txt";

  public GrowthShareGeneraterForm()
  {
    initComponents();
  }

  private void initComponents()
  {
    this.fileChooser = new JFileChooser();
    this.jLabel1 = new JLabel();
    this.jLabel2 = new JLabel();
    this.jLabel3 = new JLabel();
    this.jLabel4 = new JLabel();
    this.jLabel5 = new JLabel();
    this.sourceFileLoader = new JButton();
    this.usFileLoader = new JButton();
    this.caFileLoader = new JButton();
    this.outputPath = new JTextField();
    this.reportGenerator = new JButton();
    this.jLabel6 = new JLabel();
    this.sourceFileStatus = new JLabel();
    this.usFileStatus = new JLabel();
    this.caFileStatus = new JLabel();
    this.outputPathStatus = new JLabel();
    this.reportStatus = new JLabel();
    this.jLabel7 = new JLabel();
    this.exceptionLookupFileLoader = new JButton();
    this.exceptionLookupFileStatus = new JLabel();
    this.jMenuBar1 = new JMenuBar();
    this.jMenu1 = new JMenu();
    this.Exit = new JMenuItem();

    this.fileChooser.setFileFilter(new MyCustomFilter());

    setDefaultCloseOperation(3);

    this.jLabel1.setFont(new Font("Tahoma", 1, 18));
    this.jLabel1.setForeground(new Color(255, 0, 51));
    this.jLabel1.setText("All files must be in text format! And NO new line at the bottom of each file!");

    this.jLabel2.setFont(new Font("Tahoma", 0, 18));
    this.jLabel2.setText("Growth Share Source File:");

    this.jLabel3.setFont(new Font("Tahoma", 0, 18));
    this.jLabel3.setText("US 1TIN File:");

    this.jLabel4.setFont(new Font("Tahoma", 0, 18));
    this.jLabel4.setText("CA 2TIN File:");

    this.jLabel5.setFont(new Font("Tahoma", 0, 18));
    this.jLabel5.setText("Output File Destination:");

    this.sourceFileLoader.setFont(new Font("Tahoma", 0, 18));
    this.sourceFileLoader.setText("Browse...");
    this.sourceFileLoader.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        GrowthShareGeneraterForm.this.sourceFileLoaderActionPerformed(evt);
      }
    });
    this.usFileLoader.setFont(new Font("Tahoma", 0, 18));
    this.usFileLoader.setText("Browse...");
    this.usFileLoader.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        GrowthShareGeneraterForm.this.usFileLoaderActionPerformed(evt);
      }
    });
    this.caFileLoader.setFont(new Font("Tahoma", 0, 18));
    this.caFileLoader.setText("Browse...");
    this.caFileLoader.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        GrowthShareGeneraterForm.this.caFileLoaderActionPerformed(evt);
      }
    });
    this.outputPath.setFont(new Font("Tahoma", 0, 18));
    this.outputPath.setText("C:\\GrowthShareReports");
    this.outputPath.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        GrowthShareGeneraterForm.this.outputPathActionPerformed(evt);
      }
    });
    this.reportGenerator.setFont(new Font("Tahoma", 0, 18));
    this.reportGenerator.setText("Generate Report");
    this.reportGenerator.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        GrowthShareGeneraterForm.this.reportGeneratorActionPerformed(evt);
      }
    });
    this.jLabel6.setFont(new Font("Tahoma", 1, 18));
    this.jLabel6.setForeground(new Color(255, 0, 51));
    this.jLabel6.setText("Press Enter after typing a new path!");

    this.sourceFileStatus.setFont(new Font("Tahoma", 1, 18));

    this.usFileStatus.setFont(new Font("Tahoma", 1, 18));

    this.caFileStatus.setFont(new Font("Tahoma", 1, 18));

    this.outputPathStatus.setFont(new Font("Tahoma", 1, 18));

    this.reportStatus.setFont(new Font("Tahoma", 1, 18));

    this.jLabel7.setFont(new Font("Tahoma", 0, 18));
    this.jLabel7.setText("Exception Lookup File:");

    this.exceptionLookupFileLoader.setFont(new Font("Tahoma", 0, 18));
    this.exceptionLookupFileLoader.setText("Browse...");
    this.exceptionLookupFileLoader.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        GrowthShareGeneraterForm.this.exceptionLookupFileLoaderActionPerformed(evt);
      }
    });
    this.exceptionLookupFileStatus.setFont(new Font("Tahoma", 1, 18));

    this.jMenu1.setText("File");
    this.jMenu1.setFont(new Font("Segoe UI", 1, 18));

    this.Exit.setFont(new Font("Segoe UI", 1, 18));
    this.Exit.setText(" Exit");
    this.Exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        GrowthShareGeneraterForm.this.ExitActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.Exit);

    this.jMenuBar1.add(this.jMenu1);

    setJMenuBar(this.jMenuBar1);

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(29, 29, 29).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.outputPathStatus, -2, 512, -2).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel3).addGap(18, 18, 18).addComponent(this.usFileLoader)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel4).addGap(18, 18, 18).addComponent(this.caFileLoader))).addGap(28, 28, 28).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.usFileStatus, -2, 336, -2).addComponent(this.caFileStatus, -2, 314, -2))).addComponent(this.jLabel1).addGroup(layout.createSequentialGroup().addComponent(this.jLabel5).addGap(46, 46, 46).addComponent(this.outputPath, -2, 211, -2).addGap(26, 26, 26).addComponent(this.jLabel6)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel7).addGap(18, 18, 18).addComponent(this.exceptionLookupFileLoader).addGap(14, 14, 14).addComponent(this.exceptionLookupFileStatus, -2, 372, -2)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel2).addGap(18, 18, 18).addComponent(this.sourceFileLoader).addGap(39, 39, 39).addComponent(this.sourceFileStatus)).addGroup(layout.createSequentialGroup().addComponent(this.reportGenerator).addGap(36, 36, 36).addComponent(this.reportStatus, -1, -1, 32767))).addContainerGap(30, 32767)));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(33, 33, 33).addComponent(this.jLabel1).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.sourceFileLoader).addComponent(this.sourceFileStatus)).addGap(24, 24, 24).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel7).addComponent(this.exceptionLookupFileLoader).addComponent(this.exceptionLookupFileStatus)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.usFileLoader).addComponent(this.usFileStatus)).addGap(23, 23, 23).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.caFileLoader).addComponent(this.caFileStatus)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel5).addComponent(this.outputPath, -2, -1, -2).addComponent(this.jLabel6)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.outputPathStatus).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.reportGenerator).addComponent(this.reportStatus)).addContainerGap(66, 32767)));

    pack();
  }

  private void ExitActionPerformed(ActionEvent evt) {
    System.exit(0);
  }

  private void sourceFileLoaderActionPerformed(ActionEvent evt) {
    int returnVal = this.fileChooser.showOpenDialog(this);
    if (returnVal == 0) {
      File file = this.fileChooser.getSelectedFile();
      try {
        BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));

        this.candidates.clear();
        String s;
        while ((s = br.readLine()) != null) {
          String[] inputs = s.split("\t");
          if (this.candidates.containsKey(inputs[1])) {
            BigDecimal newValue1 = (BigDecimal)this.candidates.get(inputs[1]);
            BigDecimal newValue2 = new BigDecimal(inputs[2].replace(",", ""));
            BigDecimal newValue3 = newValue1.add(newValue2);
            this.candidates.put(inputs[1], newValue3);
          } else {
            this.candidates.put(inputs[1], new BigDecimal(inputs[2].replace(",", "")));
          }
        }

        this.sourceFileStatus.setText("Source File loaded!");
      } catch (IOException ex) {
        this.sourceFileStatus.setText("problem accessing Growth Share Source File");
      }
    } else {
      this.sourceFileStatus.setText("Loading cancelled by user.");
    }
  }

  private void usFileLoaderActionPerformed(ActionEvent evt) {
    int returnVal = this.fileChooser.showOpenDialog(this);
    if (returnVal == 0) {
      File file = this.fileChooser.getSelectedFile();
      try {
        BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));

        this.allUS.clear();
        String s;
        while ((s = br.readLine()) != null) {
          String[] inputs = s.split("\t");
          if (!this.allUS.containsKey(inputs[2])) {
            this.allUS.put(inputs[2], s);
          }
        }

        this.usFileStatus.setText("US 1TIN File loaded!");
      } catch (IOException ex) {
        this.usFileStatus.setText("problem accessing US 1TIN File");
      }
    } else {
      this.usFileStatus.setText("Loading cancelled by user.");
    }
  }

  private void caFileLoaderActionPerformed(ActionEvent evt) {
    int returnVal = this.fileChooser.showOpenDialog(this);
    if (returnVal == 0) {
      File file = this.fileChooser.getSelectedFile();
      try {
        BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));

        this.allCA.clear();
        String s;
        while ((s = br.readLine()) != null) {
          String[] inputs = s.split("\t");
          if (!this.allCA.containsKey(inputs[2])) {
            this.allCA.put(inputs[2], s);
          }
        }

        this.caFileStatus.setText("CA 2TIN File loaded!");
      } catch (IOException ex) {
        this.caFileStatus.setText("problem accessing CA 2TIN File");
      }
    } else {
      this.caFileStatus.setText("Loading cancelled by user.");
    }
  }

  private void outputPathActionPerformed(ActionEvent evt) {
    this.outputPathStatus.setText("Output file fold changed to: " + this.outputPath.getText());
  }

  private void reportGeneratorActionPerformed(ActionEvent evt)
  {
    try
    {
      File dir = new File(this.outputPath.getText());
      if ((!dir.exists()) && 
        (!dir.mkdirs())) {
        this.reportStatus.setText("Failed to create folder at: " + this.outputPath.getText());
      }

      OutputStream out = new FileOutputStream(new File(this.outputPath.getText() + File.separator + "Growth Share - US.txt"));
      OutputStream out2 = new FileOutputStream(new File(this.outputPath.getText() + File.separator + "Growth Share - CA.txt"));
      OutputStream out3 = new FileOutputStream(new File(this.outputPath.getText() + File.separator + "Not Found.txt"));

      for (String key : this.candidates.keySet()) {
        String keyNoDash = key.replaceAll("-", "");
        if (this.allUS.containsKey(keyNoDash)) {
          String tmp = "";
          for (int index = 0; index < 9; index++) {
            tmp = tmp + ((String)this.allUS.get(keyNoDash)).split("\t")[index] + "\t";
          }
          tmp = tmp + this.candidates.get(key) + "\tKWW Growth Share Payment\r\n";
          out.write(tmp.getBytes());
        } else if (this.allCA.containsKey(keyNoDash)) {
          String tmp = "";
          for (int index = 0; index < 10; index++) {
            tmp = tmp + ((String)this.allCA.get(keyNoDash)).split("\t")[index] + "\t";
          }
          tmp = tmp + this.candidates.get(key) + "\tKWW Growth Share Payment\r\n";
          out2.write(tmp.getBytes());
        } else {
          String tmp = keyNoDash + "\r\n";
          out3.write(tmp.getBytes());
        }
      }

      this.reportStatus.setText("Reports generated at: " + this.outputPath.getText());
    } catch (Exception ex) {
      this.reportStatus.setText("Failed to generate report! Please close the program and try again later!");
    }
  }

  private void exceptionLookupFileLoaderActionPerformed(ActionEvent evt) {
    if ((this.candidates == null) || (this.candidates.isEmpty())) {
      this.exceptionLookupFileStatus.setText("Please load source file first!");
    } else {
      int returnVal = this.fileChooser.showOpenDialog(this);
      if (returnVal == 0) {
        File file = this.fileChooser.getSelectedFile();
        try {
          BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
          String s;
          String[] inputs;
          while ((s = br.readLine()) != null) {
            inputs = s.split("\t");
            System.out.println(inputs[0] + " mapping to: " + inputs[1]);
            for (String key : this.candidates.keySet()) {
              String keyNoDash = key.replaceAll("-", "");
              if (keyNoDash.equals(inputs[0])) {
                System.out.println(inputs[0] + " in candidates is: " + this.candidates.get(key));
                this.candidates.put(inputs[1], this.candidates.get(key));
                this.candidates.remove(key);
                break;
              }
            }
          }

          this.exceptionLookupFileStatus.setText("Exception Lookup File loaded!");
        } catch (IOException ex) {
          this.exceptionLookupFileStatus.setText("problem accessing Exception Lookup File");
        }
      } else {
        this.exceptionLookupFileStatus.setText("Loading cancelled by user.");
      }
    }
  }

  public static void main(String[] args)
  {
    try
    {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
    }
    catch (ClassNotFoundException ex) {
      Logger.getLogger(GrowthShareGeneraterForm.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(GrowthShareGeneraterForm.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(GrowthShareGeneraterForm.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(GrowthShareGeneraterForm.class.getName()).log(Level.SEVERE, null, ex);
    }

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new GrowthShareGeneraterForm().setVisible(true);
      }
    });
  }
}