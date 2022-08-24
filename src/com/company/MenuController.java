package com.company;

import java.util.HashSet;

import static com.company.Constants.*;

public class MenuController {
    private static final MyLinkedList firstList = new MyLinkedList();
    private static final MyLinkedList secondList = new MyLinkedList();
    private static final MyLinkedList thirdList = new MyLinkedList();

    public static void showMenu(){
        System.out.println("""
                    1.Добавить в первый список.
                    2.Добавить во второй список.
                    3.Показать первый список.
                    4.Показать второй список.
                    5.Удалить первый список.
                    6.Удалить второй список.
                    7.Сформировать и вывести третий список.
                    8.Сохранить третий список.
                    9.Добавить два списка из файла.
                    10.О программе.
                    11.Выход.
                    """);
    }

    public static int findMenuItem(){
        String tempItem;
        do {
            tempItem = ScannerController.findDataFromConsole();
        } while (!isValidMenuItem(tempItem));
        return Integer.parseInt(tempItem);
    }

    public static void doChosenMenuItem(Integer key){
        switch (key){
            case (1) -> addNewLinkedList(firstList);
            case (2) -> addNewLinkedList(secondList);
            case (3) -> showLinkedList(firstList);
            case (4) -> showLinkedList(secondList);
            case (5) -> deleteLinkedList(firstList);
            case (6) -> deleteLinkedList(secondList);
            case (7) -> {
                createThirdLinkedList();
                showLinkedList(thirdList);
            }
            case (8) -> saveThirdLinkedList();
            case (9) -> findTwoLinkedLists();
            case (10) -> InfoController.showInfoAboutProgram();
        }
    }

    private static void findTwoLinkedLists() {
        showNumOfListLabel(1);
        firstList.readListInFile(LINE_OF_FIRST_LIST);
        showNumOfListLabel(2);
        secondList.readListInFile(LINE_OF_SECOND_LIST);
    }

    private static void saveThirdLinkedList() {
        thirdList.saveListToFile();
    }

    private static void deleteLinkedList(MyLinkedList list) {
        list.deleteAllElement();
        System.out.println("Успешно удалено.");
    }

    private static void showLinkedList(MyLinkedList list) {
        list.printList();
    }

    private static void addNewLinkedList(MyLinkedList list) {
        showEnterNumOfElements();
        int numOfElements = findNumOfItemsInList();
        addElementOfList(numOfElements, list);
    }

    private static void createThirdLinkedList(){
        HashSet<Integer> hs = new HashSet<>();
        LinkedListElement element;
        for (int i = 0; i < firstList.getLength(); i++){
            element = firstList.getElementById(i);
            hs.add(element.data);
        }
            for (int i = 0; i < secondList.getLength(); i++){
                element = secondList.getElementById(i);
                hs.add(element.data);
            }
        for (Integer data : hs){
            thirdList.addElementToList(data);
        }
    }

    private static void addElementOfList(int numOfItems, MyLinkedList list){
        String tempData;
        for (int i = 0; i < numOfItems; i++){
            showEnterElement();
            do {
                tempData = ScannerController.findDataFromConsole();
            } while (!isNum(tempData));
            list.addElementToList(Integer.parseInt(tempData));
        }
    }

    private static void showEnterNumOfElements(){
        System.out.println("Введите кол-во элементов: ");
    }

    private static void showEnterElement(){
        System.out.println("Введите элемент: ");
    }

    private static Integer findNumOfItemsInList(){
        String tempNum;
        do {
            tempNum = ScannerController.findDataFromConsole();
        } while (!isNum(tempNum));
        return Integer.parseInt(tempNum);
    }

    private static boolean isNum(String key){
        boolean isCorrect = true;
        try{
            Integer.parseInt(key);
        } catch (NumberFormatException e){
            isCorrect = false;
        }
        return isCorrect;
    }

    private static boolean isValidMenuItem(String key){
        boolean isCorrect = isNum(key);
        if (isCorrect && (Integer.parseInt(key) < MIN_MENU_INDEX || Integer.parseInt(key) > MAX_MENU_INDEX)){
            isCorrect = false;
        }
        if (!isCorrect){
            showUncorrectedMenuItem();
        }
        return isCorrect;
    }

    private static void showUncorrectedMenuItem(){
        System.out.println("Выберите корректный пункт меню:");
    }

    public static void showNumOfListLabel(int num){
        System.out.println("Введите путь к " + num + "-ому листу.");
    }

}
