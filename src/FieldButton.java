import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class FieldButton extends JButton implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Data data;

	public FieldButton(Data data) {
		super();
		this.data = data;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!(this.getBackground() == Color.DARK_GRAY)) {
			if (this.getBackground() == Color.LIGHT_GRAY) {
				this.setBackground(Color.GREEN);
			} else {
				this.setBackground(Color.LIGHT_GRAY);
			}
		}
		data.getView().requestFocusInWindow();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (data.isL()) {
			if (!(this.getBackground() == Color.DARK_GRAY) && this.getBackground() == Color.LIGHT_GRAY) {
				this.setBackground(Color.GREEN);
			}
		} else if (data.isD()) {
			if (!(this.getBackground() == Color.DARK_GRAY) && this.getBackground() == Color.GREEN) {
				this.setBackground(Color.LIGHT_GRAY);
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (data.isL()) {
			if (!(this.getBackground() == Color.DARK_GRAY) && this.getBackground() == Color.LIGHT_GRAY) {
				this.setBackground(Color.GREEN);
			}
		} else if (data.isD()) {
			if (!(this.getBackground() == Color.DARK_GRAY) && this.getBackground() == Color.GREEN) {
				this.setBackground(Color.LIGHT_GRAY);
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		return;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		return;
		
	}
}
