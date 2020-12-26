package ru.vsu.cs.course2.util;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.fields.StreetField;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class CircleList<T> implements Iterable<T> {
    private Element head;
    private Element tail;

    public void add(T value) {
        if (head == null) {
            head = new Element(null, tail, value);
            tail = head;
        } else {
            Element element = new Element(null, tail, value);
            tail.next = element;
            tail = element;
        }
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Element el = head;

            @Override
            public boolean hasNext() {
                return el != null;
            }


            @Override
            public T next() {
                T data = (T) el.value;
                el = el.next;
                return data;
            }
        };

    }


    class Element<T> {
        Element next;
        Element prev;
        T value;

        public Element(Element next, Element prev, T value) {
            this.next = next;
            this.value = value;
            this.prev = prev;
        }

    }

}
