

public final class Calculator implements iCalculable {

    private int primaryArg;

    public Calculator(int primaryArg) {
        this.primaryArg = primaryArg;
    }

    @Override
    public iCalculable sum(int arg) {
        primaryArg += arg;
        return this;
    }

    @Override
    public iCalculable multi(int arg) {
        primaryArg *= arg;
        return this;
    }

    @Override
    public int getResult() {
        return primaryArg;
    }
}
