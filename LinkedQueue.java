/**
 A class that implements the ADT queue by using a chain of nodes
 that has both head and tail references.

 @author Brett Sulllivan
 @version 4.0
 */
public final class LinkedQueue<T> implements QueueInterface<T>
{
    private Node firstNode; // References node at front of queue
    private Node lastNode;  // References node at back of queue

    public LinkedQueue()
    {
        firstNode = null;
        lastNode = null;
    } // end default constructor
    public void enqueue(T newEntry)
    {
        Node newNode = new Node(newEntry, null);
        if (isEmpty())
            firstNode  = newNode;
        else
            lastNode.setNextNode(newNode);
        lastNode = newNode;
    } // end enqueue// Version 4.0



    public T getFront()
    {
        if (isEmpty())
            return null;
        else
            return firstNode.getData();
    }
    // end getFront// Version 4.0



    public T dequeue()
    {
        T front = getFront();
        // Might throw EmptyQueueException
        assert firstNode != null;
        firstNode.setData(null);
        firstNode = firstNode.getNextNode();
        if (firstNode == null)
            lastNode = null;
        return front;
    } // end dequeue// Version 4.0



    public boolean isEmpty()
    {
        return (firstNode == null) && (lastNode == null);}
    // end isEmpty



    public void clear()
    {
        firstNode = null;
        lastNode = null;
    } // end clear// Version 4.0
    private class Node
    {
        private T    data; // Entry in queue
        private Node next; // Link to next node

        private Node(T dataPortion)
        {
            data = dataPortion;
            next = null;
        } // end constructor
        private Node(T dataPortion, Node linkPortion)
        {
            data = dataPortion;
            next = linkPortion;
        } // end constructor

        private T getData()
        {
            return data;
        } // end getData

        private void setData(T newData)
        {
            data = newData;
        } // end setData

        private Node getNextNode()
        {
            return next;
        } // end getNextNode
        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        } // end setNextNode
    } // end class Node
} // end LinkedQueue