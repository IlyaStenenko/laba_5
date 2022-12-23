import java.lang.annotation.*;
public class AutoInjectable {
    @Target(value = ElementType.FIELD)
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface AutoInject {

    }
}
