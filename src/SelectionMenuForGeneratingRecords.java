import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class SelectionMenuForGeneratingRecords implements ActionListener {
	/* array for carrying the tablename from which records are required */
	String tablenameandfilepath[] = new String[1];/*
												 * location 1 contains tablename
												 * as per user selection
												 */

	JFrame jf;
	/*---------Added variables for Ui Screen-----------*/

	JLabel batch, department, semester, examination;
	JComboBox lbatch, ldepartment, lsemester, lexamination;
	JButton jb;
	JDialog jdia;
	Font font = new Font("Comic Sans Ms", 20, 15);

	SelectionMenuForGeneratingRecords() {
		Font red = new Font("Comic Sans Ms", Font.BOLD, 25);
		/*----------variables for UI screen that remain invisible at first*/

		jf = new JFrame("Select The Details");
		batch = new JLabel("Select Batch");

		batch.setFont(font);
		department = new JLabel("Select Department");
		department.setFont(font);
		semester = new JLabel("Select Semester");
		semester.setFont(font);
		examination = new JLabel("Select Examination");
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
		new SelectionMenuForGeneratingRecords();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int response = JOptionPane.showConfirmDialog(jf,
				"     Your Selection:\nExamination: "
						+ lexamination.getSelectedItem().toString()
						+ "\nBatch: " + lbatch.getSelectedItem().toString()
						+ "\nDepartment: "
						+ ldepartment.getSelectedItem().toString()
						+ "\nSemester: "
						+ lsemester.getSelectedItem().toString());
		if (response == 0) {
			String tablename = lexamination.getSelectedItem().toString()
					+ lbatch.getSelectedItem().toString()
					+ ldepartment.getSelectedItem().toString()
					+ lsemester.getSelectedItem().toString();
			tablenameandfilepath[0] = tablename;
			/* Send tablenamefilepath array to class that fetches data */
			new rollnoorclasswiseoptions(tablename);
	jf.setVisible(false);
		} else {
			jf.setVisible(false);
			new SelectionMenuForGeneratingRecords();
		}
	}
}
