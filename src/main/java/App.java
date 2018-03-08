import verticle.RestApiVerticle;
import io.vertx.core.Vertx;

public class App {

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new RestApiVerticle(8080));
    }
}
