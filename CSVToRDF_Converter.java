import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Arrays;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import au.com.bytecode.opencsv.CSVReader;
public class Q4
{
   @SuppressWarnings("resource")
   public static void main(String[] args) throws Exception
   {

      CSVReader read_csv = new CSVReader(new FileReader("/home/gaurav/Desktop/IIITD/Sweb/assignment/NetflixList.csv"), ',' , '"' , 0);
       String[] st=read_csv.readNext();
      for(int j=0;j<st.length;j++)
	  {
		  System.out.println(st[j]);
	  }
      
      Model model = ModelFactory.createDefaultModel();
      
      model.write(System.out, "TURTLE");
      model.write(new FileOutputStream("A1_MT19063_Q4.ttl"),"TURTLE");
      Resource typeClass = ResourceFactory.createResource("rdfs: type");
      Resource movie = ResourceFactory.createResource("rdfs: movie");
      Resource tv_show = ResourceFactory.createResource("rdfs:tvshow");
      
      
      
      model.add (typeClass, RDF.type, RDFS.Class);
      model.add (movie, RDFS.subClassOf, typeClass);
      model.add (tv_show, RDFS.subClassOf, typeClass);
      String Base="http://example.org/";
      model.setNsPrefix("ex", Base);
      model.write(System.out, "TURTLE");
      model.write(new FileOutputStream("A1_MT19063_Q4.ttl",true),"TURTLE");
          
      int l=st.length;
   
     int n=0;
      String[] next_Line;
      
     
      while ((next_Line = read_csv.readNext()) != null) {
    	  n++;
    	  model=  ModelFactory.createDefaultModel();
    	  Property p1;
    	  Resource a;
          Resource b;
          RDFNode v1;
          a= model.createResource("ex:" + next_Line[2]);
          for (int i=1;i<l;i++)
          { if(i==2)
        	  continue;
          else if(i==1)
          {
        	  if(next_Line[i]=="Movie")
        		  model.add(a,RDF.type,movie);
        	  else
        		  model.add(a,RDF.type,tv_show);
          }
//        	  
          p1 = model.createProperty("ex:" + st[i] );
//     	   v1 = model.createTypedLiteral(next_Line[i], XSDDatatype.XSDstring);
//     	  a.addProperty(p1, v1);
     	 
       	  
       	String[] s=next_Line[i].split(",");
       	
      if(s.length!=0)
      { for(int k=0;k<s.length;k++)
      {   
    	  v1 = model.createTypedLiteral(s[k], XSDDatatype.XSDstring);
    	  a.addProperty(p1, v1);
    	  
      }
      
      System.out.print(n);
      
      }

	  
       	   
       	   
          }
          model.write(System.out, "TURTLE");
          model.write(new FileOutputStream("A1_MT19063_Q4.ttl",true),"TURTLE");
    	            
          
            
         }
      
      
       }
      
   
}