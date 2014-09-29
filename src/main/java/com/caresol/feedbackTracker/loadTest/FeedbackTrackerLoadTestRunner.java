package com.caresol.feedbackTracker.loadTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author csriram2
 * 
 */
public class FeedbackTrackerLoadTestRunner {

	private static ScheduledExecutorService scheduledThreadPool;

	private static ScheduledExecutorService singleThreadExecutor;

	private static ExecutorService fixedThreadPool;

	private static ThreadPoolExecutor threadPoolExecutor;

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static void main(String[] args) throws InterruptedException {

		PostRequestInitiator postRequestInitiator = new PostRequestInitiator();

		// threadPoolExecutor = new ThreadPoolExecutor(5, 20, 0,
		// TimeUnit.SECONDS,
		// new ArrayBlockingQueue<Runnable>(10));
		// for (int i = 0; i < 10; i++) {
		// threadPoolExecutor.execute(postRequestInitiator);
		// }

		// fixedThreadPool = Executors.newFixedThreadPool(20);
		// for (int i = 0; i < 20; i++) {
		// fixedThreadPool.submit(postRequestInitiator);
		// }

		// singleThreadExecutor =
		// Executors.newSingleThreadScheduledExecutor();
		// singleThreadExecutor.scheduleAtFixedRate(postRequestInitiator, 0,
		// 1, TimeUnit.SECONDS);

		scheduledThreadPool = Executors.newScheduledThreadPool(5);

		for (int i = 0; i < 10; i++)
			scheduledThreadPool.schedule(postRequestInitiator, 1,
					TimeUnit.SECONDS);

		Thread.sleep(30000);

		scheduledThreadPool.shutdown();
		// threadPoolExecutor.shutdown();
		// addShutdownHook();

		while (!scheduledThreadPool.isTerminated()) {
			// wait for all tasks to finish
		}
		System.out.println("Finished all jobs");
	}

	private static void addShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// fixedThreadPool.shutdown();
				// scheduledExecutorService.shutdown();
				threadPoolExecutor.shutdown();
			}
		});
	}
}
