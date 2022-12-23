import java.io.IOException;
public class main {
    public static void main(String... A)throws IOException, IllegalAccessException, InstantiationException
    {
        SomeBean somebean = (new inject<SomeBean>("config.properties").inject(new SomeBean()));
        somebean.go();
        //SomeBean = new SomeBean(); ошибка
    }
}
