package MP3_Player;

import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.Color;
import java.applet.*;

public class Player {

	private JFrame frame;
	private File songFile;
	private JLabel songTitle;
	private Player player;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Player window = new Player();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Player() {
		initialize();
	}

	public Player(FileInputStream fileInputStream) {
		
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 479, 328);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 473, 184);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play(); //재생 메서드
			}
		});
		button_1.setIcon(new ImageIcon("D:\\\\SCHOOL\\\\1107_1111_PROJECT\\\\newproject\\\\muted.png"));
		button_1.setBounds(26, 52, 64, 69);
		button_1.setFocusPainted(false);
		button_1.setBorderPainted(false);
		button_1.setSelectedIcon(null);
		button_1.setForeground(SystemColor.menu);
		button_1.setBackground(SystemColor.text);
		panel.add(button_1);
		
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause(); //일시정지 메서드
			}
		});
		button_2.setIcon(new ImageIcon("D:\\\\SCHOOL\\\\1107_1111_PROJECT\\\\newproject\\\\pause.png"));
		button_2.setBounds(104, 52, 64, 69);
		button_2.setFocusPainted(false);
		button_2.setBorderPainted(false);
		button_2.setSelectedIcon(null);
		button_2.setForeground(SystemColor.menu);
		button_2.setBackground(SystemColor.text);
		panel.add(button_2);
		
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Player p = new Player(new FileInputStream(songFile));
					p.play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		button_3.setIcon(new ImageIcon("D:\\\\SCHOOL\\\\1107_1111_PROJECT\\\\newproject\\\\play.png"));
		button_3.setBounds(185, 37, 103, 108);
		button_3.setFocusPainted(false);
		button_3.setBorderPainted(false);
		button_3.setSelectedIcon(null);
		button_3.setForeground(SystemColor.menu);
		button_3.setBackground(SystemColor.text);
		panel.add(button_3);
	
		
	
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop(); //정지 메서드
			}
		});
		button_4.setIcon(new ImageIcon("D:\\\\SCHOOL\\\\1107_1111_PROJECT\\\\newproject\\\\stop.png"));
		button_4.setBounds(305, 52, 64, 69);
		button_4.setFocusPainted(false);
		button_4.setBorderPainted(false);
		button_4.setSelectedIcon(null);
		button_4.setForeground(SystemColor.menu);
		button_4.setBackground(SystemColor.text);
		panel.add(button_4);
		
		
		
		
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});
		button_5.setIcon(new ImageIcon("D:\\\\SCHOOL\\\\1107_1111_PROJECT\\\\newproject\\\\upload.png"));
		button_5.setBounds(386, 52, 60, 69);
		button_5.setFocusPainted(false);
		button_5.setBorderPainted(false);
		button_5.setSelectedIcon(null);
		button_5.setForeground(SystemColor.menu);
		button_5.setBackground(SystemColor.text);
		panel.add(button_5);
		
		

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(0, 183, 473, 105);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel songTitle = new JLabel("Playing");
		songTitle.setFont(new Font("바탕", Font.PLAIN, 24));
		songTitle.setHorizontalAlignment(SwingConstants.CENTER);
		songTitle.setBounds(0, 15, 473, 75);
		panel_1.add(songTitle);
	}

	
	protected void play() {
		
	}

	private void open() {
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("재생할 음악 선택");
			chooser.showOpenDialog(null);
			songFile = chooser.getSelectedFile();
			songTitle.setText(songFile.getName());
			Player p = new Player(new FileInputStream(songFile));
			p.play();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void pause() {
		try {
			Player p = new Player(new FileInputStream(songFile));
			p.pause();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void stop() {
		try {
			Player p = new Player(new FileInputStream(songFile));
			p.stop();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
