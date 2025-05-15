import java.util.*;

public class Organization {
    Employee root;

    int height(Employee node) {
        return node == null ? 0 : node.height;
    }

    int balanceFactor(Employee node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    Employee rotateRight(Employee y) {
        Employee x = y.left;
        Employee T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Employee rotateLeft(Employee x) {
        Employee y = x.right;
        Employee T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private Employee rebalance(Employee node) {
        int balanceFactor = balanceFactor(node);
        // Left-heavy
        if (balanceFactor > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left); // LR
            }
            return rotateRight(node); // R
        }
        // Right-heavy
        if (balanceFactor < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right); // RL
            }
            return rotateLeft(node); // L
        }
        return node;  // No balancing needed
    }

    boolean addEmployee(String name, int salary, IT pos) {
        Employee newEmp = new Employee(name, salary, pos);
        if (contains(root, newEmp)) {
            System.out.println("Employee " + name + " already exists");
            return false;
        }
        root = insertAndRebalance(root, newEmp);
        return true;
    }

    boolean contains(Employee node, Employee target) {
        if (node == null) return false;
        if (node.name.equals(target.name) && node.salary == target.salary && node.pos == target.pos) return true;
        return contains(node.left, target) || contains(node.right, target);
    }

    Employee insertAndRebalance(Employee node, Employee newEmp) {
        if (node == null) return newEmp;
        if (newEmp.salary < node.salary) node.left = insertAndRebalance(node.left, newEmp);
        else node.right = insertAndRebalance(node.right, newEmp);
        return rebalance(node);
    }

    Employee delete(Employee node, String name) {
        if (node == null) return null;

        if (name.equals(node.name)) {
            if (node.left == null || node.right == null) {
                return (node.left != null) ? node.left : node.right;
            } else {
                Employee successor = minValueNode(node.right);
                node.name = successor.name;
                node.salary = successor.salary;
                node.pos = successor.pos;
                node.right = delete(node.right, successor.name);
            }
        } else {
            node.left = delete(node.left, name);
            node.right = delete(node.right, name);
        }
        return rebalance(node);
    }

    Employee minValueNode(Employee node) {
        Employee current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    boolean isPresent(Employee node, String name) {
        if (node == null) return false;
        if (name.equals(node.name)) return true;
        return isPresent(node.left, name) || isPresent(node.right, name);
    }

    List<Employee> getEmployeesWithSalaryMoreThan(Employee node, int threshold, List<Employee> list) {
        if (node == null) return list;
        if (node.salary > threshold) {
            getEmployeesWithSalaryMoreThan(node.left, threshold, list);
            list.add(node);
        }
        getEmployeesWithSalaryMoreThan(node.right, threshold, list);
        return list;
    }

    List<Employee> inOrder(Employee node, List<Employee> list) {
        if (node == null) return list;
        inOrder(node.left, list);
        list.add(node);
        inOrder(node.right, list);
        return list;
    }

    List<Employee> getFirstThreeLevels(Employee root) {
        List<Employee> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Employee> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty() && level < 3) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Employee curr = queue.poll();
                res.add(curr);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            level++;
        }
        return res;
    }

    Employee getSiblings(Employee root, String name) {
        if (root == null) {
            return null; // No siblings if the tree is empty
        }
        // Use a queue to perform BFS
        Queue<Employee> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Employee current = queue.poll();
            // Check if the current node has left and right children
            if (current.left != null && current.right != null) {
                // If the name matches the left child, return the right child
                if (current.left.name.equals(name)) {
                    return current.right;
                }
                // If the name matches the right child, return the left child
                if (current.right.name.equals(name)) {
                    return current.left;
                }
            }
            // Add left and right children of the current node to the queue for BFS
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        // If no sibling is found, return null
        System.out.println("No sibling found");
        return null;
    }
}

class Main {
    public static void main(String[] args) {
        Organization org = new Organization();

        org.addEmployee("Alice", 75000, IT.BACKEND_DEV);
        org.addEmployee("Bob", 60000, IT.FRONTEND_DEV);
        org.addEmployee("Charlie", 80000, IT.FULLSTACK_DEV);
        org.addEmployee("Diana", 70000, IT.HQ);
        org.addEmployee("Eve", 65000, IT.BACKEND_DEV);

        // Check if a given employee is present
        System.out.println("Is Alice present? " + org.isPresent(org.root, "Alice"));

        // Get employees with salary more than 68000
        List<Employee> highEarners = org.getEmployeesWithSalaryMoreThan(org.root, 68000, new ArrayList<>());
        System.out.println("Employees earning more than 68000:");
        for (Employee e : highEarners)
            System.out.println(e.name + " - $" + e.salary);

        // In-order traversal
        System.out.println("Employees in salary order:");
        List<Employee> ordered = org.inOrder(org.root, new ArrayList<>());
        for (Employee e : ordered)
            System.out.println(e.name + " - $" + e.salary);

        // First three levels
        System.out.println("Employees in first 3 levels:");
        List<Employee> levels = org.getFirstThreeLevels(org.root);
        for (Employee e : levels)
            System.out.println(e.name);

        // Test getSiblings method for Bob
        Employee bro = org.getSiblings(org.root, "Bob");
        if (bro != null) {
            System.out.println("Sibling of Bob: " + bro.name);  // Should print Diana
        }

        // Test getSiblings method for Charlie
        bro = org.getSiblings(org.root, "Charlie");
        if (bro != null) {
            System.out.println("Sibling of Charlie: " + bro.name);
        } else {
            System.out.println("No sibling found for Charlie");
        }

        // Delete an employee
        org.root = org.delete(org.root, "Charlie");
        System.out.println("After deleting Charlie:");
        List<Employee> afterDel = org.inOrder(org.root, new ArrayList<>());
        for (Employee e : afterDel)
            System.out.println(e.name);

        System.out.println(org.isPresent(org.root, "Charlie"));
    }
}