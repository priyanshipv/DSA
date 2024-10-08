package dsa.general;

public class Trie
{
    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        String[] dictionary = { "pirate", "king", "leetcode", "cheat", "sheet" };
        for (String word : dictionary) insert(root, word);

        System.out.println("=== check whether words exist in the trie ===");
        String[] searchWords = { "pirate", "king", "leetcode", "cheat", "sheet", "abc", "apple", "faang", "google" };
        for (String word : searchWords) {
            System.out.println(word + " exists: " + search(root, word));
        }

        System.out.println("=== check if there's any word that starts with the given prefix in the trie ===");

        String[] startsWithWords = { "p", "kin", "leet", "cheeze", "shell", "faang" };
        for (String prefix : startsWithWords) {
            System.out.println("prefix " + prefix + ": " + startsWith(root, prefix));
        }
    }
    static class TrieNode
    {
        public boolean isValidWord;
        public TrieNode[] nodes;
        public TrieNode()
        {
            isValidWord = false;
            nodes = new TrieNode[26];
        }
    }

    //Insert a word into trie
    static void insert(TrieNode root, String word){
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.nodes[index] == null) {
                current.nodes[index] = new TrieNode();
            }
            current = current.nodes[index];
        }
        current.isValidWord = true;
    }

    //Returns if the word is in trie
    static boolean search(TrieNode root, String word)
    {
        TrieNode current = root;
        for (char c : word.toCharArray())
        {
            int index = c - 'a';
            if (current.nodes[index] == null)
            {
                return false;
            }
            current = current.nodes[index];
        }
        return current.isValidWord;
    }

    //Returns if there is any word in the trie that starts with the given prefix
    static boolean startsWith(TrieNode root, String prefix)
    {
        TrieNode current = root;
        for (char c : prefix.toCharArray())
        {
            int index = c - 'a';
            if (current.nodes[index] == null)
            {
                return false;
            }
            current = current.nodes[index];
        }
        return true;
    }
}
