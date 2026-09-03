package studentmanagementsystem;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Database {

    LinkedList<Student> list = new LinkedList<>();

    Database(){
        try{
            File file = new File("Students.txt");
            boolean created = file.createNewFile();
            System.out.println("File created :" + created);
            try(BufferedReader br = new BufferedReader(new FileReader("Students.txt"))){
                    String str;
                    while((str = br.readLine())!=null){
                        String[] arr = str.split(",");
                        int id = Integer.parseInt(arr[0]);
                        String name = arr[1];
                        int age = Integer.parseInt(arr[2]);

                        list.add(new Student(id,name,age));
                }

            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }


    public boolean createNewStudent(Student st) {

         if(searchForStudent(st.getId())!=null){
             return false;
         }

         list.add(st);

         // write to file
         try(BufferedWriter bw = new BufferedWriter(new FileWriter("Students.txt"))){
             for(Student s : list){
                 bw.write(s.getId()+","+s.getName()+","+s.getAge());
                 bw.newLine();
             }
         }
         catch(IOException e){
             System.out.println(e.getMessage());
         }
         return true;

    }

    public Student searchForStudent(int id) {

        for(Student st : list){
            if(st.getId()==id) return st;
        }
        return null;
    }

    public void viewAllStudents() {
        for(Student st : list){
             System.out.println(st.toString());
        }
    }

    public void updateStudent(Scanner sc) {
        System.out.println("Enter the student id which needs to be updated ");
        int id = sc.nextInt();
        Student st = searchForStudent(id);

        if(st!=null){
            System.out.println("What needs to be updated\n 1 . name\n 2 .age");
            int ch = sc.nextInt();

            while(ch<1 || ch>2){
                System.out.println("Invalid choice . Please enter again");
                ch = sc.nextInt();

            }
            sc.nextLine();
            switch(ch) {
                case 1 : updateName(sc,st);
                         break;
                case 2 : updateAge(sc,st);
                         break;
            }

            // write to file
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("Students.txt"))){
                for(Student s : list){
                    bw.write(s.getId()+","+s.getName()+","+s.getAge());
                    bw.newLine();
                }
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }

        }
        else System.out.println("No student found");
        
    }

    private void updateAge(Scanner sc, Student st) {
        System.out.println("Enter age : ");
        int age = sc.nextInt();
        st.setAge(age);
        System.out.println("Age updated successfully");
    }

    private void updateName(Scanner sc,Student st) {
        System.out.println("Enter name : ");
        String name = sc.nextLine();
        st.setName(name);
        System.out.println("Name updated successfully");
    }

    public void deleteStudent(Scanner sc) {
        System.out.println("Enter the student id which needs to be deleted ");
        int id = sc.nextInt();
        Student st = searchForStudent(id);

        if(st!=null){
            list.remove(st);
            System.out.println("Removed successfully");
            // write to file
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("Students.txt"))){
                for(Student s : list){
                    bw.write(s.getId()+","+s.getName()+","+s.getAge());
                    bw.newLine();
                }
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
        else System.out.println("No student found");

        
    }
}
