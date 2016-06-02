# Примеры по concurrency
-------------------------------
### Папка threadObjects
  - *HelloRunnable.java* и *HelloThread.java* - два способа создать потоки.
  - *SleepMessages.java* - использование **sleep** для приостановки работы потока.
  - *SimpleThreads.java* - главный поток содает дополнительный (MessageLoop) и дожидается его завершения. MessageLoop  выводит сообщения.

### Папка synchronization
  - *Counter.java* - класс счётчик. Могут быть ошибки если с объектом класса работают несколько потоков одновременно.
  - *SynchronizedCounter.java* - класс счётчика с синхронизированными методами. Доступ к счётчику может быть только у одного потока.
  - *MsLunch.java* - обновление полей класса **с1** и **с2** должны быть синхронизированы. Чтобы можно было обновлять их одновременно, созданы два объекта, которые используются как мониторы.

### Папка liveness

  - *Deadlock.java* - пример взаимной блокировки. Альфонсо кланяется Гастону пока тот не ответит на поклон. Взаимная блокировка происходит, если они поклонятся одновременно.
  - Использование **guarded blocks** (действие потока приостанавливается пока он не получит блок и не выполнится определенное условие) для создания a Producer-Consumer application. Это пример приложения, в котором происходит обмен данными между двумя потоками: производитель, создающий данные, и потребитель, получающий данные. Два потока взаимодействуют, используя общий объект. Поток-потребитель не должен пытаться извлечь данные до того, как поток-производитель предоставит данные, а поток-производитель не должен пытаться доставить новые данные, если потребитель не извлек старые данные.
    * *Drop.java* - контейнер куда производитель помещает данные, а потребитель берет их.
    * *Producer.java* - помещает сообщение в объект класса *Drop* через произвольный интервал времени (до 5 секунд).
    *  *Consumer.java* - забирает сообщение из объекта класса *Drop* через произвольный интервал времени (до 5 секунд).
    *  *ProducerConsumerExample.java* - запускает два потока

### Папка immutableObjects

- *SynchronizedRGB.java* - определяет объект, который представляет цвет и его назвавние.
Если поток исполняет следующий код:
```sh
SynchronizedRGB color = new SynchronizedRGB(0, 0, 0, "Pitch Black");
   ...
   int myColorInt = color.getRGB();      //Statement 1
String myColorName = color.getName(); //Statement 2
```
А другой поток вызовет **color.set** после *Statement 1*, но до *Statement 2*, переменная myColorInt не будет соответствовать myColorName. Чтобы этого избежать лучше использовать следующий код:

```sh
synchronized (color) {
   int myColorInt = color.getRGB();
   String myColorName = color.getName();
}
```
- *ImmutableRGB.java* - неизменяемая (**immutable**) версия класса SynchronizedRGB

### Папка highLevelConcurrencyObjects

- *SafeLock.java* - пример решения проблемы из  *Deadlock.java*.
- *ForkBlur.java* - пример использования **Fork/Join** для размытия изображения.
- *AtomicCounter.java* - пример потокобезопасного счётчика без использования synchronised

-------------------
### Интересная информация

- ThreadLocalRandom используется для получения случайного значения. ThreadLocalRandom снижает конкуренцию в пногопоточных приложениях или при использовании Fork/Join.
```sh
int r = ThreadLocalRandom.current() .nextInt(4, 77);
``` 

