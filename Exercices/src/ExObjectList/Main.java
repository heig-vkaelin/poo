package ExObjectList;

public class Main {
    
    public static void main(String[] args) {
        // write your code here
        Object o1 = 1;
        Object o2 = "Test";
        Object o3 = 32;
        ObjectList list = new ObjectList(o1);
        System.out.println(list);
        
        list.insert(o2);
        System.out.println(list);
        
        /*
        list.insert(o3);
        System.out.println(list);
        */
    }
}
