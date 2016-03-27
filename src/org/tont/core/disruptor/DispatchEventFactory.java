package org.tont.core.disruptor;

import com.lmax.disruptor.EventFactory;

public class DispatchEventFactory implements EventFactory<DispatchEvent> {

	@Override
	public DispatchEvent newInstance() {
		return new DispatchEvent();
	}
	
}
