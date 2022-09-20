import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {
    
    
    private static final Integer ASC = 0;
    private static final Integer DESC = 1;
    
    

    /*
     * Complete the 'fetchItemsToDisplay' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_STRING_ARRAY items
     *  2. INTEGER sortParameter
     *  3. INTEGER sortOrder
     *  4. INTEGER itemsPerPage
     *  5. INTEGER pageNumber
     */

    public static List<String> fetchItemsToDisplay(List<List<String>> items, int sortParameter, int sortOrder, int itemsPerPage, int pageNumber){        
        
        // Write your code here
        List<String> result = new ArrayList<String>();
        //list no empty?
        if (items.size() > 0) {
            List<String> item = items.get(0);
            //exist parameter?
            if ((sortParameter >= 0) && (sortParameter < item.size())) {
                //is asc or desc?
                if ((sortOrder == ASC) || (sortOrder == DESC)) {
                    if (sortOrder == ASC) {
                        Comparator<List<String>> naturalComparator = new Comparator<List<String>>() {
                            @Override
                            public int compare(List<String> i1, List<String> i2) {
                                
                                String value_1 = i1.get(sortParameter);
                                String value_2 = i2.get(sortParameter);
                                
                                Scanner in_1=new Scanner(value_1);
                                Scanner in_2=new Scanner(value_2);
                                
                                if (in_1.hasNextInt() && in_2.hasNextInt()) {
                                    
                                    Integer int_1 = 0;
                                    Integer int_2 = 0;
                                    
                                    try {
                                        int_1 = Integer.parseInt(value_1);
                                        int_2 = Integer.parseInt(value_2);
                                    } catch (Exception e) {}
                                    
                                    return int_1 - int_2;
                                    
                                } else {
                                    return value_1.compareTo(value_2);
                                }
                                
                            }
                        }; 
                        result = items.stream().sorted(naturalComparator).map(e->e.get(0)).collect(Collectors.toList());
                    } else {
                        Comparator<List<String>> reverseComparator = new Comparator<List<String>>() {
                            @Override
                            public int compare(List<String> i1, List<String> i2) {
                                
                                String value_1 = i1.get(sortParameter);
                                String value_2 = i2.get(sortParameter);
                                
                                Scanner in_1=new Scanner(value_1);
                                Scanner in_2=new Scanner(value_2);
                                
                                if (in_1.hasNextInt() && in_2.hasNextInt()) {
                                    
                                    Integer int_1 = 0;
                                    Integer int_2 = 0;
                                    
                                    try {
                                        int_1 = Integer.parseInt(value_1);
                                        int_2 = Integer.parseInt(value_2);
                                    } catch (Exception e) {}
                                    
                                    return int_2 - int_1;
                                    
                                } else {
                                    return value_2.compareTo(value_1);
                                }
                                
                                
                            }
                        }; 
                        result = items.stream().sorted(reverseComparator).map(e->e.get(0)).collect(Collectors.toList());                      
                    }    
                    if (itemsPerPage <= result.size()) {
                        int numPages = result.size() / itemsPerPage;                        
                        if (result.size() % itemsPerPage != 0) {
                            numPages++;
                        }
                        if (pageNumber >= 0 && pageNumber < numPages) {
                            if (pageNumber == (numPages-1)) {
                                result = result.subList(pageNumber*itemsPerPage, result.size());
                            } else {
                                result = result.subList(pageNumber*itemsPerPage, (pageNumber+1)*itemsPerPage);
                            }
                        }
                    }
                                        
                }
            }
        }
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
	
	int itemsRows = 4;
	int itemsColumns = 3;

        

        List<List<String>> items = new ArrayList<>();
        
        List<String> elem_1 = new ArrayList<>();
        elem_1.add("owjevtkuyv");
        elem_1.add("58584272");
        elem_1.add("62930912");
        items.add(elem_1);
        
        List<String> elem_2 = new ArrayList<>();
        elem_2.add("rpaqgbjxik");
        elem_2.add("9425650");
        elem_2.add("96088250");
        items.add(elem_2);
        
        List<String> elem_3 = new ArrayList<>();
        elem_3.add("dfbkasyqcn");
        elem_3.add("37469674");
        elem_3.add("46363902");
        items.add(elem_3);
        
        List<String> elem_4 = new ArrayList<>();
        elem_4.add("vjrrisdfxe");
        elem_4.add("18666489");
        elem_4.add("88046739");
        items.add(elem_4);
        
        int sortParameter = 2;
        int sortOrder = 1;
        int itemsPerPage = 2;
        int pageNumber = 0;

        List<String> result = Result.fetchItemsToDisplay(items, sortParameter, sortOrder, itemsPerPage, pageNumber);
        System.out.println("result " +  result);

       
    }
}
