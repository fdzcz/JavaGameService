package org.tont.core.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.tont.proto.GameMsgEntity;

public class SendMsgEncoder extends MessageToByteEncoder<GameMsgEntity> {

	@Override
	protected void encode(ChannelHandlerContext ctx, GameMsgEntity msg,
			ByteBuf out) throws Exception {
		int dataLength = msg.getData() == null ? 0 : msg.getData().length;
		out.ensureWritable(4 + dataLength);
		out.writeInt(dataLength);
		out.writeShort(msg.getMsgCode());
		if (dataLength > 0) {
			out.writeBytes(msg.getData());
		}
	}

}
