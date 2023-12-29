package CS0045Fall2023;
import java.util.Random;

public class MyList<T> implements ListInterface<T>
{
    private Node firstNode;
    private int  numberOfEntries;

    public MyList()
    {
        initializeDataFields();
    }

    public void clear()
    {
        initializeDataFields();
    }

    public void add(T newEntry)
    {
        Node newNode = new Node(newEntry);

        if (isEmpty())
            firstNode = newNode;
        else
        {
            Node lastNode = getNodeAt(numberOfEntries);
            lastNode.setNextNode(newNode);
        }

        numberOfEntries++;
    }

    public void add(int givenPosition, T newEntry)
    {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1))
        {
            Node newNode = new Node(newEntry);
            if (givenPosition == 1)
            {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            }
            else
            {
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            }
            numberOfEntries++;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to add operation.");
    }

    public T remove(int givenPosition)
    {
        T result = null;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            if (givenPosition == 1)
            {
                result = firstNode.getData();
                firstNode = firstNode.getNextNode();
            }
            else
            {
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData();
                Node nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);
            }
            numberOfEntries--;
            return result;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
    }

    public T replace(int givenPosition, T newEntry)
    {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            Node desiredNode = getNodeAt(givenPosition);
            T originalEntry = desiredNode.getData();
            desiredNode.setData(newEntry);
            return originalEntry;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
    }

    public T getEntry(int givenPosition)
    {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            return getNodeAt(givenPosition).getData();
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
    }

    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];

        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null))
        {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        }

        return result;
    }

    public boolean contains(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        }

        return found;
    }

    public int getLength()
    {
        return numberOfEntries;
    }

    public boolean isEmpty()
    {
        boolean result;

        if (numberOfEntries == 0)
        {
            result = true;
        }
        else
        {
            result = false;
        }

        return result;
    }
    public void swap(int position1, int position2){
        T item1 = this.getEntry(position1);
        T item2 = this.getEntry(position2);
        replace(position1, item2);
        replace(position2, item1);
    }

    public void reverse(){
        for (int i =1; i<= numberOfEntries/2; i++ ){
            swap(i,this.numberOfEntries-i + 1);
        }
    }

    public int indexOf(T item){
        for (int i =1; i<= numberOfEntries; i++ ) {
            if (this.getEntry(i).equals(item)){
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(T item){
        for (int i =numberOfEntries; i>= 1; i-- ) {
            if (this.getEntry(i).equals(item)){
                return i;
            }
        }
        return -1;
    }

    public int countFrequency(T item){
        int count =0;
        for (int i =1; i<= numberOfEntries; i++ ) {
            if (this.getEntry(i).equals(item)){
                count++;
            }
        }
        return count;
    }

    public void removeAllFrequency(T item){
        while (this.contains(item)){
            remove(indexOf(item));
        }
    }

    public void shuffle(){
        Random rand = new Random();// 0--
        for (int i =numberOfEntries; i> 1; i-- ) {
            int j = rand.nextInt(i) +1;
            swap(i,j);
        }
    }

    public String toString(){
        String result ="";
        StringBuilder res = new StringBuilder("[");
        for (int i =1; i<= numberOfEntries; i++ ) {
            res.append(getEntry(i));
            if (i < numberOfEntries) {
                res.append(" ");
            }
        }
        res.append("]");
        return res.toString();
    }

    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries = 0;
    }

    private Node getNodeAt(int givenPosition)
    {
        Node currentNode = firstNode;

        for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();

        return currentNode;
    }

    // Inner class
    private class Node
    {
        private T	 data;
        private Node next;

        private Node(T dataPortion)
        {
            data = dataPortion;
            next = null;
        }

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        }

        private T getData()
        {
            return data;
        }

        private void setData(T newData)
        {
            data = newData;
        }

        private Node getNextNode()
        {
            return next;
        }

        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        }
    }


    public static void main(String[] args) {

        MyList<String> myList = new MyList<>();

        myList.add("November");
        myList.add("May");
        myList.add("August");
        myList.add("January");
        myList.add("November");
        myList.add("January");
        myList.add("January");
        myList.add("March");

        System.out.println("Original List : " + myList);
        myList.swap(1,4);
        System.out.println("List after Swapping : " + myList);

        myList.reverse();
        System.out.println("List after Reverse : " + myList);

        System.out.println("Index of January -First Index : " + myList.indexOf("January"));

        System.out.println("Index of January -Last index : " + myList.lastIndexOf("January"));

        System.out.println("Get Frequency of January : " + myList.countFrequency("January"));

        myList.removeAllFrequency("January");
        System.out.println("After Removing all Occurances of January : " + myList);
        myList.shuffle();
        System.out.println("After Shuffling the list is: " + myList);
    }

}











