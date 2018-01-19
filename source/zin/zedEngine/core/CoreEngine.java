package zin.zedEngine.core;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import zin.zedEngine.graphics.Display;
import zin.zedEngine.graphics.DisplayState;
import zin.zedEngine.graphics.Input;
import zin.zedEngine.graphics.shaders.BasicShader;
import zin.zedEngine.graphics.shaders.PhongShader;

public class CoreEngine {

	private boolean isRunning;
	private Game game;
	private int width, height;
	private double frameTime;

	private ArrayList<Integer> frameAvg = new ArrayList<>();

	public CoreEngine(int width, int height, double frameRate, Game game) {
		this.isRunning = false;
		this.game = game;
		this.frameTime = 1.0 / frameRate;
		this.width = width;
		this.height = height;
	}

	private void initRenderingSystem() {
		GL11.glFrontFace(GL11.GL_CW);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		BasicShader.init();
		PhongShader.init(120, width, height, 0.1f, 1000);
	}
	
	public void createDisplay(String title, DisplayState state, int samples) {
		Display.createDisplay(width, height, title, state, samples);
		initRenderingSystem();
	}

	public void startEngine() {
		if (isRunning)
			return;

		isRunning = true;

		runEngine();
	}

	private void runEngine() {
		int updates = 0;
		int frames = 0;
		double frameCounter = 0;

		Input.init();
		game.init();

		double lastTime = getTime();
		double unprocessedTime = 0;

		while (isRunning) {
			boolean render = false;

			double startTime = getTime();
			double passedTime = startTime - lastTime;
			lastTime = startTime;

			unprocessedTime += passedTime;
			frameCounter += passedTime;

			while (unprocessedTime > frameTime) {
				render = true;

				unprocessedTime -= frameTime;

				if (Display.shouldClose())
					stopEngine();

				Input.updateInput();
				game.input();

				updates++;
				game.update();

				if (frameCounter >= 1.0) {
					System.out.println("FPS: " + frames + ", UPS: " + updates);
					frameAvg.add(frames);
					updates = 0;
					frames = 0;
					frameCounter = 0;
				}
			}
			if (render) {
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				game.render();
				Display.updateDisplay();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		cleanEngine();
	}

	public void stopEngine() {
		if (!isRunning)
			return;

		isRunning = false;

		if (frameAvg.size() != 0) {
			int fpsAvg = 0;

			for (int fps : frameAvg) {
				fpsAvg += fps;
			}

			System.out.println("Stopped engine succesfully with an avarage of " + fpsAvg / frameAvg.size() + " FPS.");
		} else {
			System.out.println("Stopped engine succesfully.");
		}

	}

	private void cleanEngine() {
		Display.destroyDisplay();
		game.cleanUp();
	}

	public double getTime() {
		return (double) System.nanoTime() / (double) 1000000000L;
	}

}
