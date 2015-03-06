/**
 * Copyright (C) 2015
 * by 52 North Initiative for Geospatial Open Source Software GmbH
 *
 * Contact: Andreas Wytzisk
 * 52 North Initiative for Geospatial Open Source Software GmbH
 * Martin-Luther-King-Weg 24
 * 48155 Muenster, Germany
 * info@52north.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.n52.wps.csv2wiki;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * CSV to twiki table markup. Uses http://commons.apache.org/proper/commons-csv/
 * 
 * @author matthes rieke
 *
 */
public class CSV2TWikiProcess {
	
    public static void main( String[] args ) throws IOException {
    	InputStream res = CSV2TWikiProcess.class.getResourceAsStream("/test.csv");
    	String s = new CSV2TWikiProcess().transform(res);
    	System.out.println(s);
    }
    
    public String transform(InputStream csv) throws IOException {
    	CSVParser parser = CSVFormat.DEFAULT.parse(new InputStreamReader(csv));
    	return transformWithParser(parser);
    }

	private String transformWithParser(CSVParser parser) throws IOException {
    	StringBuilder sb = new StringBuilder();

    	boolean header = true;
    	for (CSVRecord h : parser.getRecords()) {
    		for (int i = 0; i < h.size(); i++) {
    			if (header) {
    				sb.append("| *");
            		sb.append(h.get(i));
            		sb.append("* ");	
    			}
    			else {
    				sb.append("| ");
            		sb.append(h.get(i));
            		sb.append(" ");
    			}
        		
			}
    		sb.append("|");
    		sb.append(System.getProperty("line.separator"));
    		header = false;
    	}
    	
    	return sb.toString();
	}

	public String transform(String input) throws IOException {
		CSVParser parser = CSVFormat.DEFAULT.parse(new StringReader(input));
    	return transformWithParser(parser);
	}
}
