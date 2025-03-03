import java.util.Scanner;

class TicketBookingSystem implements Runnable {
    private static int seats;
    private int seatCount;

    public TicketBookingSystem(int seatCount) {
        this.seatCount = seatCount;
    }

    public synchronized void bookTicket(String name) {
        if (seatCount <= seats) {
            System.out.println(name + " booked " + seatCount + " seat(s).");
            seats -= seatCount;
        } else {
            System.out.println(name + " booking failed. Not enough seats.");
        }
    }

    @Override
    public void run() {
        bookTicket(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total seats available: ");
        seats = sc.nextInt();
        System.out.print("Enter number of VIP bookings: ");
        int vip = sc.nextInt();
        System.out.print("Enter number of Normal bookings: ");
        int normal = sc.nextInt();

        Thread[] threads = new Thread[vip + normal];

        for (int i = 0; i < vip; i++) {
            System.out.print("Enter seats for VIP " + (i + 1) + ": ");
            int seat = sc.nextInt();
            threads[i] = new Thread(new TicketBookingSystem(seat), "VIP " + (i + 1));
            threads[i].setPriority(Thread.MAX_PRIORITY);
        }

        for (int i = 0; i < normal; i++) {
            System.out.print("Enter seats for User " + (i + 1) + ": ");
            int seat = sc.nextInt();
            threads[vip + i] = new Thread(new TicketBookingSystem(seat), "User " + (i + 1));
            threads[vip + i].setPriority(Thread.NORM_PRIORITY);
        }

        for (Thread t : threads) {
            t.start();
        }
    }
}
