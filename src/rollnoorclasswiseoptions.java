import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class rollnoorclasswiseoptions extends JFrame implements ActionListener {
	String tablename;
	Border border = BorderFactory.createTitledBorder("Select Semester");
	JLabel labelrollnum;
	JTextField rollnumber;
	JRadioButton one, two, three, four, five, six, seven, eight, consolidated;
	ButtonGroup rbg;
	JButton generatepdf;
	JPanel jp;

	public rollnoorclasswiseoptions(String arg) { /*this arg (eg mains2010cse) contains the name of the table in the database*/
		Font font = new Font("Comic Sans Ms", Font.BOLD, 15);
		tablename = arg;

		labelrollnum = new JLabel("Enter Roll Number");
		labelrollnum.setFont(font);

		rollnumber = new JTextField();
		rollnumber.setFont(font);

		rbg = new ButtonGroup();
		one = new JRadioButton("1st");
		one.setFont(font);
		one.setActionCommand("1st");

		two = new JRadioButton("2nd");
		two.setFont(font);
		two.setActionCommand("2nd");

		three = new JRadioButton("3rd");
		three.setFont(font);
		three.setActionCommand("3rd");

		four = new JRadioButton("4th");
		four.setFont(font);
		four.setActionCommand("4th");

		five = new JRadioButton("5th");
		five.setFont(font);
		five.setActionCommand("5th");

		six = new JRadioButton("6th");
		six.setFont(font);
		six.setActionCommand("6th");

		seven = new JRadioButton("7th");
		seven.setFont(font);
		seven.setActionCommand("7th");

		eight = new JRadioButton("8th");
		eight.setFont(font);
		eight.setActionCommand("8th");

		consolidated = new JRadioButton("Consolidated");
		consolidated.setFont(font);
		consolidated.setActionCommand("consolidated");

		rbg.add(consolidated);
		rbg.add(eight);
		rbg.add(five);
		rbg.add(four);
		rbg.add(one);
		rbg.add(seven);
		rbg.add(six);
		rbg.add(three);
		rbg.add(two);

		generatepdf = new JButton("Generate");
		generatepdf.setFont(font);

		setSize(850, 750);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		labelrollnum.setBounds(50, 200, 200, 30);
		rollnumber.setBounds(250, 200, 100, 30);
		generatepdf.setBounds(200, 390, 100, 30);
		generatepdf.addActionListener(this);

		jp = new JPanel();
		jp.setBorder(border);
		jp.setBounds(50, 250, 600, 100);

		jp.add(one);
		jp.add(two);
		jp.add(three);
		jp.add(four);
		jp.add(five);
		jp.add(six);
		jp.add(seven);
		jp.add(eight);
		jp.add(consolidated);

		add(jp);
		add(labelrollnum);
		add(rollnumber);
		add(generatepdf);
		setVisible(true);
	}

	public static void main(String... s) {
		new rollnoorclasswiseoptions("null");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (rbg.getSelection().getActionCommand().equals("consolidated")) {
			String array[] = new String[10];
			array[0] = tablename;
			array[1] = rollnumber.getText();
			array[2] = "1st";
			array[3] = "2nd";
			array[4] = "3rd";
			array[5] = "4th";
			array[6] = "5th";
			array[7] = "6th";
			array[8] = "7th";
			array[9] = "8th";
			GetValuesFromROLLnoOrClasswiseClass.main(array);
			setVisible(false);
		} else {
			String array[] = new String[3];
			array[0] = tablename;
			array[1]=rollnumber.getText();
			array[2] = rbg.getSelection().getActionCommand();
			GetValuesFromROLLnoOrClasswiseClass.main(array);
			setVisible(false);
		}

	}

}
