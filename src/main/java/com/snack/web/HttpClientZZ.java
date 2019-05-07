package com.snack.web;
/**
*
* @author ZhuangQianMin
* @version 创建时间：2019年4月9日 下午2:27:23
*
*/

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.snack.utils.MyPool;

public class HttpClientZZ {
	
	@Autowired
	static MyPool myPool;

	static HttpClient client = HttpClientBuilder.create().build();
	
	public final void select() {
		
	}
	public static void select(String url) {
		HttpPost post=new HttpPost(url);
	    myPool.setPoolTwo().execute(()->{
    		System.out.println(Thread.currentThread());
    		for (int i = 0; i <1; i++) {
    			try {
    				HttpResponse response = client.execute(post);
    				System.out.println(EntityUtils.toString(response.getEntity()));
    			} catch (ClientProtocolException e) {
    				e.printStackTrace();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}				
		});
	    myPool.setPoolOne().execute(()->{
    		System.out.println(Thread.currentThread());
    		for (int i = 0; i < 1; i++) {
    			try {
    				HttpResponse response = client.execute(post);
    				System.out.println(EntityUtils.toString(response.getEntity()));
    			} catch (ClientProtocolException e) {
    				e.printStackTrace();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}				
	    });
	    Thread thread = new Thread(()->{
	    	System.out.println();
	    });
	}
	public static void main(String[] args) {
//		select("http://118.25.111.50:8080/lastsecond/gbf?id=dswwwadas");
//		myPool.setPoolOne().shutdownNow();
		//myPool.setPoolTwo().shutdownNow();
		//select("http://localhost:8085/lastsecond/gbf?id=dswwwadas");
//		Thre thre = new Thre();
//		thre.start();
//		Thr thr=new Thr();
//		Thread thread = new Thread(thr);
//		thread.start();
		
		ThredCall tc=new ThredCall();
		FutureTask<String> ft=new FutureTask<String>(tc);
		new Thread(ft).start();
		System.gc();
		try {
			String integer = ft.get();
			System.out.println(integer);
		} catch (Exception e) {
			System.out.println(e.getCause());
		} finally {
			System.out.println("555");
		}
		System.out.println("dsadsadsadsadsa");
		
//		y y=new y();
//		FutureTask<String> a=new FutureTask<String>(y);
//		new Thread(a).start();
//		try {
//			System.out.println(a.get());
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		} finally {
//			System.out.println("22222");
//		}
		
//		for (int i = 0; i < 10; i++) {			
//			///System.out.println((int)(Math.random()*10));
//		}
//		Random r=new Random(1);
//		for (int i = 0; i < 10; i++) {
//			System.out.println(r.nextInt(10));
//		}
	}
}

class a{
	private int code;
	private String msg;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "a [code=" + code + ", msg=" + msg + "]";
	}
}

class y implements Callable<String>{
	@Override
	public String call() throws Exception {
		a f=new a();
		f.setCode(1);
		f.setMsg("ok");
		return JSONObject.toJSONString(f);
	}
}



class ThredCall implements Callable<String> {

	@Override
	public String call() throws Exception {
		int sum = 0;
        for (int i = 0; i <= 100000; i++) {
            sum += i;
            if(sum>10000) {            	
            	throw new Exception("数据超出异常");
            }
        }
        return "ok";
	}
}

class Thre extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			HttpResponse response;
			try {
				response = HttpClientZZ.client.execute(new HttpPost("http://localhost:8085/lastsecond/gbf?id=dswwwadas"));
				System.out.println("继承:"+EntityUtils.toString(response.getEntity()));
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class Thr implements Runnable{
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			HttpResponse response;
			try {
				//response = HttpClientZZ.client.execute(new HttpPost("http://118.25.111.50:8080/lastsecond/gbf?id=dswwwadas"));
				response = HttpClientZZ.client.execute(new HttpPost("http://localhost:8085/lastsecond/gbf?id=dswwwadas"));
				System.out.println("实现"+EntityUtils.toString(response.getEntity()));
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
