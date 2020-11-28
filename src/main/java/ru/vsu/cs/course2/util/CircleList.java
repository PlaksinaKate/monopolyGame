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
            head = new Element(null, value);
            tail = head;
        } else {
            Element element = new Element(null, value);
            tail.next = element;
            tail = element;
            element.next = head;
        }
    }

    public int size() {
        int count = 0;
        Element el = head;
        while (el != null) {
            count++;
            el = el.next;
        }
        return count;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Element el = head;

            @Override
            public boolean hasNext() {
                if (el != tail)
                    return el != null;
                else
                    return false;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T data = (T) el.value;
                    el = el.next;
                    return data;
                }
                return null;
            }
        };

    }


    class Element<T> {
        Element next;
        T value;

        public Element(Element next, T value) {
            this.next = next;
            this.value = value;
        }

    }

}
