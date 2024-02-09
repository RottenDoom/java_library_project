import Includes.*;

public class PendingRequests {
    private int length = 0;
    private Node<RequestData> front;
    private Node<RequestData> back;

    public boolean insert(Node<RequestData> insnode) {
        if (this.length == 0) {
            this.front = insnode;
            this.back = insnode;
            this.length++;
            return true;
        }
        this.back.next = insnode;
        insnode.previous = this.back;
        this.back = insnode;
        this.length++;
        return true;
    }

    public boolean delete(Node<RequestData> delnode) {
        Node<RequestData> temp = this.front;
        int pos = 0;
        while(temp != null) {
            if (this.length > 1) {
                if (((temp.data.ISBN == delnode.data.ISBN) && (temp.data.UserID == delnode.data.UserID)) && ((0 < pos) && (pos < this.length - 1))) {
                    temp.previous.next = temp.next;
                    temp.next.previous = temp.previous;
                    temp.previous = null;
                    temp.next = null;
                    length--;
                    return true;
                } else if (((temp.data.ISBN == delnode.data.ISBN) && (temp.data.UserID == delnode.data.UserID)) && (pos == 0)) {
                    this.front.next.previous = null;
                    this.front = front.next;
                    this.length--;
                    return true;
                } else if (((temp.data.ISBN == delnode.data.ISBN) && (temp.data.UserID == delnode.data.UserID)) && (pos == this.length - 1)) {
                    this.back.previous.next = null;
                    this.back = this.back.previous;
                    length--;
                    return true;
                }
                temp = temp.next;
                pos++;
            } else {
                if ((temp.data.ISBN == delnode.data.ISBN && temp.data.UserID == delnode.data.UserID)) {
                    this.front = null;
                    this.back = null;
                    temp = null;
                    this.length--;
                }
            }
            
        }
        return false;
    }

    public Node<RequestData> find(int ISBN) {
        Node<RequestData> temp = front;

        while (temp != null) {
            if (temp.data.ISBN == ISBN) {
                return temp;
            }

            temp = temp.next;
        }

        return null;
    }

    public String toString(){
        Node<RequestData> temp = front;
        System.out.println("This is Linked List");
        String s = "Length: " + length + "\n";
        while(temp != null){
            s += temp.data.toString();
            temp = temp.next;
        }
        return s;
    }
}
