package test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CacheManager {

    HashSet<String> cache;
    int size;
    CacheReplacementPolicy crp;
    static IOSearcher searcher = new IOSearcher();

    public CacheManager(int size, CacheReplacementPolicy crp) {
        cache = new HashSet<>(size);
        this.size = size;
        this.crp = crp;
    }

    public boolean query(String word) {
        return cache.contains(word);
    }

    public void add(String word) {
        crp.add(word);
        cache.add(word);
        if (cache.size() > size)
            cache.remove(crp.remove());
    }

    static class ParIOSearcher {

        ExecutorService es;

        ParIOSearcher() {
            es = Executors.newCachedThreadPool();
        }

        public boolean search(String word, String... fileNames) {
            ArrayList<IOSearcher> searchers = new ArrayList<>();
            ArrayList<Future<Boolean>> fs = new ArrayList<>();
            System.out.println("in search");
            for (String fn : fileNames) {
                System.out.println("in for");
                IOSearcher s = new IOSearcher();
                searchers.add(s);
                System.out.println("after first add");
                fs.add(es.submit(() -> {
                    boolean found = searcher.search(word, fn);
                    System.out.println("after found");
                    if (found) {
                        searchers.forEach(srch -> srch.stop());
                    }
                    return found;
                }));
            }

            boolean found = false;
            for (Future<Boolean> f : fs) {
                try {
                    found |= f.get();
                } catch (InterruptedException | ExecutionException e) {
                }
            }

            return found;
        }

        public void stop() {
            es.shutdownNow();
        }

        @Override
        public void finalize() {
            es.shutdown();
        }


    }
}