package studentmanagementsystem;

import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Database db = new Database();


        int ch = -1;
        while(ch!=6){
            System.out.println("-----Student Management System-----");
            System.out.println("1 . Add a student \n" +
                    "2 . View all students \n" +
                    "3 . Search a student \n" +
                    "4 . Update student \n" +
                    "5 . Delete student \n" +
                    "6 . Exit\n");

            System.out.println("please provide your choice \n");
            ch = sc.nextInt();


            switch(ch){
                case 1 :
                        Student st = getStudentDetailsFromUser(sc);
                        if(db.createNewStudent(new Student(st.getId(), st.getName(), st.getAge()))){
                            System.out.println("Student created");
                        }
                        else{
                            System.out.println("Student already exists with this ID");
                        }
                        break;

                case 2 : db.viewAllStudents();
                        break;

                case 3 : System.out.println("Enter student id : ");
                        Student searchSt = db.searchForStudent(sc.nextInt());
                        if(searchSt!=null) {
                            System.out.println(searchSt.toString());
                        }
                        else System.out.println("No Student found with this ID");
                        break;

                case 4 : db.updateStudent(sc);
                        break;

                case 5 : db.deleteStudent(sc);
                        break;

                case 6 :
                    System.exit(0);
            }
        }
    }

    public static Student getStudentDetailsFromUser(Scanner sc){

        int id,age;
        String name;
        System.out.println("Enter Student id : ");
        id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Student name : ");
        name = sc.nextLine();
        System.out.println("Enter Student age :");
        age = sc.nextInt();
        while(age<0) {
            System.out.println("Invalid age ! Enter age again :");
            age = sc.nextInt();
        }
        return new Student(id,name,age);
    }


}
