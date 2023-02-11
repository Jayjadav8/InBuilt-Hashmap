import java.util.*;
public class BuiltHashMap {

    static class JaiHashMap<K,V> {  // generics
            // dont know type of key and value -- int / boolean etc
            // so write general -- dont know now , but figure it out

            private class Node{
                K key;
                V value;

                public Node(K key, V value){
                    this.key = key;
                    this.value = value;
                }
            }
            // private -- access modifier
        private int n; // no.  of nodes
        private int N; // bucket
        private LinkedList<Node> buckets[]; // N = buckets.length
            // private int arr[] -- arr type integer
            // same bucket type -linkedlist of nodes

            @SuppressWarnings("unchecked") // to prevent from throwing warning

            public JaiHashMap(){
            
                this.N = 64;
                this.buckets = new LinkedList[N];
                for(int i=0;i<N;i++){
                this.buckets[i] = new LinkedList<>();}
                 }

                 // take the key and return bucket(index)
            private int hashFunction(K key){ // 0 to n-1
                int bi = key.hashCode(); // key.hashCode -- change key form and return integer
                return Math.abs(bi)% N; // making it positive
                // but always btw 0 to N-1 so %modulo
            }

            private int searchInLL(K key , int bi){
                LinkedList<Node> ll = buckets[bi]; // equal to ll store in bucket
                for(int i=0;i<ll.size();i++){
                    if(ll.get(i).key == key){

                        return i;
                    }
                }
                return -1;
            }
            @SuppressWarnings("unchecked") // to prevent from throwing warning

            private void rehash(){
                LinkedList<Node> oldBucket [] = buckets;
                // store previously in another

                buckets = new LinkedList[N*2]; // create new

                for(int i=0;i<N*2;i++){
                    buckets[i] = new LinkedList<>();
                } // create blank ll

                for(int i=0;i<oldBucket.length;i++){
                    LinkedList<Node> ll = oldBucket[i];

                    for(int j=0;j<ll.size();j++){
                        Node node = ll.get(j);
                        put(node.key , node.value);
                    }
                }
            }


            public void put(K key , V value){
                int bi = hashFunction(key);  // get index of array where store
                int di = searchInLL(key,bi); //pass key and find data index(of ll)

                if(di ==-1){
                    // key not exist
                    buckets[bi].add(new Node(key,value));
                    n++;
                }else{
                Node node =  buckets[bi].get(di);
                node.value = value;
                }

                double lambda = (double)n/N;
                if(lambda> 2.0){
                    // rehashing
                    rehash();
                }
            }

            public boolean containsKey(K key){
             // check key exist or not

                int bi = hashFunction(key);
                int di = searchInLL(key,bi); 

                if(di ==-1){
                  return false;
                }else{
                return true;
                }
            }


            public V get(K key){
              
                int bi = hashFunction(key); // bucket index
                int di = searchInLL(key,bi); // data index

                if(di ==-1){          
                    return null;
                
                }else{
                Node data =  buckets[bi].get(di);
                return data.value;
                }
            }


            public V remove(K key){
                int bi = hashFunction(key);
                int di = searchInLL(key,bi); 

                if(di ==-1){
                    return null;
                
                }else{  // remove value and return value
                Node data =  buckets[bi].remove(di);
                n--;
                return data.value;
                }
            }

            public boolean isEmpty(){
                    return n==0; // when no node is present
            }

            // place all keys in arraylist and returns
            public ArrayList<K> keySet(){
                ArrayList<K> keys = new ArrayList<>();
                
                for(int i=0;i<buckets.length;i++){  // bi
                    LinkedList<Node> ll = buckets[i];
                    for(int j=0;j<ll.size();j++){  // di
                        Node node = ll.get(j);
                        keys.add(node.key);
                    }
                }
                return keys;
            }



    }
    public static void main(String[] args) {

        JaiHashMap<String, Integer> map = new JaiHashMap<>();
        map.put("India",200);
        map.put("china",250);
        map.put("nepal",50);
        map.put("usa",150);
        
        // printing keys with values
        ArrayList<String> keys = map.keySet();
        for(int i=0;i<keys.size();i++){
            System.out.println(keys.get(i) +" " + map.get(keys.get(i)));
        }

        map.remove("china");
        System.out.println(map.get("china"));

    }}
    
   
    

