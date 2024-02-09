import java.util.*;

import Includes.*;

public class LibraryStorage {
    // HashMap
    // process return
    public HashMap<Integer, BookData> storage; // This map holds the data mapped from ISBN to BookData
    private RequestQueue rqQueue;
    private PendingRequests prLinkedList;

    public LibraryStorage() {
        storage = new HashMap<Integer, BookData>();
        for (int i=100001; i<100021; i++) {
            BookData book = new BookData();
            MyDate dateor = new MyDate();
            dateor.month = 0;
            dateor.year = 0;
            book.BorrowedStatus = false;
            book.BorrowedByUserID = 0;
            book.ISBN = i;
            book.Publisher = "publisher";
            book.Author = "author";
            book.DateOfReturn = dateor;
            storage.put(i, book);
        }

        rqQueue = new RequestQueue();
        prLinkedList = new PendingRequests();
    }

    public void push(int ISBN, int UserID) {
        rqQueue.push(ISBN, UserID);
    }

    public boolean processQueue() {

        RequestData bookInfo = rqQueue.getFront();

        if (!(storage.get(bookInfo.ISBN).BorrowedStatus)) 
        {
            BookData book = storage.get(bookInfo.ISBN);
            book.BorrowedStatus = true;
            book.BorrowedByUserID = bookInfo.UserID;
            book.ISBN = bookInfo.ISBN;
            book.DateOfReturn = new MyDate();
            book.DateOfReturn.month = bookInfo.RequestDate.month + 1;
            storage.replace(bookInfo.ISBN, storage.get(bookInfo.ISBN), book);
            rqQueue.pop();
        } 
        else 
        {
            Node<RequestData> bookNode = new Node<>();
            bookNode.data = bookInfo;
            prLinkedList.insert(bookNode);
            rqQueue.pop();
        }

        return false;
    }

    public boolean processReturn(BookData book) {
        book.BorrowedStatus = false;
        Node<RequestData> requestNode = prLinkedList.find(book.ISBN);
        if (requestNode != null) {
            prLinkedList.delete(requestNode);
            book.BorrowedByUserID = requestNode.data.UserID;
            MyDate returnDate = book.DateOfReturn;
            book.DateOfReturn.month = returnDate.month + 1;
            storage.replace(book.ISBN, storage.get(book.ISBN), book);
        } else {
            storage.replace(book.ISBN, storage.get(book.ISBN), book);
        }
        return false;
    }

    public String rqQueueToString(){
        return rqQueue.toString();
    }

    public String prLinkedListToString(){
        return prLinkedList.toString();
    }
    
}
