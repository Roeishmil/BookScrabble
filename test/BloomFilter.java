package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

public class BloomFilter {
    private final String[] algs;
    private final BitSet bitSet;
    private final int size;

    public BloomFilter(int size, String... algs) {
        this.algs = algs;
        this.size = size;
        this.bitSet = new BitSet(size);
    }

    public void add(String word) {
        for (String alg : algs) { // Loop through each hash function
            try {
                MessageDigest md = MessageDigest.getInstance(alg); // Get message digest for algorithm
                byte[] bts = md.digest(word.getBytes()); // Hash the word
                BigInteger bi = new BigInteger(bts); // Convert hash to integer index
                int i = Math.abs(bi.intValue()) % size;
                bitSet.set(i); // Set bit at index in bitset
            } catch (NoSuchAlgorithmException e) {
                // Ignore the exception
            }
        }
    }

    public boolean contains(String word) {
        for (String alg : algs) { // Loop through each hash function 
            try {
                MessageDigest md = MessageDigest.getInstance(alg); // Get message digest 
                byte[] bts = md.digest(word.getBytes()); // Hash the word
                BigInteger bi = new BigInteger(bts); // Convert hash to integer index
                int i = Math.abs(bi.intValue()) % size;
                if (!bitSet.get(i)) { // Return false if bit not set
                    return false;
                }
            } catch (NoSuchAlgorithmException e) {
                // Ignore the exception
            }
        }

        return true; // All bits were set, so return true
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(); // Build string from bits
        for (int i = 0; i < bitSet.length(); i++) {
            stringBuilder.append(bitSet.get(i) ? "1" : "0");
        }
        return stringBuilder.toString();
    }
}