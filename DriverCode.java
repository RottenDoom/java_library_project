import Includes.*;

public class DriverCode {
    public static void main(String[] args) {
        LibraryStorage ls = new LibraryStorage();

        // Pushing to RequestQueue and testing that length is updated.
        ls.push(100001, 1);
        ls.push(100020, 2);
        ls.push(100010, 3);
        ls.push(100010, 4);
        ls.push(100006, 5);

        // Processing
        boolean resp;
        for (int i=0; i<5; i++) {
            resp = ls.processQueue();
            if (!resp) {
                System.out.println(ls.rqQueueToString());
                System.out.println(ls.prLinkedListToString());
            }
        }

        ls.processReturn(ls.storage.get(100010));

        System.out.println(ls.storage.get(100010).DateOfReturn.month);
    }
}









































































// Left to Done [TBD]

// 1. The 5 second processing requires more thought into it. Either we have to use pre built time class or just use something which is just a loop
// 2. We have to make IO better that is since literally only one class has a constructor we have to make somewhat of a predefined constructors outside to actually test out stuff.
// 3. Step 2 needs to be done in another branch of the assignment code. For now I will see the solutions on Friday.
// 4. Instead of being a piece of shit and not even providing a constructor we are going to make a well defined library and probably apply our code to backend of a website.
// 5. Write it down in my CV.
