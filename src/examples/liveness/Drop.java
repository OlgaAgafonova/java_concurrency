package examples.liveness;

public class Drop {
    //Message sent from producer to consumer
    private String message;
    //True if consumer should wait for producer to send message,
    //False if produser should wait for consumer to retrieve message
    private boolean empty = true;

    public synchronized String take() {
        //Wait until message is available
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        //Toggle status
        empty = true;
        //Notify producer that status has changed
        notifyAll();
        return message;
    }

    public synchronized void put(String message) {
        //Wait until message has been retrieved
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        //Toggle status
        empty = false;
        //Store message
        this.message = message;
        //Notify consumer that status has changed
        notifyAll();
    }
}
