import java.util .*;

// Eager(Early) Initialization (Thread Safe);
class EagerSingleton {
    private EagerSingleton () {
    }
    private static final EagerSingleton defaultInst = new EagerSingleton();
    public static EagerSingleton getInstance () {
        return defaultInst;
    }
}

// Lazy Initialization (On-Demand Initialization) (Thread Unsafe)
class LazySingleton {
    private LazySingleton (){
    }
//    private static volatile LazySingleton lins1;

//    public static synchronized LazySingleton getInstance (){
//        if(lins1 == null) {
//            synchronized (LazySingleton.class){
//                if(lins1 == null) {
//                    lins1 = new LazySingleton();
//                }
//            }
//        }
//        return lins1;
//    }

    // Bill Pugh Singleton
    private static class Holder {
        private static final LazySingleton defaultInst = new LazySingleton();
    }
    public static LazySingleton getInstance() {
        return Holder.defaultInst;
    }
}

public class Singleton {
    public static void main(String[] args) {
        // Eager
        EagerSingleton eisn1 = EagerSingleton.getInstance();
        EagerSingleton eisn2 = EagerSingleton.getInstance();
        System.out.println(eisn1);
        System.out.println(eisn2);

        //lazy
        LazySingleton ls1 = LazySingleton.getInstance();
        LazySingleton ls2 = LazySingleton.getInstance();
        System.out.println(ls1);
        System.out.println(ls2);

        //Bill Pugh
        LazySingleton ls3 = LazySingleton.getInstance();
        System.out.println(ls3);
    }
}
