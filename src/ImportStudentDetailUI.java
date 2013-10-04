import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

public class ImportStudentDetailUI extends JFrame implements ActionListener {

	JButton browse, importbutton;
	JTextField filename;
	JFileChooser jfc;
	JLabel selectedfileforimport;
	JButton reset;
	java.awt.Font font = new java.awt.Font("Comic Sans Ms", 10, 15);

	public ImportStudentDetailUI() {
		getContentPane().setBackground(new Color(102,126,255));
		reset = new JButton("Cancel");
		browse = new JButton("Browse");
		importbutton = new JButton("Import");
		filename = new JTextField("no file selected");
		jfc = new JFileChooser(".");
		selectedfileforimport = new JLabel("Selected File:");
		selectedfileforimport.setBounds(700, 60, 100, 30);
		selectedfileforimport.setFont(font);
		filename.setBounds(220, 60, 400, 30);
		browse.setBounds(240, 120, 100, 30);
		browse.setFont(font);
		importbutton.setBounds(390, 120, 100, 30);
		importbutton.setFont(font);
		reset.setBounds(290, 200, 100, 30);
		reset.setFont(font);
		importbutton.addActionListener(this);
		reset.addActionListener(this);
		browse.addActionListener(this);

		setSize(1050, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		add(browse);
		add(filename);
		add(importbutton);
		add(selectedfileforimport);
		add(reset);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand() == "Browse") {

			System.out.println("browse button");
			int x = jfc.showOpenDialog(null);
			if (x == JFileChooser.APPROVE_OPTION) {
				File name = jfc.getSelectedFile();
				System.out.println(name.getName());
				filename.setText(name.getAbsolutePath());
			}
			} 
		else if (arg0.getActionCommand() == "Import") {
				System.out.println("import button");
				/*
				 * call the class for importing student detail here with file
				 * path
				 */
			} else {
				System.out.println("Cancel");
				setVisible(false);
				new ImportStudentDetailUI();
			}

		}
	

	public static void main(String... s) {
		new ImportStudentDetailUI();
	}
}