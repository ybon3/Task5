package com.dtc.test.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;

public class TestEP implements EntryPoint {
	private static final RpcServiceAsync rpc = GWT.create(RpcService.class);

	private DateField dateField = new DateField();
	private TextButton button = new TextButton("送出");
	//private boolean rpcFinish = false;
	//private int counter = 0;
	private DateTimeFormat format = DateTimeFormat.getFormat("HH:mm:ss");

	private int testInt = Integer.MAX_VALUE;
	
	@Override
	public void onModuleLoad() {
		Object c = null;
		try {
			c.getClass();
		} catch (Exception e) {
			GWT.log("zzz: " + e.getClass());
		}
		
		button.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				start();
			}
		});
		
		VerticalLayoutContainer vc = new VerticalLayoutContainer();
		vc.add(dateField);
		vc.add(button);

		RootPanel.get().add(vc);
	}
	
	class MyAsyncCallback implements AsyncCallback<Boolean> {
		private boolean rpcFinish = false;
		
		public boolean getRpcFinish(){return rpcFinish;}
		@Override
		public void onSuccess(Boolean result) {
			GWT.log(result + "你的時間比較" + (result ? "早" : "晚"));
			rpcFinish = true;
		}
		
		@Override
		public void onFailure(Throwable caught) {
			GWT.log("出問題啦！訊息：" + caught.getMessage());
			rpcFinish = true;
		}
	}
	
	private void start() {
		GWT.log("開始啦開始啦～～～～qq");
		System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		String str = "byte: " + (Byte.MAX_VALUE+1) + "\r\n"
				+ "short: " + (Short.MAX_VALUE+1) + "\r\n"
				+ "int: " + (Integer.MAX_VALUE+1) + "\r\n"
				+ "long: " + (Long.MAX_VALUE+1) + "\r\n"
				+ "float: " + (Float.MAX_VALUE+1) + "\r\n"
				+ "double: " + (Double.MAX_VALUE+1);
		GWT.log("TEST: " + str);
		//counter = 0;
		//rpcFinish = false;
		
		final MyAsyncCallback async = new MyAsyncCallback();
		
		rpc.before(dateField.getValue(), async); 
		//發 RPC
		/*
		rpc.before(dateField.getValue(), new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				GWT.log(result + "你的時間比較" + (result ? "早" : "晚"));
				rpcFinish = true;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("出問題啦！訊息：" + caught.getMessage());
				rpcFinish = true;
			}
		});*/
		
		//每隔 1 秒鐘寫一行 log 以顯示出 async 的行為
		Scheduler.get().scheduleFixedPeriod(new RepeatingCommand() {
			int counter = 0;
			
			@Override
			public boolean execute() {
				counter++;
				GWT.log("第 " + counter + " 次 @ " + format.format(new Date()));
				return !async.getRpcFinish();
			}
		}, 1000);		
	}
}
