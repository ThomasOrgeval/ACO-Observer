public interface Capteur extends Observer {

    /*
    @Override
    void attach(Observer o);

    @Override
    void detach(Observer o);
     */

    Integer getValue();

    void tick();
}