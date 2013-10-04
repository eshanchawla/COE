import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Authenticate extends JFrame implements ActionListener{
	JPasswordField jp;
	JTextField jtf;
	JLabel user,welcome,date;
	JLabel pass;
	JButton login;
	JLabel error=new JLabel("Check Username/Password");
	
	Font font=new Font("Comic Sans Ms",Font.PLAIN,20);
	{
		Date d=new Date();
		
		
		date=new JLabel(d.toString().substring(0, 11)+(d.getYear()+1900));
		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
	int width=dim.width;
	int height=dim.height;
	error.setForeground(Color.RED);
	error.setVisible(false);
		login=new JButton("Login");
		getContentPane().setBackground(new Color(102,126,255));
		welcome=new JLabel("Welcome User");
		welcome.setForeground(Color.white);
		welcome.setFont(font);
		welcome.setBounds(width/8,height/8, width/8, height/8);
		date.setBounds(width/2, height/8,400,100);
		date.setForeground(Color.white);
		date.setFont(font);
		add(date);
		
		add(welcome);
		login.setBounds(400, 500, 150, 30);
		user=new JLabel("Username ");
		user.setForeground(Color.white);
		pass=new JLabel("Password ");
		pass.setForeground(Color.white);
		jp=new JPasswordField();
		jtf=new JTextField();
		error.setBounds(550, 300, 300, 30);
		error.setFont(font);
		error.setForeground(Color.white);
		user.setFont(font);
	pass.setFont(font);
	jtf.setFont(font);
	jp.setFont(font);
		setSize(width,height);
		user.setBounds(275,300,100, 30);
		pass.setBounds(275, 375, 100, 30);
		jtf.setBounds(400,300,150,30);
		jp.setBounds(400,375,150,30);
	login.addActionListener(this);
		add(login);
		add(error);
		add(user);
		add(pass);
		add(jtf);
		add(jp);
		setLayout(null);
		setVisible(true);

setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
public static void main(String...s)
{
	new Authenticate();
}
@Override
public void actionPerformed(ActionEvent arg0) {
	if(jtf.getText().equals("AdminUser") && jp.getText().equals("admin"))
		{
		this.setVisible(false);
		new ImportOrGenerateOption();
		}
	else
	{
		error.setVisible(true);
	}
}

}
