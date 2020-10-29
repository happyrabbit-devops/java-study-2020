package ru.sbrf.cu.list;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T>, MyQueue<T> {

    private ListItem<T> head = null;
    private ListItem<T> tail = null;

    @Override
    public void add( T item ) {
        // Создаем новый элемент
        var internalItem = new ListItem(item);
        //internalItem.value = item;
        //Если голова ещё не задана - то её присваиваем новому элементу
        if ( head == null ) {
            head = internalItem;
        }
        // Хвост заменяется на новый
        var tempTail = tail;
        tail = internalItem;
        // Связываем старый хвост и новый хвост между собой
        if ( tempTail != null ) {
            tempTail.next = tail;
        }
        tail.prev = tempTail;
    }

    @Override
    public void sortedAdd(T item) {
        // Создаем новый элемент в заведомо отсортированном порядке
        var internalItem = new ListItem(item);
        if(head == null) {
            head = tail = internalItem;
            return;
        } else {
            var temp = new ListItem(item);
            var current = head;
            while((current != null) && current.value.compareTo(item) < 0) {
                temp = current;
                current = current.getNext();
            }
            if(current == head) {
                addAtHead(item);
                return;
            }
            if (current == null) {
                addAtTail(item);
                return;
            }
            temp.setNext(internalItem);
            internalItem.setNext(current);
            current.setPrev(internalItem);
            internalItem.setPrev(temp);
        }
    }


    public void addAtHead(T item) {
        var internalItem = new ListItem(item);
        internalItem.setNext(head);
        if (head != null)
            head.setPrev(internalItem);
        head = internalItem;
        if (tail == null)
            tail = internalItem;
    }

    public void addAtTail(T item) {
        var internalItem = new ListItem(item);
        if(tail == null) {
            tail = internalItem;
            head = internalItem;
        } else {
            internalItem.setPrev(tail);
            tail.setNext(internalItem);
            tail = internalItem;
        }
    }

    @Override
    public int size() {
        if (head == null)
            return 0;
        var current = head;
        int size = 0;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

    @Override
    public T get( int index ) {
        // Ошибка если индекс больше размера
        int size = size();
        if (index > size) {
            throw new MyLinkedListIndexException();
        }
        // Если индекс > size/2 - то перебираем от tail
        T result = null;
        if (index <= size / 2) {
            int currPosition = 0;
            var item = head;
            // Пошли перебирать элементы пока не дойдем до индекса или же не выйдем за размеры списка
            while (result == null && currPosition < size) {
                if (index == currPosition) {
                    result = item.value;
                }
                item = item.next;
                currPosition++;
            }
        } else {
            int currPosition = size-1;
            var item = tail;
            // Пошли перебирать элементы пока не дойдем до индекса или же не попадем в начало списка
            while (result == null && currPosition >= 0) {
                if (index == currPosition) {
                    result = item.value;
                }
                item = item.prev;
                currPosition--;
            }
        }
        return result;
    }

    @Override
    public boolean remove( T item ) {
        var currItem = head;
        while (currItem != null) {
            if (currItem.value.compareTo(item) == 0) {
                if (currItem.prev != null && currItem.next != null) {
                    currItem.prev.next = currItem.next;
                    currItem.next.prev = currItem.prev;
                } else if (currItem.prev != null) {
                    currItem.prev.next = null;
                    tail = currItem.prev;
                } else if (currItem.next != null) {
                    currItem.next.prev = null;
                    head = currItem.next;
                }
                return true;
            }
            if (currItem.next != null) {
                currItem = currItem.next;
            } else {
                currItem = null;
            }
        }
        return false;
    }

    @Override
    public T poll() {
        T val = null;
        int size = size();
        if (size > 0){
            var first = head;
            var next = head.next;
            val = first.value;
            if (size == 1) {
                head = null;
            }
            else {
                head = next;
            }
            size--;
        }
        return val;
    }

    @Override
    public void print() {
        for (int i=0; i < size(); i++) {
            System.out.print(get(i)+" ");
        }
        System.out.println();
    }

    /*@Override
    public void sort() {

        print();

        boolean wasChange = true;
        while ( wasChange ){
            wasChange = false;
            ListItem<T> first = head;
            ListItem<T> second = head.next;
            while ( second != null ){
                wasChange = wasChange || compareAndReplaceItem(first, second);
                first = second;
                second = second.next;
            }
        }

        print();
    }

    private boolean compareAndReplaceItem( ListItem<T> first, ListItem<T> second ) {
        if (second.value.compareTo( first.value ) < 0){
            second.prev = first.prev;
            if (second.prev == null){
                head = second;
            }
            first.next = second.next;
            if (first.next == null){
                tail = first;
            }
            second.next = first;
            first.prev = second;
            return true;
        }
        return false;
    }*/

    private class ListItem<T> {
        T value;
        ListItem<T> prev;
        ListItem<T> next;

        public ListItem(T v) {
            value = v;
            next = null;
            prev = null;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public ListItem<T> getPrev() {
            return prev;
        }

        public void setPrev(ListItem<T> prev) {
            this.prev = prev;
        }

        public ListItem<T> getNext() {
            return next;
        }

        public void setNext(ListItem<T> next) {
            this.next = next;
        }
    }
}
