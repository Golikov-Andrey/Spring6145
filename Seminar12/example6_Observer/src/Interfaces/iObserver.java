package Interfaces;

// Наблюдатель, он будет получать уведомления
// В данном случае это человек, ищущий работу, получает уведомления от рекрутинговского агенства
public interface iObserver {
    void receiveOffer(String nameCompany, int salary);
}
