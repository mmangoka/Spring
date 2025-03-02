package annot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary //used if you have two classes and want one to be called
public class MediaTek implements MobileProcessor{

    @Override
    public void process() {
        System.out.println("2nd best CPU");
    }
}
