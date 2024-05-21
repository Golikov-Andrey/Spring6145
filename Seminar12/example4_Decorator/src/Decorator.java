public class Decorator implements iCalculable {

    private Calculator oldCalculator;
    private Logger logger;


    public Decorator(Calculator oldCalculator, Logger logger) {
        this.oldCalculator = oldCalculator;
        this.logger = logger;
    }
    
    @Override
    public iCalculable sum(int arg) {
        int firstArg = oldCalculator.getResult();

        logger.log(String.format("Первое значение калькулятора %d. Начало вызова метода sum с параметром %d", firstArg, arg));
        iCalculable result = oldCalculator.sum(arg);
        logger.log(String.format("Вызова метода sum произошел"));

        return result;
    }
    @Override
    public iCalculable multi(int arg) {
        int firstArg = oldCalculator.getResult();
        logger.log(String.format("Первое значение калькулятора %d. Начало вызова метода multi с параметром %d", firstArg, arg));
        iCalculable result = oldCalculator.multi(arg);
        logger.log(String.format("Вызова метода multi произошел"));
        return result;
    }
    @Override
    public int getResult() {
        int result = oldCalculator.getResult();
        logger.log(String.format("Получение результата %d", result));
        return result;
    }

    
    
}
