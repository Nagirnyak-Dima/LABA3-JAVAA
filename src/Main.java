import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n ГОЛОВНЕ МЕНЮ ");
            System.out.println("1. Завдання 1 ( Держава)");
            System.out.println("2. Завдання 2 (Number)");
            System.out.println("3. Завдання 3 (MathNumber)");
            System.out.println("0. Вихід");
            System.out.print("Оберіть номер завдання: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    runTask1();
                    break;
                case 2:
                    runTask2();
                    break;
                case 3:
                    runTask3();
                    break;
                case 0:
                    System.out.println("Вихід з програми.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }


    // Завдання 1
    public static void runTask1() {
        State[] states = {
                new Republic("Україна", 38000000, "Володимир Зеленський"),
                new Monarchy("Японія", 124000000, "Імператор"),
                new Kingdom("Сполучене Королівство", 67000000, "Король", "Віндзорська")
        };

        System.out.println("\n--- Виконання Завдання 1 ---");
        for (State s : states) {
            s.show();
        }
    }


    // Завдання 2

    public static void runTask2() {
        Number n1 = new IntegerNum(10);
        Number n2 = new RealNum(5.5);

        System.out.println("\n--- Виконання Завдання 2 ---");
        System.out.println("n1 = " + n1);
        System.out.println("n2 = " + n2);
        System.out.println("Додавання (n1 + n2): " + n1.add(n2));
        System.out.println("Множення (n1 * n2): " + n1.multiply(n2));

        Number n3 = new IntegerNum(10);
        System.out.println("n1 equals n3? " + n1.equals(n3));
    }

    // Завдання 3

    public static void runTask3() {
        MathNumber num1 = new IntNumber(20);
        MathNumber num2 = new FloatNumber(4.5);

        System.out.println("\n--- Виконання Завдання 3 ---");
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("Ділення (num1 / num2): " + num1.divide(num2));
        System.out.println("Віднімання (num1 - num2): " + num1.subtract(num2));
    }
}

class State {
    protected String name;
    protected long population;

    public State(String name, long population) {
        this.name = name;
        this.population = population;
    }

    public void show() {
        System.out.println("Держава: " + name + ", Населення: " + population + " осіб");
    }
}

class Republic extends State {
    private String presidentName;

    public Republic(String name, long population, String presidentName) {
        super(name, population);
        this.presidentName = presidentName;
    }

    @Override
    public void show() {
        System.out.println("[Республіка] Назва: " + name + ", Населення: " + population + ", Президент: " + presidentName);
    }
}

class Monarchy extends State {
    private String monarchTitle;

    public Monarchy(String name, long population, String monarchTitle) {
        super(name, population);
        this.monarchTitle = monarchTitle;
    }

    @Override
    public void show() {
        System.out.println("[Монархія] Назва: " + name + ", Населення: " + population + ", Форма правління: " + monarchTitle);
    }
}

class Kingdom extends Monarchy {
    private String royalHouse;

    public Kingdom(String name, long population, String monarchTitle, String royalHouse) {
        super(name, population, monarchTitle);
        this.royalHouse = royalHouse;
    }

    @Override
    public void show() {
        System.out.println("[Королівство] Назва: " + name + ", Населення: " + population + ", Титул: " + super.name + ", Королівська династія: " + royalHouse);
    }
}

abstract class Number {
    public abstract Number add(Number other);
    public abstract Number subtract(Number other);
    public abstract Number multiply(Number other);
    public abstract Number divide(Number other);
}

class IntegerNum extends Number {
    private int value;

    public IntegerNum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public Number add(Number other) {
        if (other instanceof IntegerNum) {
            return new IntegerNum(this.value + ((IntegerNum) other).value);
        } else if (other instanceof RealNum) {
            return new RealNum(this.value + ((RealNum) other).getValue());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Number subtract(Number other) {
        if (other instanceof IntegerNum) {
            return new IntegerNum(this.value - ((IntegerNum) other).value);
        } else if (other instanceof RealNum) {
            return new RealNum(this.value - ((RealNum) other).getValue());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Number multiply(Number other) {
        if (other instanceof IntegerNum) {
            return new IntegerNum(this.value * ((IntegerNum) other).value);
        } else if (other instanceof RealNum) {
            return new RealNum(this.value * ((RealNum) other).getValue());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Number divide(Number other) {
        if (other instanceof IntegerNum) {
            IntegerNum o = (IntegerNum) other;
            if (o.value == 0) throw new ArithmeticException();
            return new IntegerNum(this.value / o.value);
        } else if (other instanceof RealNum) {
            RealNum o = (RealNum) other;
            if (o.getValue() == 0.0) throw new ArithmeticException();
            return new RealNum(this.value / o.getValue());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "IntegerNum: " + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IntegerNum that = (IntegerNum) obj;
        return value == that.value;
    }
}

class RealNum extends Number {
    private double value;

    public RealNum(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public Number add(Number other) {
        double val = (other instanceof IntegerNum) ? ((IntegerNum) other).getValue() : ((RealNum) other).value;
        return new RealNum(this.value + val);
    }

    @Override
    public Number subtract(Number other) {
        double val = (other instanceof IntegerNum) ? ((IntegerNum) other).getValue() : ((RealNum) other).value;
        return new RealNum(this.value - val);
    }

    @Override
    public Number multiply(Number other) {
        double val = (other instanceof IntegerNum) ? ((IntegerNum) other).getValue() : ((RealNum) other).value;
        return new RealNum(this.value * val);
    }

    @Override
    public Number divide(Number other) {
        double val = (other instanceof IntegerNum) ? ((IntegerNum) other).getValue() : ((RealNum) other).value;
        if (val == 0.0) throw new ArithmeticException();
        return new RealNum(this.value / val);
    }

    @Override
    public String toString() {
        return "RealNum: " + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RealNum realNum = (RealNum) obj;
        return Double.compare(realNum.value, value) == 0;
    }
}

interface MathNumber {
    MathNumber add(MathNumber other);
    MathNumber subtract(MathNumber other);
    MathNumber multiply(MathNumber other);
    MathNumber divide(MathNumber other);
}

class IntNumber implements MathNumber {
    private int value;

    public IntNumber(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public MathNumber add(MathNumber other) {
        if (other instanceof IntNumber) {
            return new IntNumber(this.value + ((IntNumber) other).value);
        } else if (other instanceof FloatNumber) {
            return new FloatNumber(this.value + ((FloatNumber) other).getValue());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public MathNumber subtract(MathNumber other) {
        if (other instanceof IntNumber) {
            return new IntNumber(this.value - ((IntNumber) other).value);
        } else if (other instanceof FloatNumber) {
            return new FloatNumber(this.value - ((FloatNumber) other).getValue());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public MathNumber multiply(MathNumber other) {
        if (other instanceof IntNumber) {
            return new IntNumber(this.value * ((IntNumber) other).value);
        } else if (other instanceof FloatNumber) {
            return new FloatNumber(this.value * ((FloatNumber) other).getValue());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public MathNumber divide(MathNumber other) {
        if (other instanceof IntNumber) {
            IntNumber o = (IntNumber) other;
            if (o.value == 0) throw new ArithmeticException();
            return new IntNumber(this.value / o.value);
        } else if (other instanceof FloatNumber) {
            FloatNumber o = (FloatNumber) other;
            if (o.getValue() == 0.0) throw new ArithmeticException();
            return new FloatNumber(this.value / o.getValue());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "IntNumber: " + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IntNumber intNumber = (IntNumber) obj;
        return value == intNumber.value;
    }
}

class FloatNumber implements MathNumber {
    private double value;

    public FloatNumber(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public MathNumber add(MathNumber other) {
        double val = (other instanceof IntNumber) ? ((IntNumber) other).getValue() : ((FloatNumber) other).value;
        return new FloatNumber(this.value + val);
    }

    @Override
    public MathNumber subtract(MathNumber other) {
        double val = (other instanceof IntNumber) ? ((IntNumber) other).getValue() : ((FloatNumber) other).value;
        return new FloatNumber(this.value - val);
    }

    @Override
    public MathNumber multiply(MathNumber other) {
        double val = (other instanceof IntNumber) ? ((IntNumber) other).getValue() : ((FloatNumber) other).value;
        return new FloatNumber(this.value * val);
    }

    @Override
    public MathNumber divide(MathNumber other) {
        double val = (other instanceof IntNumber) ? ((IntNumber) other).getValue() : ((FloatNumber) other).value;
        if (val == 0.0) throw new ArithmeticException();
        return new FloatNumber(this.value / val);
    }

    @Override
    public String toString() {
        return "FloatNumber: " + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FloatNumber that = (FloatNumber) obj;
        return Double.compare(that.value, value) == 0;
    }
}