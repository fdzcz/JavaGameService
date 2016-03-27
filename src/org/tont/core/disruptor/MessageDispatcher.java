package org.tont.core.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.tont.proto.GameMsgEntity;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class MessageDispatcher {
	private RingBuffer<DispatchEvent> ringBuffer;
	private EventFactory<DispatchEvent> eventFactory;
	private ExecutorService executor;
	private int ringBufferSize; //RingBuffer的数组大小
	private Disruptor<DispatchEvent> disruptor;
	private EventHandler<DispatchEvent> eventHandler;
	private WaitStrategy strategy;
	
	public MessageDispatcher(int ringBufferSize, WaitStrategy strategy) {
		this.ringBufferSize = ringBufferSize;
		this.strategy = strategy;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void init() {
		eventFactory = new DispatchEventFactory();
		executor = Executors.newCachedThreadPool();
		disruptor = new Disruptor<DispatchEvent>(eventFactory,
		                ringBufferSize, executor, ProducerType.MULTI,
		                strategy);
		        
		eventHandler = new DispatchEventHandler();
		disruptor.handleEventsWith(eventHandler);
		disruptor.start();
		
		ringBuffer = disruptor.getRingBuffer();
	}
	
	public void onData(GameMsgEntity inMsgEntity)
	{
	    long sequence = ringBuffer.next();
	    try
	    {
	    	DispatchEvent event = ringBuffer.get(sequence);
	    	event.setMsgEntity(inMsgEntity);
	    }
	    finally
	    {
	        ringBuffer.publish(sequence);
	    }
	}
}
