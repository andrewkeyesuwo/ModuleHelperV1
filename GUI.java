import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class GUI {
	private boolean doneAdding;
	private Student person;
	private String[][] data = new String[50][3];
	Panel topPnl;
	JTextField addtext;
	JTextField addtext2;
	Button addButton;
	Button doneButton;
	JFrame program;
	JScrollPane courseList;
	int i = 0;
	String[] columnNames={"Credits","Name","Grade"};
	
	public GUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		doneAdding=false;
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		program = new JFrame();
		setIcon();
		program.setLayout(new FlowLayout());
		program.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		program.setSize(700, 700);
		program.setLocation(600,200);
		
		Panel logoPnl = new Panel();
		addImage(logoPnl);
		program.add(logoPnl);
		program.getContentPane().setBackground(Color.white);
		topPnl = new Panel();
		topPnl.setLayout(new FlowLayout());
		
		JLabel descrip = new JLabel("Enter your course: ");
		addtext = new JTextField();
		addtext2 = new JTextField();
		JLabel gradedes = new JLabel("Grade:");
		addtext.setPreferredSize(new Dimension(120,30));
		addtext2.setPreferredSize(new Dimension(30, 30));
		Button addButton = new Button("Add Course");
		Button doneButton = new Button("Done");
		topPnl.add(descrip);
		topPnl.add(addtext);
		topPnl.add(gradedes);
		topPnl.add(addtext2);
		topPnl.add(addButton);
		topPnl.add(doneButton);
		program.add(topPnl);
		courseList = new JScrollPane(new JTable(data, columnNames));
		courseList.setPreferredSize(new Dimension(600,200));
		program.add(courseList);
		program.setVisible(true);
		person = new Student();
		
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String text = addtext.getText();
				Course tempCourse = new Course(text, (float) 0.5);
				String text2 = addtext2.getText();
				tempCourse.setGrade(Float.parseFloat(text2));
				addtext.setText("");
				addtext2.setText("");
				person.addCourse(tempCourse);
				addCourse(tempCourse);
			}
		});
		
		
		doneButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				doneAdding=true;
			}
		});	
	}
	
	public Student getStudent(){
		return person;
	}
	
	
	public boolean doneAdding(){
		if(doneAdding){
			addtext.setEditable(false);
			addtext2.setEditable(false);
			return true;
		}
		return false;
	}
	
	private void addCourse(Course course){
		JScrollPane toAdd = addToTable(course);
		courseList = toAdd;
		courseList.repaint();
		courseList.revalidate();
		program.revalidate();
		program.repaint();
		
	}
	
	private void setIcon(){
		try{
			InputStream imageInputStream = program.getClass().getResourceAsStream("/default.png");
	        BufferedImage bufferedImage = ImageIO.read(imageInputStream);
	        program.setIconImage(bufferedImage);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void addImage(Panel com){
		try{
			InputStream imageInputStream = program.getClass().getResourceAsStream("/Horizontal_Full.png");
	        BufferedImage bufferedImage = ImageIO.read(imageInputStream);
	        JLabel westernLogo = new JLabel(new ImageIcon(bufferedImage));
	        com.add(westernLogo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private JScrollPane buildTable(String[][] data){
		JTable coursetable = new JTable(data, columnNames);
		JScrollPane courseList = new JScrollPane();
		return courseList;
	}
	
	private JScrollPane addToTable(Course tempCourse){
		data[i][0] = String.valueOf(tempCourse.getCredits());
		data[i][1] = tempCourse.getName();
		data[i][2] = String.valueOf(tempCourse.getGrade());
		i++;
		return buildTable(data);
	}
	
	public void buildMajorList(String[][] data){
		String[] text = {"Modules student is available for"};
		JTable coursetable = new JTable(data, text);
		JScrollPane courseList = new JScrollPane(coursetable);
		courseList.setPreferredSize(new Dimension(600,200));
		courseList.setEnabled(false);
		program.add(courseList);
		program.repaint();
		program.revalidate();
	}
	
}
