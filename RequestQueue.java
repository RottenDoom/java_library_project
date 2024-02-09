import Includes.*;

public class RequestQueue {
    private Node<RequestData> front;
    private Node<RequestData> back;
    private int length = 0;

    public RequestData getFront() {
        return this.front.data;
    }

    public int getLength() {
        return this.length;
    }

    public void push(int ISBN, int UserID) {
        // Make new Node
        Node<RequestData> newNode = new Node<RequestData>();
        newNode.data = new RequestData();
        newNode.data.ISBN = ISBN;
        newNode.data.UserID = UserID;
        MyDate date = new MyDate(); // date can be altered
        date.month = 0;
        date.year = 0;
        newNode.data.RequestDate = date;

        // Add the new Node (if no data front back both equals to newnode pointer)
        if (this.length == 0) {
            front = newNode;
            back = newNode;
            this.length++;
        } else {
            back.next = newNode;
            newNode.previous = back;
            back = newNode;
            this.length++;
        }
    }

    public void pop() {
        // [TBD] = Processing before popping. Maybe not needed.
        if (this.length > 1) {
            front.next.previous = null;
            Node<RequestData> temp = front.next;
            front.next = null;
            front = temp;
            this.length--;
        } else if (this.length == 1) {
            this.front = null;
            this.back = null;
            length--;
        } else {
            throw new NullPointerException("What?");
        }
    }

    public String toString(){
        Node<RequestData> temp = front;
        System.out.println("This is Queue");
        String s = "Length: " + length + "\n";
        while(temp != null){
            s+=temp.data.toString();
            temp = temp.next;
        }
        return s;
    }
}
