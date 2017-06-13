package com.searchengin.full.client;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.searchengin.full.config.InitParams;
import com.searchengin.full.index.IndexServer;
import com.searchengin.util.StringUtils;

public class OpIndex implements ServletContextListener{
	private long PERIODTIME = getPeriodTime();
	
	protected long getPeriodTime(){
		String pt = StringUtils.getConfigParam(InitParams.INDEX_PERIOD, "2", InitParams.SEARCH_PROPERTIES);
		long periodTime = Long.valueOf(pt);
		return periodTime*1000*60;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("destroyed!");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Timer tim = new Timer();
		TimerTask tTask = new TimerTask(){
			IndexServer indexServer = (IndexServer)IndexServer.getService();
			@Override
			public void run() {
				long indexStart = System.currentTimeMillis();
				indexServer.createIndex();
				long indexEnd = System.currentTimeMillis();
				System.out.println("Your index total spent on "+(indexEnd-indexStart)+" ms.");
			}
			
		};
		tim.schedule(tTask, 0, PERIODTIME);
		
	}

}
