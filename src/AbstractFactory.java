//Bad Practice without using Any factory pattern and not following SRP and not extendable

//interface PaymentGateway {
//    void processPayment(double amount);
//}
//
//// Concrete implementation: Razorpay
//class RazorpayGateway implements PaymentGateway {
//    public void processPayment(double amount) {
//        System.out.println("Processing INR payment via Razorpay: " + amount);
//    }
//}
//
//// Concrete implementation: PayU
//class PayUGateway implements PaymentGateway {
//    public void processPayment(double amount) {
//        System.out.println("Processing INR payment via PayU: " + amount);
//    }
//}
//
//// Interface representing invoice generation
//interface Invoice {
//    void generateInvoice();
//}
//
//// Concrete invoice implementation for India
//class GSTInvoice implements Invoice {
//    public void generateInvoice() {
//        System.out.println("Generating GST Invoice for India.");
//    }
//}
//
//// CheckoutService that directly handles object creation (bad practice)
//class CheckoutService {
//    private String gatewayType;
//    CheckoutService(String gatewayType) {
//        this.gatewayType = gatewayType;
//    }
//
//    public void checkOut (double amount) {
//        PaymentGateway paymentGateway;
//        if (gatewayType.equals("razorpay")) {
//             paymentGateway = new RazorpayGateway();
//          }
//        else {
//             paymentGateway = new PayUGateway();
//        }
//        paymentGateway.processPayment(amount);
//
//        Invoice invoice = new GSTInvoice();
//        invoice.generateInvoice();
//    }
//}


// Abstract Factory Pattern
// ========== Interfaces ==========
interface PaymentGateway {
    void processPayment(double amount);
}

interface Invoice {
    void generateInvoice();
}

// ========== India Implementations ==========
class RazorpayGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via Razorpay: " + amount);
    }
}

class PayUGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via PayU: " + amount);
    }
}

class GSTInvoice implements Invoice {
    public void generateInvoice() {
        System.out.println("Generating GST Invoice for India.");
    }
}

// ========== US Implementations ==========
class PayPalGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing USD payment via PayPal: " + amount);
    }
}

class StripeGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing USD payment via Stripe: " + amount);
    }
}

class USInvoice implements Invoice {
    public void generateInvoice() {
        System.out.println("Generating Invoice as per US norms.");
    }
}

// ========== Abstract Factory ==========
interface RegionFactory {
    PaymentGateway createPaymentGateway(String gatewayType);
    Invoice createInvoice();
}

// ========== Concrete Factories ==========
class IndiaFactory implements RegionFactory {
    @Override
    public PaymentGateway createPaymentGateway (String gatewayType) {
        if("razorpay".equalsIgnoreCase(gatewayType)) {
            return new RazorpayGateway();
        }
        else if ("payU".equalsIgnoreCase(gatewayType)) {
            return new PayUGateway();
        }
        throw new IllegalArgumentException("Unsupported gateway for India: " + gatewayType);
    }
    @Override
    public Invoice createInvoice() {
        return new GSTInvoice();
    }
}

class USFactory implements RegionFactory {
    public PaymentGateway createPaymentGateway(String gatewayType) {
        if (gatewayType.equalsIgnoreCase("paypal")) {
            return new PayPalGateway();
        } else if (gatewayType.equalsIgnoreCase("stripe")) {
            return new StripeGateway();
        }
        throw new IllegalArgumentException("Unsupported gateway for US: " + gatewayType);
    }

    public Invoice createInvoice() {
        return new USInvoice();
    }
}

class JapanFacroty implements RegionFactory {
    public PaymentGateway createPaymentGateway(String gatewayType) {
        if (gatewayType.equalsIgnoreCase("paypal")) {
            return new PayPalGateway();
        } else if (gatewayType.equalsIgnoreCase("stripe")) {
            return new StripeGateway();
        }
        throw new IllegalArgumentException("Unsupported gateway for US: " + gatewayType);
    }

    public Invoice createInvoice() {
        return new USInvoice();
    }
}

// ========== Checkout Service ==========
class CheckoutService {
    private PaymentGateway paymentGateway;
    private Invoice invoice;
    private String gatewayType;

    public CheckoutService(RegionFactory factory, String gatewayType) {
        this.gatewayType = gatewayType;
        this.paymentGateway = factory.createPaymentGateway(gatewayType);
        this.invoice = factory.createInvoice();
    }

    public void completeOrder(double amount) {
        paymentGateway.processPayment(amount);
        invoice.generateInvoice();
    }
}

public class AbstractFactory {
    public static void main(String args []) {
//        CheckoutService checkoutService = new CheckoutService("razorpay");
//        checkoutService.checkOut (5000);

        // Using Razorpay in India
        CheckoutService indiaCheckout = new CheckoutService(new IndiaFactory(), "razorpay");
        indiaCheckout.completeOrder(1999.0);

        System.out.println("---");

        // Using PayPal in US
        CheckoutService usCheckout = new CheckoutService(new USFactory(), "paypal");
        usCheckout.completeOrder(49.99);// Using PayPal in US

        System.out.println("---");

        CheckoutService japanCheckout = new CheckoutService(new JapanFacroty(), "paypal");
        japanCheckout.completeOrder(49.99);
    }
}
