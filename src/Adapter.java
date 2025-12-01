import java.util.*;

interface PaymentGatewayAdapter {
    void pay(String orderId, double amount);
}

class PayUGatewayAdapter implements PaymentGatewayAdapter {
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Paid Rs. " + amount + " using PayU for order: " + orderId);
    }
}

class RazorPay {
    public void makePayment(String invoiceId, double amountInRupees){
        System.out.println("Paid Rs. " + amountInRupees + " using Razorpay for invoice: " + invoiceId);
    }
}

class RazorPayAdapter implements PaymentGatewayAdapter {
    private RazorPay rpay;
    public RazorPayAdapter () {
        this.rpay = new RazorPay();
    }
    @Override
    public void pay (String orderId, double amount) {
        rpay.makePayment(orderId, amount);
    }
}

class CheckOutService {
    private PaymentGatewayAdapter paymentGateway;
    public CheckOutService (PaymentGatewayAdapter paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void checkOut(String orderId, double amount){
        this.paymentGateway.pay(orderId,amount);
    }
}



public class Adapter {
    public static void main(String[] args) {
        CheckOutService checkoutService = new CheckOutService(new PayUGatewayAdapter());
        checkoutService.checkOut("fjlkcf",3920);

        CheckOutService checkoutService2 = new CheckOutService(new RazorPayAdapter());
        checkoutService.checkOut("razorpay selected",8432);

    }
}
