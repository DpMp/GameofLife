import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class View extends JFrame implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FieldButton[][] field = new FieldButton[86][59];
	private Data data;

	private JLabel lbl_generation;
	private JButton btn_start_stop;
	private JButton btn_reset;
	private JButton btn_clear;

	private JSlider sl_timeInterval;
	private JLabel lbl_interval;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View(new Data());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View(Data data) {
		this.data = data;

		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(this);

		data.setView(this);

		setTitle("Game of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1700, 1400);
		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);

		for (int i = 0; i <= 85; i++) {
			for (int j = 0; j <= 58; j++) {
				field[i][j] = new FieldButton(data);
				field[i][j].setBounds(i * 20, j * 20, 20, 20);
				field[i][j].setVisible(true);
				field[i][j].setBackground(Color.LIGHT_GRAY);
				getContentPane().add(field[i][j]);

				field[0][j].setBackground(Color.DARK_GRAY);
				if (i == 85) {
					field[85][j].setBackground(Color.DARK_GRAY);
				}

			}
			field[i][0].setBackground(Color.DARK_GRAY);
			field[i][58].setBackground(Color.DARK_GRAY);
		}
		lbl_generation = new JLabel("Generation: 0");
		lbl_generation.setBounds(1730, 0, 100, 30);
		getContentPane().add(lbl_generation);

		btn_start_stop = new JButton("⏵Start");
		btn_start_stop.setBounds(1730, 50, 100, 50);
		getContentPane().add(btn_start_stop);
		btn_start_stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				data.setStart(!data.isStart());
				if (data.isStart()) {
					if (data.getSave() == null) {
						data.setSave(field);
					}
					btn_start_stop.setText("⏸ Stopp");
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {

						@Override
						public void run() {
							synchronized (data) {
								if (data.isStart()) {
									int livingNeighbors = 0;
									for (int i = 0; i < 86; i++) {
										for (int j = 0; j < 59; j++) {
											if (i > 1 && j > 1 && field[i - 1][j - 1].getBackground() == Color.GREEN) {
												livingNeighbors++;
											}
											if (i > 1 && field[i - 1][j].getBackground() == Color.GREEN) {
												livingNeighbors++;
											}
											if (i > 1 && j < 57 && field[i - 1][j + 1].getBackground() == Color.GREEN) {
												livingNeighbors++;
											}
											if (j > 1 && field[i][j - 1].getBackground() == Color.GREEN) {
												livingNeighbors++;
											}
											if (j < 58 && field[i][j + 1].getBackground() == Color.GREEN) {
												livingNeighbors++;
											}
											if (i < 85 && j > 1 && field[i + 1][j - 1].getBackground() == Color.GREEN) {
												livingNeighbors++;
											}
											if (i < 58 && field[i + 1][j].getBackground() == Color.GREEN) {
												livingNeighbors++;
											}
											if (i < 85 && j < 58
													&& field[i + 1][j + 1].getBackground() == Color.GREEN) {
												livingNeighbors++;
											}

											if (field[i][j].getBackground() == Color.GREEN
													&& (livingNeighbors < 2 || livingNeighbors > 3)) {
												data.getDeadNew().add(field[i][j]);
											} else if (field[i][j].getBackground() == Color.LIGHT_GRAY
													&& livingNeighbors == 3) {
												data.getLivingNew().add(field[i][j]);
											}
											livingNeighbors = 0;
										}
									}
									for (FieldButton x : data.getLivingNew()) {
										x.setBackground(Color.GREEN);
									}
									for (FieldButton x : data.getDeadNew()) {
										x.setBackground(Color.LIGHT_GRAY);
									}
									data.getLivingNew().clear();
									data.getDeadNew().clear();

									data.setGeneration(data.getGeneration() + 1);
									lbl_generation.setText("Generation: " + data.getGeneration());
								} else {
									timer.cancel();
								}

							}

						}
					}, 0, data.getInterval());
				} else {
					btn_start_stop.setText("⏵Start");
				}
				data.getView().requestFocusInWindow();
			}
		});

		btn_reset = new JButton("⏮ Reset");
		btn_reset.setBounds(1730, 105, 100, 50);
		getContentPane().add(btn_reset);
		btn_reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 86; i++) {
					for (int j = 0; j < 59; j++) {
						field[i][j].setBackground(data.getSave()[i][j].getBackground());
						data.getLivingNew().clear();
						data.getDeadNew().clear();
						data.setStart(false);
					}
				}
				
				data.getView().requestFocusInWindow();
				
			}
		});

		btn_clear = new JButton("⌫ Clear");
		btn_clear.setBounds(1730, 160, 100, 50);
		getContentPane().add(btn_clear);
		btn_clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < 86; i++) {
					for (int j = 0; j < 59; j++) {
						if (field[i][j].getBackground() != Color.DARK_GRAY) {
							field[i][j].setBackground(Color.LIGHT_GRAY);
						}
					}
				}
				data.getLivingNew().clear();
				data.getDeadNew().clear();
				data.setSave(null);
				data.setStart(false);
				data.setGeneration(0);
				lbl_generation.setText("Generation: 0");
				data.getView().requestFocusInWindow();

			}
		});

		lbl_interval = new JLabel("Intervall: 500ms");
		lbl_interval.setBounds(1735, 220, 100, 50);
		getContentPane().add(lbl_interval);

		sl_timeInterval = new JSlider(100, 1000, 500);
		sl_timeInterval.setBounds(1730, 255, 100, 20);
		getContentPane().add(sl_timeInterval);
		sl_timeInterval.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				data.setInterval(sl_timeInterval.getValue());
				lbl_interval.setText("Intervall: " + data.getInterval() + "ms");
				data.getView().requestFocusInWindow();
			}
		});

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_S) {
			data.setL(true);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			data.setD(true);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_S) {
			data.setL(false);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			data.setD(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}
}
