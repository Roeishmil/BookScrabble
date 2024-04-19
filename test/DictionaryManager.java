package test;

import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {

    private Map<String, Dictionary> dictionaryMap;
    private static DictionaryManager manager;

    private DictionaryManager() {
        this.dictionaryMap = new HashMap<>();
    }

    public static DictionaryManager get() {
        if (manager == null)
            manager = new DictionaryManager();
        return manager;
    }

    public boolean query(String... args) {
        boolean ret = false;
        String word = args[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            String fn = args[i];
            if (!this.dictionaryMap.containsKey(fn)) {
                Dictionary dic = new Dictionary(fn);
                this.dictionaryMap.put(fn, dic);
            }
        }
        for (Dictionary value : dictionaryMap.values()) {
            if (value.query(word)) {
                ret = true;
                break; // Exit the loop as soon as a match is found
            }
        }
        return ret;
    }

    public boolean challenge(String... args) {
        boolean ret = false;
        String word = args[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            String fn = args[i];
            if (!this.dictionaryMap.containsKey(fn)) {
                Dictionary dictionary = new Dictionary(fn);
                this.dictionaryMap.put(fn, dictionary);
            }
        }
        for (Dictionary dict : dictionaryMap.values()) {
            if (dict.challenge(word)) {
                ret = true;
                break; // Exit the loop as soon as a match is found
            }
        }
        return ret;
    }

    public int getSize() {
        return this.dictionaryMap.size();
    }
}