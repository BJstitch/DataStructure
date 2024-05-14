/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookssystem;

/**
 *
 * @author batoo
 */
class CircularLinkedList<E> {
    
    class Node<E> {

        private E value;
        private Node next;

        Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }

        E getValue() {
            return value;
        }

        Node getNext() {
            return next;
        }

        void setValue(E value) {
            this.value = value;
        }

        void setNext(Node next) {
            this.next = next;
        }
    }
    
    Node<E> head = null;
    Node<E> tail = null;
    int size = 0;

    CircularLinkedList() {}

    int getSize() {
        return size;
    }

    E first() {
        return head.getValue();
    }

    E last() {
        return tail.getValue();
    }

    boolean isEmpty() {
        return size == 0;
    }

    String display() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append("Empty List.. ");
            return sb.toString();
        }
        sb.append("Display List: ");
        Node<E> current = head;
        int count = 1;
        do {
            sb.append("\tNode ").append(count).append(": ").append(current.getValue());
            current = current.getNext();
            count++;
        } while (current != head);
        return sb.toString();
    }


    String findNode(E key) {
        if (isEmpty()) {
            return "Empty List.. ";
        }

        Node<E> current = head;
        int counter = 1;
        boolean flag = false;

        do {
            if (current.getValue().equals(key)) {
                flag = true;
                break;
            }
            counter++;
            current = current.getNext();
        } while (current != head);

        if (flag)
            return "Found in position# " + counter;
        else
            return "Value not found ";
    }

    void removeFirst() {
        if (isEmpty()) {
            System.out.println("Empty List.. ");
            return;
        }

        head = head.getNext();
        tail.setNext(head);
        size--;
        if (size == 0) {
            head = null;
            tail = null;
        }
    }

    void addFirst(E value) {
        Node<E> newNode = new Node<E>(value, head);
        head = newNode;
        if (size == 0) {
            tail = head;
        }
        tail.setNext(head);
        size++;
    }

    void addLast(E value) {
        Node<E> newNode = new Node<E>(value, null);
        if (size == 0) {
            head = newNode;
            tail = head;
        } else {
            tail.setNext(newNode);
            tail = newNode; 
        }

        tail.setNext(head);
        size++;
    }

    void removeNode(E key) {
        if (isEmpty()) {
            System.out.println("\nEmpty list...");
            return;
        }

        if (head.getValue().equals(key)) {
            removeFirst();
            return;
        }

        Node<E> current = head;
        Node<E> prev = head;

        while (!current.getValue().equals(key)) {
            if (current.getNext() == head) {
                System.out.println("\nNot found...");
                return;
            }
            prev = current;
            current = current.getNext();
        }

        prev.setNext(current.getNext());
        size--;
    }

    public void addAtPosition(E e, int position) {
        if (position < 1 || position > size) {
            System.out.println("Invalid Position");
            return;
        }

        if (position == 1) {
            addFirst(e);
            return;
        }

        Node<E> newNode = new Node<E>(e, null);
        Node<E> current = head;
        int count = 1;
        while (count < position - 1) {
            current = current.getNext();
            count++;
        }

        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
    }

    public void removeAtPosition(int position) {
        if (isEmpty()) {
            System.out.println("Empty List.. ");
            return;
        }

        if (position < 1 || position > size) {
            System.out.println("Invalid Position");
            return;
        }

        if (position == 1) {
            removeFirst();
            return;
        }

        Node<E> current = head;
        Node<E> prev = head;

        int count = 1;
        while (count < position) {
            prev = current;
            current = current.getNext();
            count++;
        }

        prev.setNext(current.getNext());
        size--;
    }

    public void rotate() {
        if (!isEmpty()) {
            tail = head;
            head = head.getNext();
        }
    }
}
