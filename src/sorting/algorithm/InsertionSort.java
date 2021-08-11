package sorting.algorithm;

public class InsertionSort {
    private static int index = 1;

    public static boolean sort(Pixel[] pixels){
        if(index+1 > pixels.length){
                return true;
        }

            Pixel key = pixels[index];
            int j = index-1;
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && pixels[j].id > key.id) {
                pixels[j + 1] = pixels[j];
                j = j - 1;
            }
            pixels[j + 1] = key;
            index++;
        return false;
    }
}
