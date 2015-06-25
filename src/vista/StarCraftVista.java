package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Color;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JToggleButton;

public class StarCraftVista {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StarCraftVista window = new StarCraftVista();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StarCraftVista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("AlgoCraft");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("450px"),},
			new RowSpec[] {
				RowSpec.decode("31px"),
				RowSpec.decode("216px"),
				RowSpec.decode("25px"),}));
		
		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, "1, 1, fill, top");
		
		JButton btnNuevoJuego = new JButton("Nuevo Juego");
		btnNuevoJuego.setBackground(Color.LIGHT_GRAY);
		toolBar.add(btnNuevoJuego);
		
		JToggleButton tglbtnPasarTurno = new JToggleButton("PASAR TURNO");
		tglbtnPasarTurno.setBackground(Color.RED);
		tglbtnPasarTurno.setForeground(Color.WHITE);
		frame.getContentPane().add(tglbtnPasarTurno, "1, 3");
	}

}
