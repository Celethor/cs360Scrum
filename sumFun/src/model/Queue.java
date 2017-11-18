package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Queue<E> extends ArrayList<E>{
	/* class implements a queue using a linked list
	 * 
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 968124375431704053L;
	//Data Members
	private ArrayList<E> queue;
	
	//constructor
	public Queue() {
		queue = new ArrayList<E>();
	}
	
	//Enqueue adds integer to the end of queue
	void enqueue(E element) {
		queue.add(element);
	}
	
	//Dequeue removes element form the front of the queue
	E dequeue() {
		return queue.remove(0);
	}
	
	//getElement returns element 
	public E getElement(int index) {
		return queue.get(index);
	}
	
	//size of the array
	int getSize() {
		return queue.size();
	}
	
	//used to print elements of ArrayList<E>
	String printQueue() {
		String answer = "";
		answer = answer + Arrays.toString(queue.toArray());
		
		return answer;
		
	}
	
	public static void main(String args[]) {
		Queue<Integer> myQueue = new Queue<Integer>();
		Random rand = new Random();
		for(int i=0;i<5;i++){
			try {
				myQueue.enqueue(rand.nextInt(9));
			} catch (Exception e) {
				System.out.println("Queue initialization exception");
			}
		}
		
		myQueue.dequeue();
		myQueue.dequeue();
		
		
		System.out.println(myQueue.printQueue());
		
		if(myQueue.size() < 5) {
			while(myQueue.getSize() < 5) {
			myQueue.enqueue(rand.nextInt(9));
			}
		}
		
		System.out.println(myQueue.printQueue());
	}
	
}
