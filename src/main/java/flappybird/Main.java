package flappybird;

import flappybird.input.Input;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by lukas on 17.4.2018.
 */

/*
public class Main {

    public static void main(String[] args) {
        GameLoop game = new GameLoop();
        game.run();
    }
}
*/

public class Main implements Runnable {

    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private Thread thread;
    private boolean running = true;

    private long window;

    private void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    public void run() {
        init();
        while (running) {
            //System.out.println("test");
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
        window = glfwCreateWindow(WIDTH, HEIGHT, "Flappy Börd", NULL, NULL);
        if (window == NULL) {
            //Handle later
            System.out.println("fail 2");
            return;
        }

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - WIDTH) / 2, (vidmode.height() - HEIGHT) / 2);

        glfwSetKeyCallback(window, new Input());

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
    }

    private void update() {
        glfwPollEvents();
    }

    private void render() {
        glfwSwapBuffers(window);
    }

    public static void main(String[] args) {
        new Main().start();
    }
}