package zin.zedEngine.core;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import zin.game.core.TestGame;
import zin.zedEngine.game.Game;
import zin.zedEngine.graphics.Display;

public class Main {

	private static boolean isRunning;
	private static Game game;
	private static Display display;

	private static ArrayList<Integer> frameAvg = new ArrayList<>();

	public static void main(String[] args) {
		game = new TestGame();
		display = new Display(Game.WIDTH, Game.HEIGHT, Game.TITLE, Game.STATE, Game.SAMPLES);

		display.createDisplay();

		GL11.glFrontFace(GL11.GL_CW);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);

		System.out.println("[Zed] Window created succesfully.");

		startEngine();
	}

	private static void startEngine() {
		if (isRunning)
			return;

		isRunning = true;

		runEngine();
	}

	private static void runEngine() {
		System.out.println("[Zed] Engine started succesfully.");

		int updates = 0;
		int frames = 0;
		double frameCounter = 0;

		game.startGame(display);

		System.out.println("[Zed] Game initialized succesfully.");

		double lastTime = getTime();
		double unprocessedTime = 0;

		System.out.println("[Zed] Game loop started succesfully.");

		while (isRunning) {
			boolean render = false;

			double startTime = getTime();
			double passedTime = startTime - lastTime;
			lastTime = startTime;

			unprocessedTime += passedTime;
			frameCounter += passedTime;

			while (unprocessedTime > (1.0 / Game.FPS_LIMIT)) {
				render = true;

				unprocessedTime -= (1.0 / Game.FPS_LIMIT);

				if (display.shouldClose())
					stopEngine();

				// Window.updateInput();

				updates++;
				game.updateGame();

				if (frameCounter >= 1.0) {
					System.out.println("[Zed] FPS: " + frames + ", UPS: " + updates);
					frameAvg.add(frames);
					updates = 0;
					frames = 0;
					frameCounter = 0;
				}
			}
			if (render) {
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				game.renderGame();
				display.updateDisplay();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("[Zed] Clean up succesful.");
		cleanEngine();
	}

	private static void stopEngine() {
		if (!isRunning)
			return;

		isRunning = false;

		if (frameAvg.size() != 0) {
			int fpsAvg = 0;

			for (int fps : frameAvg) {
				fpsAvg += fps;
			}

			System.out.println(
					"[Zed] Stopped engine succesfully with an avarage of " + fpsAvg / frameAvg.size() + " FPS.");
		} else {
			System.out.println("[Zed] Stopped engine succesfully.");
		}

	}

	private static void cleanEngine() {
		game.cleanUp();
		display.destroyDisplay();
	}

	public static double getTime() {
		return (double) System.nanoTime() / (double) 1000000000L;
	}

}
