package org.tont.core.disruptor;

import org.tont.proto.GameMsgEntity;

import com.lmax.disruptor.EventHandler;

public class DispatchEventHandler implements EventHandler<DispatchEvent> {

	@Override
	public void onEvent(DispatchEvent dispatchEvent, long sequence, boolean endOfBatch)
			throws Exception {
		//¥¶¿Ì
		GameMsgEntity msg = dispatchEvent.getMsgEntity();
		
		switch(msg.getMsgCode()) {
		case 100:
			registe(msg);
			break;
		case 101:
			login(msg);
			break;
		}
	}
	
	public void registe(GameMsgEntity msg) {}
	
	public void login(GameMsgEntity msg) {}

}
