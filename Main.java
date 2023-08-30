import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class Main{
	public static int checkstop2(ArrayList<String> stop,String s) {
		for(int i=0;i<stop.size();i++) {
		if(s.equals(stop.get(i)))
			return i;
		}
		return -1;
	}
	
	public static boolean checkstop(ArrayList<String> stop,String s) {
		for(int i=0;i<stop.size();i++) {
		if(s.equals(stop.get(i)))
			return true;
		}
		return false;
	}
	
	public static int check(ArrayList<ArrayList<String>> category,String s) {
		for(int i=0;i<category.size();i++) {
			if(category.get(i).size()>0) {
			if(s.compareTo(category.get(i).get(0))==0) {
				return i;
			}
			}
		}
		return -1;
	}
	
	public static void main(String[] args)  {
   	 Scanner sc = new Scanner (System.in);
 // initialize
  File training = new File("src/FileInput");
  File stop = new File("src/FileInput2");
  int num = sc.nextInt();
  int count = 0 ;  
  int indi = 0;
  
  ArrayList<ArrayList<String>> rec = new ArrayList<>();
  ArrayList<String> stopword = new ArrayList<String>();
  ArrayList<String> finalword = new ArrayList<String>();
  ArrayList<ArrayList<String>> category = new ArrayList<>();
  
  ArrayList<String> nameword = new ArrayList<String>();//name list 
  ArrayList<String>  catefinal = new ArrayList<String>();//category list
  
  ArrayList<ArrayList<String>> recfinal = new ArrayList<>();//final list of words
  Set<String> finalwordlist = new HashSet<>(); //list without repetition
  //construct stop word list
  try {

	  String strCurrentLine;
	  BufferedReader ss = new BufferedReader(new FileReader(stop));
	  while ((strCurrentLine = ss.readLine()) != null) {
		  String stopw = "";
		  for(int i=0;i<strCurrentLine.length();i++) {
			  if(strCurrentLine.charAt(i)!=' ')
				  stopw += Character.toString(strCurrentLine.charAt(i));
			  else {
				  stopword.add(stopw);stopw="";
			  }
			  
			  if(i==strCurrentLine.length()-1&&stopw!=""){
				  stopword.add(stopw);stopw="";
			  }
		  }
	  }	 
	  ss.close();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
  //end of construct stop word list

  
  try {
	   String strCurrentLine;

	   BufferedReader sd = new BufferedReader(new FileReader(training));
	   ArrayList<String> temp = new ArrayList<String>();
	   ArrayList<String> tempfinal = new ArrayList<String>();
	   while ((strCurrentLine = sd.readLine()) != null) {
		
	    if(!strCurrentLine.isEmpty()) {
	    
	    if(indi==0) {
	    indi = 1;
	    }
	    	    	   
	    if(count<num) {
	    //System.out.println(strCurrentLine.toLowerCase());
	    temp.add(strCurrentLine.toLowerCase());
	    String sl = strCurrentLine.toLowerCase();
	    String finalw = "";
	   
		  for(int ii=0;ii<sl.length();ii++) {
			  if(Character.isAlphabetic(sl.charAt(ii)))
				  finalw += Character.toString(sl.charAt(ii));
			  else {
				  finalw = finalw.replaceAll("\\s", "");
				  if(finalw.length()>2&&!checkstop(stopword,finalw))
				  tempfinal.add(finalw);finalw="";
			  }
			  
			  if(ii==sl.length()-1&&finalw!=""){
				  if(finalw.length()>2&&!checkstop(stopword,finalw))
				  tempfinal.add(finalw);finalw="";
			  }
		  }

	    }
	    }
	    else {
	    	if(indi == 1) {
	    		indi =0;
	    		count++;
	    		
	    		if(temp.size()>0) {
	    			//get name and put it in the stop word list
	    			if(temp.get(0).length()>1) {
	    				String name="";
	    				String po = temp.get(0);
	    				for(int ii=0;ii<po.length();ii++) {
	    					if(Character.isAlphabetic(po.charAt(ii)))
	    						  name += Character.toString(po.charAt(ii));
	    					  else {
	    						  nameword.add(name);
	    						  stopword.add(name);name="";
	    					  }
	    					  
	    					  if(ii==po.length()-1&&name!=""){	    				
	    						  stopword.add(name);
	    						  nameword.add(name);name="";
	    					  }	    						     						 
	    				}
	    			}
	    		}
	    		//end of getting name and put it in the stop word list	    		
	    		if(temp.size()>1) {	    			
	    		String target=temp.get(1).replaceAll("\\s", "");
	    		int result=check(category,target);
	    		catefinal.add(target);
	    		if(result!=-1) {
	    			 ArrayList<String> temp2 = new ArrayList<String>();
	    			 temp2.add(target);
	    			 int df = Integer.parseInt(category.get(result).get(1))+1;
	    			 String fg = Integer.toString(df);
	    			 temp2.add(fg);
	    			category.set(result, temp2);
	    		}
	    		else {
	    			ArrayList<String> temp2 = new ArrayList<String>();
	    			temp2.add(target);
	    			temp2.add("1");
	    			category.add(temp2);
	    		}
	    		temp.set(1, "");
	    		}
	    		if(temp.size()>0)
	    		rec.add(temp);	    		
	    		temp = new ArrayList<String>();
	    			  for(int j=0;j<tempfinal.size();j++) {
	    				  if(!checkstop(nameword,tempfinal.get(j))) {
	    					 tempfinal.set(j, "42");
	    					  break;
	    				  }  
	    			  }	    		
	    		if(tempfinal.size()>0)
	    		recfinal.add(tempfinal);
	    		tempfinal = new ArrayList<String>();	    		
	    	}
	    }	    	    	    	   
	   }
	   sd.close();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
 
  for(int i=0;i<rec.size();i++) {
	  ArrayList<String>rt = rec.get(i);
	  for(int j=0;j<rt.size();j++) {
		  String sl = rt.get(j);
		  
		  
		  String finalw = "";
		  for(int ii=0;ii<sl.length();ii++) {
			  if(Character.isAlphabetic(sl.charAt(ii)))
				  finalw += Character.toString(sl.charAt(ii));
			  else {
				  finalw = finalw.replaceAll("\\s", "");
				  if(finalw.length()>2&&!checkstop(stopword,finalw))
				  finalword.add(finalw);finalw="";
			  }
			  
			  if(ii==sl.length()-1&&finalw!=""){
				  if(finalw.length()>2&&!checkstop(stopword,finalw))
				  finalword.add(finalw);finalw="";
			  }
		  }
		  
		  
		  
		  
		  
		  
	  }
  }
 
  
//finish construction of recfinal(list of lists) and finalword(list without names & category)
  for(String s:finalword) {
	  finalwordlist.add(s);
  }
 
  int ck=0;
 // int [] rount1 =new int[finalwordlist.size()];
  //start to count
ArrayList<ArrayList<Integer>> tempe = new ArrayList<>();

  for(String s:finalwordlist) {
	  ArrayList<Integer> tempe2 = new ArrayList<Integer>();
	  int [] rount =new int[category.size()];
	  
	  for(int i=0;i<recfinal.size();i++) {	//here recfinal corresponds to the sequence of catefinal
		  ArrayList<String>tempfin = recfinal.get(i);
		  for(int z =0;z<tempfin.size();z++) {
			  if(s.equals(tempfin.get(z))&&ck==0) {
				  ck=1;
				 int mark = check(category, catefinal.get(i));
				 rount[mark]+=1;
			  }
		  if(z==tempfin.size()-1)
			  ck=0;
		  }
	  }

	  for(int ii=0;ii<category.size();ii++)
		  tempe2.add(rount[ii]);
	  
	  tempe.add(tempe2);
  }
ArrayList<String> fwl = new ArrayList<>(); //final word list finalized 
for(String ss: finalwordlist) {
	fwl.add(ss);
}
//  System.out.println(finalwordlist);  	 
//   	 for(int i=0;i<tempe.size();i++)
//   		System.out.print(tempe.get(i)+" ");  	 
//  	System.out.println(tempe.get(17));
//   	System.out.println();
//   	for(int i=0;i<fwl.size();i++) {
//   		System.out.println(fwl.get(i)+" "+tempe.get(i));
//   	}


ArrayList<Double> freqC = new ArrayList<>();
for(int i=0;i<category.size();i++) {
	double freqc = Double.parseDouble(category.get(i).get(1))/(double)num;
	double pc = (freqc+0.1)/(double)(1+0.1*category.size());
	double lc = -Math.log10(pc);
	freqC.add(lc);
}







ArrayList<ArrayList<Double>> freqT = new ArrayList<>();
	for(int i=0;i<tempe.size();i++) {
		ArrayList<Double> tempv = new ArrayList<>();
		
		for(int j=0;j<tempe.get(i).size();j++) {
		//	double freqc = Double.parseDouble(category.get(i).get(1))/(double)num;
			double freqt =(double)tempe.get(i).get(j)/Double.parseDouble(category.get(j).get(1));
			double pwc = (freqt+0.1)/1.2;
			double lwc = -Math.log10(pwc);
			
			tempv.add(lwc);
		}
		freqT.add(tempv);
	}
   	 //freqT is defined as the name shows 
	//tempe is defined as Occ(W|C)
//	System.out.println(category); 
//	System.out.println(recfinal); 
	
	
	  int count2 = 0 ;  
	  int indi2 = 0;
	  
	  ArrayList<ArrayList<String>> rec2 = new ArrayList<>();

	  ArrayList<String> finalname = new ArrayList<String>();
	  ArrayList<ArrayList<String>> category2 = new ArrayList<>();
	  
	  ArrayList<String> nameword2 = new ArrayList<String>();//name list 
	  ArrayList<String>  catefinal2 = new ArrayList<String>();//category list,used to compare the ans
	  ArrayList<String>  ans = new ArrayList<String>();//stores the answer
	  ArrayList<ArrayList<String>> recfinal2 = new ArrayList<>();//final list of words
//	  Set<String> finalwordlist = new HashSet<>(); //list without repetition
	  //construct stop word list
	//test case 
	  try {
		   String strCurrentLine;

		   BufferedReader sd = new BufferedReader(new FileReader(training));
		   ArrayList<String> temp = new ArrayList<String>();
		   ArrayList<String> tempfinal = new ArrayList<String>();
		   while ((strCurrentLine = sd.readLine()) != null) {			
		    if(!strCurrentLine.isEmpty()) {		    
		    if(indi2==0) {
		    indi2 = 1;
		    }
		    	    	   
		    if(count2>=num) {
		    //System.out.println(strCurrentLine.toLowerCase());
		    temp.add(strCurrentLine.toLowerCase());
		    String sl = strCurrentLine.toLowerCase();
		    String finalw = "";
		   
			  for(int ii=0;ii<sl.length();ii++) {
				  if(Character.isAlphabetic(sl.charAt(ii)))
					  finalw += Character.toString(sl.charAt(ii));
				  else {
					  finalw = finalw.replaceAll("\\s", "");
					  if(finalw.length()>2&&!checkstop(stopword,finalw)&&checkstop(fwl,finalw))
					  tempfinal.add(finalw);finalw="";
				  }
				  
				  if(ii==sl.length()-1&&finalw!=""){
					  if(finalw.length()>2&&!checkstop(stopword,finalw)&&checkstop(fwl,finalw))
					  tempfinal.add(finalw);finalw="";
				  }
			  }
			//  System.out.println(tempfinal);
		    }
		    }
		    else {
		    	if(indi2 == 1) {
		    		indi2 =0;
		    		count2++;
		    		
		    		if(temp.size()>0) {
		    			//get name and put it in the stop word list
		    			if(temp.get(0).length()>1) {
		    				String name="";
		    				String po = temp.get(0);
		    				finalname.add(po);
		    				for(int ii=0;ii<po.length();ii++) {
		    					if(Character.isAlphabetic(po.charAt(ii)))
		    						  name += Character.toString(po.charAt(ii));
		    					  else {
		    						  nameword2.add(name);
		    						  stopword.add(name);name="";
		    					  }
		    					  
		    					  if(ii==po.length()-1&&name!=""){	    				
		    						  stopword.add(name);
		    						  nameword2.add(name);name="";
		    					  }	    						     						 
		    				}
		    			}
		    		}
		    		//end of getting name and put it in the stop word list	    		
		    		if(temp.size()>1) {	    			
		    		String target=temp.get(1).replaceAll("\\s", "");
		    		int result=check(category2,target);
		    		catefinal2.add(target);
		    		if(result!=-1) {
		    			 ArrayList<String> temp2 = new ArrayList<String>();
		    			 temp2.add(target);
		    			 int df = Integer.parseInt(category2.get(result).get(1))+1;
		    			 String fg = Integer.toString(df);
		    			 temp2.add(fg);
		    			category2.set(result, temp2);
		    		}
		    		else {
		    			ArrayList<String> temp2 = new ArrayList<String>();
		    			temp2.add(target);
		    			temp2.add("1");
		    			category2.add(temp2);
		    		}
		    		temp.set(1, "");
		    		}
		    		if(temp.size()>0)
		    		rec2.add(temp);	    		
		    		temp = new ArrayList<String>();
		    			  for(int j=0;j<tempfinal.size();j++) {
		    				  if(!checkstop(nameword2,tempfinal.get(j))) {
		    					 tempfinal.set(j, "42");
		    					  break;
		    				  }  
		    			  }	    		
		    		if(tempfinal.size()>0)
		    		recfinal2.add(tempfinal);
		    		
		    		
		    		tempfinal = new ArrayList<String>();	    		
		    	}
		    }	    	    	    	   
		   }
recfinal2.add(tempfinal);//finalname.add(temp.get(0));
		   
		   
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
	  
	  
	//  System.out.println(rec2);
	
//	  System.out.println(recfinal2); 
//	  System.out.println(freqC); 
//	  System.out.println(freqT); 
	 
	  ArrayList<ArrayList<Double>> lclist = new ArrayList<>();
	  ArrayList<ArrayList<Double>> lclistans = new ArrayList<>();
	//	  for(int j=0;)
		  for(int j=0;j<recfinal2.size();j++) {
			  ArrayList<String>sss = recfinal2.get(j);
			  ArrayList<Double> tp = new ArrayList<>();
			 for(int i=0;i<category.size();i++) {
				  double lbc = freqC.get(i);
				 for(int z=0;z<sss.size();z++) {
					 String op = sss.get(z);
					 if(op.length()>2) {
						 int indices = checkstop2(fwl,op);
						 lbc+=freqT.get(indices).get(i);					 
					 }
				 }
				 tp.add(lbc);
			 }
			 lclist.add(tp);
		  }
//
//		  System.out.println(catefinal2); 
	
		  for(int i=0;i<lclist.size();i++) {
			  double min = 10000.0;
			  int ind = -1;
			  for(int j=0;j<lclist.get(i).size();j++) {
				  if(lclist.get(i).get(j)<min) {
					  min = lclist.get(i).get(j);
					  ind = j;
				  }
			  }
			  ans.add(category.get(ind).get(0));
		  }
	
		  ArrayList<ArrayList<Double>> lclistans2 = new ArrayList<>();
		  for(int i=0;i<lclist.size();i++) {
			  ArrayList<Double>temp = lclist.get(i);
			  double min = 10000.0;
			  for(int j=0;j<temp.size();j++) {
				  if(temp.get(j)<min)
					  min=temp.get(j);
			  }
			  ArrayList<Double>xi = new ArrayList<>();
			  for(int j=0;j<temp.size();j++) {
				  if(temp.get(j)-min<7.0) {
					  xi.add(Math.pow(10, min-temp.get(j)));
				  }
				  else {
					  xi.add(0.0);
				  }
			  }
			  lclistans2.add(xi);
		  }
		//  DecimalFormat df = new DecimalFormat("#.##"); 
		  for(int i=0;i<lclistans2.size();i++) {
			  ArrayList<Double>temp = lclistans2.get(i);
			  double sum=0;ArrayList<Double>xi = new ArrayList<>();
			  for(int j=0;j<temp.size();j++) {
				  sum+=temp.get(j);
			  }
			  for(int j=0;j<temp.size();j++) {
				  double val = temp.get(j)/sum;
				  val = val*100;
				  val = Math.round(val);
				  val = val /100;
				  xi.add(val);
			  }
			  lclistans.add(xi);
		  }
		  
		  
		  
//		  System.out.println(lclist); 
//	
//	
//	System.out.println(category2);
	int sum=0;
	int right=0;
	for(int i=0;i<finalname.size();i++) {
		boolean y = ans.get(i).equals(catefinal2.get(i));
		System.out.println(finalname.get(i) +"  "+" Predictions: "+ ans.get(i)+" "+"Results: "+y);
		for(int ii=0;ii<category.size();ii++)
			System.out.print(category.get(ii).get(0)+" ");
		
		System.out.println();
		System.out.println(lclistans.get(i));
		sum++;
		if(y==true)
			right++;
		if(i==finalname.size()-1)
			System.out.println("ACCURACY: "+ (double)right/(double)sum);
		
	}
	
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    sc.close();   	
	}
		
	}
