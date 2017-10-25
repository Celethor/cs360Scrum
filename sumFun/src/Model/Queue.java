package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queue<E> extends ArrayList<E>{
	/* class implements a queue using a linked list
	 * 
	 */
	
	//Data Members
	private ArrayList<E> queue;
	
	//constructor
	public Queue() {
		queue = new ArrayList<E>();
	}
	
	//Enqueue adds integer to the end of queue
	private void enqueue(E element) {
		queue.add(element);
	}
	
	//Dequeue removes element form the front of the queue
	private E dequeue() {
		return queue.remove(0);
	}
	
	//getElement returns element 
	private E getElement(int index) {
		return queue.get(index);
	}
	
	//used to print elements of ArrayList<E>
	private String printQueue() {
		String answer = "";
		answer = answer + Arrays.toString(queue.toArray());
		
		return answer;
		
	}
	
}
