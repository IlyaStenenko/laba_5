import somePackage.SomeInterface;
import somePackage.SomeOtherIntarface;

class SomeBean {

    @AutoInjectable.AutoInject
    private SomeInterface someField;
    @AutoInjectable.AutoInject
    private SomeOtherIntarface otherField;

    public SomeBean() {
    }


    public void go(){
        someField.doSome();
        otherField.doSome();
    }


}
