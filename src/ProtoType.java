import java.util.*;

//interface EmailTemplate {
//    void setContent(String content);
//    void send(String to);
//}
//
//class WelcomeEmail implements EmailTemplate {
//    private String subject;
//    private String content;
//
//    WelcomeEmail() {
//        this.subject = "Welcome To TUF +";
//        this.content = "Hi There Thanks for Joining";
//    }
//
//    @Override
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    @Override
//    public void send(String to) {
//        System.out.println("Sending to " + to + ": [" + subject + "] " + content);
//    }
//}

// Prototype Pattern
interface EmailTemplate extends Cloneable {
    EmailTemplate clone();
    void setContent(String content);
    void send(String to);
}

class WelcomeEmail implements EmailTemplate {
    private String subject;
    private String content;
    public WelcomeEmail() {
        this.subject = "Welcome to TUF+";
        this.content = "Hi there! Thanks for joining us.";
    }

    @Override
    public WelcomeEmail clone() {
        try {
            return (WelcomeEmail) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone failed", e);
        }
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void send(String to) {
        System.out.println("Sending to " + to + ": [" + subject + "] " + content);
    }

}

// Template Registry to store and provide clones
class EmailTemplateRegistry {
    private static final Map<String, EmailTemplate> templates = new HashMap<>();

    static {
        templates.put("welcome", new WelcomeEmail());
    }

    public static EmailTemplate getTemplate(String type) {
        return templates.get(type).clone(); // clone to avoid modifying original
    }
}


public class ProtoType {
    public static void main (String [] args){
//        WelcomeEmail email1 = new WelcomeEmail();
//        email1.send("user1@example.com");
//
//        // Suppose we want a similar email with slightly different content
//        WelcomeEmail email2 = new WelcomeEmail();
//        email2.setContent("Hi there! Welcome to TUF Premium.");
//        email2.send("user2@example.com");
//
//        // Yet another variation
//        WelcomeEmail email3 = new WelcomeEmail();
//        email3.setContent("Thanks for signing up. Let's get started!");
//        email3.send("user3@example.com");


        EmailTemplate welcomeEmail1 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail1.setContent("Hi Alice, welcome to TUF Premium!");
        welcomeEmail1.send("alice@example.com");

        EmailTemplate welcomeEmail2 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail2.setContent("Hi Bob, thanks for joining!");
        welcomeEmail2.send("bob@example.com");

        System.out.println(welcomeEmail1);
        System.out.println(welcomeEmail2);

    }
}
