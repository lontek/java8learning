import java.util.function.Consumer;

class Resource {

    private Resource() {
    }

    Resource op1() {
        System.out.println("op1");
        return this;
    }

    Resource op2() {
        System.out.println("op2");
        return this;
    }

    Resource op3() {
        System.out.println("op2");
        return this;
    }

    void doSomething() {
        System.out.println("cleaning up");
    }

    static void use(Consumer<Resource> block) {
        final Resource resource = new Resource();
        try {
            block.accept(resource);
        } finally {
            resource.doSomething();
        }
    }
}


public class AroundMethodPattern {
    public static void main(String... args) {
        Resource.use(
                resource ->
                {
                    resource.op1();
                    resource.op2();
                    resource.op3();
                });

    }
}
