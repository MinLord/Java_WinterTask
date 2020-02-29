import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Playlist{
	JFileChooser fc = new JFileChooser();
	ArrayList ls = new ArrayList();
	
	void add(JFrame frame) {
		fc.setMultiSelectionEnabled(true);
		int fileValid = fc.showOpenDialog(frame);
		if(fileValid == javax.swing.JFileChooser.CANCEL_OPTION) {
			return;
		} else if(fileValid == javax.swing.JFileChooser.APPROVE_OPTION) {
			File[] file = fc.getSelectedFiles();
			ls.addAll(Arrays.asList(file));
		}
	}
	ArrayList getListSong() {
		return ls;
	}
	
	FileInputStream fis;
	ObjectInputStream ois;
	
	public void openPls(JFrame frame) {
		fc.setMultiSelectionEnabled(false);
		int Valid = fc.showOpenDialog(frame);
		if(Valid == javax.swing.JFileChooser.CANCEL_OPTION) {
			return;
		}
		if(Valid == javax.swing.JFileChooser.APPROVE_OPTION) {
			File pls = fc.getSelectedFile();
			try {
				fis = new FileInputStream(pls);
				ois = new ObjectInputStream(fis);
				File tmp;
				while((tmp = (File)ois.readObject()) != null) {
					ls.add(tmp);
				}
				if((tmp = (File)ois.readObject()) == null) {
					ls.isEmpty();
				}
				ois.close();
			}catch(Exception e) {
				
			}
		}
	}
}