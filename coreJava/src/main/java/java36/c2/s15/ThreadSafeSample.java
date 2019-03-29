package java36.c2.s15;

public class ThreadSafeSample {
    public int sharedState;
    public void nonSafeAction(){
        while(sharedState < 10000){
            int former = sharedState++;
            int latter = sharedState;
            if(former != latter -1){
                System.out.println("Observed data race,former is "+former+", latter is"+latter);
            }
        }
    }

    public void safeAction(){
        while(sharedState < 10000){
            synchronized (this){
                int former = sharedState++;
                int latter = sharedState;
                if(former != latter -1){
                    System.out.println("Observed data race,former is "+former+", latter is"+latter);
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException{
        ThreadSafeSample sample = new ThreadSafeSample();
        Thread threadA = new Thread() {
            public void run() {
                sample.nonSafeAction();
            }
        };
        Thread threadB = new Thread(){
            public void run(){
                sample.nonSafeAction();
            }
        };
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
//        ThreadSafeSample sample1 = new ThreadSafeSample();
//
//        Runnable runnableA = new Runnable() {
//            @Override
//            public void run() {
//                sample1.safeAction();
//            }
//        };
//
//        Runnable runnableB = new Runnable() {
//            @Override
//            public void run() {
//                sample1.safeAction();
//            }
//        };
//        Thread threadC = new Thread(runnableA);
//        Thread threadD = new Thread(runnableB);
//        threadC.start();
//        threadD.start();

    }
}
