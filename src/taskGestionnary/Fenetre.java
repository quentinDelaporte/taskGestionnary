package taskGestionnary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Fenetre extends JFrame {
	  private Panneau pan = new Panneau();
	  private label addMenu = new label("Ajouter:");
	  private label add = new label("Nom de la tache à ajouter:");
	  ArrayList<String> taskname = new ArrayList<String>();
	  ArrayList<Button> btnList = new ArrayList<Button>();
	  private JTextField titleTaskToAdd = new JTextField("");
	  private Button btnAdd = new Button("Ajouter");
	  Properties p = System.getProperties();
	  private String csvPath="C://Users/"+p.getProperty("user.name")+"/.taskGestionary/task.csv";
	  private File dossier=new File(csvPath);
 
	  public Fenetre() throws Exception {
		  String userName = p.getProperty("user.name");
		  File dir = new File("c:\\Users\\"+userName+"\\.taskGestionary");
		  String csv= "task.csv";
		  File csvDir = new File(dir+"\\"+csv);
	        if (!dir.exists()) {
	            if (dir.mkdir()) {}
	        }
	        if (!csvDir.exists()) {
	            if (csvDir.createNewFile()) {}
	        }
		  if(!dossier.exists()) {
			  FileWriter writer = new FileWriter(csvPath);
			  writer.append("ID");
			  writer.append(',');
			  writer.append("name");
			  writer.append('\n');
			  writer.flush();
			  writer.close();
		  } 
		    this.setTitle("Animation");
		    this.setSize(900, 700);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setLocationRelativeTo(null);
		    generateTaskList();		
		    Font font = new Font("Courier", Font.BOLD, 20);		  
		    titleTaskToAdd.setSize(150, 25);
		    titleTaskToAdd.setFont(font);
		    titleTaskToAdd.setForeground(Color.black);
			btnAdd.addActionListener(new ButtonAddListener()); 
	  }
	  
	  public void generateAffichage() {
		  generateBtn();
		  Box Taskbtn = Box.createVerticalBox();
		  Box btn = Box.createVerticalBox();
		  for(int n=0; n<taskname.size()-1;n++) {
			  Box Task = Box.createHorizontalBox();
			  if(n==0) {
				  Task.add( new label(taskname.get(0)));
			  } else {
				  Task.add(new label(taskname.get(n)));
				  Task.add(btnList.get(n));
			  }
			  Taskbtn.add(Task);
		  }
		  Box taskList=Box.createHorizontalBox();
		  taskList.add(Taskbtn);
		  taskList.add(btn);
		  taskList.setBorder(new EmptyBorder(5, 5, 5, 5));
		  Box formAddTaskSub=Box.createHorizontalBox();
		  formAddTaskSub.add(add);
		  formAddTaskSub.add(titleTaskToAdd);
		  formAddTaskSub.add(btnAdd);
		  Box formAddTask=Box.createVerticalBox();
		  formAddTask.add(addMenu);
		  formAddTask.add(formAddTaskSub);
		  formAddTask.setMaximumSize(new Dimension(500, 250)); 
		  Box f1 = Box.createVerticalBox();
		  f1.add(pan);
		  f1.add(taskList);
		  f1.add(formAddTask);
		  this.setContentPane(f1);
		  this.setVisible(true);
	  }
	  
	  public void generateBtn() {
		  int nb=0;
		for (int j = 0; j < taskname.size()-1 ; j ++) {
			btnList.add(new Button("check"));
			btnList.get(j).addActionListener(new ButtonListener()); 
			nb++;
		}
	  }
	  
	  public void generateTaskList() throws Exception {
		  BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
		  try
		    {
			  int i=0;
			  String chaine;
			while((chaine = csvReader.readLine())!= null)
			{
				  	String[] taskDecompose = chaine.split(",");
				  	taskname.add(taskDecompose[1]);
				  	i++;
			}
			csvReader.close();
		  }
		  catch (FileNotFoundException e)
		  {
			  System.err.println("Le fichier est introuvable !");
		  }		  
	      generateAffichage();
	  }
	  
	  public void reGenerateTaskList() throws Exception {
		  taskname.clear();
		  btnList.clear();
		  BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
		  try
		    {
			  String chaine;
			while((chaine = csvReader.readLine())!= null)
			{
				  	String[] taskDecompose = chaine.split(",");
				  	taskname.add(taskDecompose[1]);
			}
			csvReader.close();
		  }
		  catch (FileNotFoundException e)
		  {
			  System.err.println("Le fichier est introuvable !");
		  }		  
	      generateAffichage();
	  }
	  
	  public void delTask(int id) throws IOException {
		String[] tab = new String[taskname.size()];
		int w=0;
		for(int i=0; i<taskname.size();i++) {
			if(i!=id){
				tab[w]=taskname.get(i);
				w++;
			}
		}
		FileWriter writer = new FileWriter(csvPath);
		writer.append("ID");
		writer.append(',');
		writer.append("name");
		writer.append('\n');
		int v=1;
		for(int i=1; i<tab.length;i++) {
		  if(i!=id) {
			  writer.append(toString(v));
			  writer.append(',');
			  writer.append(tab[v]);
			  writer.append('\n');
			  v++;
		  }
		}
		writer.flush();
		writer.close();
	  }
	  
	  public void addTask(String title) throws Exception {
		String[] tab = new String[taskname.size()];
		int w=0;
		for(int i=0; i<taskname.size();i++) {
				tab[w]=taskname.get(i);
				w++;
		}
		FileWriter writer = new FileWriter(csvPath);
		writer.append("ID");
		writer.append(',');
		writer.append("name");
		writer.append('\n');
		int v=1;
		for(int i=1; i<tab.length;i++) {
			  writer.append(toString(v));
			  writer.append(',');
			  writer.append(tab[v]);
			  writer.append('\n');
			  v++;
		}
		writer.append(toString(v));
		writer.append(',');
		writer.append(title);
		writer.append('\n');
		writer.flush();
		writer.close();
		reGenerateTaskList();
	  }
	  
	  public String toString(int i) {
		  return i+"";
	  }
	  
	  class ButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				for(int id=0; id<btnList.size();id++) {
					if (e.getSource()==btnList.get(id)) {
						try {
							delTask(id);
							reGenerateTaskList();
							break;
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
	  }
	  class ButtonAddListener implements ActionListener{
		    public void actionPerformed(ActionEvent arg0) {
		    	try {
					addTask(titleTaskToAdd.getText());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
	  }
}
