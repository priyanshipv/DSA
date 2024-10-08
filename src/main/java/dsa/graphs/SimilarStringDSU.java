package dsa.graphs;

class DSU{
    int[] parent;
    int[] rank;

    public DSU(int size){
        parent = new int[size];
        for(int i=0; i<size; i++)
            parent[i]=i;
        rank = new int[size];
    }

    public int find(int x){
        if(parent[x]!=x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y){
        int parentX= find(x);
        int parentY = find(y);
        if(parentX==parentY){
            return;
        }
        else if(rank[parentX]> rank[parentY]){
            parent[parentY]= parentX;
        }
        else{
            parent[parentY] = parentX;
            rank[parentX]++;
        }
    }
}
class SimilarStringDSU {
    public boolean isSimilar(String a, String b){
        int diff=0;
        for(int i=0; i< a.length(); i++){
            if(a.charAt(i)!= b.charAt(i)){
                diff++;
            }
        }
        return diff==0 || diff==2;
    }
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        DSU dsu = new DSU(n);
        int count = n;

        for(int i=0; i< n ; i++){
            for(int j=i+1; j<n; j++){
                if (isSimilar(strs[i], strs[j]) && dsu.find(i) != dsu.find(j)) {
                    count --;
                    dsu.union(i, j);
                }
            }
        }
        return count;
    }
}