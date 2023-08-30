import java.util.ArrayList;
import java.util.stream.IntStream;

public class test {
	public static boolean checkif(ArrayList<Integer>a,int target) {
		for(int i=0;i<a.size();i++) {
			if((int)a.get(i)==target) 
				return true;
			}
		
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		final Integer[] strArr = {(-1),1,1};
//		int[] sortedIndices = IntStream.range(0, strArr.length)
//		                .boxed().sorted((i, j) -> -strArr[i].compareTo(-strArr[j]) )
//		                .mapToInt(ele -> ele).toArray();
//		for (int i=0;i<sortedIndices.length;i++) {
//			System.out.println(sortedIndices[i]);
//		}
		
		for(int i=4;i<=4;i++)
			System.out.println("yes");
		
		 ArrayList<Integer>waitline=new ArrayList<Integer>();
		for(int i =1;i<=1;i++)
			waitline.add(i);
		System.out.print(checkif(waitline,1));
	}

}
