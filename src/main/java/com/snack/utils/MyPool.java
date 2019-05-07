package com.snack.utils;
/**
*
* @author ZhuangQianMin
* @version 创建时间：2019年3月26日 上午11:21:11
*
*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import lombok.Data;

@Component
@Data
public class MyPool {
	
   static ExecutorService pool;
	
	public static ExecutorService setPoolOne() {
		//ThreadPoolExecutor.prestartAllCoreThreads();
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		//加上 ThreadFactoryBuilder().setNameFormat(nameFormat)build()设置线程名称
		ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("哈哈哈-%d").build();
		pool=new ThreadPoolExecutor(availableProcessors, availableProcessors, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), factory);
		return pool;
	}
	
	public  static ExecutorService setPoolTwo() {
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		pool=Executors.newFixedThreadPool(availableProcessors);
		return pool;
	}
	
	public ExecutorService poolText() {
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(availableProcessors);
		return newFixedThreadPool;
	}
	public  void mazin() {
		ExecutorService poolText = poolText();
		poolText.execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(1);
			}
		});
	}
}
