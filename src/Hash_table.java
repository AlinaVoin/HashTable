import java.util.*;

public class Hash_table<T> implements Set<T> {

    ArrayList<T> data = new ArrayList<T>();
    int total = 0;
    int size = 47;

    static class HashIterator<T> implements Iterator<T>{

        int pos = 0;
        Hash_table<T> master = null;
        public HashIterator(Hash_table<T> ht){
            master = ht;
        }

        @Override
        public boolean hasNext() {
            if (master.total == 0) return false;
            pos++;
            while(pos < master.data.size() && master.data.get(pos) == null){
                pos++;
            }
            return pos < master.data.size();
        }

        @Override
        public T next() {
            if (pos < 0 || pos >= master.data.size()){
                throw new NoSuchElementException();
            }
            else return master.data.get(pos);
        }

        @Override
        public void remove() {
            while(pos > 0 && master.data.get(pos)==null){
                pos--;
            }
            if (pos < 0) throw new IllegalStateException();
            master.data.set(pos, null);
            master.total--;
        }
    }

    public Hash_table(){
        for (int i =0; i< size; i++){
            data.add(null);
        }
    }

    private int hash2(Object o){
        return Math.abs(o.hashCode()) % data.size();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i =0; i < data.size(); i++){
            if (data.get(i) == null) continue;
            sb.append("#");
            sb.append(i);
            sb.append(":");
            sb.append(data.get(i).toString());
            sb.append("  ");
        }
        return sb.toString();
    }

    public int size() {
        return total;
    }

    public boolean isEmpty() {
        return total == 0;
    }

    public boolean contains(Object o) {
        int h = Math.abs(o.hashCode()) % data.size();
        if (data.get(h) == null) return false;
        if (data.get(h).equals(o)) return true;
        int step = hash2(o);
        int i = h + step;
        while(i != h){
            if (data.get(i) == null) return false;
            if (data.get(i).equals(o)) return true;
            i = (i + step) % data.size();
        }
        return false;
    }

    public Iterator<T> iterator() {
        return new HashIterator<T>(this);
    }

    public Object[] toArray() {
        Object[] ar = new Object[total];
        int count = 0;
        for (T datEl : data) {
            if (datEl != null) {
                ar[count] = datEl;
                count++;
            }
        }
        return ar;
    }

    public <T1> T1[] toArray(T1[] t1s) {
        int count = 0;
        if (t1s.length <= total){
            for (T datEl : data) {
                if (datEl != null) {
                    t1s[count] = (T1) datEl;
                    count++;
                }
            }
            return t1s;
        }
        else{
            T1[] newAr = (T1[]) new Object[total];
            for (T datEl : data) {
                if (datEl != null) {
                    newAr[count] = (T1) datEl;
                    count++;
                }
            }
            return newAr;
        }
    }
    public int Eratosfen(int n) {
        boolean[] primes = new boolean[n+1];

        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 2; i < primes.length; i++) {
            if (primes[i]) {
                for (int j = 2; i * j < primes.length; ++j) {
                    primes[i * j] = false;

                }
            }
        }
        for (int i = n; i>0; i--){
            if (primes[i]) return i;
        }
        return -1;
    }

    public void reHash(T t){
        int temp = size*2;
        size = Eratosfen(temp);

        ArrayList<T> newData = new ArrayList<>();
        for (int i = 0; i < size; i++){
            newData.add(null);
        }
        newData.addAll(data);
        data = newData;
        data.add(t);
    }

    public boolean add(T t) {
        if (total == data.size()){
            reHash(t);
            return true;
        }
        int h = Math.abs(t.hashCode()) % data.size();
        if (data.get(h) == null){
            data.set(h, t);
            total++;
            return true;
        }
        if (data.get(h).equals(t)) return false;
        int st = Math.abs(t.hashCode()) % data.size();
        int i = h + st;
        while(i != h){
            if (data.get(i) == null){
                data.set(i, t);
                total++;
                return true;
            }
            if (data.get(i).equals(t)) return false;
            i = (i + st) % data.size();
        }
        return false;
    }

    public boolean remove(Object o) {
        if (o == null) return false;
        int h = Math.abs(o.hashCode()) % data.size();
        if (data.get(h) == null) return false;
        if (data.get(h).equals(o)){
            data.set(h, null);
            total--;
            return true;
        }
        int st = Math.abs(o.hashCode()) % data.size();
        int i = h + st;
        while(i != h){
            if (data.get(i) == null) return false;
            if (data.get(i).equals(o)){
                data.set(i, null);
                total--;
                return true;
            }
            i = (i + st) % data.size();
        }
        return false;
    }

    public boolean containsAll(Collection<?> collection){
        for (var elem: collection){
            if (!contains(elem)) return false;
        }
        return true;
    }

    public boolean addAll(Collection<? extends T> collection) {
        int oldSize = total;
        for (var elem: collection) {
            add(elem);
        }
        return total > oldSize;

    }

    public boolean retainAll(Collection<?> collection) {
        int oldSize = total;
        for (int i =0; i< data.size(); i++){
            if (data.get(i) == null) continue;
            if (!collection.contains(data.get(i))){
                data.set(i, null);
                total--;
            }
        }
        return total < oldSize;
    }

    public boolean removeAll(Collection<?> collection) {
        int oldSize = total;
        for (int i =0; i< data.size(); i++){
            if (data.get(i) == null) continue;
            if (collection.contains(data.get(i))){
                data.set(i, null);
                total--;
            }
        }
        return total < oldSize;
    }

    public void clear() {
        for (int i = 0; i < data.size(); i++){
            data.set(i, null);
        }
        total = 0;
    }
}