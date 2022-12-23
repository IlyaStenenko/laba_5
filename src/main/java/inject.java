import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;
/*Класс для внедрения зависимостей в классы помеченные @AutoInjectable
* Используем шаблоны (<T>) тк как работа идет с еще не известными по типу обьектами*/
public class inject<T> {
    // прописываем записанные в конфиге пропертис с помощью класса пропертис
    private Properties properties;

    inject(String pathToPropertiesFile) throws IOException {
        // инициализация пропертис
        properties = new Properties();
        properties.load(new FileInputStream(new File(pathToPropertiesFile)));
    }

    T inject(T obj) throws IOException, IllegalAccessException, InstantiationException {
        // ссылка на зависимсоть в исследуемом классе
        Class dependency;

        Class cl = obj.getClass();

        // получаем список всех полей в объекте obj
        Field[] fields = cl.getDeclaredFields();
        for (Field field: fields){
            // проверяем, есть ли аннотация AutoInjectable в исследуемом поле
            Annotation a = field.getAnnotation(AutoInjectable.AutoInject.class);
            if (a != null){
                String[] fieldType = field.getType().toString().split(" ");
                String equalsClassName = properties.getProperty(fieldType[1], null);
                if (equalsClassName != null){
                    try {
                        dependency = Class.forName(equalsClassName);
                    } catch (ClassNotFoundException e){
                        System.out.println("class not found  " + equalsClassName);
                        continue;
                    }
                    field.setAccessible(true);
                    // инициализируем поле объектом, указанным в конфигурации
                    field.set(obj, dependency.newInstance());// new instance зачеркнуло но это ничего страшного просто он старый
                }
                else
                    System.out.println("No properties,no inject!" + fieldType[1]);
            }
        }
        return obj;
    }

}
