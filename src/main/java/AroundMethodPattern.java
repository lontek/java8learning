import java.util.function.Consumer;

class Connection implements AutoCloseable {

    private Connection() {
    }

    Connection executeQuery() {
        System.out.println("executing");
        return this;
    }

    public static void use(Consumer<Connection> block) {
        try (final Connection connection = new Connection()) {
            block.accept(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("Auto connection closed");
    }
}


public class AroundMethodPattern {
    public static void main(String... args) {
        Connection.use(Connection::executeQuery);
    }
}
