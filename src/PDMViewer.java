import java.io.File;

import org.yjgoo.jpdmviewer.ui.MainWindow;

/**
 * PDM Viewer main program
 * 
 * @author yjgoo
 * 
 */
public class PDMViewer {

	public static void main(String[] args) {
		MainWindow jwin = new MainWindow();
		jwin.show();
		if (args.length > 1 && args[1] != null) {
			File f = new File(args[1]);
			if (f.exists() && f.isFile()) {
				jwin.loadPDM(f);
			}
		}
	}

}
