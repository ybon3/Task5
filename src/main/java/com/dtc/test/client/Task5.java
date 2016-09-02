package com.dtc.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;

public class Task5 implements EntryPoint {

	private DateField dateField = new DateField();
	private TextButton button = new TextButton("送出");
	
	@Override
	public void onModuleLoad() {
		
		button.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				
			}
		});
		
		VerticalLayoutContainer vc = new VerticalLayoutContainer();
		vc.add(dateField);
		vc.add(button);

		RootPanel.get().add(vc);
	}
}
