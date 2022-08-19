package Output;

import java.util.*;

import Osoby.Robotnik;
import Symulacja.*;

public class FullOutput {
    private InfoOutput info;
    private Vector <RobotnikOutput> robotnicy;
    private Vector <SpekulantOutput> spekulanci;

    public FullOutput(Symulacja symulacja) {
        this.info = new InfoOutput(symulacja);
        this.robotnicy = new Vector <RobotnikOutput>();
        for (int i = 0; i < symulacja.podajGielda().podajRobotnicy().size(); i++)
            this.robotnicy.add(new RobotnikOutput(symulacja.podajGielda().podajRobotnicy().get(i)));
        this.spekulanci = new Vector <SpekulantOutput>();
        for (int i = 0; i < symulacja.podajGielda().podajSpekulanci().size(); i++)
            this.spekulanci.add(new SpekulantOutput(symulacja.podajGielda().podajSpekulanci().get(i)));
    }
}
