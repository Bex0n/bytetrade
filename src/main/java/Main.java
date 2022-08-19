import com.google.gson.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;

import Input.*;
import Symulacja.*;
import Output.*;
import Przedmioty.*;


public class Main {
    public static void main(String args[]) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FullInput map;

        try(FileReader reader = new FileReader(args[0])) {
            map = gson.fromJson(reader, FullInput.class);
        }

        Symulacja symulacja = Symulacja.stworz(map);

        try(FileWriter file = new FileWriter(args[1])) {
            gson.toJson(new FullOutput(symulacja), file);
        }
    }
}