package com.company;

import java.io.*;
import java.util.Objects;

import static com.company.Constants.EMPTY_LIST_SIZE;

public class MyLinkedList {
    private LinkedListElement head;
    private LinkedListElement tail;

    public LinkedListElement getElementById(int id){
        LinkedListElement element = this.head;
        for (int i = 1; i <= id; i++){
            element = element.next;
        }
        return element;
    }

    public void addElementToList(Integer data){
        LinkedListElement newElem = new LinkedListElement();
        newElem.data = data;
        if (tail == null){
            head = newElem;
        } else {
            tail.next = newElem;
        }
        tail = newElem;
    }

    public void deleteAllElement(){
        head = null;
        tail = null;
    }

    public void printList()
    {
        LinkedListElement list = this.head;
        if (list == null){
            System.out.println("Список пустой.");
        } else {
            while (list != null) {
                System.out.print(list.data + " ");
                list = list.next;
            }
            System.out.println();
        }
    }

    public int getLength(){
        int length = 0;
        LinkedListElement pointer = head;
        while (pointer != null){
            length++;
            pointer = pointer.next;
        }
        return length;
    }


    public void readListInFile(int numOfStr){
        boolean isCorrect = true;
        String temp = null;
        this.deleteAllElement();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(findFilePath()));
            for (int i = 0; i < numOfStr; i++){
                temp = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Файл поврежден.");
            isCorrect = false;
        }
        if (Objects.isNull(temp)){
            System.out.println("Файл поврежден.");
            isCorrect = false;
        } else {
            int[] arr = createArr(temp);
            for (int j : arr) {
                this.addElementToList(j);
            }
            if (isCorrect) {System.out.println("Успешно загружено.");}
        }
    }

    public void saveListToFile(){
        if (this.head == null){
            System.out.println("Список пустой, невозможно сохранить.");
        } else {
            try {
                FileOutputStream outputStream = new FileOutputStream(findFilePath());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                LinkedListElement element = this.head;
                while (element != null) {
                    objectOutputStream.writeObject(element);
                    element = element.next;
                }
                outputStream.close();
                System.out.println("Успешно сохранено.");
            } catch (FileNotFoundException e) {
                System.err.println("File not found.");
            } catch (IOException e) {
                System.err.println("I/O error.");
            }
        }
    }

    private int findNumOfSpaces(String inputStr){
        int counter = 0;
        char[] cArr = inputStr.toCharArray();
        for (char c : cArr) {
            if (c == ' ') {
                counter++;
            }
        }
        return counter;
    }

    private int[] createArr (String inputStr){
        int[] iArr = new int[findNumOfSpaces(inputStr) + 1];
        int counter = 0;
        char[] cArr = inputStr.toCharArray();
        StringBuilder temp = new StringBuilder();
        for (char c : cArr) {
            if (c != ' ') {
                temp.append(c);
            } else {
                if (isNum(String.valueOf(temp))) {
                    iArr[counter] = Integer.parseInt(String.valueOf(temp));
                    counter++;
                    temp.setLength(EMPTY_LIST_SIZE);
                }
            }
        }
        iArr[counter] = Integer.parseInt(String.valueOf(temp));
        return iArr;
    }


    private static boolean isNum(String data){
        boolean isCorrect = true;
        try{
            Integer.parseInt(data);
        } catch (NumberFormatException e){
            isCorrect = false;
        }
        return isCorrect;
    }

    private static String findFilePath() {
        String filePath;
        FileReader reader = null;
        boolean isIncorrect;
        do {
            System.out.print("Введите путь к файлу: ");
            filePath = ScannerController.findDataFromConsole();
            isIncorrect = false;
            try {
                reader = new FileReader(filePath);
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден.");
                isIncorrect = true;
            }
        } while (isIncorrect);
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("I/O error.");
        }
        return filePath;
    }

}

