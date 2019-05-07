package com.snack.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author ZhuangQianMin
 * @version 创建时间：2019年3月26日 上午10:09:46
 *
 */
@Configuration
@EnableScheduling
public class ScheduledTasks {

	@Autowired
	MyPool pool;

//每30秒执行一次
	@Scheduled(fixedRate = 1000 * 30)
	public void reportCurrentTime() {

		pool.setPoolOne().execute(() -> {
			for (int ys = 0; ys < 10; ys++) {
				System.out.println(1);
			}
		});

		pool.setPoolTwo().execute(() -> {
			for (int ys = 0; ys < 10; ys++) {
				System.out.println(2);
			}
		});

		new Thread(() -> {
			System.out.println("哈哈哈哈");
		}).start();
	}

//在固定时间执行
	@Scheduled(cron = "0 */1 * * * * ")
	public void reportCurrentByCron() {
		System.out.println("Scheduling Tasks Examples By Cron: The time is now " + dateFormat().format(new Date()));
	}

	private SimpleDateFormat dateFormat() {
		return new SimpleDateFormat("HH:mm:ss");
	}
}
