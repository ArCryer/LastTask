package com.myproject;
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class ReaderCSVServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain;charset=UTF-16");
	
	String var2 = req.getParameter("var2");  
	String operation = req.getParameter("operation");
	String operation2 = req.getParameter("operation2");
    String result="Empty";
   
   
	String csvFile = "Structure.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ";";
    Integer i=1,b = 1;
    String[] firstline = null;
  
    try {
		 
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
            
		        // use ; as separator
			String[] output = line.split(cvsSplitBy);
			if (i==1){ 
				firstline=output;	
			}
			if (i==0){
			b = Integer.valueOf(output[0]);;
			}else{
				i=0;
			}
		}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	String[][] lines=new String[b][5];
	String[][] saved=new String[b][5];
	
	
	i=0;
	try {
 
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
            
		        // use ; as separator
			String[] output = line.split(cvsSplitBy);
            if (i>0){
            	i--;
            	lines[i][0]=output[0];
            	lines[i][1]=output[1];
            	lines[i][2]=output[2];
            	lines[i][3]=output[3];
            	lines[i][4]=output[4];
            	//System.out.println(lines[i][0]+" "+lines[i][1]+" "+lines[i][2]+" "
            	//		+lines[i][3]+" "+lines[i][4]);
            	i++;
            	i++;
            } else {
            	i=1;
            	
            }
			
 
		}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	if(operation.equals("0")){
		i=0;
		resp.getWriter().println("<html><body><table border=1 align=center><tr><td>"+firstline[0]+"</td><td>"+firstline[1]+"</td><td>"+firstline[2]+"</td><td>"
    			+firstline[3]+"</td><td>"+firstline[4]+"</td></tr></body></html>");
	    for(int j = 0 ; j < (lines.length); j++){
	  resp.getWriter().println("<html><body><tr><td>"+lines[j][0]+"</td><td>"+lines[j][1]+"</td><td>"+lines[j][2]+"</td><td>"
	          		+lines[j][3]+"</td><td>"+lines[j][4]+"</td></tr></body></html>");
	    	
	    	
	 
	    }
	}
	saved=lines;
	if((operation.equals("1")) || (operation.equals("2"))){
		for(int j = (lines.length-1) ; j > 0 ; j--){
	        for(int k = 0 ; k < j ; k++){
	           String f,g;
	           f=lines[k][2];
	           g=lines[k+1][2];
	           char ch=g.charAt(0);
	           if (ch=='"'){g=g.substring(1, g.length());};
	           //resp.getWriter().println(g);
	           ch=f.charAt(0);
	           if (ch=='"'){f=f.substring(1, f.length());};
	           //resp.getWriter().println(f);
	            if( f.compareTo(g)>0 ){
	                float tmp = new Float(lines[k][3]);
	                lines[k][3] = lines[k+1][3];
	                lines[k+1][3] = String.valueOf(tmp);
	                tmp = new Float(lines[k][4]);
	                lines[k][4] = lines[k+1][4];
	                lines[k+1][4] = String.valueOf(tmp);
	                Integer tmp1 = new Integer(lines[k][0]);
	                lines[k][0] = lines[k+1][0];
	                lines[k+1][0] = Integer.toString(tmp1);
	                String tmp2 = lines[k][1];
	                lines[k][1] = lines[k+1][1];
	                lines[k+1][1] = tmp2;
	                tmp2 = lines[k][2];
	                lines[k][2] = lines[k+1][2];
	                lines[k+1][2] = tmp2;
	            }
	        }
	    }	
		 i=0;
		    if(operation.equals("2")){
		    for(int q = lines.length ; q > -1 ; q--){
		    if (i>0){
		    	
		    	resp.getWriter().println("<html><body><tr><td>"+lines[q][0]+"</td><td>"+lines[q][1]+"</td><td>"+lines[q][2]+"</td><td>"
		          		+lines[q][3]+"</td><td>"+lines[q][4]+"</td></tr></body></html>");
		    	
		    	
		    } else {
		    	i=1;
		    	resp.getWriter().println("<html><body><table border=1 align=center><tr><td>"+firstline[0]+"</td><td>"+firstline[1]+"</td><td>"+firstline[2]+"</td><td>"
		    			+firstline[3]+"</td><td>"+firstline[4]+"</td></tr></body></html>");	
		    }
		    }}
		    if(operation.equals("1")){
		        for(int q = -1 ; q < lines.length ; q++){
		        if (i>0){
		        	
		        	resp.getWriter().println("<html><body><tr><td>"+lines[q][0]+"</td><td>"+lines[q][1]+"</td><td>"+lines[q][2]+"</td><td>"
		              		+lines[q][3]+"</td><td>"+lines[q][4]+"</td></tr></body></html>");
		        	
		        	
		        } else {
		        	i=1;
		        	resp.getWriter().println("<html><body><table border=1 align=center><tr><td>"+firstline[0]+"</td><td>"+firstline[1]+"</td><td>"+firstline[2]+"</td><td>"
		        			+firstline[3]+"</td><td>"+firstline[4]+"</td></tr></body></html>");	
		        }
		        }}
		
		
		
	}
	if((operation.equals("3")) || (operation.equals("4"))){
    for(int j = (lines.length-1) ; j > 0 ; j--){
        for(int k = 0 ; k < j ; k++){
           float f,g=0;
           f=new Float(lines[k][4]);
           g=new Float(lines[k+1][4]);
           
            if( f > g ){
                float tmp = new Float(lines[k][3]);
                lines[k][3] = lines[k+1][3];
                lines[k+1][3] = String.valueOf(tmp);
                tmp = new Float(lines[k][4]);
                lines[k][4] = lines[k+1][4];
                lines[k+1][4] = String.valueOf(tmp);
                Integer tmp1 = new Integer(lines[k][0]);
                lines[k][0] = lines[k+1][0];
                lines[k+1][0] = Integer.toString(tmp1);
                String tmp2 = lines[k][1];
                lines[k][1] = lines[k+1][1];
                lines[k+1][1] = tmp2;
                tmp2 = lines[k][2];
                lines[k][2] = lines[k+1][2];
                lines[k+1][2] = tmp2;
            }
        }
    }
    i=0;
    if(operation.equals("4")){
    for(int q = lines.length ; q > -1 ; q--){
    if (i>0){
    	
    	resp.getWriter().println("<html><body><tr><td>"+lines[q][0]+"</td><td>"+lines[q][1]+"</td><td>"+lines[q][2]+"</td><td>"
          		+lines[q][3]+"</td><td>"+lines[q][4]+"</td></tr></body></html>");
    	
    	
    } else {
    	i=1;
    	resp.getWriter().println("<html><body><table border=1 align=center><tr><td>"+firstline[0]+"</td><td>"+firstline[1]+"</td><td>"+firstline[2]+"</td><td>"
    			+firstline[3]+"</td><td>"+firstline[4]+"</td></tr></body></html>");	
    }
    }}
    if(operation.equals("3")){
        for(int q = -1 ; q < lines.length ; q++){
        if (i>0){
        	
        	resp.getWriter().println("<html><body><tr><td>"+lines[q][0]+"</td><td>"+lines[q][1]+"</td><td>"+lines[q][2]+"</td><td>"
              		+lines[q][3]+"</td><td>"+lines[q][4]+"</td></tr></body></html>");
        	
        	
        } else {
        	i=1;
        	resp.getWriter().println("<html><body><table border=1 align=center><tr><td>"+firstline[0]+"</td><td>"+firstline[1]+"</td><td>"+firstline[2]+"</td><td>"
        			+firstline[3]+"</td><td>"+firstline[4]+"</td></tr></body></html>");	
        }
        }}
    }
	
  
}
	
	
}
