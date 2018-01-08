package zin.zedEngine.core;

import java.util.ArrayList;

public class Main {

	private static boolean isRunning;
//	private static Game game;

	private static ArrayList<Integer> frameAvg = new ArrayList<>();

	public static void main(String[] args) {
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

//		game.initGame();

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

			while (unprocessedTime > (1.0 / 500/*game.FPS_LIMIT*/)) {
				render = true;

				unprocessedTime -= (1.0 / 500/*game.FPS_LIMIT*/);

				//if (Window.shouldClose())
					//stopEngine();

				//game.updateInput();
				//Window.updateInput();

				updates++;
				//game.updateGame();

				if (frameCounter >= 1.0) {
					System.out.println("[Zed] FPS: " + frames + ", UPS: " + updates);
					frameAvg.add(frames);
					updates = 0;
					frames = 0;
					frameCounter = 0;
				}
			}
			if (render) {
				//glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				//game.renderGame();
				//Window.updateWindow();
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

	/*private static void stopEngine() {
		if (!isRunning)
			return;

		isRunning = false;

		if (frameAvg.size() != 0) {
			int fpsAvg = 0;

			for (int fps : frameAvg)
			{
				fpsAvg += fps;
			}

			System.out.println(
					"[Zed] Stopped engine succesfully with an avarage of " + fpsAvg / frameAvg.size() + " FPS.");
		} else {
			System.out.println("[Zed] Stopped engine succesfully.");
		}

	}*/

	private static void cleanEngine()
	{
//		game.cleanUp();
//		Window.destroyWindow();
	}

	public static double getTime()
	{
		return (double) System.nanoTime() / (double) 1000000000L;
	}

}
