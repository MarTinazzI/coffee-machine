package machine;

import java.util.Scanner;

public class CoffeeMachine {

  public static void main(String[] args) {
    new Program().run();
  }

  public static class Program {

    public static Machine machine;
    public static Scanner sc;

    public Program() {
      machine = new Machine();
      sc = new Scanner(System.in);
    }

    public void run() {
      boolean running = true;

      do {
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
        String action = sc.next();
        System.out.println();
        switch (action.toLowerCase()) {
          case "buy":
            buy();
            break;
          case "fill":
            fill();
            break;
          case "take":
            take();
            break;
          case "remaining":
            remaining();
            break;
          default:
            running = false;
        }
      } while (running);

    }

    private void remaining() {
      System.out.println(machine.toString());
    }

    private void take() {
      System.out.println("I gave you $" + machine.money + "\n");
      machine.money = 0;
    }

    private void fill() {
      System.out.println("Write how many ml of water do you want to add: \n");
      machine.water += sc.nextInt();
      System.out.println("Write how many ml of milk do you want to add: \n");
      machine.milk += sc.nextInt();
      System.out.println("Write how many grams of coffee beans do you want to add: \n");
      machine.beans += sc.nextInt();
      System.out.println("Write how many disposable cups of coffee do you want to add: \n");
      machine.cups += sc.nextInt();
    }

    private void buy() {
      System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
      String selected = sc.next();

      boolean valid;
      switch (selected) {
        case "1":
          valid = machine.buy(new Espresso());
          break;
        case "2":
          valid = machine.buy(new Latte());
          break;
        case "3":
          valid = machine.buy(new Cappuccino());
          break;
        default:
          return;
      }

      if (valid) {
        System.out.println("I have enough resources, making you a coffee!");
      }

    }

  }

  public static class CoffeeType {

    int water;
    int milk;
    int beans;
    int cups;
    int money;

    public CoffeeType() {
      this.water = 0;
      this.milk = 0;
      this.beans = 0;
      this.cups = 0;
      this.money = 0;
    }

    public boolean build(Machine machine) {
      if (!this.canBuild(machine)) return false;
      machine.water -= this.water;
      machine.milk -= this.milk;
      machine.beans -= this.beans;
      machine.cups -= this.cups;
      machine.money += this.money;
      return true;
    }

    public boolean canBuild(Machine machine) {
      if (machine.water - this.water < 0) {
        System.out.println("Sorry, not enough water!");
        return false;
      }
      if (machine.milk - this.milk < 0) {
        System.out.println("Sorry, not enough milk!");
        return false;
      }
      if (machine.beans - this.beans < 0) {
        System.out.println("Sorry, not enough beans!");
        return false;
      }
      if (machine.cups - this.cups < 0) {
        System.out.println("Sorry, not enough cups!");
        return false;
      }
      return true;
    }

  }

  public static class Machine {

    int water = 400;
    int milk = 540;
    int beans = 120;
    int cups = 9;
    int money = 550;

    public boolean buy(CoffeeType coffeeType) {
      return coffeeType.build(this);
    }

    @Override
    public String toString() {
      return "The coffee machine has:\n" +
          water + " of water\n" +
          milk + " of milk\n" +
          beans + " of coffee beans\n" +
          cups + " of disposable cups\n" +
          money + " of money\n";
    }

  }

  public static class Espresso extends CoffeeType {

    public Espresso() {
      water = 250;
      milk = 0;
      beans = 16;
      cups = 1;
      money = 4;
    }

  }

  public static class Latte extends CoffeeType {

    public Latte() {
      water = 350;
      milk = 75;
      beans = 20;
      cups = 1;
      money = 7;
    }

  }

  public static class Cappuccino extends CoffeeType {

    public Cappuccino() {
      water = 200;
      milk = 100;
      beans = 12;
      cups = 1;
      money = 6;
    }

  }

}
