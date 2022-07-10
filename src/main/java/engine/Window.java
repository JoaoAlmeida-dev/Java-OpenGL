package engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

	private final String title;
	public long windowHandle;
	public boolean resized;
	private int width;
	private int height;
	private boolean vSync;

	public Window(String title, int width, int height, boolean vSync) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.vSync = vSync;
		this.resized = false;
	}

	public void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (!glfwInit()) {
			throw new IllegalStateException("WINDOW::init::Unable to initialize GLFW");
		}

		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);


		// Create the window
		windowHandle = GLFW.glfwCreateWindow(width, height, title, NULL, NULL);
		if (windowHandle == NULL) {
			throw new IllegalStateException("WINDOW::init::Failed to create the GLFW window");
		}
		GLFW.glfwMakeContextCurrent(windowHandle);

		// Setup resize callback
		GLFW.glfwSetFramebufferSizeCallback(windowHandle, (window, widthCallback, heightCallback) -> {
			Window.this.width = widthCallback;
			Window.this.height = heightCallback;
			Window.this.setResized(true);
		});

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
			}
		});

		// Get the resolution of the primary monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		// Center our window
		glfwSetWindowPos(
				windowHandle,
				(vidmode.width() - width) / 2,
				(vidmode.height() - height) / 2
		);


		// Make the OpenGL context current
		glfwMakeContextCurrent(windowHandle);

		if (isvSync()) {
			// Enable v-sync
			glfwSwapInterval(1);
		}

		// Make the window visible
		glfwShowWindow(windowHandle);

		GL.createCapabilities();

		// Set the clear color
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

	}


	public boolean isKeyPressed(int keyCode) {
		return GLFW.glfwGetKey(windowHandle, keyCode) == GLFW.GLFW_PRESS;
	}

	public void update() {
		//swap image rendered in buffer to the window with given id
		GLFW.glfwSwapBuffers(windowHandle);
		GLFW.glfwPollEvents();
		System.out.println("fps");
	}

	public void closeWindow() {
		GLFW.glfwDestroyWindow(windowHandle);
		GLFW.glfwTerminate();
	}

	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(windowHandle);
	}

	public void setClearColor(float r, float g, float b, float alpha) {
		glClearColor(r, g, b, alpha);
	}

	//region getters
	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	boolean isvSync() {
		return vSync;
	}
	//endregion

	//region setters
	public void setvSync(boolean vSync) {
		this.vSync = vSync;
	}

	public void setResized(boolean resized) {
		this.resized = resized;
	}
	//endregion


}
