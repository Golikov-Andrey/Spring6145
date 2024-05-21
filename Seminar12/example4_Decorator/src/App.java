public class App {
    public static void main(String[] args) throws Exception {

        //iCalculable calcOld = new Calculator(0);
        iCalculable decorator = new Decorator(new Calculator(0), new Logger());
        ViewCalculator view = new ViewCalculator(decorator);
        view.run();

    }
}
