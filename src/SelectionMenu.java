
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class SelectionMenu implements ActionListener {
	/* array for carrying the tablename and path of imported file */
	String tablenameandfilepath[] = new String[2];/*
												 * location 0 contains absolute
												 * file path and location 1 the
												 * tablename as per user
												 * selection
												 */

	JFrame jf;
	/*---------Added variables for Ui Screen-----------*/
	JButton browse, importbutton;
	JTextField filename;
	JFileChooser jfc;
	JLabel selectedfileforimport;
	JButton reset;

	JLabel batch, department, semester, examination;
	JComboBox lbatch, ldepartment, lsemester, lexamination;
	JButton jb;
	JDialog jdia;
	Font font = new Font("Comic Sans Ms", 20, 20);

	SelectionMenu() {

		Font red = new Font("Comic Sans Ms", Font.BOLD, 25);
		/*----------variables for UI screen that remain invisible at first*/
		reset = new JButton("Cancel");
		browse = new JButton("Browse");
		importbutton = new JButton("Import");
		filename = new JTextField("no file selected");
		jfc = new JFileChooser(".");
		selectedfileforimport = new JLabel("Selected File:");
		selectedfileforimport.setBounds(670, 60, 130, 30);
		selectedfileforimport.setFont(font);
		filename.setBounds(820, 60, 400, 30);
		browse.setBounds(840, 120, 120, 30);
		browse.setFont(font);
		importbutton.setBounds(990, 120, 100, 30);
		importbutton.setFont(font);
		reset.setBounds(920, 180, 100, 30);
		reset.setFont(font);

		reset.addActionListener(this);
		browse.addActionListener(this);
		importbutton.addActionListener(this);

		jf = new JFrame("Select The Details");
		batch = new JLabel("Select Batch");
		batch.setForeground(Color.white);
	jf.getContentPane().setBackground(new Color(102,126,255));
		batch.setFont(font);
		department = new JLabel("Select Department");
		department.setForeground(Color.white);
		department.setFont(font);
		semester = new JLabel("Select Semester");
		semester.setForeground(Color.white);
		semester.setFont(font);
		examination = new JLabel("Select Examination");
		examination.setForeground(Color.white);
		examination.setFont(font);
		int i = 2010;

		String batchnum[] = new String[100];
		int j = 0;
		while (j < 100) {
			batchnum[j] = Integer.toString(i++);
			j++;
		}
		lbatch = new JComboBox(batchnum);
		lbatch.setFont(font);
		String dpmt[] = { "CSE", "MECH", "ECE", "EEE", "IT", "CIVIL" };
		ldepartment = new JComboBox(dpmt);
		ldepartment.setFont(font);
		String sem[] = { "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th" };
		lsemester = new JComboBox(sem);
		lsemester.setFont(font);
		String exam[] = { "Mains", "SummerExamination" };
		lexamination = new JComboBox(exam);
		lexamination.setFont(font);
		jb = new JButton("Proceed");
		jf.setSize(1280, 750);

		examination.setBounds(50, 54, 200, 50);
		lexamination.setBounds(350, 65, 250, 30);

		batch.setBounds(50, 154, 200, 50);
		lbatch.setBounds(350, 165, 250, 30);

		department.setBounds(50, 250, 200, 50);
		ldepartment.setBounds(350, 265, 250, 30);

		semester.setBounds(50, 350, 200, 50);
		lsemester.setBounds(350, 365, 250, 30);
		jb.setFont(font);
		jb.setBounds(250, 500, 150, 30);
		jb.addActionListener(this);

		jf.add(browse);
		jf.add(filename);
		jf.add(importbutton);
		jf.add(selectedfileforimport);
		jf.add(reset);

		browse.setVisible(false);
		filename.setVisible(false);
		importbutton.setVisible(false);
		selectedfileforimport.setVisible(false);
		reset.setVisible(false);

		jf.add(jb);
		jf.add(batch);
		jf.add(department);
		jf.add(examination);
		jf.add(semester);

		jf.add(lbatch);
		jf.add(ldepartment);
		jf.add(lexamination);
		jf.add(lsemester);

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		jdia = new JDialog(jf);

		jf.setVisible(true);

	}

	public static void main(String... s) {
		new SelectionMenu();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand() == "Proceed") {
			if (lexamination.getSelectedItem() == null
					|| lbatch.getSelectedItem() == null
					|| ldepartment.getSelectedItem() == null
					|| lsemester.getSelectedItem() == null) {

			} else {
				int response = JOptionPane.showConfirmDialog(jf,
						"     Your Selection:\nExamination: "
								+ lexamination.getSelectedItem().toString()
								+ "\nBatch: "
								+ lbatch.getSelectedItem().toString()
								+ "\nDepartment: "
								+ ldepartment.getSelectedItem().toString()
								+ "\nSemester: "
								+ lsemester.getSelectedItem().toString());
				if (response == 0) {
					String tablename = lexamination.getSelectedItem()
							.toString()
							+ lbatch.getSelectedItem().toString()
							+ ldepartment.getSelectedItem().toString()
							+ lsemester.getSelectedItem().toString();
					System.out.println(tablename);
					tablenameandfilepath[1] = tablename;
					/* Make the UI Screen Controls Visible */
					browse.setVisible(true);
					filename.setVisible(true);
					importbutton.setVisible(true);
					selectedfileforimport.setVisible(true);
					reset.setVisible(true);

				} else {
					new SelectionMenu();
					jf.setVisible(false);
				}
			}
		} else if (arg0.getActionCommand() == "Cancel") {
			jf.setVisible(false);
			new SelectionMenu();
		} else if (arg0.getActionCommand() == "Browse") {

			System.out.println("browse button");
			int x = jfc.showOpenDialog(null);
			if (x == JFileChooser.APPROVE_OPTION) {
				File name = jfc.getSelectedFile();
				System.out.println(name.getName());
				filename.setText(name.getAbsolutePath());
				tablenameandfilepath[0] = name.getAbsolutePath();
			}

		} else if (arg0.getActionCommand() == "Import") {
			importfile.main(tablenameandfilepath);
		jf.setVisible(false);
		new ImportOrGenerateOption();
			// TestClass.main(tablenameandfilepath);
			/*----------->>>>>>>>>>>>>>chirag you need to call the class for import the database here ..the variable name tablename contains the name of the tablee*/
		}
	}
}
