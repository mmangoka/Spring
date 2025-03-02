package annot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component//non qualified and decapitalized
public class Samsung {
    @Autowired
    @Qualifier("mediaTek")
    MobileProcessor cpu;

    public MobileProcessor getCpu() {
        return cpu;
    }

    public void setCpu(MobileProcessor cpu) {
        this.cpu = cpu;
    }

    public void config(){

        System.out.println("Octa core ,4 gb Ram.");
        cpu.process();
    }
}
