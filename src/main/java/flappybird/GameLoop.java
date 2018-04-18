package flappybird;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.system.MemoryUtil.*;

/**
 * Created by lukas on 17.4.2018.
 */

public class GameLoop {

    private boolean running = true;

    private long window;

    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    public void run() {
        init();
        while (running) {
            System.out.println("test");
            update();
            render();

            if (glfwWindowShouldClose(window)) {
                running = false;
            }
        }
    }

    private void init() {
        if (!glfwInit()) {
            //Handle later (?); I don't even know if the condition is written correctly
            System.out.println("fail 1");
            return;
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        window = glfwCreateWindow(WIDTH, HEIGHT, "Flappy bird", NULL, NULL);
        if (window == NULL) {
            //Handle later
            System.out.println("fail 2");
            return;
        }

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, 100, 100);     //These 2 int parameters are different from the ones in the video
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
    }

    private void update() {
        glfwPollEvents();
    }

    private void render() {
        glfwSwapBuffers(window);
    }
}
