public interface MyFunc<T> {

    T MyFunc(int a);
    default String getName()
    {
        return "hello java8";
    }
}
