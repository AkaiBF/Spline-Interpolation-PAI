import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Controller of a splines-based function creator given n points
 * 
 * Country: Spain
 * University: Universidad de La Laguna
 * Subject: Programación de Aplicaciones Interactivas
 * Repository: https://github.com/AkaiBF/Spline-Interpolation-PAI
 * 
 * @author Ernesto Echeverría González
 * @email alu0100881622@ull.edu.es
 * @since 05-03-2017
 * @version 1.0.0
 */

public class Controller extends JPanel {
	private static final long serialVersionUID = 1L;

	protected final int IMGSIZE = 30;															// Image size
	
	private JTextField textField;
	private JButton refreshButton;
	
	public Controller() {
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setLayout(new FlowLayout());
		
		ImageIcon informationIcon = new ImageIcon("information.png");
		informationIcon = new ImageIcon(informationIcon.getImage().getScaledInstance(IMGSIZE, IMGSIZE,  java.awt.Image.SCALE_SMOOTH));
		InformationLabel informationLabel = new InformationLabel(informationIcon);
		
		textField = new JTextField(15);
		refreshButton = new JButton("Refresh");
		
		add(textField);
		add(refreshButton);
		add(informationLabel);
	}
	
	// Getters & Setters
	public JTextField getTextField() {
		return textField;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	public JButton getRefreshButton() {
		return refreshButton;
	}
	public void setRefreshButton(JButton refreshButton) {
		this.refreshButton = refreshButton;
	}
}
