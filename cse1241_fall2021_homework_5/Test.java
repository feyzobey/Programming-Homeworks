public class Test {

   public static void main(String[] args) {

      Factory myFactory = new Factory("My Factory", 100, 5);

      Employee emp1 = new Employee(1, "Ahmet", "Yilmaz", 8, 3);
      Employee emp2 = new Employee(2, "Ayse", "Yildirim", 6, 2);
      Employee emp3 = new Employee(3, "Mehmet", "Kara", 10, 1);

      myFactory.removeEmployee(123);

      myFactory.addEmployee(emp1);
      myFactory.addEmployee(emp2);
      myFactory.addEmployee(emp3);

      myFactory.removeEmployee(emp1.getId());
      myFactory.removeEmployee(emp2.getId());
      myFactory.removeEmployee(emp3.getId());

      System.out.println(emp1.toString());
      System.out.println(emp2.toString());
      System.out.println(emp3.toString());

      for (Employee employee : myFactory.getEmployees()) {
         System.out.println(employee.getId() + " " + employee.getName() + " " + employee.getSurname() + " "
               + employee.getPayroll().calculateSalary());
      }
      if (myFactory.getEmployees().length == 0) {
         System.err.println("No employees left");
      }

      System.out.println("Revenue: " + myFactory.getRevenue());
      System.out.println("Paid salaries: " + myFactory.getPaidSalaries());
      System.out.println("Total item produced: " + Item.numberOfItems);
   }
}