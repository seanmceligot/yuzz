package org.yuzz.web;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.yuzz.functor.Fun.F;
import org.yuzz.functor.Procedure.Proc2;

public class MapReduce {
	
	public class Emmitter<ListItem> {
		private BlockingQueue<ListItem> _queue = new LinkedBlockingQueue<ListItem>(); 
		public void emit(ListItem item) {
			_queue.offer(item);
		}
		/*
		public <Result> Result reduce(Reducer<ListItem,Result> reducer) {
			try {
			       while(true) { 
			    	   ListItem i1 = _queue.take();
			    	   ListItem i2 = _queue.take();
			    	   reducer.reduce(adder, i1,i2);
			       }
			     } catch (InterruptedException ex) { 
			     }
			return reducer.reduce(list);
		}*/
	}
	public static <In, Out> void transform(BlockingQueue<In> in, BlockingQueue<Out> out, F<In, Out> fun) {
		while(!in.isEmpty()) {
			try {
				In item = in.take();
				Out result = fun.f(item);
				out.offer(result);
			} catch (InterruptedException e) {
			}
		}
	}
	public static <Collector, In> void reduce(Collector c, BlockingQueue<In> in, Proc2<Collector, In> proc) {
		while(!in.isEmpty()) {
			try {
				In item = in.take();
				proc.f(c, item);
			} catch (InterruptedException e) {
			}
		}
		
	}
	public interface Mapper<R,T> {
		public R map(T t);

	}
	/*public interface Reducer<ListItem,Adder adder> {
		public Result reduce(List<ListItem> items);
	}*/
	/*
	public <ListItem,Input> List<ListItem> map(Mapper<ListItem,Input> mapper, Input t) {
		return mapper.map(t);
	}*/
	/*
	public static final class Adder<Input, Child> extends F2<Input, Input, Child> {
	}*/


}
