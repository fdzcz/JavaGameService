package org.tont.core.netty;

import java.nio.ByteOrder;

import org.tont.proto.GameMsgEntity;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class RevMsgDecoder extends LengthFieldBasedFrameDecoder{

	/**
	 * @param byteOrder
	 * @param maxFrameLength
	 *            字节最大长度,大于此长度则抛出异常
	 * @param lengthFieldOffset
	 *            开始计算长度位置,这里使用0代表放置到最开始
	 * @param lengthFieldLength
	 *            描述长度所用字节数
	 * @param lengthAdjustment
	 *            长度补偿,这里由于命令码使用2个字节.需要将原来长度计算加2
	 * @param initialBytesToStrip
	 *            开始计算长度需要跳过的字节数
	 * @param failFast
	 */
	public RevMsgDecoder(ByteOrder byteOrder, int maxFrameLength,
			int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment,
			int initialBytesToStrip, boolean failFast) {
		super(byteOrder, maxFrameLength, lengthFieldOffset, lengthFieldLength,
				lengthAdjustment, initialBytesToStrip, failFast);
	}
	
	public RevMsgDecoder () {
		this(ByteOrder.BIG_ENDIAN, 100000, 0, 4, 2, 4, true);
	}
	
	/**
	 * 根据构造方法自动处理粘包,半包.然后调用此decode
	 * */
	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {

		ByteBuf frame = (ByteBuf) super.decode(ctx, byteBuf);
		if (frame == null) {
			return null;
		}

		short msgCode = frame.readShort();// 先读取两个字节命令码
		
		GameMsgEntity msg = new GameMsgEntity();
		if (msgCode > 200) {	//截取pid和token
			int pid = frame.readInt();
			String token = frame.readBytes(64).toString();
			msg.setPid(pid);
			msg.setToken(token);
		}
		
		byte[] data = new byte[frame.readableBytes()];// 其它数据为实际数据
		frame.readBytes(data);
		
		msg.setMsgCode(msgCode);
		msg.setData(data);
		
		frame.release();
		return msg;
	}

}
