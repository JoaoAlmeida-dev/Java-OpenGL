import org.lwjgl.opengl.GL11;

public class MainApp {
	public static void main(String[] args) {
		Window window = new Window();
		window.createWindow(1280,720,"Tutorial");

		while (!window.shouldClose()){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			GL11.glClearColor(1,0,0,1);
			window.updateWindow();
		}
		window.closeWindow();
	}

}
