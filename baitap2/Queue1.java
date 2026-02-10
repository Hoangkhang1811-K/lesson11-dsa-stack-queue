package baitap2;

public class Queue1 {
    static class Node {
        int data;
        Node link;

        Node(int data) {
            this.data = data;
        }
    }

    static class Queue {
        Node front;
        Node rear;

        void enQueue(int value) {
            Node node = new Node(value);
            if (front == null) {
                front = rear = node;
                rear.link = front;
                return;
            }
            rear.link = node;
            rear = node;
            rear.link = front;
        }

        Integer deQueue() {
            if (front == null) return null;

            int value;
            if (front == rear) {
                value = front.data;
                front = rear = null;
                return value;
            }

            value = front.data;
            front = front.link;
            rear.link = front;
            return value;
        }

        void displayQueue() {
            if (front == null) {
                System.out.println("EMPTY");
                return;
            }
            Node temp = front;
            do {
                System.out.print(temp.data);
                temp = temp.link;
                if (temp != front) System.out.print(" ");
            } while (temp != front);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue();

        q.enQueue(14);
        q.enQueue(22);
        q.enQueue(6);
        q.displayQueue();

        System.out.println(q.deQueue());
        q.displayQueue();

        q.enQueue(9);
        q.enQueue(20);
        q.displayQueue();
    }
}
