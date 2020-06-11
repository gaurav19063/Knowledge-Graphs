import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.apache.jena.atlas.json.JSON;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Q3_sweb {
	static JSONObject ReadObj(InputStream LINK) throws IOException, JSONException {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(LINK, Charset.forName("UTF-8")));
			String message = org.apache.commons.io.IOUtils.toString(br);
//	      String cont = READCONT(br);
			int index = message.indexOf(
					"{\"@context\": [\"http://api.conceptnet.io/ld/conceptnet5.7/context.ld.json\"], \"@id\": \"/c/en/example\", \"edges\": ");

			message = message.substring(index);
			int index1 = message.indexOf("</script>");
			System.out.println(message.substring(0, index1).length());
			JSONObject json = new JSONObject(message.substring(0, index1).trim());
			System.out.println(json.toString());
			return json;
//	      
		} finally {
			LINK.close();

		}

	}

	public static void main(String[] args) throws IOException, JSONException, Exception {
		// TODO Auto-generated method stub
		String URL = "http://api.conceptnet.io/c/en/example";
		InputStream LINK = new URL(URL).openStream();
		JSONObject json = ReadObj(LINK);
		Model model = ModelFactory.createDefaultModel();
		model.write(System.out, "TURTLE");
		model.write(new FileOutputStream("A1_MT19063_Q3.ttl"), "TURTLE");

		String Base = "http://example.org/";
		model.setNsPrefix("ex", Base);

		model.write(System.out, "TURTLE");
		model.write(new FileOutputStream("A1_MT19063_Q3.ttl", true), "TURTLE");
		model = ModelFactory.createDefaultModel();

//	    Resource mainnode=ResourceFactory.createResource((String)json.get("@id"));
		Resource r = ResourceFactory.createResource(Base);

		Resource b = model.createResource((String) json.get("@id"));
//	    String Base="ex:";

//        String Base="http://example.org/";
//        model.setNsPrefix("ex", Base);
//        
//	    
//        model.write(System.out, "TURTLE");
//        model.write(new FileOutputStream("A1_MT19063_Q3.ttl",true),"TURTLE");

		Iterator iter = json.keys();
		int count = 0;
//		if(!iter.hasNext())
//			
//		{
//			System.out.println("kgdfsljgs5555555555555555555555555555555555555555555555555558888888888888888888"+(String) json.get("@id"));
//			
//			
//		}
		
		
		while (iter.hasNext()) {
			Object a = iter.next();
			Property p1;
			RDFNode v1;
//            System.out.println(((String)a));
//            System.out.println(((String)iter.next()));
//            System.out.println(((String)iter.next()));
//            System.out.println(((String)iter.next()));
//        	System.out.println(json.get((String)a));
			count += 1;
			
			String s = "_B" + Integer.toString(count);
//	        System.out.println(a.toString()+"dsfadfsdfdsfsdfdasfdsfsd");
			p1 = model.createProperty("ex: " + (String) a);
//        	System.out.println(json.get((String)a));
			String stc=json.get((String)a).toString();
			
			try { JSON.parse(stc);}
			catch(Exception e) 
			{Property p5;
			RDFNode v5;
//				System.out.println("7777777777777777777777777777777777777"+stc);
				
				Resource r8 = model.createResource(s);
				
				
//		        System.out.println(a.toString());
				p5 = model.createProperty("ex: " + (String) a);
//	        	System.out.println(json.get((String)a)+"25555555555555555");
				v5 = model.createTypedLiteral("ex: "+stc, XSDDatatype.XSDstring);
//	        	Object a = iter.next();
//				if(count2==1) {
				b.addProperty(p5, v5);
				model.write(System.out, "TURTLE");
				model.write(new FileOutputStream("A1_MT19063_Q3.ttl", true), "TURTLE");
				
				
				
			}

//        	Object a = iter.next();
//        System.out.println(((String)a));
			if(a.toString().equals("@context"))
					continue;
			Object cont = json.get((a.toString()));
//        	System.out.println(cont+"sdgfgsdfgd");
//        	 JSONObject json1 = new JSONObject(json.get(cont));
//        	 Object json2 = new JSONTokener(cont).nextValue();
			String ct = cont.getClass().getSimpleName();
			System.out.println(ct);
			if (ct.equals("JSONArray")) {
				v1 = model.createTypedLiteral(s, XSDDatatype.XSDstring);
				b.addProperty(p1, v1);
				JSONArray docs = json.getJSONArray(a.toString());
				System.out.println(docs.length() + "yessss");
//-----------------------------------------------------------------------------------------------------------------------------------------
				for (int t = 0; t < docs.length(); t++) 
				{ 
					System.out.println(t+"sdjfa;sldkjf;ksldjl");
					
				
					JSONObject jsonobject1 = docs.getJSONObject(t);
//					System.out.println(jsonobject1.toString()+"mcvmxcmmxmmmmmc");
					
//					Resource r1 = ResourceFactory.createResource("fasdfs");

//					Resource b1 = model.createResource((String) jsonobject1.get("@id"));

					Iterator iter1 = jsonobject1.keys();
					int count2 = 0;
					while (iter1.hasNext()) {
						Object a1 = iter1.next();
						Property p2;
						RDFNode v2;
//						System.out.println("innnnnnnnnnnnnnnnnnnnnnnnnnn");
						count2 += 1;
//			            System.out.println(((String)a1));
						
						
						
						
						
						
						
						
						
//			        System.out.println(a1.toString()+"sdfasdvxcvxzcvxcv");
						if(a1.toString().equals("@id")||a1.toString().equals("license")||a1.toString().equals("@type")||a1.toString().equals("dataset")||a1.toString().equals("rel")||a1.toString().equals("surfaceText")||a1.toString().equals("start")||a1.toString().equals("end")||a1.toString().equals("weight"))
							{
							Model model1 = ModelFactory.createDefaultModel();
						String s1 = "_B1" + Integer.toString(count);
						
						Resource r2 = model1.createResource(s);
						
						
//				        System.out.println(a.toString());
						p2 = model1.createProperty("ex: " + (String) a1);
//			        	System.out.println(jsonobject1.get((String)a1)+"25555555555555555");
						v2 = model1.createTypedLiteral("ex: "+jsonobject1.get((String)a1), XSDDatatype.XSDstring);
//			        	Object a = iter.next();
//						if(count2==1) {
//						r2.addProperty(p2, v2);
//						model1.write(System.out, "TURTLE");
//						model1.write(new FileOutputStream("A1_MT19063_Q3.ttl", true), "TURTLE");
						
//						}
//						else {
//							b.addProperty(p2, v2);
//							model.write(System.out, "TURTLE");
//							model.write(new FileOutputStream("A1_MT19063_Q3.ttl", true), "TURTLE");
//							
//						}
						
						
						
						count++;
							continue;
							}
						Model model1 = ModelFactory.createDefaultModel();
						String s1 = "_B1" + Integer.toString(count);
						
						Resource r2 = model1.createResource(s1);
						
						
//				        System.out.println(a.toString());
						p2 = model1.createProperty("ex: " + (String) a1);
//			        	System.out.println(json.get((String)a));
						v2 = model1.createTypedLiteral(s1, XSDDatatype.XSDstring);
//			        	Object a = iter.next();
//						r2.addProperty(p2, v2);
//						model1.write(System.out, "TURTLE");
//						model1.write(new FileOutputStream("A1_MT19063_Q3.ttl", true), "TURTLE");
			        
						Object cont1 = jsonobject1.get((a1.toString()));
						
						String ct1 = cont.getClass().getSimpleName();
						System.out.println(ct1);
						
						if (ct1.equals("JSONArray")) {
							JSONArray docs2 = jsonobject1.getJSONArray(a1.toString());
//							System.out.println(docs2.length() + "yessss2222222222");
							for (int q = 0; q < docs2.length(); q++) 
							{
								JSONObject jsonobject4 = docs2.getJSONObject(q);
								System.out.println(jsonobject4.toString());
//################################################################################################
								Iterator iter4 = jsonobject4.keys();
								int count4 = 0;
//								
//								if(!iter4.hasNext())
//								{
//									System.out.println("77777777777777777777777777777777777777777777");
//									
//								}
								
								
								
							
								
								
								while (iter4.hasNext()) {
									Object a4 = iter4.next();
									Property p4;
									RDFNode v4;
//									System.out.println("innnnnnnnnnnnnnnnnnnnnnnnnnn");
									count4 += 1;
//						            System.out.println(((String)a1));
									
									
									
//						        System.out.println(a4.toString()+"sdfasdvxcvxzcvxcv");
//									if(a1.toString().equals("@id")||a1.toString().equals("license")||a1.toString().equals("@type")||a1.toString().equals("dataset")||a1.toString().equals("rel")||a1.toString().equals("surfaceText")||a1.toString().equals("start")||a1.toString().equals("end")||a1.toString().equals("weight"))
//										{
										
										Model model4 = ModelFactory.createDefaultModel();
									String s2 = "_B11" + Integer.toString(count);
									
									Resource r4 = model4.createResource(s1);
									
									
//							        System.out.println(a.toString());
									p4 = model4.createProperty("ex: " + (String) a4);
									System.out.println(a4.toString());
//						        	System.out.println( jsonobject4.get((String)a4)+"25555555555555555");
									v4 = model4.createTypedLiteral("ex: "+jsonobject4.get((String)a4), XSDDatatype.XSDstring);
//						        	Object a = iter.next();
								if(count4==0)
									{r4.addProperty(p4, v4);
									model4.write(System.out, "TURTLE");
									model4.write(new FileOutputStream("A1_MT19063_Q3.ttl", true), "TURTLE");
									continue;
									}
										count++;
										r2.addProperty(p4, v4);
								}
								
								model1.write(System.out, "TURTLE");
								model1.write(new FileOutputStream("A1_MT19063_Q3.ttl", true), "TURTLE");
								
								
								
								
//#####################################################3333
								
								
								
								
								
								
								
								
								
								
								

							}
//							System.out.println("jsdlkfjas;ljfslad;kjfl;sdjf");

						}

						

					}
					
//-------------------------------------------------------------------------------------------------------					
					
					

				}

			}
			else {
				v1 = model.createTypedLiteral(json.get((String)a), XSDDatatype.XSDstring);
				b.addProperty(p1, v1);
				
			
			
			}
//			model.write(System.out, "TURTLE");
//			model.write(new FileOutputStream("A1_MT19063_Q3.ttl", true), "TURTLE");

		}

//        model = ModelFactory.createDefaultModel();
//       
//	    JSONArray docs = json.getJSONArray("edges");

//	    String csv = CDL.toString(docs);
//	    System.out.println(docs.length());
//	    
//	    for (int i = 0; i < docs.length(); i++) {
//	        JSONObject jsonobject = docs.getJSONObject(i);
//	        
//	        b =model.createResource((String)json.get("@id"));

//	        System.out.println(jsonobject.toString());
//	        String name = jsonobject.getString("name");
//	        String url = jsonobject.getString("url");
//	        iter = jsonobject.keys();
//	         count=0;

//	        while(iter.hasNext()){
////	            listArray.add((String)iter.next());
////	        	System.out.println((String)iter.next());
//	            count +=1;
//	            System.out.print(count);
//	        	Object a = iter.next();
////	            System.out.println(((String)a));
////	            System.out.println(((String)iter.next()));
////	            System.out.println(((String)iter.next()));
////	            System.out.println(((String)iter.next()));
////	        	System.out.println(jsonobject.get((String)a));
//	        	p1 = model.createProperty("ex:"+"" + (String )a );
//	        	v1 = model.createTypedLiteral(jsonobject.get((String)a), XSDDatatype.XSDstring);
//	        	b.addProperty(p1, v1);

//
//	        }

	}

}
