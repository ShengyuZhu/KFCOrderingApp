import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 菜品类，用于表示菜单中的每一道菜品
class MenuItem {
    // 菜品的名称
    private String name;
    // 菜品的价格
    private double price;

    // 构造方法，用于创建一个新的菜单项
    // 参数 name 是菜品的名称，price 是菜品的价格
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // 获取菜品名称的方法
    public String getName() {
        return name;
    }

    // 获取菜品价格的方法
    public double getPrice() {
        return price;
    }

    // 重写 toString 方法，方便打印菜单项信息
    @Override
    public String toString() {
        return name + " - ￥" + price;
    }
}

// 购物车项类，用于表示购物车中的每一项商品
class CartItem {
    // 对应的菜单项
    private MenuItem menuItem;
    // 该菜品的数量
    private int quantity;

    // 构造方法，用于创建一个新的购物车项
    // 参数 menuItem 是对应的菜单项，quantity 是该菜品的数量
    public CartItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    // 获取对应的菜单项的方法
    public MenuItem getMenuItem() {
        return menuItem;
    }

    // 获取该菜品数量的方法
    public int getQuantity() {
        return quantity;
    }

    // 计算该购物车项总价的方法
    public double getTotalPrice() {
        return menuItem.getPrice() * quantity;
    }

    // 重写 toString 方法，方便打印购物车项信息
    @Override
    public String toString() {
        return menuItem.getName() + " x " + quantity + " - ￥" + getTotalPrice();
    }
}

// 肯德基点餐系统类，包含了整个点餐系统的核心功能
class KFCOrderingSystem {
    // 菜单列表，存储所有的菜品信息
    private List<MenuItem> menu;
    // 购物车列表，存储用户选择的菜品信息
    private List<CartItem> cart;

    // 构造方法，初始化菜单和购物车
    public KFCOrderingSystem() {
        menu = new ArrayList<>();
        cart = new ArrayList<>();
        // 调用初始化菜单的方法
        initializeMenu();
    }

    // 初始化菜单的方法，添加一些常见的肯德基菜品到菜单列表中
    private void initializeMenu() {
        menu.add(new MenuItem("吮指原味鸡", 12.0));
        menu.add(new MenuItem("香辣鸡腿堡", 18.0));
        menu.add(new MenuItem("薯条（中）", 9.0));
        menu.add(new MenuItem("可乐（中）", 7.0));
    }

    // 显示菜单的方法，将菜单中的所有菜品信息打印输出
    public void displayMenu() {
        System.out.println("肯德基点餐系统 - 菜单");
        // 遍历菜单列表，打印每个菜品的编号和信息
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }
    }

    // 将菜品添加到购物车的方法
    // 参数 itemIndex 是菜品在菜单中的编号，quantity 是要添加的数量
    public void addToCart(int itemIndex, int quantity) {
        // 检查菜品编号是否有效
        if (itemIndex >= 1 && itemIndex <= menu.size()) {
            // 根据编号获取对应的菜单项
            MenuItem menuItem = menu.get(itemIndex - 1);
            boolean found = false;
            // 遍历购物车列表，检查该菜品是否已经在购物车中
            for (CartItem cartItem : cart) {
                if (cartItem.getMenuItem().getName().equals(menuItem.getName())) {
                    // 如果已经存在，更新该菜品的数量
                    cartItem = new CartItem(menuItem, cartItem.getQuantity() + quantity);
                    found = true;
                    break;
                }
            }
            // 如果该菜品不在购物车中，将其添加到购物车
            if (!found) {
                cart.add(new CartItem(menuItem, quantity));
            }
            System.out.println(menuItem.getName() + " x " + quantity + " 已添加到购物车。");
        } else {
            // 如果菜品编号无效，提示用户重新选择
            System.out.println("无效的菜品编号，请重新选择。");
        }
    }

    // 显示购物车内容的方法，打印购物车中的所有菜品信息和总价
    public void displayCart() {
        System.out.println("购物车内容：");
        // 检查购物车是否为空
        if (cart.isEmpty()) {
            System.out.println("购物车为空。");
        } else {
            // 遍历购物车列表，打印每个购物车项的信息
            for (CartItem cartItem : cart) {
                System.out.println(cartItem);
            }
            // 打印购物车的总价
            System.out.println("总价: ￥" + getTotalPrice());
        }
    }

    // 计算购物车总价的方法
    public double getTotalPrice() {
        double total = 0;
        // 遍历购物车列表，累加每个购物车项的总价
        for (CartItem cartItem : cart) {
            total += cartItem.getTotalPrice();
        }
        return total;
    }

    // 结算购物车的方法
    public void checkout() {
        // 检查购物车是否为空
        if (cart.isEmpty()) {
            System.out.println("购物车为空，无法结算。");
        } else {
            // 打印结算信息，包括总价
            System.out.println("结算成功！总价: ￥" + getTotalPrice());
            // 清空购物车
            cart.clear();
        }
    }
}

// 主类，包含程序的入口方法 main
public class KFCOrderingApp {
    public static void main(String[] args) {
        // 创建肯德基点餐系统的实例
        KFCOrderingSystem system = new KFCOrderingSystem();
        // 创建 Scanner 对象，用于从控制台读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 循环显示操作菜单，直到用户选择退出
        while (true) {
            System.out.println("\n请选择操作：");
            System.out.println("1. 查看菜单");
            System.out.println("2. 添加菜品到购物车");
            System.out.println("3. 查看购物车");
            System.out.println("4. 结算");
            System.out.println("5. 退出");

            // 读取用户的选择
            int choice = scanner.nextInt();
            // 根据用户的选择执行相应的操作
            switch (choice) {
                case 1:
                    // 调用显示菜单的方法
                    system.displayMenu();
                    break;
                case 2:
                    // 先显示菜单，方便用户选择菜品
                    system.displayMenu();
                    System.out.print("请输入要添加的菜品编号: ");
                    int itemIndex = scanner.nextInt();
                    System.out.print("请输入数量: ");
                    int quantity = scanner.nextInt();
                    // 调用添加菜品到购物车的方法
                    system.addToCart(itemIndex, quantity);
                    break;
                case 3:
                    // 调用显示购物车的方法
                    system.displayCart();
                    break;
                case 4:
                    // 调用结算购物车的方法
                    system.checkout();
                    break;
                case 5:
                    System.out.println("感谢使用肯德基点餐系统，再见！");
                    // 关闭 Scanner 对象
                    scanner.close();
                    return;
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }
    }
}