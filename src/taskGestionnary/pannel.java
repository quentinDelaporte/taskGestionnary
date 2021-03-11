package taskGestionnary;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

public class pannel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public pannel() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane, BorderLayout.SOUTH);
		
		JLabel lblAjouter = new JLabel("Ajouter une tache");
		splitPane.setLeftComponent(lblAjouter);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(2);
		splitPane.setRightComponent(splitPane_1);
		
		JButton btnAjouter = new JButton("ajouter");
		splitPane_1.setRightComponent(btnAjouter);
		
		textField = new JTextField();
		textField.setMinimumSize(new Dimension(250, 20));
		splitPane_1.setLeftComponent(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblTaskGestionnary = new JLabel("Task Gestionnary");
		panel.add(lblTaskGestionnary);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setMinimumSize(new Dimension(500, 25));
		add(splitPane_2, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane_2.setRightComponent(scrollPane);

	}

}
