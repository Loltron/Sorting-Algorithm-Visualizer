package sorting.algorithm;

public class MasterSorter {

    public static boolean sort(Pixel[]pixels, SortingMethod method, int iterations){

        for (int i = 0; i< iterations; i++) {

            if (method == SortingMethod.BubbleSort) {
                return BubbleSort.sort(pixels);
            }
            else if (method == SortingMethod.SelectionSort) {
                return SelectionSort.sort(pixels);
            }
            else if (method == SortingMethod.InsertionSort) {
                return InsertionSort.sort(pixels);
            }
        }
        return isSorted(pixels);
    }

    public static boolean isSorted(Pixel[] pixels){
        for(int i = 0; i < pixels.length; i++){
            Pixel pixel = pixels[i];
            if(i != pixel.id) return false;
        }
        return true;
    }
}
