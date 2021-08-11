package sorting.algorithm;

public class SelectionSort {
    private static int index = 0; //current index of sort

    public static boolean sort(Pixel[] pixels){

        if(index+1 > pixels.length){
            return true;
        }

        int n = pixels.length;            //method to find length of array
             // int index = i;
            //int i =index;
            int min = index;          // taking the min element as ith element of array
            for (int j = index+1; j < n; j++)
            {
                if (pixels[j].id < pixels[min].id)
                {
                    min = j;
                    //min = pixels[j];
                }
            }
            Pixel t = pixels[min];         //Interchange the places of the elements
            pixels[min] = pixels[index];
            pixels[index] = t;
            index++;
        return false;
    }
}
