package Model;

public class ArrayList<E> {
    private E[] containers = ((E[]) new Object[100]);
    private int size = 0;

    public ArrayList() {
    }

    public ArrayList(E[] containers) {
        this.containers = containers;
        size = containers.length;
        expand();
    }

    private void expand(){
        E[] temp = ((E[]) new Object[size * 5]);
        System.arraycopy(containers,0,temp,0,size);
        containers = temp;
    }

    public void add(E data){
        containers[size] = data;
        if (size >= containers.length - 26){
            expand();
        }
        size++;
    }

    public void insert(E data, int index){
        for (int i = size; i > index; i--){
            containers[i] = containers[i-1];
        }
        containers[index] = data;
        size++;

        if (size >= containers.length - 26){
            expand();
        }
    }

    public void remove(E value){
        for (int i = 0; i < size; i++){
            if (containers[i].equals(value)){
                remove(i);
                i--;
            }
        }
        size--;
    }

    public void remove(int index){
        for (int i = index; i < size; i++){
            containers[i] = containers[i + 1];
        }
        size--;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++){
            result.append(containers[i]);
            if (i < size - 1){
                result.append(", ");
            }
        }return result.append("]").toString();
    }

    public E get(int index){
        return containers[index];
    }

    public String getClassName(){
        if (containers[0] == null){
            return "Components";
        }
        return containers[0].getClass().getSimpleName();
    }

    public void set(int index, E data){
        containers[index] = data;
    }

    public E[] toArray(){
        E[] result = (E[]) new Object[size];
        System.arraycopy(containers , 0 , result , 0 , size);
        return result;
    }

}
