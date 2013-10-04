import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImportOrGenerateOption extends JFrame implements ActionListener {
	JButton importbutton, ViewRecords, StudentDetails;
	JLabel message;
	Font font = new Font("Comic Sans Ms", 10, 15);
	Font fontforlabel = new Font("Comic Sans Ms", 10, 25);
	ImportOrGenerateOption() {
		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		int width=dim.width;
		int height=dim.height;
		getContentPane().setBackground(new Color(100, 120, 250));
		message=new JLabel("Please select an appropriate Option");
		message.setForeground(Color.white);
message.setBounds(width/8+width/10, height/55-height/10,width/2, height/2);
message.setFont(fontforlabel);
add(message);
		importbutton = new JButton("Import Data");
		ViewRecords = new JButton("View/Generate Records");
		StudentDetails = new JButton("Import Student Details");
		StudentDetails.setFont(font);
		importbutton.setFont(font);
		ViewRecords.setFont(font);
		setBackground(Color.BLUE);
		importbutton.addActionListener(this);
		ViewRecords.addActionListener(this);
		StudentDetails.addActionListener(this);
		importbutton.setBounds(400, 240, 200, 30);
		ViewRecords.setBounds(400, 340, 200, 30);
		StudentDetails.setBounds(400, 440, 200, 30);
		add(importbutton);
		add(StudentDetails);
		add(ViewRecords);
		setSize(width,height);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String... s) {
		new ImportOrGenerateOption();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand() == "Import Data") {
			setVisible(false);
			new SelectionMenu();
		} 
		else if(arg0.getActionCommand() == "Import Student Details")
		{
			setVisible(false);
			new ImportStudentDetailUI();
		}
		
		else {
			setVisible(false);
			new SelectionMenuForGeneratingRecords();
		}
	}
}
