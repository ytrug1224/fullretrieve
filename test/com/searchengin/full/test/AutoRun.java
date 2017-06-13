package com.searchengin.full.test;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 自动启动运行测试
 * @author xyq
 *
 */
public class AutoRun implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("destroyed!");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Timer tim = new Timer();
		TimerTask tTask = new TimerTask(){

			@Override
			public void run() {
				System.out.println("It's work !");
			}
			
		};
		tim.schedule(tTask, 0, 200);
		
	}

}
