import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Information icon container which displays an information dialog when clicked
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

public class InformationLabel extends JLabel implements MouseListener {
	private static final long serialVersionUID = 1L;

	protected final String MESSAGE = "<html>"
			+ "<H1>Author: Ernesto Echeverría González</H1"
			+ "<H2>University: Universidad de La Laguna</H2>"
			+ "<H2><i>Subject: Programación de Aplicaciones Interactivas</i></H2>"
			+ "<p>E-mail: alu0100881622@ull.edu.es</p>"
			+ "<p>Repository: https://github.com/AkaiBF/Spline-Interpolation-PAI</p>"
			+ "<p>Country: Spain</p></html>";
	
	public InformationLabel(ImageIcon input) {
		super(input);
		addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		JDialog messageDialog = new JDialog();
		JLabel messageLabel = new JLabel(MESSAGE);
		messageDialog.add(messageLabel);
		messageDialog.setSize(new Dimension(500, 300));
		messageDialog.setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
