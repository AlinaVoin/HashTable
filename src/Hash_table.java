import java.util.*;

public class Hash_table<T> implements Set<T> {

    ArrayList<T> data = new ArrayList<T>();
    int total = 0;
    static final int INITIAL_CAPACITY = 47;

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
            if (pos < master.data.size()) return true;
            else return false;
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
        for (int i =0; i< INITIAL_CAPACITY; i++){
            data.add(null);
        }
    }


    private int hash2(Object o){
        return o.hashCode() % data.size();
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
        for (int i = 0; i< data.size(); i++){
            if (data.get(i)!= null){
                ar[count] = data.get(i);
                count++;
            }
        }
        return ar;
    }

    public <T1> T1[] toArray(T1[] t1s) {
        int count = 0;
        if (t1s.length <= total){
            for (int i = 0; i< data.size(); i++){
                if (data.get(i) != null){
                    t1s[count] = (T1) data.get(i);
                    count++;
                }
            }
            return t1s;
        }
        else{
            T1[] newAr = (T1[]) new Object[total];
            for (int i = 0; i< data.size(); i++){
                if (data.get(i)!= null){
                    newAr[count] = (T1) data.get(i);
                    count++;
                }
            }
            return newAr;
        }
    }

    public boolean add(T t) {
        if (total == data.size()) return false;
        int h = Math.abs(t.hashCode()) % data.size();
        if (data.get(h) == null){
            data.set(h, t);
            total++;
            return true;
        }
        if (data.get(h).equals(t)) return false;
        int step = hash2(t);
        int i = h + step;
        while(i != h){
            if (data.get(i) == null){
                data.set(i, t);
                total++;
                return true;
            }
            if (data.get(i).equals(t)) return false;
            i = (i + step) % data.size();
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
        int step = hash2(o);
        int i = h + step;
        while(i != h){
            if (data.get(i) == null) return false;
            if (data.get(i).equals(o)){
                data.set(i, null);
                total--;
                return true;
            }
            i = (i + step) % data.size();
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
        if (total > oldSize) return true;
        else return false;

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
        if (total < oldSize) return true;
        else return false;
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
        if (total < oldSize) return true;
        else return false;
    }

    public void clear() {
        for (int i = 0; i < data.size(); i++){
            data.set(i, null);
        }
        total = 0;
    }
}