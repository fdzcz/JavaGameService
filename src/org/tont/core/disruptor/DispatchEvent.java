package org.tont.core.disruptor;

import org.tont.proto.GameMsgEntity;

public class DispatchEvent {

	private GameMsgEntity msgEntity;

	public GameMsgEntity getMsgEntity() {
		return msgEntity;
	}

	public void setMsgEntity(GameMsgEntity msgEntity) {
		this.msgEntity = msgEntity;
	}
	
}
