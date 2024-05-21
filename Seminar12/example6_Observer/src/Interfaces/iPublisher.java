package Interfaces;

// Это интерфейс рассылки наблюдателям
// Он может регистрировать наблюдателей, избавляться от них, и рассылать им всем сообщения
public interface iPublisher {
    void registerObserver(iObserver observer);
    void removeObserver(iObserver observer);
    void sendOffer(String nameCompany, int salary);
}
