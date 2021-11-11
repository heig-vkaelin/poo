import ExClassArray.*;
import ExObjectList.*;

public class Main {

    public static void main(String[] args) {
//        exClassArray();
        exObjectList();
    }

    public static void exClassArray() {
        Object[] array = new Object[7];
        array[0] = 3;
        array[1] = 4;
        array[2] = 2;
        array[3] = "String";
        array[4] = "String2";
        array[5] = false;
        array[6] = true;

        ClassArray test = new ClassArray(7);
        test.printTypes(array);
    }

    public static void exObjectList() {
        Object o1 = 1;
        Object o2 = "Test";
        Object o3 = 32;
        ObjectList list = new ObjectList(o1);
        System.out.println(list);

        list.insert(o2);
        System.out.println(list);
        list.insert(o3);
        System.out.println(list);
        list.remove(o3);
        System.out.println(list);
        list.remove(o1);
        System.out.println(list);
        list.remove(o2);
        System.out.println(list);

        /*
        list.insert(o3);
        System.out.println(list);
        */
    }
}
