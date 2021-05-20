import java.util.Optional;

/**
 * @ClassName NewMan
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/20
 **/
public class NewMan {
    private Optional<Godness> godness = Optional.empty();

    public NewMan() {
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "godness=" + godness +
                '}';
    }

    public Optional<Godness> getGodness() {
        return godness;
    }

    public void setGodness(Optional<Godness> godness) {
        this.godness = godness;
    }

    public NewMan(Optional<Godness> godness) {
        this.godness = godness;
    }
}

