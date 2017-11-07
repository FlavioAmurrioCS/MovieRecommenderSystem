public class Timer {
    private long startTime;
    private String method;

    public Timer() {
        this.startTime = System.currentTimeMillis();
        this.method = "";
    }

    public Timer(String method) {
        this.startTime = System.currentTimeMillis();
        this.method = method;
        System.out.print(this.method + " ...");
    }

    public void time() {
        long markTime = System.currentTimeMillis();
        long ellapseTime = markTime - this.startTime;
        ellapseTime /= 1000;
        String timeStr = (ellapseTime < 60) ? ellapseTime + "s" : (ellapseTime / 60) + "m " + ellapseTime % 60 + "s";
        if (this.method.equals("")) {
            System.out.println("Time: " + timeStr);
        } else {
            System.out.println("\r" + this.method + " took: " + timeStr);
        }
    }

}