package sumFun;

public class Queue {
	/* class implements a queue using a circular array
	 * 
	 */
	
	//queue data member will be the circular array
	private int[] queue;
	
	//the front of the queue, this item will be returned upon dequeue
	private int read = 0;
	
	//the end of the queue, this is where numbers will be stored upon queue operation
	private int write = 0;
	
	//constructor will create a queue of specified size
	//size+1 is included as in implementation one array element is always empty
	//
	public Queue(int size){
		this.queue = new int[size+1];
	}
	
	//enqueue adds integer to the write of the queue
	private void enqueue(int x) throws Exception{
		if (read == (write + 1) % (queue.length)){
			throw new Exception("Queue is full");
		}
		queue[write] = x;
		write = (write + 1) % (queue.length);
	}
	
	//returns the element at the fromt of the queue
	private int dequeue() throws Exception{
		int result;
		
		if(read == write){
			throw new Exception("Queue is empty");
		}
		result = queue[read];
		queue[read] = -1;
		
		read = (read+1)%(queue.length);
		
		return result;
	}
	
	//returns item in queue at element
	private int getQueueElement(int index){
		int element;
		element = queue[(read + index) % queue.length];
		return element;
	}
	
	//toString method in order to test Circular Array
	//does not include empty element as it is never filled and is not 
	//relevent outside of making sure array stays within specified size

	public String toString(){
		String result = "HEAD--> [ ";
		int i;
		for(i=0; i<queue.length-1; i++){
			result = result + getQueueElement(i) + ", ";
		}
		result = result + "] <-- TAIL";
		return result;
	}
}
