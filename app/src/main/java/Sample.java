import android.annotation.SuppressLint;

public class Sample {
    public void Sample() {
        System.out.println("Default Const");
    }

    @SuppressLint("NotConstructor")
    public void Sample(int a) {
        System.out.println("Print A Value : " + a);
    }
}
