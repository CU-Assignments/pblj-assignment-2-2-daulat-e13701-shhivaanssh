import java.io.*;
import java.util.*;

class Product implements Serializable {
    String name;
    int id;
    String category;
    double price;

    public Product(String name, int id, String category, double price) {
        this.name = name;
        this.id = id;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', category='" + category + "', price=" + price + '}';
    }
}

class Student implements Serializable {
    int id;
    String name;
    double grade;

    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', grade=" + grade + '}';
    }
}

public class EXP5 {
    static final String PRODUCT_FILE = "products.dat";
    static final String STUDENT_FILE = "student.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Sum of a List of Integers (Autoboxing and Unboxing)");
            System.out.println("2. Serialize and Deserialize a Student Object");
            System.out.println("3. Product Management");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            if (choice == 1) {
                // Sum of a List of Integers (Autoboxing and Unboxing)
                List<Integer> numbers = new ArrayList<>();
                numbers.add(10);
                numbers.add(20);
                numbers.add(30);
                numbers.add(40);
                int sum = calculateSum(numbers);
                System.out.println("The sum of the list is: " + sum);

            } else if (choice == 2) {
                // Serialize and Deserialize a Student Object
                Student student = new Student(1, "John Doe", 95.5);
                serializeStudent(student);
                deserializeStudent();

            } else if (choice == 3) {
                // Product Management
                productManagement(scanner);

            } else if (choice == 4) {
                // Exit
                break;
            }
        }
        scanner.close();
    }

    // Method for calculating sum of integers using autoboxing and unboxing
    public static int calculateSum(List<Integer> numbers) {
        int sum = 0;
        for (Integer num : numbers) {
            sum += num;  // unboxing
        }
        return sum;
    }

    // Serialize a Student object
    public static void serializeStudent(Student student) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(STUDENT_FILE))) {
            out.writeObject(student);
            System.out.println("Student object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserialize a Student object
    public static void deserializeStudent() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(STUDENT_FILE))) {
            Student student = (Student) in.readObject();
            System.out.println("Deserialized Student: " + student);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Product Management Menu
    public static void productManagement(Scanner scanner) {
        while (true) {
            System.out.println("Product Management:");
            System.out.println("1. Add a Product");
            System.out.println("2. Display All Products");
            System.out.println("3. Go back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            if (choice == 1) {
                // Add Product
                System.out.print("Enter product name: ");
                String name = scanner.nextLine();
                System.out.print("Enter product id: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                System.out.print("Enter product category: ");
                String category = scanner.nextLine();
                System.out.print("Enter product price: ");
                double price = scanner.nextDouble();

                Product product = new Product(name, id, category, price);
                addProductToFile(product);

            } else if (choice == 2) {
                // Display All Products
                displayProducts();

            } else if (choice == 3) {
                // Go back to main menu
                break;
            }
        }
    }

    // Add Product to file
    public static void addProductToFile(Product product) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PRODUCT_FILE, true))) {
            out.writeObject(product);
            System.out.println("Product added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Display All Products
    public static void displayProducts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(PRODUCT_FILE))) {
            System.out.println("Product List:");
            while (true) {
                Product product = (Product) in.readObject();
                System.out.println(product);
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
