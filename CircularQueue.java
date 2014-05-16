
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tony
 * @param <Item>
 */
public class CircularQueue<Item> implements Iterable<Item> {
    private static int N, M, sz;
    private Node first, last;

    public CircularQueue() { 
        first = null;
        last = null;
        sz = 0;
    }
    
    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() { 
        return first == null;
    }

    public int size() { 
        return sz;
    }
    
    /**
     * Add item to the end of the list 
     * @param item 
     */
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty())
            first = last;
        else
            oldlast.next = last;
        sz++;
    }
    /**
     * Remove item from the beginning of the list 
     * @return 
     */
    public Item removeFirst() { 
        Item item = first.item;
        first = first.next;
        sz--;
        if(isEmpty())
            last = null;
        
        return item;
    }

    /**
     * 
     * @return
     */
    @Override
    public Iterator iterator() { 
        return new ListIterator<>(first);  
    }
    
    private class ListIterator<Item> implements Iterator<Item> {
        private Node current;

        public ListIterator(Node first) {
            current = first;
        }

        @Override
        public boolean hasNext() { 
            return current != null;
        }
        @Override
        public void remove()      { throw new UnsupportedOperationException();  }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = (Item) current.item;
            current = current.next; 
            return item;
        }
    }
    
    public static void main(String[] args) { 
        N = Integer.valueOf(args[0]);
        M = Integer.valueOf(args[1]);
        CircularQueue q = new CircularQueue();
        for (int i=0; i<N; i++)
            q.enqueue(i);
        
        Iterator itr = q.iterator();
        while (itr.hasNext() && sz != 0) {
            for (int i=0; i<M-1; i++) {
                q.enqueue(q.removeFirst());
            }
            StdOut.print(q.removeFirst() + " ");
        }

    }
}
