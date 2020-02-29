import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MP3 extends JFrame {
	Playlist pl = new Playlist();
	ArrayList updateList = new ArrayList();
	javazoom.jl.player.Player player;
	File stan;
	JList JPlaylist;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MP3 frame = new MP3();
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
	public MP3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				add();
			}
		});
		btnAdd.setBounds(126, 43, 105, 45);
		contentPane.add(btnAdd);
		
		JButton btnDel = new JButton("DEL");
		btnDel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				delete();
			}
		});
		btnDel.setBounds(332, 43, 105, 45);
		contentPane.add(btnDel);
		
		JPlaylist = new JList();
		JPlaylist.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		JPlaylist.setBounds(14, 120, 541, 129);
		contentPane.add(JPlaylist);
		
		JScrollPane scrollPane = new JScrollPane(JPlaylist);
		scrollPane.setBounds(14, 120, 541, 223);
		contentPane.add(scrollPane);
		
		JButton btnPlay = new JButton(">");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				start();
			}
		});
		btnPlay.setBounds(245, 378, 100, 100);
		contentPane.add(btnPlay);
		
		JButton btnPause = new JButton("||");
		btnPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				stop();
			}
		});
		btnPause.setBounds(76, 378, 100, 100);
		contentPane.add(btnPause);
		
		JButton btnUp = new JButton("UP");
		btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				up();
			}
		});
		btnUp.setBounds(399, 378, 100, 45);
		contentPane.add(btnUp);
		
		JButton btnDown = new JButton("DOWN");
		btnDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				down();
			}
		});
		btnDown.setBounds(399, 433, 100, 45);
		contentPane.add(btnDown);
	}
	private void updateList() {
		updateList = pl.getListSong();
		DefaultListModel model = new DefaultListModel();
		for(int i = 0; i < updateList.size(); i++) {
			int j = i + 1;
			model.add(i, j + " | " + ((File)updateList.get(i)).getName());
		}
		JPlaylist.setModel(model);
	}
	private void add() {
		pl.add(this);
		updateList();
	}
	private void delete() {
		try {
			int del = JPlaylist.getLeadSelectionIndex();
			pl.ls.remove(del);
			updateList();
		}catch(Exception e){
		}
	}
	private void up() {
		try {
			int uf = JPlaylist.getLeadSelectionIndex();
			stan = (File)pl.ls.get(uf);
			pl.ls.remove(uf);
			pl.ls.add(uf - 1, stan);
			updateList();
			JPlaylist.setSelectedIndex(uf - 1);
		}catch(Exception e){
		}
	}
	private void down() {
		try {
			int df = JPlaylist.getLeadSelectionIndex();
			stan = (File)pl.ls.get(df);
			pl.ls.remove(df);
			pl.ls.add(df + 1, stan);
			updateList();
			JPlaylist.setSelectedIndex(df + 1);
		}catch(Exception e){
		}
	}
	
	File plays;
	static int a = 0;
	private void start() {
		if(a == 0) {
			try {
				int p = JPlaylist.getSelectedIndex();
				plays = (File)this.updateList.get(p);
				FileInputStream fis = new FileInputStream(plays);
				BufferedInputStream bis = new BufferedInputStream(fis);
				player = new javazoom.jl.player.Player(bis);
				a = 1;
			}catch(Exception e) {
				System.out.println("실행 파일에 문제 발생");
				System.out.println(e);
			}
			new Thread() {
				public void run() {
					try {
						player.play();
					}catch(Exception e) {
					}
				}
			}.start();
		} else {
			player.close();
			a = 0;
			start();
		}
	}
	private void stop() {
		player.close();
	}
}